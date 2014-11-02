package dhbw.karlsruhe.it.solar.core.stages;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;


public class BackgroundStage extends BaseBackgroundStage
{

    public BackgroundStage(SolarEngine SE)
    {
        super(SE, "Background");

        addActor(SE.Service.AddBackgroundImage());        
    }
}
