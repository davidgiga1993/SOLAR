package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUILabel;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 11.02.2015.
 */
abstract class ScaleSlider extends WidgetGroup {

    final Slider scaleSlider;
    GUILabel curValue;
    SolarActorScale scale;
    private Class solarActorType;

    /**
     * Instantiates a new ScaleSlider
     * @param stage that contains the ScaleSlider
     * @param scale that should be modified
     * @param minValue of the slider
     * @param maxValue of the slider
     * @param solarActorType corresponding class of the scale
     */
    ScaleSlider(final Stage stage, final SolarActorScale scale, float minValue, float maxValue, final Class solarActorType) {
        super();
        this.scale = scale;
        this.solarActorType = solarActorType;
        Label textLabel = new Label(solarActorType.getSimpleName() + ": ", Styles.DEFAULTLABEL_STYLE);
        curValue =  new GUILabel("", Styles.DEFAULTLABEL_STYLE, stage);
        curValue.disableTooltips();
        curValue.setAlignment(Align.right);
        float step = (maxValue - minValue) / 100;
        step = Math.round(step);
        if(step <= 0) {
            step = 0.1f;
        }
        scaleSlider = new Slider(minValue, maxValue, step, false, Styles.TOOLTIPSKIN);


        scaleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                onChange();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(textLabel).width(100);
        table.add(scaleSlider);
        table.add(curValue).width(80);

        this.addActor(table);
        this.layout();

        initialize();
    }

    private void onChange() {
        float newValue = scaleSlider.getValue();
        updateScale(newValue);
        setValueLabelText(newValue);
    }

    private void setValueLabelText(float value) {
        curValue.setText(String.valueOf(value));
    }

    protected abstract void initialize();
    protected abstract void updateScale(float value);

    public void refreshValues() {
        initialize();
    }

}
