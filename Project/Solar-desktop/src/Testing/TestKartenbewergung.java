package Testing;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.me.solar.Main;

public class TestKartenbewergung
{

    public TestKartenbewergung()
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testZoom()
    {
        float startZoom = Main.Engine.camera.zoom;
        Robot robot;
        try
        {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PLUS);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_PLUS);
            if(startZoom <= Main.Engine.camera.zoom)
                fail("Zoom not working");
            
            startZoom = Main.Engine.camera.zoom;            
            robot.keyPress(KeyEvent.VK_MINUS);
            Thread.sleep(700);
            robot.keyRelease(KeyEvent.VK_MINUS);
            if(startZoom >= Main.Engine.camera.zoom)
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
