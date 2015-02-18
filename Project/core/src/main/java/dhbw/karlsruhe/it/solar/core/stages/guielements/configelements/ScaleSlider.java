package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUILabel;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

/**
 * Created by Arga on 11.02.2015.
 */
public abstract class ScaleSlider extends WidgetGroup {

    protected GUILabel curValue;
    protected final Slider scaleSlider;
    protected SolarActorScale scale;
    private Class solarActorType;

    /**
     * Instantiates a new ScaleSlider
     * @param stage that contains the ScaleSlider
     * @param scale that should be modified
     * @param minValue of the slider
     * @param maxValue of the slider
     * @param solarActorType corresponding class of the scale
     */
    public ScaleSlider(final Stage stage, final SolarActorScale scale, float minValue, float maxValue ,final Class solarActorType) {
        super();
        this.scale = scale;
        this.solarActorType = solarActorType;
        Label textLabel = new Label(solarActorType.getSimpleName() + ": ", SolarEngine.get().styles.defaultLabelStyle);
        curValue =  new GUILabel("", SolarEngine.get().styles.defaultLabelStyle, stage);
        curValue.disableTooltips();
        curValue.setAlignment(Align.right);
        float step = (maxValue - minValue) / 100;
        step = Math.round(step);
        if(step <= 0) {
            step = 0.1f;
        }
        scaleSlider = new Slider(minValue, maxValue, step, false, SolarEngine.get().styles.tooltipSkin);


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

    public void onChange() {
        float newValue = scaleSlider.getValue();
        updateScale(newValue);
        setValueLabelText(newValue);
        for (Actor a : SolarEngine.get().stageManager.getStage("GameStartStage").getActors()) {
            if (solarActorType.isInstance(a)) {
                ((SolarActor) (a)).updateScale();
            }
        }
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
