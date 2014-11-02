package dhbw.karlsruhe.it.solar.core.stages;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;


public class BaseBackgroundStage extends BaseStage
{
    public BaseBackgroundStage(SolarEngine SE, String TAG)
    {
        super(SE, TAG, SE.Backgroundcamera);
    }
}
