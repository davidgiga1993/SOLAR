package dhbw.karlsruhe.it.solar.junit;

import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;
import dhbw.karlsruhe.it.solar.core.usercontrols.Asteroid.AsteroidType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon.MoonType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet.PlanetType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star.StarType;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AstronomicalObjectsJUnit
{

	private SolarSystem solarSystem;
	private Star star;
	private Planet planet;
	
    @Before
    public void setUp() throws Exception
    {
       TestHelper.sendRunnableToOpenGL(new Runnable() {
		
			@Override
			public void run() {
				runSetUp();
				
			}
       });        
    }
    
    public void runSetUp() {
        GameStartStage.startGame();
    	solarSystem = new SolarSystem("Testsystem");
    	star =  CreateAnAstronomicalBody.named("Testsonne").whichHasTheFollowingOrbitalProperties(solarSystem, new Length(0, Length.Unit.kilometres), new Angle(0)).andHasTheFollowingBodyProperties(new Length(1392684f/2, Length.Unit.kilometres), new Mass(1, Mass.Unit.KILOGRAM)).buildAs(StarType.GTYPE, solarSystem);
    	planet = CreateAnAstronomicalBody.named("Testplanet").whichHasTheFollowingOrbitalProperties(star, new Length(1.5f, Length.Unit.astronomicalUnit), new Angle(23)).andHasTheFollowingBodyProperties(new Length(10000.4f/2, Length.Unit.kilometres), new Mass(0.5f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.TERRAN, solarSystem);
    	CreateAnAstronomicalBody.named("Testmond").whichHasTheFollowingOrbitalProperties(planet, new Length(200000, Length.Unit.kilometres), new Angle(-50)).andHasTheFollowingBodyProperties(new Length(4879.4f/2, Length.Unit.kilometres), new Mass(0.1f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.LUNAR, solarSystem);
    	CreateAnAstronomicalBody.named("Testasteroid").whichHasTheFollowingOrbitalProperties(star, new Length(900, Length.Unit.kilometres), new Angle(42)).andHasTheFollowingBodyProperties(new Length(1500.4f/2, Length.Unit.kilometres), new Mass(20000, Mass.Unit.KILOGRAM)).buildAs(AsteroidType.DTYPE, solarSystem);
    	}

    @After
    public void tearDown() throws Exception
    {
        TestHelper.sendRunnableToOpenGL(new Runnable() {
            @Override
            public void run() {
                runTearDown();
            }
        });
    }

    public void runTearDown() {
        GameStartStage.endGame();
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
