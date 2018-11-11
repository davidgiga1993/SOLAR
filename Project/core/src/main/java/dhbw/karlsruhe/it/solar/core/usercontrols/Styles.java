package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class Styles {
    private static final BitmapFont DEFAULT_FONT = new BitmapFont();
    private static final BitmapFont MENUE_FONT = new BitmapFont(Gdx.files.internal("font.fnt"));
    private static final BitmapFont BOLD_FONT = new BitmapFont(Gdx.files.internal("boldedFont.fnt"));
    public static final TextButtonStyle TEXTBUTTON_STYLE = null;
    public static final LabelStyle DEFAULTLABEL_STYLE = new LabelStyle(DEFAULT_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final LabelStyle MENUELABEL_STYLE = new LabelStyle(MENUE_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final LabelStyle MENUELABEL_RED = new LabelStyle(MENUE_FONT, Color.RED);
    public static final LabelStyle MENUELABEL_ORANGE = new LabelStyle(MENUE_FONT, Color.ORANGE);
    public static final LabelStyle MENUELABEL_YELLOW = new LabelStyle(MENUE_FONT, Color.YELLOW);
    public static final LabelStyle MENUELABEL_GREEN = new LabelStyle(MENUE_FONT, Color.GREEN);
    public static final LabelStyle BOLDLABEL_STYLE = new LabelStyle(BOLD_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final TextField.TextFieldStyle DEFAULTTEXTFIELD_STYLE = new TextField.TextFieldStyle(DEFAULT_FONT, new Color(0, .5f, .5f, 1f), null, null, null);

    public static final Skin TOOLTIPSKIN = new Skin(Gdx.files.internal("data/skin/uiskin.json"));

    private Styles() {
    }
}
