package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

/**
 * Created by argannor on 18.02.15.
 */
public abstract class ScalePresetButton extends TextButton {

    public ScalePresetButton(String text) {
        super(text, SolarEngine.get().styles.tooltipSkin);
        this.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onClick();
                applyPreset();
            }
        });
    }

    public abstract void onClick();

    public void applyPreset() {
        Array<Actor> actors = SolarEngine.get().stageManager.getStage("GameStartStage").getActors();
        for (Actor a : actors) {
            if (a instanceof SolarActor) {
                ((SolarActor) a).updateScale();
            }
        }
    }

    public static void loadPreset1() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(1,1);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(1,1);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(1, 1);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(1,1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(1,1);
    }

    public static void loadPreset2() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(2,2);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(80,20);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(20,0.3f);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(20,1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(2,2);
    }

    public static void loadPreset3() {
        ConfigurationConstants.SCALE_FACTOR_ASTEROID.set(3,3);
        ConfigurationConstants.SCALE_FACTOR_MOON.set(500,50);
        ConfigurationConstants.SCALE_FACTOR_PLANET.set(80,0.6f);
        ConfigurationConstants.SCALE_FACTOR_STAR.set(24,1);
        ConfigurationConstants.SCALE_FACTOR_UNITS.set(3,3);
    }
}
