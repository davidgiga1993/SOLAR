package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Contains string and label style font to be used for displaying fuzzy logic information.
 *
 * @author Andi
 * created 2015-04-24
 */
public class FuzzyInformation {

    private String physicalValues;
    private String information;
    private LabelStyle font;

    public FuzzyInformation(String value, String information, LabelStyle font) {
        this.physicalValues = value;
        this.information = information;
        this.font = font;
    }

    public String getPhysicalValue() {
        return physicalValues;
    }

    public String getFuzzyInfo() {
        return information;
    }

    public LabelStyle getStyle() {
        return font;
    }

}
