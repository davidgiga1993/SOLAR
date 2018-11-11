package dhbw.karlsruhe.it.solar.core.junit;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.testhelper.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.fail;

public class KartenbewegungJUnit {

    private SolarEngine engine;

    public KartenbewegungJUnit() {
    }

    @Before
    public void setUp() {
        engine = TestSuite.getEngine();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testZoom() throws AWTException, InterruptedException {
        Runnable startGame = () -> {
            engine.removeStage("StartStage");
            GameStartStage.startNewGame();
        };
        TestHelper.sendRunnableToOpenGL(startGame);

        float startZoom = engine.getSolarCameraZoom();

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(700);
        robot.keyRelease(KeyEvent.VK_E);
        if (startZoom <= engine.getSolarCameraZoom())
            fail("Zoom not working");

        startZoom = engine.getSolarCameraZoom();
        robot.keyPress(KeyEvent.VK_Q);
        Thread.sleep(700);
        robot.keyRelease(KeyEvent.VK_Q);
        if (startZoom >= engine.getSolarCameraZoom())
            fail("Zoom not working");


    }

    @Test
    public void testScroll() {
        // No test case -> camera can't be tested
    }

}
