package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.UserControls.Asteroid;
import com.me.UserControls.Moon;
import com.me.UserControls.SolarSystem;
import com.me.UserControls.Star;
import com.me.UserControls.Planet;

public class TestAstronomicalObjects
{

	SolarSystem solarSystem;
	Star star;
	Planet planet;
	
    @Before
    public void setUp() throws Exception
    {
        solarSystem = new SolarSystem("Testsystem");
        star = solarSystem.placeNewStar("Testsonne", 1, 0, 0);
	    planet = star.placeNewPlanet("Testplanet", 0.5, 1.5, 23);
	    planet.placeNewMoon("Testmond", 0.1, 200000, -50);
	    star.placeNewAsteroid("Testasteroid", 20000, 900, 42);
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
    @Test
    public void testSystemCreation()
    {   	
        assertEquals("Testsystem", solarSystem.getName());
    }

    /**
     * Tests whether stars can be successfully added to a solar system.
     * Checks if object is of type star.
     * Checks by searching for the name in the list of satellites.
     */
    @Test
    public void testStarCreation()
    {   	
        Actor object = solarSystem.findSatelliteByName("Testsonne");
        assertEquals(true, object instanceof Star);
        assertEquals("Testsonne", object.getName());
     }
    
    /**
     * Tests whether planets can be successfully added to a solar system.
     * Checks if object is of type planet.
     * Checks by searching for the name in the list of satellites.
     */
    @Test
    public void testPlanetCreation()
    {   	
        Actor object = star.findSatelliteByName("Testplanet");
        assertEquals(true, object instanceof Planet);
        assertEquals("Testplanet", object.getName());
    }
    
    /**
     * Tests whether moons can be successfully added to a solar system.
     * Checks if object is of type moon.
     * Checks by searching for the name in the list of satellites.
     */
    @Test
    public void testMoonCreation()
    {   	
        Actor object = planet.findSatelliteByName("Testmond");
        assertEquals(true, object instanceof Moon);
        assertEquals("Testmond", object.getName());
    }
    
    /**
     * Tests whether moons can be successfully added to a solar system.
     * Checks if object is of type moon.
     * Checks by searching for the name in the list of satellites.
     */
    @Test
    public void testAsteroidCreation()
    {   	
        Actor object = star.findSatelliteByName("Testasteroid");
        assertEquals(true, object instanceof Asteroid);
        assertEquals("Testasteroid", object.getName());
    }
}
