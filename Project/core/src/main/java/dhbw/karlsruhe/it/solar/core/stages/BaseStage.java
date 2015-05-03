package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBarManagerSettings;

public abstract class BaseStage extends Stage  {
    protected final SolarEngine se;
    protected final String tag;

    public BaseStage(SolarEngine se, String tag)    {
        this(se, tag, se.getCamera());
    }
    
    public BaseStage(SolarEngine se, String tag, Camera cam) {
        super(new ScreenViewport(cam));
        this.se = se;
        this.tag = tag;
    }

    public void resize(int width, int height) {
        getViewport().update(width, height);
    }
    
    public String getTag() {
        return tag;
    }
}
