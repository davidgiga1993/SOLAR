package dhbw.karlsruhe.it.solar.core.space_units;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.player.Player;

/**
 * Code for the space station unit object
 *
 * @author Andi
 */
public class SpaceStation extends SpaceUnit {
    private static float stationSpeed = 500f;

    private SpaceStation(String name, Length width, Length length, Player owner) {
        super(name, owner, stationSpeed);
        setupSolarActorSprite("space_station");
        initSpaceUnit(width, length);
    }

    /**
     * Adds a new space station object to the game.
     *
     * @param name          Desired name of the station.
     * @param startLocation Desired location at which the station is to appear.
     */
    public static SpaceStation placeNewStation(String name, Vector2 startLocation, Player owner) {
        SpaceStation newStation = new SpaceStation(name, new Length(1, Length.DistanceUnit.KILOMETERS), new Length(1, Length.DistanceUnit.KILOMETERS), owner);
        newStation.setPosition(startLocation.x, startLocation.y);
        newStation.setKinematicPosition(startLocation);
        newStation.setDestination(startLocation);
        owner.assignNewUnit(newStation);
        return newStation;
    }

    @Override
    public String getTypeName() {
        return "Space Station";
    }

    @Override
    public Credits getRunningCosts() {
        return SPACE_STATION_YEARLY_RUNNING_COST;
    }
}
