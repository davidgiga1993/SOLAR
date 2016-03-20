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
    public void testGetStage() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runGetStage());

        assertTrue(result.boolResult);
    }

    private boolean runGetStage() {
        StageManager Manager = new StageManager(TestSuite.getEngine());
        BaseStage TestStage = new TestStage("Test");
        Manager.addStage(TestStage);
        return Manager.getStage("Test") != null;
    }

    @Test
    public void testInsertStageToBack() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runInsertStageToBack());

        assertTrue(result.boolResult);
    }

	private boolean runInsertStageToBack() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.insertStageToBack(new TestStage("TestBack"));
        return Manager.getStage(0).getTag().equals("TestBack");
    }

    @Test
    public void testSwapCurrentStage() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runSwapCurrentStage());

        assertTrue(result.boolResult);
    }

	private boolean runSwapCurrentStage() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.swapCurrentStage(new TestStage("TestBack"));
        return Manager.getStage(1).getTag().equals("TestBack");
    }

    @Test
    public void testAddStage() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runAddStage());

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
        return Manager.getStage("Test3") != null;
    }

    @Test
    public void testRemoveStage() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runRemoveStage());

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
        return Manager.getStage("Test3") != null;
    }

    @Test
    public void testRemoveStages() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runRemoveStages());

        assertTrue(result.boolResult);
    }

	private boolean runRemoveStages() {
		StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));
        
        Manager.removeStages();

        return Manager.getStage(0) == null;
    }

}
