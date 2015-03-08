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
    protected float maxQuota;

    private float absoluteRadius;

    private float relativeRadius = (float) (384399 / SolarActor.stageScalingFactor) * 2;
    public Color color;

    public PreviewActor(SolarActor parent, float relativeRadius, float zoomLevel, Color color) {
        this.parent = parent;
        this.zoomLevel = zoomLevel;
        this.maxQuota = 2500 / zoomLevel;
        this.color = color;
        this.relativeRadius = this.relativeRadius > relativeRadius ? this.relativeRadius : relativeRadius;
    }

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        float zoomQuota = camera.zoom / zoomLevel;
        zoomQuota = Math.min(zoomQuota, maxQuota);

        absoluteRadius = relativeRadius * zoomQuota;
        float absoluteWidth = absoluteRadius * 2;

        libGDXShapeRenderer.setColor(color);
        libGDXShapeRenderer.rect(parent.getX()+parent.getOriginX() - absoluteRadius, parent.getY()+parent.getOriginY() - absoluteRadius, absoluteWidth, absoluteWidth);
    }
}
