package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.me.UserControls.Asteroid;
import com.me.UserControls.Moon;
import com.me.UserControls.Star;
import com.me.UserControls.Planet;

public class TestAstronomicalObjects
{

    private Star testStar;
    private Planet testPlanet;
    private Moon testMoon;
    private Asteroid testAsteroid;

    @Before
    public void setUp() throws Exception
    {
        testStar = new Star("Yavin");
        testStar.setPosition(22, 33);
        testPlanet = new Planet("Alderan");
        testPlanet.setPosition(44, 55);
        testMoon = new Moon("Death Star");
        testMoon.setPosition(66, 77);
        testAsteroid = new Asteroid("Astra");
        testAsteroid.setPosition(22, 33);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    /**
     * Eine Klasse "Stern" sinnvoll erstellen und in irgendeiner sinnvollen Form anzeigen.
     */
    @Test
    public void testStarCreation()
    {
        assertEquals(500f, testStar.getWidth(), 0);
        assertEquals(500f, testStar.getHeight(), 0);
        assertEquals("Yavin", testStar.getName());
    }

    /**
     * Eine Klasse "Planet" sinnvoll erstellen und in irgendeiner sinnvollen Form anzeigen.
     */
    @Test
    public void testPlanetCreation()
    {
        assertEquals(200f, testPlanet.getWidth(), 0);
        assertEquals(200f, testPlanet.getHeight(), 0);
        assertEquals("Alderan", testPlanet.getName());
    }

    /**
     * Eine Klasse "Moon" sinnvoll erstellen und in irgendeiner sinnvollen Form anzeigen.
     */
    @Test
    public void testMoonCreation()
    {
        assertEquals(75f, testMoon.getWidth(), 0);
        assertEquals(75f, testMoon.getHeight(), 0);
        assertEquals("Death Star", testMoon.getName());
    }

    /**
     * Eine Klasse "Asteroid" sinnvoll erstellen und in irgendeiner sinnvollen Form anzeigen.
     */
    @Test
    public void testAsteroidCreation()
    {
        assertEquals(35f, testAsteroid.getWidth(), 0);
        assertEquals(20f, testAsteroid.getHeight(), 0);
        assertEquals("Astra", testAsteroid.getName());
    }

}
