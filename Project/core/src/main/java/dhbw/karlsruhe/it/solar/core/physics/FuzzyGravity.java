package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * @author Andi
 * 2015-05-01
 */
public class FuzzyGravity extends FuzzyValue {

    private SurfaceGravity gravity;

    public FuzzyGravity(SurfaceGravity gravity) {
        this.gravity = gravity;
        this.fuzzyValueOptimal = gravityOptimal();
        this.fuzzyValueTooHigh = gravityTooHigh();
        this.fuzzyValueTooLow = 1 - fuzzyValueOptimal - fuzzyValueTooHigh;
    }

    private float gravityOptimal() {
        return calculateFuzzyValue(gravity.inG(), FuzzyLogic.OPTIMAL_GRAVITY.inG(), 0, 3 * FuzzyLogic.OPTIMAL_GRAVITY.inG());
    }

    private float gravityTooHigh() {
        return calculateHighFuzzyValue(gravity.inG(), 3 * FuzzyLogic.OPTIMAL_GRAVITY.inG(), FuzzyLogic.OPTIMAL_GRAVITY.inG());
    }

    @Override
    public FuzzyInformation displayFuzzyState() {

        if (fuzzyValueOptimal > 0.95) {
            return new FuzzyInformation(gravityValueDisplay(), "Optimal", Styles.MENU_LABEL_GREEN);
        }
        if (fuzzyValueTooLow > 0.75) {
            return new FuzzyInformation(gravityValueDisplay(), "Too Low", Styles.MENU_LABEL_ORANGE);
        }
        if (fuzzyValueTooHigh > 0.75) {
            return new FuzzyInformation(gravityValueDisplay(), "Too High", Styles.MENU_LABEL_RED);
        }
        if (fuzzyValueTooLow > 0.05) {
            return new FuzzyInformation(gravityValueDisplay(), "Low", Styles.MENU_LABEL_YELLOW);
        }
        if (fuzzyValueTooHigh > 0.05) {
            return new FuzzyInformation(gravityValueDisplay(), "High", Styles.MENU_LABEL_ORANGE);
        }
        return new FuzzyInformation(gravityValueDisplay(), "Unknown Anomaly", Styles.MENU_LABEL_RED);
    }

    private String gravityValueDisplay() {
        return gravity.toString();
//        return gravity.toString() + " ( " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " )";
    }
}
