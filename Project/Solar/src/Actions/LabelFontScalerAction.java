package Actions;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelFontScalerAction extends TemporalAction
{
    private float scale;
    private float startScale;

    public LabelFontScalerAction(float scale, float StartScale)
    {
        this.scale = scale;
        this.startScale = StartScale;
    }

    @Override
    protected void update(float percent)
    {
        ((Label) actor).setFontScale((scale - startScale) * percent + startScale);
    }
}
