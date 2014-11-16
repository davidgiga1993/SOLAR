package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public abstract class BaseStage extends Stage
{
    protected final SolarEngine SE;
    public String TAG;

    public BaseStage(SolarEngine SE, String TAG)
    {
    	this(SE, TAG, SE.camera);
    }
    
    public BaseStage(SolarEngine SE, String TAG, Camera cam) {
    	super(new ScreenViewport(cam));
    	
    	this.SE = SE;
    	this.TAG = TAG;
    }

    public BaseStage(SolarEngine SE,String TAG, Viewport v) {
        super(v);
        this.SE=SE;
        this.TAG=TAG;
    }

    public void resize(int width, int height) {
        getViewport().update(width, height);
    }
}
