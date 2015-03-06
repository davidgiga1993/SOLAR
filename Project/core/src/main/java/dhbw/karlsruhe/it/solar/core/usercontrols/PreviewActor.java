package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * Created by argannor on 06.03.15.
 */
public class PreviewActor implements ShapeRenderable {

    protected SolarActor parent;
    protected OrthographicCamera camera = SolarEngine.get().camera;
    protected float zoomLevel;

    private float absoluteRadius;

    public PreviewActor(SolarActor parent, float relativeRadius, float zoomLevel) {
        this.parent = parent;
        this.zoomLevel = zoomLevel;
    }

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        float zoomQuota = camera.zoom / zoomLevel ;
        float relativeRadius = (float) (384399 / SolarActor.stageScalingFactor);
        absoluteRadius = relativeRadius * zoomQuota;
        libGDXShapeRenderer.end();
        libGDXShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        libGDXShapeRenderer.setColor(Color.RED);
        libGDXShapeRenderer.circle(parent.getX()+parent.getWidth()/2, parent.getY()+parent.getHeight()/2, absoluteRadius);
        libGDXShapeRenderer.end();
        libGDXShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    }
}
