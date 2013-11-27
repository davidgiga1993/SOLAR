package UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.solar.TextureCacher;

public class Styles
{
    public TextButtonStyle textButtonStyle;

    public LabelStyle defaultLabelStyle;

    public BitmapFont defaultFont;

    public Styles(TextureCacher loader)
    {
        defaultFont = new BitmapFont();
        defaultLabelStyle = new LabelStyle(defaultFont, new Color(0.8f, 0.8f, 0.8f, 1f));

        // textButtonStyle = new TextButtonStyle(new NinePatchDrawable(loader.NinePatchMap.get("button_regular")), new NinePatchDrawable(loader.NinePatchMap.get("button_selected")), new NinePatchDrawable(loader.NinePatchMap.get("button_active")), new BitmapFont());
    }
}
