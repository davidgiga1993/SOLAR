package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by argannor on 18.02.15.
 */
public abstract class ScalePresetButton extends TextButton {

    public ScalePresetButton(String text) {
        super(text, Styles.TOOLTIPSKIN);
        this.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onClick();
                applyPreset();
            }
        });
    }

    public static void loadPreset1() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(1, 1);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(1, 1);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(1, 1);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(1, 1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(20000, 1);
    }

    public static void loadPreset2() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(6, 1);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(12, 1);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(6, 0.3f);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(6, 1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(400000, 2);
    }

    public static void loadPreset3() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(3, 3);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(100, 6.8f);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(40, 0.5f);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(48, 1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(3500000, 3);
    }

    public abstract void onClick();

    private void applyPreset() {
        Array<Actor> actors = SolarEngine.get().getStage("GameStartStage").getActors();
        for (Actor a : actors) {
            if (a instanceof SolarActor) {
                ((SolarActor) a).updateScale();
            }
        }
    }
}
