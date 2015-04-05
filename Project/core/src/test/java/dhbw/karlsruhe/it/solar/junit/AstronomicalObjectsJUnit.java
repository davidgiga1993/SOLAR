package dhbw.karlsruhe.it.solar.junit;

import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;
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
    	star =  CreateAnAstronomicalBody.named("Testsonne").whichHasTheFollowingOrbitalProperties(solarSystem, new Length(0, Length.DistanceUnit.KILOMETERS), new Angle(0)).andHasTheFollowingBodyProperties(new Length(1392684f/2, Length.DistanceUnit.KILOMETERS), new Mass(1, Mass.MassUnit.KILOGRAM)).buildAs(new StarType(StarType.TypeOfStar.GTYPE), solarSystem);
    	planet = CreateAnAstronomicalBody.named("Testplanet").whichHasTheFollowingOrbitalProperties(star, new Length(1.5f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(23)).andHasTheFollowingBodyProperties(new Length(10000.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.5f, Mass.MassUnit.EARTH_MASS)).buildAs(new PlanetType(PlanetType.TypeOfPlanet.TERRAN), solarSystem);
    	CreateAnAstronomicalBody.named("Testmond").whichHasTheFollowingOrbitalProperties(planet, new Length(200000, Length.DistanceUnit.KILOMETERS), new Angle(-50)).andHasTheFollowingBodyProperties(new Length(4879.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.1f, Mass.MassUnit.EARTH_MASS)).buildAs(new MoonType(MoonType.TypeOfMoon.LUNAR), solarSystem);
    	CreateAnAstronomicalBody.named("Testasteroid").whichHasTheFollowingOrbitalProperties(star, new Length(900, Length.DistanceUnit.KILOMETERS), new Angle(42)).andHasTheFollowingBodyProperties(new Length(1500.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(20000, Mass.MassUnit.KILOGRAM)).buildAs(new AsteroidType(AsteroidType.SpectralType.DTYPE), solarSystem);
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
