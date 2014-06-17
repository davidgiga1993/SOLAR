package Testing;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.me.stages.BaseStage;
import com.me.stages.StageManager;
import com.me.stages.TestStage;

public class TestStageManager
{
    @Test
    public void testGetStage()
    {
        StageManager Manager = new StageManager(null);
        BaseStage TestStage = new TestStage("Test");
        Manager.addStage(TestStage);
        if (Manager.getStage("Test") == null)
            fail("No stage found");
    }

    @Test
    public void testInsertStageToBack()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Test0"));
        Manager.insertStageToBack(new TestStage("TestBack"));
        if (Manager.getStage(0).TAG.equals("TestBack") == false)
            fail("Stage not inserted correctly");
    }

    @Test
    public void testSwapCurrentStage()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.swapCurrentStage(new TestStage("TestBack"));
        if (Manager.getStage(1).TAG.equals("TestBack") == false)
            fail("Stage swap was wrong");
    }

    @Test
    public void testAddStage()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));

        if (Manager.getStage("Test0") == null)
            fail("Add failed");
        if (Manager.getStage("Test1") == null)
            fail("Add failed");
        if (Manager.getStage("Test2") == null)
            fail("Add failed");
        if (Manager.getStage("Test3") == null)
            fail("Add failed");
    }

    @Test
    public void testRemoveStage()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));
        
        Manager.removeStage("Test1");
        
        if (Manager.getStage("Test0") == null)
            fail("Add failed");
        if (Manager.getStage("Test1") != null)
            fail("Add failed");
        if (Manager.getStage("Test2") == null)
            fail("Add failed");
        if (Manager.getStage("Test3") == null)
            fail("Add failed");
    }

    @Test
    public void testRemoveStages()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Test0"));
        Manager.addStage(new TestStage("Test1"));
        Manager.addStage(new TestStage("Test2"));
        Manager.addStage(new TestStage("Test3"));
        
        Manager.removeStages();
        
        if(Manager.getStage(0) != null)
            fail("Remove failed");
    }

}
