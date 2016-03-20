package dhbw.karlsruhe.it.solar.junit;

import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.*;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.physics.Angle.AngularUnit;
import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
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
    public void setUp() throws InterruptedException {
        TestHelper.sendRunnableToOpenGL(() -> runSetUp());
    }

    private void runSetUp() {
        GameStartStage.startNewGame();
    	solarSystem = new SolarSystem("Testsystem");
    	star =  CreateAnAstronomicalBody.named("Testsonne").whichHasTheFollowingOrbitalProperties(solarSystem, new Length(0, Length.DistanceUnit.KILOMETERS), new Angle()).andHasTheFollowingBodyProperties(new Length(1392684f/2, Length.DistanceUnit.KILOMETERS), new Mass(1, Mass.MassUnit.SOLAR_MASS), new Albedo(0.3f)).buildAs(new StarType(StarType.MorganKeenanSpectralType.G, StarType.SpectralTypeSubdivision.TWO, StarType.LuminosityClass.MAIN_SEQUENCE_STAR), solarSystem);
    	planet = CreateAnAstronomicalBody.named("Testplanet").whichHasTheFollowingOrbitalProperties(star, new Length(1.5f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(23, AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(10000.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.5f, Mass.MassUnit.EARTH_MASS), new Albedo(0.3f)).whichRotatesEvery(new Time(0.99726968f,TimeUnit.DAYS)).buildAs(new PlanetType(PlanetType.TypeOfPlanet.TERRESTRIAL, PlanetType.TextureTypeOfPlanet.TERRAN), solarSystem);
    	CreateAnAstronomicalBody.named("Testmond").whichHasTheFollowingOrbitalProperties(planet, new Length(200000, Length.DistanceUnit.KILOMETERS), new Angle(-50, AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(4879.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.1f, Mass.MassUnit.EARTH_MASS), new Albedo(0.3f)).whichIsTidallyLockedToItsPrimary().buildAs(new MoonType(MoonType.TypeOfMoon.TERRESTRIAL_MOON, MoonType.TextureTypeOfMoon.LUNAR), solarSystem);
    	CreateAnAstronomicalBody.named("Testasteroid").whichHasTheFollowingOrbitalProperties(star, new Length(900, Length.DistanceUnit.KILOMETERS), new Angle(742, AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(1500.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(20000, Mass.MassUnit.KILOGRAM), new Albedo(0.3f)).whichIsTidallyLockedToItsPrimary().buildAs(new AsteroidType(AsteroidType.TypeOfAsteroid.DTYPE, AsteroidType.TextureTypeOfAsteroid.PHOEBE_DEFAULT_IMAGE), solarSystem);
    }

    @After
    public void tearDown() throws InterruptedException {
        TestHelper.sendRunnableToOpenGL(() -> runTearDown());
    }

    private void runTearDown() {
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
        Actor object = solarSystem.findAstronomicalBodyByName("Testsonne");
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
        Actor object = star.findAstronomicalBodyByName("Testplanet");
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
        Actor object = planet.findAstronomicalBodyByName("Testmond");
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
        Actor object = star.findAstronomicalBodyByName("Testasteroid");
        assertEquals(true, object instanceof Asteroid);
        assertEquals("Testasteroid", object.getName());
    }
}
