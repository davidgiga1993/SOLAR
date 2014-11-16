package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 16.11.2014.
 */
public class BaseGUIStage extends BaseStage {

    public BaseGUIStage(SolarEngine solarEngine, String tag)
    {
        super(solarEngine, tag, solarEngine.guiCamera);
   //     super(solarEngine, tag, new StretchViewport(Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), solarEngine.guiCamera))
    }


}
