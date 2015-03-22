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
	private static final BitmapFont defaultFont = new BitmapFont();
    public static final TextButtonStyle textButtonStyle = null;
    public static final LabelStyle defaultLabelStyle = new LabelStyle(defaultFont, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final TextField.TextFieldStyle defaultTextFieldStyle = new TextField.TextFieldStyle(defaultFont, new Color(0,.5f,.5f,1f), null, null, null);

    public static final Skin tooltipSkin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));;

    public Styles(TextureCacher loader)    {
        BitmapFont defaultFont = new BitmapFont();
        FontCacher.addFont(defaultFont, "default");
        TextureAtlas guiAtlas = new TextureAtlas(Gdx.files.internal("data/skin/uiskin.atlas"));
        tooltipSkin.addRegions(guiAtlas);
        // textButtonStyle = new TextButtonStyle(new NinePatchDrawable(loader.NinePatchMap.get("button_regular")), new NinePatchDrawable(loader.NinePatchMap.get("button_selected")), new NinePatchDrawable(loader.NinePatchMap.get("button_active")), new BitmapFont());
    }
}
