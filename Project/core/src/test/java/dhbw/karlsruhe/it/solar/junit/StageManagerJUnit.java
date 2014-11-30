package dhbw.karlsruhe.it.solar.junit;

import dhbw.karlsruhe.it.solar.core.stages.BaseStage;
import dhbw.karlsruhe.it.solar.core.stages.StageManager;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;
import dhbw.karlsruhe.it.solar.testhelper.TestResult;
import dhbw.karlsruhe.it.solar.testhelper.TestStage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StageManagerJUnit
{
    @Test
    public void testGetStage()
    {
       	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runGetStage();
			}
		});
    	
    	assertTrue(result.boolResult);
    }
    
    public boolean runGetStage() {
    	StageManager Manager = new StageManager(TestSuite.getEngine());
        BaseStage TestStage = new TestStage("Test");
        Manager.addStage(TestStage);
        if (Manager.getStage("Test") == null)
            return false;
        return true;
    }

    @Test
    public void testInsertStageToBack()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runInsertStageToBack();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runInsertStageToBack() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.insertStageToBack(new TestStage("TestBack"));
        if (Manager.getStage(0).TAG.equals("TestBack") == false)
            return false;
        return true;
	}

    @Test
    public void testSwapCurrentStage()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runSwapCurrentStage();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runSwapCurrentStage() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.swapCurrentStage(new TestStage("TestBack"));
        if (Manager.getStage(1).TAG.equals("TestBack") == false)
            return false;
        return true;
	}

    @Test
    public void testAddStage()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runAddStage();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runAddStage() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));

        if (Manager.getStage("Test0") == null)
            return false;
        if (Manager.getStage("Test1") == null)
        	return false;
        if (Manager.getStage("Test2") == null)
        	return false;
        if (Manager.getStage("Test3") == null)
        	return false;
        return true;
	}

    @Test
    public void testRemoveStage()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runRemoveStage();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runRemoveStage() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));
        
        Manager.removeStage("Test1");
        
        if (Manager.getStage("Test0") == null)
            return false;
        if (Manager.getStage("Test1") != null)
        	return false;
        if (Manager.getStage("Test2") == null)
        	return false;
        if (Manager.getStage("Test3") == null)
        	return false;
        return true;
	}

    @Test
    public void testRemoveStages()
    {
    	final TestResult result = new TestResult();
    	TestHelper.sendRunnableToOpenGL(new Runnable() {
			
			@Override
			public void run() {
				result.boolResult = runRemoveStages();
			}
		});
    	
    	assertTrue(result.boolResult);
    }

	private boolean runRemoveStages() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));
        
        Manager.removeStages();
        
        if(Manager.getStage(0) != null)
            return false;
        return true;
	}

}
