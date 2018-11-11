package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBarManagerSettings;
import dhbw.karlsruhe.it.solar.player.Player;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the necessary information for converting a game state from the stage into a XML save file and back.
 *
 * @author Andi
 * created: 2015-04-05
 */
@XmlRootElement(name = "SaveGame")
@XmlSeeAlso({AstroBodyInfo.class, SpaceUnitInfo.class, PlayerInfo.class})
class SaveGame {

    private Array<Actor> saveGameActors;
    private List<SpaceUnit> spaceUnits;
    private List<AstronomicalBody> spaceObjects;
    private List<Player> ingamePlayers;
    @XmlElement(name = "Settings")
    private InfoBarManagerSettings settings;
    @XmlElement(name = "Game_Time_Elapsed")
    private Time gameTime;
    @XmlElement(name = "Player")
    private List<PlayerInfo> players;
    @XmlElement(name = "SpaceUnit")
    private List<SpaceUnitInfo> units;
    @XmlElement(name = "AstronomicalBody")
    private List<AstroBodyInfo> bodies;

    public SaveGame() {
    }

    public void assignInformation(GameStartStage stage) {
        this.saveGameActors = stage.getActors();

        spaceObjects = filterOutAstroBodies();
        bodies = createAstroBodyInfo();
        spaceUnits = filterOutUnits();
        units = createSpaceUnitInfo();
        ingamePlayers = stage.getPlayers();
        players = createPlayerInfo();
        settings = stage.getSettings();
        gameTime = GameStartStage.GAMETIME.getGameTimeElapsed();
    }

    private List<PlayerInfo> createPlayerInfo() {
        List<PlayerInfo> newList = new ArrayList<>();
        for (Player player : ingamePlayers) {
            PlayerInfo pi = new PlayerInfo();
            pi.fillInInfos(player);
            newList.add(pi);
        }
        return newList;
    }

    private List<AstronomicalBody> filterOutAstroBodies() {
        List<AstronomicalBody> newList = new ArrayList<>();
        for (Actor actor : saveGameActors) {
            if (actor instanceof AstronomicalBody && !(actor instanceof PlanetaryRing)) {
                newList.add((AstronomicalBody) actor);
            }
        }
        return newList;
    }

    private List<AstroBodyInfo> createAstroBodyInfo() {
        List<AstroBodyInfo> newList = new ArrayList<>();
        for (AstronomicalBody body : spaceObjects) {
            AstroBodyInfo ai = new AstroBodyInfo();
            ai.fillAstroBodyInfo(body);
            newList.add(ai);
        }
        return newList;
    }

    private List<SpaceUnit> filterOutUnits() {
        List<SpaceUnit> newList = new ArrayList<>();
        for (Actor actor : saveGameActors) {
            if (actor instanceof SpaceUnit) {
                newList.add((SpaceUnit) actor);
            }
        }
        return newList;
    }

    private List<SpaceUnitInfo> createSpaceUnitInfo() {
        List<SpaceUnitInfo> newList = new ArrayList<>();
        for (SpaceUnit unit : spaceUnits) {
            SpaceUnitInfo si = new SpaceUnitInfo();
            si.fillSpaceUnitInfo(unit);
            newList.add(si);
        }
        return newList;
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public List<AstroBodyInfo> getAstroBodies() {
        return bodies;
    }

    public List<SpaceUnitInfo> getSpaceUnits() {
        return units;
    }

    public List<MissionInfoExtended> getMissions() {
        List<MissionInfoExtended> missionList = new ArrayList<>();
        for (SpaceUnitInfo unit : units) {
            MissionInfo mission = unit.getMissionInfo();
            if (null != mission) {
                missionList.add(new MissionInfoExtended(unit.getName(), mission));
            }
        }
        return missionList;
    }

    public InfoBarManagerSettings getSettings() {
        return settings;
    }

    public Time getGameTimeElapsed() {
        return gameTime;
    }
}
