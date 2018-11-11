package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class FuzzyBiosphere extends FuzzyValue {

    private Biosphere biosphere;

    public FuzzyBiosphere(Biosphere biosphere) {
        this.biosphere = biosphere;
        this.fuzzyValueOptimal = biosphereOptimal();
        this.fuzzyValueTooHigh = 0;
        this.fuzzyValueTooLow = 1 - fuzzyValueOptimal;
    }

    private String getBioValue() {
        if (null != biosphere) {
            return biosphere.toString();
        }
        return "";
    }

    private float biosphereOptimal() {
        if (null == biosphere) {
            return 0;
        }
        return biosphere.getUsableBioCover();
    }

    @Override
    public FuzzyInformation displayFuzzyState() {
        if (null == biosphere) {
            return new FuzzyInformation(bioValueDisplay(), "None", Styles.MENU_LABEL_RED);
        }
        if (fuzzyValueOptimal > 0.9) {
            return new FuzzyInformation(bioValueDisplay(), "Optimal", Styles.MENU_LABEL_GREEN);
        }
        if (fuzzyValueOptimal < 0) {
            return new FuzzyInformation(bioValueDisplay(), "Too Dangerous", Styles.MENU_LABEL_ORANGE);
        }
        if (fuzzyValueTooLow > 0.1) {
            return new FuzzyInformation(bioValueDisplay(), "Sparse", Styles.MENU_LABEL_YELLOW);
        }
        return new FuzzyInformation(bioValueDisplay(), "Unknown Anomaly", Styles.MENU_LABEL_RED);
    }

    private String bioValueDisplay() {
        return getBioValue();
//        return getBioValue() + " ( " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " )";
    }
}
