package dhbw.karlsruhe.it.solar.core.junit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.player.PlayerManager;
import dhbw.karlsruhe.it.solar.core.space_units.Spaceship;
import mikera.vectorz.Vector2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class SpaceshipJUnit {

    private Spaceship testShip;

    @Before
    public void setUp() {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable runner = () -> {
            try {
                testShip = placeTestShip();
            } catch (Exception e) {
                e.printStackTrace();
            }
            latch.countDown();
        };
        Gdx.app.postRunnable(runner);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Spaceship placeTestShip() {
        final PlayerManager playerManager = new PlayerManager();
        return new Spaceship("Testschiff", new Length(1, Length.DistanceUnit.KILOMETERS), new Length(2, Length.DistanceUnit.KILOMETERS), playerManager.createPlayer("human", Color.BLUE));
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests whether the ship has been created at all.
     */
    @Test
    public void testShipCreation() {
        Assert.assertEquals("Testschiff", testShip.getName());
    }

    /**
     * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedr�ckt wird, wird das Raumschiff angew�hlt.
     * Beim wiederholten dr�cken auf das Raumschiff oder wenn nicht auf das Objekt gedr�ckt wird, wird das Raumschiff wieder abgew�hlt.
     */
    @Test
    public void testSelectionFunctionality() {
        testShip.select();
        assertTrue(testShip.isSelected());
        testShip.deselect();
        assertFalse(testShip.isSelected());
    }

    /**
     * Wenn das Raumschiff ausgew�hlt ist und die rechte Maustaste im GameScreen gedr�ckt wird, bewegt sich das Raumschiff an die Stelle, an die gedr�ckt worden ist.
     */
    @Test
    public void testSpaceshipDestination() {
        testShip.setDestination(new Vector2(12345, 67890));

        assertEquals(12345, testShip.getDestinationVector().x, 1);
        assertEquals(67890, testShip.getDestinationVector().y, 1);
    }
}
