package dhbw.karlsruhe.it.solar.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
public class SpaceshipJUnit
{

    private Spaceship testShip;

    @Before
    public void setUp() throws Exception
    { 
    	final CountDownLatch latch = new CountDownLatch(1);
    	Runnable runner = new Runnable() {

    	@Override
    	public void run() {
    		try {
				testShip = placeTestShip();
			} catch (Exception e) {
				e.printStackTrace();
			}
    		latch.countDown();
    	}
    	};
    	Gdx.app.postRunnable(runner);
    	try {
    		latch.await();
    	}
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    private Spaceship placeTestShip() throws Exception
    {
    	return new Spaceship("Testschiff");
    }

    @After
    public void tearDown() throws Exception
    {
    }

    /**
     * Tests whether the ship has been created at all.
     */
    @Test
    public void testShipCreation()
    {
        assertEquals("Testschiff", testShip.getName());
    }

    /**
     * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedr�ckt wird, wird das Raumschiff angew�hlt.
     * Beim wiederholten dr�cken auf das Raumschiff oder wenn nicht auf das Objekt gedr�ckt wird, wird das Raumschiff wieder abgew�hlt.
     */
    @Test
    public void testSelectionFunctionality()
    {
        testShip.select();
        assertTrue(testShip.isSelected());
        testShip.deselect();
        assertFalse(testShip.isSelected());
    }

    /**
     * Wenn das Raumschiff ausgew�hlt ist und die rechte Maustaste im GameScreen gedr�ckt wird, bewegt sich das Raumschiff an die Stelle, an die gedr�ckt worden ist.
     */
    @Test
    public void testSpaceshipDestination()
    {
    	testShip.setDestination(new Vector2(12345, 67890));

        assertEquals(12345, testShip.getDestination().x, 1);
        assertEquals(67890, testShip.getDestination().y, 1);
    }
}