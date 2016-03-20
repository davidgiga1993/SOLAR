package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 16.11.2014.
 */
public class BaseGUIStage extends BaseStage {

    BaseGUIStage(SolarEngine solarEngine, String tag) {
        super(solarEngine, tag, solarEngine.getGUICamera());
   //     super(solarEngine, tag, new StretchViewport(Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), solarEngine.guiCamera))
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport = getViewport();
        viewport.update(width, height, true);
    }
}
