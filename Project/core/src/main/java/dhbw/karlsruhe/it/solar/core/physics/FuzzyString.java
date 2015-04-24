package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Contains string and labelstyle font to be used for displaying fuzzy logic information.
 * @author Andi
 * created 2015-04-24
 */
public class FuzzyString {
    
    private String information;
    private LabelStyle font;
    
    public FuzzyString(String information, LabelStyle font) {
        this.information = information;
        this.font = font;
    }

    public String getInfo() {
        return information;
    }

    public LabelStyle getStyle() {
        return font;
    }

}
