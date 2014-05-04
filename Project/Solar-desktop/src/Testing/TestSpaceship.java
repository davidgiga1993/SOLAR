package Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import com.me.UserControls.Spaceship;
import com.me.solar.Main;


public class TestSpaceship {
	
	private Spaceship ship;
	
	public TestSpaceship() {
		
	}

    @BeforeClass
    public static void setUpOnce()
    {
        Main.main(null);
    }
    
    @AfterClass
    public static void tearDownOnce()
    {
        Gdx.app.exit();
    }

    @Before
    public void setUp() throws Exception
    {
        ship = new Spaceship("Test");
        ship.setPosition(22, 33);
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
	/**
	 * Klasse "Raumschiff" erstellen und in irgendeiner Form im GameScreen anzeigen
	 */
	@Test
	public void testShipCreation() {
		assertEquals(33f, ship.getWidth(), 0);
		assertEquals(33f, ship.getHeight(), 0);
		assertEquals("Test", ship.getName());
	}
	
	/**
	 * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedr�ckt wird, wird das Raumschiff angew�hlt.
	 * Beim wiederholten dr�cken auf das Raumschiff oder wenn nicht auf das Objekt gedr�ckt wird, wird das Raumschiff wieder abgew�hlt.
	 */
	@Test
	public void testSelectionFunctionality() {
		ship.select();
		assertTrue(ship.getSelected());
		ship.deselect();
		assertFalse(ship.getSelected());	
	}
	
	/**
	 * Das Raumschiff soll sich in irgendeiner Weise auf dem Bildschirm bewegen.
	 */
	@Test
	public void testMovement() {
		ship.setDestination(new GridPoint2(12345,67890));
		ship.moveSpaceship();
	}
	
	/**
	 * Wenn das Raumschiff ausgew�hlt ist und die rechte Maustaste im GameScreen gedr�ckt wird, bewegt sich das Raumschiff an die Stelle, an die gedr�ckt worden ist.
	 */
	@Test
	public void testSpaceshipDestination() {
		ship.setDestination(new GridPoint2(12345,67890));
		assertEquals(12345, ship.getDestination().x);
		assertEquals(67890, ship.getDestination().y);
	}

}
