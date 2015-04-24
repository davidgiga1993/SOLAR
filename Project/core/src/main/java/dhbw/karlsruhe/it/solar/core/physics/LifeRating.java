package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyAtmosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyBiosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyGravity;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyHydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyTemperature;
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

    public String inPercent() {
        return formatValue() + " %";
    }
    
    private String formatValue() {
        return String.format("%.00f", rating*100);
    }

    public void setFuzzyEnums(FuzzyGravity fuzzyGravity, FuzzyTemperature fuzzyTemp, FuzzyAtmosphere fuzzyAtmo, FuzzyHydrosphere fuzzyHydro, FuzzyBiosphere fuzzyBio) {
        this.gravity = fuzzyGravity;
        this.temperature = fuzzyTemp;
        this.atmosphere = fuzzyAtmo;
        this.hydrosphere = fuzzyHydro;
        this.biosphere = fuzzyBio;       
    }

    public FuzzyString atmosphereFuzzy() {
        switch(atmosphere) {
            case NONE:
                return new FuzzyString("None", Styles.MENUELABEL_RED);
            case LETHAL_GAS_CONCENTRATION:
                return new FuzzyString("Lethal", Styles.MENUELABEL_RED);
            case DANGEROUS_GAS_CONCENTRATION:
                return new FuzzyString("Hazardous", Styles.MENUELABEL_ORANGE);
            case NO_OXYGEN:
                return new FuzzyString("No Oxygen", Styles.MENUELABEL_ORANGE);
            case LOW_OXYGEN:
                return new FuzzyString("Trace Oxygen", Styles.MENUELABEL_ORANGE);
            case SLIGHTLY_LOW_OXYGEN:
                return new FuzzyString("Low Oxygen", Styles.MENUELABEL_YELLOW);
            case OPTIMAL_BREATHABLE:
                return new FuzzyString("Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyString("Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public FuzzyString temperatureFuzzy() {
        switch(temperature) {
            case EXTREMELY_COLD:
                return new FuzzyString("Extremely Cold", Styles.MENUELABEL_RED);
            case TOO_COLD:
                return new FuzzyString("Too Cold", Styles.MENUELABEL_ORANGE);
            case COLD:
                return new FuzzyString("Cold", Styles.MENUELABEL_YELLOW);
            case OPTIMAL:
                return new FuzzyString("Optimal", Styles.MENUELABEL_GREEN);
            case HOT:
                return new FuzzyString("Hot", Styles.MENUELABEL_YELLOW);
            case TOO_HOT:
                return new FuzzyString("Too Hot", Styles.MENUELABEL_ORANGE);
            case EXTREMELY_HOT:
                return new FuzzyString("Extremely Hot", Styles.MENUELABEL_RED);
            default:
                return new FuzzyString("Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public FuzzyString hydrosphereFuzzy() {
        switch(hydrosphere) {
            case NONE:
                return new FuzzyString("None", Styles.MENUELABEL_RED);
            case FROZEN:
                return new FuzzyString("Frozen Crust", Styles.MENUELABEL_ORANGE);
            case SUBSURFACE_OCEAN:
                return new FuzzyString("Subsurface Ocean", Styles.MENUELABEL_YELLOW);
            case ARID:
                return new FuzzyString("Too Arid", Styles.MENUELABEL_YELLOW);
            case HUMID:
                return new FuzzyString("Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyString("Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public FuzzyString biosphereFuzzy() {
        switch(biosphere) {
            case LIFELESS:
                return new FuzzyString("None", Styles.MENUELABEL_RED);
            case DANGEROUS_ALIEN:
                return new FuzzyString("Too Dangerous", Styles.MENUELABEL_ORANGE);
            case SPARSE:
                return new FuzzyString("Too Sparse", Styles.MENUELABEL_YELLOW);
            case LUSH:
                return new FuzzyString("Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyString("Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public static LabelStyle determineStyle(float value) {
        if(value > 0.80) {
            return Styles.MENUELABEL_GREEN;
        }
        if(value > 0.3) {
            return Styles.MENUELABEL_YELLOW;
        }
        if(value > 0.1) {
            return Styles.MENUELABEL_ORANGE;
        }
        return Styles.MENUELABEL_RED;
    }

    public LabelStyle getLRStyle() {
        return determineStyle(rating);
    }

    public FuzzyString gravityFuzzy() {
        switch(gravity) {
            case TOO_HIGH:
                return new FuzzyString("Too High", Styles.MENUELABEL_RED);
            case SLIGHTLY_HIGH:
                return new FuzzyString("High", Styles.MENUELABEL_ORANGE);
            case OPTIMAL:
                return new FuzzyString("Optimal", Styles.MENUELABEL_GREEN);
            case SLIGHTLY_LOW:
                return new FuzzyString("Low", Styles.MENUELABEL_YELLOW);
            case TOO_LOW:
                return new FuzzyString("Too Low", Styles.MENUELABEL_ORANGE);
            default:
                return new FuzzyString("Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }
}
