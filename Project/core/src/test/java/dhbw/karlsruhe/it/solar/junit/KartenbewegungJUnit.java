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
		
        float startZoom = engine.camera.zoom;
        Robot robot;
        try
        {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PLUS);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_PLUS);
            if(startZoom <= engine.camera.zoom)
                fail("Zoom not working");
            
            startZoom = engine.camera.zoom;            
            robot.keyPress(KeyEvent.VK_MINUS);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_MINUS);
            if(startZoom >= engine.camera.zoom)
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
