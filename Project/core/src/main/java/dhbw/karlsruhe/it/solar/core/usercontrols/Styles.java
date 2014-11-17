package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import dhbw.karlsruhe.it.solar.core.solar.FontCacher;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

public class Styles
{
    public TextButtonStyle textButtonStyle;

    public LabelStyle defaultLabelStyle;

    public Skin tooltipSkin;

    public Styles(TextureCacher loader)
    {
        BitmapFont defaultFont = new BitmapFont();
        FontCacher.addFont(defaultFont, "default");
        defaultLabelStyle = new LabelStyle(defaultFont, new Color(0.8f, 0.8f, 0.8f, 1f));
        TextureAtlas guiAtlas = new TextureAtlas(Gdx.files.internal("data/skin/uiskin.atlas"));
        tooltipSkin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
        tooltipSkin.addRegions(guiAtlas);
        // textButtonStyle = new TextButtonStyle(new NinePatchDrawable(loader.NinePatchMap.get("button_regular")), new NinePatchDrawable(loader.NinePatchMap.get("button_selected")), new NinePatchDrawable(loader.NinePatchMap.get("button_active")), new BitmapFont());
    }
}
