package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 16.11.2014.
 */
public class GUILabel extends Label implements GUIActor {

    private final Tooltip tooltip = new Tooltip("Test", Styles.TOOLTIP_SKIN);
    private InputListener tooltipInputListener;
    private boolean tooltipIsEnabled = false;

    public GUILabel(CharSequence text, LabelStyle style, Stage stage) {
        super(text, style);
        tooltip.setVisible(false);
        stage.addActor(tooltip);

        tooltipInputListener = new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                tooltip.updatePosition();
                tooltip.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                tooltip.setVisible(false);
            }

        };
        enableTooltips();

    }

    public void disableTooltips() {
        if (!tooltipIsEnabled) {
            return;
        }
        this.removeListener(tooltipInputListener);
        tooltipIsEnabled = false;
    }

    private void enableTooltips() {
        if (tooltipIsEnabled) {
            return;
        }
        this.addListener(tooltipInputListener);
        tooltipIsEnabled = true;
    }


}
