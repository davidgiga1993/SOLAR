package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.me.UserControls.Spaceship;
import com.me.solar.SolarEngine;
import com.me.stages.GameStartStage;
import com.me.stages.StageManager;
import com.me.stages.StartStage;

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
        assertEquals(33f, ship.getWidth(), 0);
        assertEquals(33f, ship.getHeight(), 0);
        assertEquals("Test", ship.getName());
    }

    /**
     * Sobald mit der linken Maustaste auf der vorhandene Objekt "Raumschiff" gedrückt wird, wird das Raumschiff angewählt.
     * Beim wiederholten drücken auf das Raumschiff oder wenn nicht auf das Objekt gedrückt wird, wird das Raumschiff wieder abgewählt.
     */
    @Test
    public void testSelectionFunctionality()
    {
        ship.select();
        assertTrue(ship.getSelected());
        ship.deselect();
        assertFalse(ship.getSelected());
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
     * Wenn das Raumschiff ausgewählt ist und die rechte Maustaste im GameScreen gedrückt wird, bewegt sich das Raumschiff an die Stelle, an die gedrückt worden ist.
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
