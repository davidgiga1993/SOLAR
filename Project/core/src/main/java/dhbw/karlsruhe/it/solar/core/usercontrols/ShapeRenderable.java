package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * Created by Arga on 28.11.2014.
 */
public interface ShapeRenderable {
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer);
}
