package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class LifeRating {

    private float rating;
    private FuzzyGravity gravity;
    private FuzzyTemperature temperature;
    private FuzzyAtmosphere atmosphere;
    private FuzzyHydrosphere hydrosphere;
    private FuzzyBiosphere biosphere;

    public LifeRating(float rating) {
        this.rating = rating;
    }

    public void setFuzzyValues(FuzzyGravity fuzzyGravity, FuzzyTemperature fuzzyTemp, FuzzyAtmosphere fuzzyAtmo, FuzzyHydrosphere fuzzyHydro, FuzzyBiosphere fuzzyBio) {
        this.gravity = fuzzyGravity;
        this.temperature = fuzzyTemp;
        this.atmosphere = fuzzyAtmo;
        this.hydrosphere = fuzzyHydro;
        this.biosphere = fuzzyBio;
    }

    public FuzzyInformation gravityFuzzy() {
        return gravity.displayFuzzyState();
    }

    public FuzzyInformation temperatureFuzzy() {
        return temperature.displayFuzzyState();
    }

    public FuzzyInformation atmosphereFuzzy() {
        return atmosphere.displayFuzzyState();
    }

    public FuzzyInformation hydrosphereFuzzy() {
        return hydrosphere.displayFuzzyState();
    }

    public FuzzyInformation biosphereFuzzy() {
        return biosphere.displayFuzzyState();
    }

    public FuzzyInformation lifeRatingFuzzy(AstronomicalBody astronomicalBody) {
        return new FuzzyInformation("", inPercent(), determineStyle());
    }

    private String inPercent() {
        return formatValue() + " %";
    }

    private String formatValue() {
        return String.format("%.00f", rating * 100);
    }

    private LabelStyle determineStyle() {
        if (rating > 0.80) {
            return Styles.MENU_LABEL_GREEN;
        }
        if (rating > 0.3) {
            return Styles.MENU_LABEL_YELLOW;
        }
        if (rating > 0.1) {
            return Styles.MENU_LABEL_ORANGE;
        }
        return Styles.MENU_LABEL_RED;
    }

    public float getNumber() {
        return rating;
    }
}
