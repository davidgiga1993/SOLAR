package Testing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.badlogic.gdx.Gdx;
import com.me.solar.Main;

@RunWith(Suite.class)
@SuiteClasses(
{ TestAstronomicalObjects.class, TestSpaceship.class, TestStageManager.class, TestKartenbewergung.class })
public class AllTests
{

    @BeforeClass
    public static void setUp()
    {
        Main.main(null);
    }

    @AfterClass
    public static void tearDown()
    {
        Gdx.app.exit();
    }
}
