package dhbw.karlsruhe.it.solar.core.savegames;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Tries to save current game state to a XML file.
 * @author Andi
 * created: 2015-04-05
 */
@XmlRootElement( name = "SaveGame")
@XmlSeeAlso({ AstroBodyInfo.class, SpaceUnitInfo.class, PlayerInfo.class})
public class SaveGame {
   
    private Array<Actor> saveGameActors;
    private List<SpaceUnit> spaceUnits;
    private List<AstronomicalBody> spaceObjects;
    private List<Player> ingamePlayers;
    @XmlElement(name ="Player")
    private List<PlayerInfo> players;
    @XmlElement(name ="SpaceUnit")
    private List<SpaceUnitInfo> units;
    @XmlElement(name ="AstronomicalBody")
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
    }
    
    private List<PlayerInfo> createPlayerInfo() {
        List<PlayerInfo> newList = new ArrayList<PlayerInfo>();
        for(Player player : ingamePlayers) {
            PlayerInfo pi = new PlayerInfo();
            pi.fillInInfos(player);
            newList.add(pi);
        }
        return newList;
    }

    private List<AstronomicalBody> filterOutAstroBodies() {
        List<AstronomicalBody> newList = new ArrayList<AstronomicalBody>();
        for (Actor actor : saveGameActors) {
            if(actor instanceof AstronomicalBody) {
                newList.add((AstronomicalBody)actor);
            }
        }
        return newList;
    }
    
    private List<AstroBodyInfo> createAstroBodyInfo() {
        List<AstroBodyInfo> newList = new ArrayList<AstroBodyInfo>();
        for (AstronomicalBody body : spaceObjects) {
            AstroBodyInfo ai = new AstroBodyInfo();
            ai.fillAstroBodyInfo(body);
            newList.add(ai);
        }
        return newList;
    }
    
    private List<SpaceUnit> filterOutUnits() {
        List<SpaceUnit> newList = new ArrayList<SpaceUnit>();
        for (Actor actor : saveGameActors) {
            if(actor instanceof SpaceUnit) {
                newList.add((SpaceUnit)actor);
            }
        }
        return newList;
    }
    
    private List<SpaceUnitInfo> createSpaceUnitInfo() {
        List<SpaceUnitInfo> newList = new ArrayList<SpaceUnitInfo>();
        for (SpaceUnit unit : spaceUnits) {
            SpaceUnitInfo si = new SpaceUnitInfo();
            si.fillSpaceUnitInfo(unit);
            newList.add(si);
        }
        return newList;
    }
}
