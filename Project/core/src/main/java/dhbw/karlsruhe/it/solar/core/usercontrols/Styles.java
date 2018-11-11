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
    private static final BitmapFont MENU_FONT = new BitmapFont(Gdx.files.internal("font.fnt"));
    private static final BitmapFont BOLD_FONT = new BitmapFont(Gdx.files.internal("boldedFont.fnt"));
    public static final TextButtonStyle TEXT_BUTTON_STYLE = null;
    public static final LabelStyle DEFAULT_LABEL_STYLE = new LabelStyle(DEFAULT_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final LabelStyle MENU_LABEL_STYLE = new LabelStyle(MENU_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final LabelStyle MENU_LABEL_RED = new LabelStyle(MENU_FONT, Color.RED);
    public static final LabelStyle MENU_LABEL_ORANGE = new LabelStyle(MENU_FONT, Color.ORANGE);
    public static final LabelStyle MENU_LABEL_YELLOW = new LabelStyle(MENU_FONT, Color.YELLOW);
    public static final LabelStyle MENU_LABEL_GREEN = new LabelStyle(MENU_FONT, Color.GREEN);
    public static final LabelStyle BOLD_LABEL_STYLE = new LabelStyle(BOLD_FONT, new Color(0.8f, 0.8f, 0.8f, 1f));
    public static final TextField.TextFieldStyle DEFAULT_TEXTFIELD_STYLE = new TextField.TextFieldStyle(DEFAULT_FONT, new Color(0, .5f, .5f, 1f), null, null, null);

    public static final Skin TOOLTIP_SKIN = new Skin(Gdx.files.internal("data/skin/uiskin.json"));

    private Styles() {
    }
}
