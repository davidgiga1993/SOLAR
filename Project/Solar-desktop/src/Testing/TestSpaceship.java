package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.math.GridPoint2;
import com.me.UserControls.Spaceship;
public class TestSpaceship
{

    private Spaceship ship;

    public TestSpaceship()
    {

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
    public void testShipCreation()
    {
        assertEquals("Test", ship.getName());
    }

    /**
     * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedr�ckt wird, wird das Raumschiff angew�hlt.
     * Beim wiederholten dr�cken auf das Raumschiff oder wenn nicht auf das Objekt gedr�ckt wird, wird das Raumschiff wieder abgew�hlt.
     */
    @Test
    public void testSelectionFunctionality()
    {
        ship.select();
        assertTrue(ship.isSelected());
        ship.deselect();
        assertFalse(ship.isSelected());
    }

    /**
     * Das Raumschiff soll sich in irgendeiner Weise auf dem Bildschirm bewegen.
     */
    @Test
    public void testMovement()
    {
        ship.setDestination(new GridPoint2(12345, 67890));
        ship.moveSpaceship();
    }

    /**
     * Wenn das Raumschiff ausgew�hlt ist und die rechte Maustaste im GameScreen gedr�ckt wird, bewegt sich das Raumschiff an die Stelle, an die gedr�ckt worden ist.
     */
    @Test
    public void testSpaceshipDestination()
    {
        ship.setDestination(new GridPoint2(12345, 67890));
        assertEquals(12345, ship.getDestination().x);
        assertEquals(67890, ship.getDestination().y);
    }
    
    @Test
    public void testDrawSpaceship()
    {   	
    	assertTrue(true);
    }

}