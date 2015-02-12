package dhbw.karlsruhe.it.solar.core.stages.guielements.configElements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

/**
 * Created by Arga on 12.02.2015.
 */
public class OrbitScaleSlider extends ScaleSlider{

    public OrbitScaleSlider(final Stage stage, final SolarActorScale scale, float minValue, float maxValue ,final Class solarActorType) {
        super(stage, scale, minValue, maxValue, solarActorType);
        scaleSlider.setStepSize(0.1f);
    }

    @Override
    protected void initialize() {
        scaleSlider.setValue(scale.orbitScale);
        curValue.setText(""+scale.orbitScale);
    }

    @Override
    protected void updateScale(float value) {
        scale.orbitScale = value;
    }
}
