package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 16.11.2014.
 */
public class GUILabel extends Label implements GUIActor {

    Tooltip tooltip = new Tooltip("Test", SolarEngine.get().styles.tooltipSkin);

    public GUILabel (CharSequence text, LabelStyle style, Stage stage) {
        super(text, style);
        tooltip.setVisible(false);
        stage.addActor(tooltip);


        this.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                tooltip.updatePosition();
                tooltip.setVisible(true);
                return;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                tooltip.setVisible(false);
                return;
            }

        });

    }

}
