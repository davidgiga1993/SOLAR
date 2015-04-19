package dhbw.karlsruhe.it.solar.core.space_units;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Code for the space station unit object
 * @author Andi
 */
public class Spacestation extends SpaceUnit  {
    private static float stationSpeed = 500f;
    
    public Spacestation(String name, Length width, Length length, Player owner)    {
        super(name, owner,stationSpeed);
        setupSolarActorSprite("space_station");  
        initSpaceUnit(width, length);
    }
    
    /**
     * Adds a new space station object to the game.
     * @param name Desired name of the station.
     * @param startlocation Desired location at which the station is to appear.
     */
    public static Spacestation placeNewStation(String name, Vector2 startlocation, Player owner)    {
        Spacestation newStation = new Spacestation(name, new Length(1, Length.DistanceUnit.KILOMETERS), new Length(1, Length.DistanceUnit.KILOMETERS), owner);
        newStation.setPosition(startlocation.x, startlocation.y);
        newStation.setKinematicPosition(startlocation);
        newStation.setDestination(startlocation);
        return newStation;
    }

    @Override
    public String getTypeName() {
        return "Space Station";
    }
}
