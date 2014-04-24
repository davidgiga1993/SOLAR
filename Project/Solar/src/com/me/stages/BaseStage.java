package com.me.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.solar.SolarEngine;

public abstract class BaseStage extends Stage
{
    protected final SolarEngine SE;
    
    public String TAG;

    public BaseStage(SolarEngine SE, String TAG)
    {
        super(SolarEngine.Width, SolarEngine.Height, false);       
        this.SE = SE;
        this.TAG = TAG;
        setCamera(SE.camera);
    }
}
