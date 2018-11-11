package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public abstract class BaseStage extends Stage {
    final SolarEngine se;
    final String tag;

    protected BaseStage(SolarEngine se, String tag) {
        this(se, tag, se.getCamera());
    }

    BaseStage(SolarEngine se, String tag, Camera cam) {
        super(new ScreenViewport(cam));
        this.se = se;
        this.tag = tag;
    }

    void resize(int width, int height) {
        getViewport().update(width, height);
    }

    public String getTag() {
        return tag;
    }
}
