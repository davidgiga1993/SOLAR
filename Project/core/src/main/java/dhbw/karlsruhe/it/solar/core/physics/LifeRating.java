package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyAtmosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyBiosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyGravity;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyHydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyTemperature;

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

    public String atmosphereFuzzy() {
        switch(atmosphere) {
            case NONE:
                return "None";
            case LETHAL_GAS_CONCENTRATION:
                return "Lethal";
            case DANGEROUS_GAS_CONCENTRATION:
                return "Hazardous";
            case LOW_OXYGEN:
                return "Oxygen Deficient";
            case SLIGHTLY_LOW_OXYGEN:
                return "Low on Oxygen";
            case OPTIMAL_BREATHABLE:
                return "Optimal";
            default:
                return "Anomaly: Unknown";
        }
    }
}
