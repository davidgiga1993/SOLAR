package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.EventQueue;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.me.UserControls.Spaceship;
import com.me.solar.Main;
import com.me.solar.SolarEngine;
import com.me.stages.BaseStage;
import com.me.stages.GameStartStage;
import com.me.stages.StageManager;
import com.me.stages.StartStage;
import com.me.stages.TestStage;
public class TestSpaceship
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
    	testShip.setDestination(new GridPoint2(12345, 67890));
        assertEquals(12345, testShip.getDestination().x);
        assertEquals(67890, testShip.getDestination().y);
    }
}
