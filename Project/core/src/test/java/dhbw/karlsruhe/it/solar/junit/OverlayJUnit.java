package dhbw.karlsruhe.it.solar.junit;

import com.badlogic.gdx.scenes.scene2d.Group;
import dhbw.karlsruhe.it.solar.core.stages.BaseStage;
import dhbw.karlsruhe.it.solar.core.stages.StageManager;
import dhbw.karlsruhe.it.solar.testhelper.TestHelper;
import dhbw.karlsruhe.it.solar.testhelper.TestResult;
import dhbw.karlsruhe.it.solar.testhelper.TestStage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OverlayJUnit {
    public OverlayJUnit() {
    }

    /**
     * Eine neues "Overlay" erstellen und überprüfen ob diese richtig angezeigt wird.
     */

    @Test
    public void testCreateOverlay() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runCreateOverlay());

        assertTrue(result.boolResult);
    }

    private boolean runCreateOverlay() {
        StageManager Manager = new StageManager(TestSuite.getEngine());
        BaseStage Overlay = new TestStage(TestSuite.getEngine(), "Overlay");
        Manager.addStage(Overlay);
        return Manager.getStage("Overlay") != null;
    }

    /**
     * Noch mehr Overlays hinzufügen und überprüfen ob diese richtig angezeigt werden.
     */

    @Test
    public void testAddNextOverlay() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runAddNextOverlay());
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
        return Manager.getStage("Overlay 4") != null;
    }

    /**
     * Alle Overlays entferenn und überprüfen ob diese richtig entfernt wurden.
     */

    @Test
    public void testRemoveOverlay() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runRemoveOverlay());
        assertTrue(result.boolResult);
    }

    private boolean runRemoveOverlay() {
        StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 1"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 3"));

        Manager.removeStage("Overlay 1");

        if (Manager.getStage("Overlay 1") != null) {
            return false;
        }

        Manager.removeStages();

        if (Manager.getStage("Overlay 2") != null) {
            return false;
        }
        return Manager.getStage("Overlay 3") == null;
    }

    @Test
    public void testChangingCurrentStage() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runChangingCurrentStage());
        assertTrue(result.boolResult);
    }

    private boolean runChangingCurrentStage() {
        StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 1"));
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        Manager.swapCurrentStage(new TestStage(TestSuite.getEngine(), "Overlay 2"));
        return Manager.getStage(1).getTag().equals("Overlay 2");
    }

    @Test
    public void testAddGroup() throws InterruptedException {
        final TestResult result = new TestResult();
        TestHelper.sendRunnableToOpenGL(() -> result.boolResult = runAddGroup());
        assertTrue(result.boolResult);
    }

    private boolean runAddGroup() {
        StageManager Manager = new StageManager(TestSuite.getEngine());
        Manager.addStage(new TestStage(TestSuite.getEngine(), "Overlay"));

        Group TestGroup = new Group();
        Manager.addActor(TestGroup);

        return TestGroup.hasParent();
    }

}
