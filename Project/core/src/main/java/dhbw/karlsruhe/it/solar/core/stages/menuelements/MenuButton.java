package dhbw.karlsruhe.it.solar.core.stages.menuelements;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dhbw.karlsruhe.it.solar.core.actions.LabelFontScalerAction;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by argannor on 17.02.15.
 */
public abstract class MenuButton extends Label{

    public MenuButton(CharSequence text, final SolarEngine engine) {
        super(text, engine.getDefaultLabelStyle());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onClick();
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                animateLabelIn();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                animateLabelOut();
            }
        });

    }

    protected abstract void onClick();

    private void animateLabelIn()    {
        animateLabel(1.2f);
    }

    private void animateLabelOut()    {
        animateLabel(1);
    }

    private void animateLabel(float scale)   {
        LabelFontScalerAction ac = new LabelFontScalerAction(scale, getFontScaleX());
        ac.setDuration(0.7f);
        ac.setInterpolation(Interpolation.exp10);
        addAction(ac);
    }

}


