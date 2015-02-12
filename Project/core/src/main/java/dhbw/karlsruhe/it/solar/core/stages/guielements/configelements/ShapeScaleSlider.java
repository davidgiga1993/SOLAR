package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

/**
 * Created by Arga on 12.02.2015.
 */
public class ShapeScaleSlider extends ScaleSlider {

    public ShapeScaleSlider(final Stage stage, final SolarActorScale scale, float minValue, float maxValue ,final Class solarActorType) {
        super(stage, scale, minValue, maxValue, solarActorType);
    }

    @Override
    protected void initialize() {
        scaleSlider.setValue(scale.shapeScale);
        curValue.setText(""+scale.shapeScale);
    }

    @Override
    protected void updateScale(float value) {
        scale.shapeScale = value;
    }
}
