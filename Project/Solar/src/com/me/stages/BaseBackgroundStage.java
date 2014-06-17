package com.me.stages;

import com.me.solar.SolarEngine;

public class BaseBackgroundStage extends BaseStage
{
    public BaseBackgroundStage(SolarEngine SE, String TAG)
    {
        super(SE, TAG);
        setCamera(SE.Backgroundcamera);
    }
}
