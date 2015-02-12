package dhbw.karlsruhe.it.solar.junit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
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
    	return new Spaceship("Testschiff", new Length(1, Length.Unit.kilometres), new Length(2, Length.Unit.kilometres), null);
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
     * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedrückt wird, wird das Raumschiff angewählt.
     * Beim wiederholten drücken auf das Raumschiff oder wenn nicht auf das Objekt gedrückt wird, wird das Raumschiff wieder abgewählt.
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
     * Wenn das Raumschiff ausgewählt ist und die rechte Maustaste im GameScreen gedrückt wird, bewegt sich das Raumschiff an die Stelle, an die gedrückt worden ist.
     */
    @Test
    public void testSpaceshipDestination()
    {
    	testShip.setDestination(new Vector2(12345, 67890));

        assertEquals(12345, testShip.getDestination().x, 1);
        assertEquals(67890, testShip.getDestination().y, 1);
    }
}
