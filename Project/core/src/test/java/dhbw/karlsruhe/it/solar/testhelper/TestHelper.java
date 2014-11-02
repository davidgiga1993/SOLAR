package dhbw.karlsruhe.it.solar.testhelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.security.Permission;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;


public class TestHelper {
	
	public static boolean exitStatus = false;
	public static TimeUnit timeOutUnit = TimeUnit.SECONDS;
	// v- this one is for debugging
	//public static TimeUnit timeOutUnit = TimeUnit.HOURS;
	
	/**
	 * Sets the current Thread to sleep for the given duration
	 * @param ms time to sleep in ms
	 */
	public static void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
	
	/**
	 * Convenience Method to create a LwjglApplicationConfiguration for testing purposes.
	 * @return
	 */
	public static LwjglApplicationConfiguration createTestConfig() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL30 = true;
		config.width = ConfigurationConstants.SCREENWIDTH;
		config.height = ConfigurationConstants.SCREENHEIGHT;
		return config;
	}
	
	
	public static class NoExitSecurityManager extends SecurityManager {
		@Override
		public void checkPermission(Permission perm) {
			// allow anything
		}
		@Override
		public void checkPermission(Permission perm, Object context) {
			// allow anything
		}
		@Override
		public void checkExit(int status) {
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

    public static class ExitException extends SecurityException 
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 5169325296866009143L;

		public ExitException(int status) 
        {
            super("There is no escape!");
            exitStatus = true;
        }
    }
    
    
    /**
     * Clicks (left mouse button) on the specified location (rel. to your game's bottom left corner).
     * @param x [px]
     * @param y [px]
     */
    public static void clickOnLocation(int x, int y) {
		Gdx.app.getInput().setCursorPosition(x, y);
		try {
			Robot bot;
			bot = new Robot();
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Convenience method. Calls changeScreen with a new MainMenuScreen
     * @throws InterruptedException
     */
    public static void setToMainMenuScreen() throws InterruptedException {
    	// TODO: uncomment the next line and replace the MainMenuScreen's constructor
    	// changeScreen(new MainMenuScreen());
    }
    
    /**
     * Sets your game to a specific screen. <br>
     * This method will pass the instructions to the main-loop's threat.
     * @param targetScreen
     * @throws InterruptedException shouldn't occur
     */
    public static void changeScreen(final Screen targetScreen) throws InterruptedException {
    	final SolarEngine game = (SolarEngine) Gdx.app.getApplicationListener();
    	final CountDownLatch latch = new CountDownLatch(1);
    	Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run() {
				Gdx.graphics.setDisplayMode(ConfigurationConstants.SCREENWIDTH, ConfigurationConstants.SCREENHEIGHT, false);
				// game.setScreen(targetScreen);
				latch.countDown();
			}
		});
    }

	/**
	 * Basic java robot to press a specific key.<br> 
	 * see java.awt.event.KeyEvent for keycodes.
	 * @param keyEvent
	 */
	public static void pressButton(int keyEvent) {
		try {
			Robot bot;
			bot = new Robot();
			bot.keyPress(keyEvent);
			wait(50);
			bot.keyRelease(keyEvent);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sends the Runnable to the main thread containing the OpenGL context.
	 * This method blocks until the runnable is completely executed.
	 * @param runnable
	 */
	public static void sendRunnableToOpenGL(final Runnable runnable) {
		final CountDownLatch latch = new CountDownLatch(1);
    	Runnable runner = new Runnable() {

    	@Override
    	public void run() {
    		runnable.run();
    		latch.countDown();
    	}
    	};
    		Gdx.app.postRunnable(runner);
    	try {
    		latch.await();
    	}catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	}
}
