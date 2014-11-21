package dhbw.karlsruhe.it.solar.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import static org.junit.Assert.assertNotNull;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;

/*
 * 
 * Insert your unit test classes in the @SuiteClasses annotation before CukeRunnerJUnit.class 
 * 
 * Example:
@RunWith(Suite.class)
@SuiteClasses(
		{ ExitGameScreenJUnit.class, LevelDAOJUnit.class, ScreenHelperJUnit.class, ScoreDAOJUnit.class, CukeRunnerJUnit.class}
		)
 * 
 */

@RunWith(Suite.class)
@SuiteClasses(
		{ AstronomicalObjectsJUnit.class, KartenbewegungJUnit.class, OverlayJUnit.class, SpaceshipJUnit.class, StageManagerJUnit.class, LengthJUnit.class}
		)
public class TestSuite {

	private static LwjglApplication app; // only for convenience, keep if you want to

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = new LwjglApplication(new SolarEngine(), TestHelper.createTestConfig());
		TestHelper.wait(1200);
		
		// you have to wait here in order to let libGDX load up everything. It won't hurt you. srsly.
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}
	
	public static SolarEngine getEngine() {
		assertNotNull("Tests should be called only from the TestSuite", app);
		return (SolarEngine) app.getApplicationListener();
	}

}

