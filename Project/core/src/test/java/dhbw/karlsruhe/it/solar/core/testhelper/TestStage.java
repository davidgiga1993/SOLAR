package dhbw.karlsruhe.it.solar.core.testhelper;

import dhbw.karlsruhe.it.solar.core.stages.BaseStage;
import dhbw.karlsruhe.it.solar.core.junit.TestSuite;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;


public class TestStage extends BaseStage {

    public TestStage(SolarEngine SE, String TAG) {
        super(SE, TAG);
    }

    public TestStage(String TAG) {
        super(TestSuite.getEngine(), TAG);
    }

}
