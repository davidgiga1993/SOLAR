package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import dhbw.karlsruhe.it.solar.core.solar.FontCacher;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

public class Styles  {
    private static final BitmapFont DEFAULT_FONT = new BitmapFont();
    public static final TextButtonStyle TEXTBUTTON_STYLE = null;
    public static final LabelStyle DEFAULTLABEL_STYLE = new LabelStyle(DEFAULT_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final TextField.TextFieldStyle DEFAULTTEXTFIELD_STYLE = new TextField.TextFieldStyle(DEFAULT_FONT, new Color(0,.5f,.5f,1f), null, null, null);

    public static final Skin TOOLTIPSKIN = new Skin(Gdx.files.internal("data/skin/uiskin.json"));

    public Styles(TextureCacher loader)    {
        BitmapFont fontDefault = new BitmapFont();
        FontCacher.addFont(fontDefault, "default");
        TextureAtlas guiAtlas = new TextureAtlas(Gdx.files.internal("data/skin/uiskin.atlas"));
        TOOLTIPSKIN.addRegions(guiAtlas);
        // textButtonStyle = new TextButtonStyle(new NinePatchDrawable(loader.NinePatchMap.get("button_regular")), new NinePatchDrawable(loader.NinePatchMap.get("button_selected")), new NinePatchDrawable(loader.NinePatchMap.get("button_active")), new BitmapFont());
    }
}
