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
            case NO_OXYGEN:
                return "No Oxygen";
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

    public String temperatureFuzzy() {
        switch(temperature) {
            case EXTREMELY_COLD:
                return "Extremely Cold";
            case TOO_COLD:
                return "Too Cold";
            case COLD:
                return "Cold";
            case OPTIMAL:
                return "Optimal";
            case HOT:
                return "Hot";
            case TOO_HOT:
                return "Too Hot";
            case EXTREMELY_HOT:
                return "Extremely Hot";
            default:
                return "Anomaly: Unknown";
        }
    }

    public CharSequence hydrosphereFuzzy() {
        switch(hydrosphere) {
            case NONE:
                return "No Water";
            case ARID:
                return "Too Arid";
            case HUMID:
                return "Optimal";
            default:
                return "Anomaly: Unknown";
        }
    }

    public CharSequence biosphereFuzzy() {
        switch(biosphere) {
            case DANGEROUS_ALIEN:
                return "Too Dangerous";
            case LIFELESS:
                return "Lifeless";
            case SPARSE:
                return "Too Sparse";
            case LUSH:
                return "Optimal";
            default:
                return "Anomaly: Unknown";
        }
    }
}
