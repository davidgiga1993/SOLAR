package dhbw.karlsruhe.it.solar.core.actions;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelFontScalerAction extends TemporalAction {
    private float scale;
    private float startScale;

    public LabelFontScalerAction(float scale, float startScale)    {
        this.scale = scale;
        this.startScale = startScale;
    }

    @Override
    protected void update(float percent)    {
        ((Label) actor).setFontScale((scale - startScale) * percent + startScale);
    }
}
