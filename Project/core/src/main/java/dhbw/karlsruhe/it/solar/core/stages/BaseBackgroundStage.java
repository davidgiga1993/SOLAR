package dhbw.karlsruhe.it.solar.core.stages;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;


class BaseBackgroundStage extends BaseStage {
    BaseBackgroundStage(SolarEngine se, String tag) {
        super(se, tag, se.getBackgroundCamera());
    }
}
