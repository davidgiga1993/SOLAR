package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public abstract class BaseStage extends Stage  {
    protected final SolarEngine se;
    protected final String tag;

    public BaseStage(SolarEngine se, String tag)    {
        this(se, tag, se.camera);
    }
    
    public BaseStage(SolarEngine se, String tag, Camera cam) {
        super(new ScreenViewport(cam));
        this.se = se;
        this.tag = tag;
    }

    public BaseStage(SolarEngine se,String tag, Viewport v) {
        super(v);
        this.se=se;
        this.tag=tag;
    }

    public void resize(int width, int height) {
        getViewport().update(width, height);
    }
    
    public String getTag() {
        return tag;
    }
}
