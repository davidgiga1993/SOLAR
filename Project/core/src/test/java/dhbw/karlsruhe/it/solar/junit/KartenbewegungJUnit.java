package dhbw.karlsruhe.it.solar.junit;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

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
