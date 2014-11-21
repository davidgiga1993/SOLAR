package dhbw.karlsruhe.it.solar.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Group;

import dhbw.karlsruhe.it.solar.core.stages.BaseStage;
import dhbw.karlsruhe.it.solar.core.stages.StageManager;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;
import dhbw.karlsruhe.it.solar.testhelper.TestResult;
import dhbw.karlsruhe.it.solar.testhelper.TestStage;

public class OverlayJUnit 
{
    public OverlayJUnit()
    {
    }
    
    /**
	 * Eine neues "Overlay" erstellen und überprüfen ob diese richtig angezeigt wird.
	 */
    
    @Test
    public void testCreateOverlay()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runCreateOverlay();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runCreateOverlay() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        BaseStage Overlay = new TestStage(TestSuite.getEngine(), "Overlay");
        Manager.addStage(Overlay);
        if (Manager.getStage("Overlay") == null)
            return false;
        return true;
	}
    
    /**
	 * Noch mehr Overlays hinzufügen und überprüfen ob diese richtig angezeigt werden.
	 */
    
    @Test
    public void testAddNextOverlay()
    {
    	final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runAddNextOverlay();
			}
		});
    	assertTrue(result.boolResult);
    }

	private boolean runAddNextOverlay() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 1"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 3"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 4"));

        if (Manager.getStage("Overlay 1") == null)
            return false;
        if (Manager.getStage("Overlay 2") == null)
        	return false;
        if (Manager.getStage("Overlay 3") == null)
        	return false;
        if (Manager.getStage("Overlay 4") == null)
        	return false;
        return true;
	}
    
    /**
	 * Alle Overlays entferenn und überprüfen ob diese richtig entfernt wurden.
	 */
    
    @Test
    public void testRemoveOverlay()
    {
    	final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runRemoveOverlay();
			}
		});
    	assertTrue(result.boolResult);
    }

	private boolean runRemoveOverlay() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 1"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 3"));
        
        Manager.removeStage("Overlay 1");
        
        if (Manager.getStage("Overlay 1") != null) return false;
        
        Manager.removeStages();
        
        if (Manager.getStage("Overlay 2") != null) return false;
        if (Manager.getStage("Overlay 3") != null) return false;
        return true;
	}

    @Test
    public void testChangingCurrentStage()
    {
    	final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runChangingCurrentStage();
			}
		});
    	assertTrue(result.boolResult);
    }

	private boolean runChangingCurrentStage() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 1"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        Manager.swapCurrentStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        if (Manager.getStage(1).TAG.equals("Overlay 2") == false)
            return false;
        return true;
	}
    
    @Test
    public void testAddGroup()
    {
    	final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runAddGroup();
			}
		});
    	assertTrue(result.boolResult);
    }

	private boolean runAddGroup() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay"));
        
        Group TestGroup = new Group();
        Manager.addActor(TestGroup);

        if (TestGroup.hasParent() == false)
            return false;
        return true;
	}

}
