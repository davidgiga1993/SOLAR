package dhbw.karlsruhe.it.solar.core.physics;

/**
 * @author Andi
 * 2015-05-01
 */
abstract class FuzzyValue {

    float fuzzyValueTooLow;
    float fuzzyValueOptimal;
    float fuzzyValueTooHigh;

    float calculateFuzzyValue(float value, float optimalValue, float minimumThreshold, float maximumThreshold) {
        if (value < minimumThreshold) {
            return 0;
        }
        if (value < optimalValue) {
            return (value - minimumThreshold) / (optimalValue - minimumThreshold);
        }
        if (value < maximumThreshold) {
            return 1 - (value - optimalValue) / (maximumThreshold - optimalValue);
        }
        return 0;
    }

    float calculateHighFuzzyValue(float value, float optimalValue, float minimumThreshold) {
        if (value < minimumThreshold) {
            return 0;
        }
        if (value < optimalValue) {
            return (value - minimumThreshold) / (optimalValue - minimumThreshold);
        }
        return 1;
    }

    float calculateLowFuzzyValue(float value, float optimalValue, float maximumThreshold) {
        if (value < optimalValue) {
            return 1;
        }
        if (value < maximumThreshold) {
            return 1 - (value - optimalValue) / (maximumThreshold - optimalValue);
        }
        return 0;
    }

    public abstract FuzzyInformation displayFuzzyState();

    public float tooLow() {
        return fuzzyValueTooLow;
    }

    public float tooHigh() {
        return fuzzyValueTooHigh;
    }

    public float optimal() {
        return fuzzyValueOptimal;
    }

    String formatValue(float value) {
        return String.format("%.02f", value);
    }
}
