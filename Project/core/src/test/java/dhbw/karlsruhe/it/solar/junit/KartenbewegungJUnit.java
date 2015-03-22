package dhbw.karlsruhe.it.solar.junit;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.fail;

public class KartenbewegungJUnit
{

	protected SolarEngine engine;
	
    public KartenbewegungJUnit()
    {
    }

    @Before
    public void setUp() throws Exception
    {
    	engine = TestSuite.getEngine();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testZoom()
    {
    	Runnable startGame = new Runnable() {
			
			@Override
			public void run() {
				engine.stageManager.removeStage("StartStage");
				GameStartStage.startGame();
			}
		};
		TestHelper.sendRunnableToOpenGL(startGame);
		
        float startZoom = engine.getSolarCameraZoom();
        Robot robot;
        try
        {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_E);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_E);
            if(startZoom <= engine.getSolarCameraZoom())
                fail("Zoom not working");

            startZoom = engine.getSolarCameraZoom();
            robot.keyPress(KeyEvent.VK_Q);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_Q);
            if(startZoom >= engine.getSolarCameraZoom())
                fail("Zoom not working");
            
        }
        catch(InterruptedException ex)
        {
            
        }
        catch (AWTException e)
        {
        }
        
    }
    
    @Test
    public void testScroll()
    {
       // No test case -> camera can't be tested
    }

}
