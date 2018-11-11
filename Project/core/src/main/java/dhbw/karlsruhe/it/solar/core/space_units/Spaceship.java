package dhbw.karlsruhe.it.solar.core.space_units;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.player.Player;

/**
 * @author Andi
 */
public class Spaceship extends SpaceUnit {
    private static float shipSpeed = 1000f;

    public Spaceship(String name, Length width, Length length, Player owner) {
        super(name, owner, shipSpeed);
        setupSolarActorSprite("Cruiser");
        initSpaceUnit(width, length);
    }

    /**
     * Adds a new spaceship object to the game.
     *
     * @param name          Desired name of the spaceship.
     * @param startLocation Desired location at which the ship is to appear.
     */
    public static Spaceship placeNewShip(String name, Vector2 startLocation, Player owner) {
        Spaceship newShip = new Spaceship(name, new Length(1, Length.DistanceUnit.KILOMETERS), new Length(1, Length.DistanceUnit.KILOMETERS), owner);
        newShip.setPosition(startLocation.x, startLocation.y);
        newShip.setKinematicPosition(startLocation);
        newShip.setDestination(startLocation);
        owner.assignNewUnit(newShip);
        return newShip;
    }

    @Override
    public String getTypeName() {
        return "Space Ship";
    }

    @Override
    public Credits getRunningCosts() {
        return SPACESHIP_YEARLY_RUNNING_COST;
    }
}
