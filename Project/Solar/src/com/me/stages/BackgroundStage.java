package com.me.stages;

import com.me.solar.SolarEngine;

public class BackgroundStage extends BaseBackgroundStage
{

    public BackgroundStage(SolarEngine SE)
    {
        super(SE, "Background");

        addActor(SE.Service.AddBackgroundImage());        
    }
}
