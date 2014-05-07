package Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.me.solar.Main;
import com.me.stages.BaseStage;
import com.me.stages.StageManager;
import com.me.stages.TestStage;

public class TestOverlay 
{
    public TestOverlay()
    {
    }
    
    /**
	 * Eine neues "Overlay" erstellen und überprüfen ob diese richtig angezeigt wird.
	 */
    
    @Test
    public void testCreateOverlay()
    {
        StageManager Manager = new StageManager(null);
        BaseStage Overlay = new TestStage("Overlay");
        Manager.addStage(Overlay);
        if (Manager.getStage("Overlay") == null)
            fail("No stage found");
    }
    
    /**
	 * Noch mehr Overlays hinzufügen und überprüfen ob diese richtig angezeigt werden.
	 */
    
    @Test
    public void testAddNextOverlay()
    {
        StageManager Manager = new StageManager(null);
        
        Manager.addStage(new TestStage("Overlay 1"));
        Manager.addStage(new TestStage("Overlay 2"));
        Manager.addStage(new TestStage("Overlay 3"));
        Manager.addStage(new TestStage("Overlay 4"));

        if (Manager.getStage("Overlay 1") == null)
            fail("Add failed");
        if (Manager.getStage("Overlay 2") == null)
            fail("Add failed");
        if (Manager.getStage("Overlay 3") == null)
            fail("Add failed");
        if (Manager.getStage("Overlay 4") == null)
            fail("Add failed");
    }
    
    /**
	 * Alle Overlays entferenn und überprüfen ob diese richtig entfernt wurden.
	 */
    
    @Test
    public void testRemoveOverlay()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Overlay 1"));
        Manager.addStage(new TestStage("Overlay 2"));
        Manager.addStage(new TestStage("Overlay 3"));
        
        Manager.removeStage("Overlay 1");
        
        assertTrue(Manager.getStage("Overlay 1") == null);
        
        Manager.removeStages();
        
        assertTrue(Manager.getStage("Overlay 2") == null);
        assertTrue(Manager.getStage("Overlay 3") == null);
    }

    @Test
    public void testChangingCurrentStage()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Overlay 1"));
        Manager.addStage(new TestStage("Overlay 2"));
        Manager.swapCurrentStage(new TestStage("Overlay 2"));
        if (Manager.getStage(1).TAG.equals("Overlay 2") == false)
            fail("Stage changing was wrong");
    }
    
    @Test
    public void testAddGroup()
    {
        StageManager Manager = new StageManager(null);
        Manager.addStage(new TestStage("Overlay"));
        
        Group TestGroup = new Group();
        Manager.addActor(TestGroup);

        if (TestGroup.hasParent() == false)
            fail("Stage not inserted correctly");
    }

}
