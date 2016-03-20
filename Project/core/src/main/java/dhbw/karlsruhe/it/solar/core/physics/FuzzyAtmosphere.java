package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class FuzzyAtmosphere extends FuzzyValue {
    
    private Atmosphere atmosphere;
    private AtmosphericGas mostToxicGas;

    public FuzzyAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
        this.fuzzyValueOptimal = atmosphereOxygenOptimal();
        this.fuzzyValueTooHigh = atmosphereToxic();
        this.fuzzyValueTooLow = atmosphereOxygenLow();
    }
    
    private float atmosphereOxygenOptimal() {
        if(null==atmosphere) {            
            return 0;
        }
        return calculateFuzzyValue(atmosphere.getOxygenPartialPressure().asBar(), FuzzyLogic.OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar(), 0, AtmosphericGas.getOxygenToxicPartialPressure());
    }
    
    private float atmosphereOxygenLow() {
        if(null==atmosphere) {            
            return 0;
        }
        return calculateLowFuzzyValue(atmosphere.getOxygenPartialPressure().asBar(), 0, FuzzyLogic.OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar());
    }
    
    private float atmosphereToxic() {
        if(null==atmosphere) {            
            return 0;
        }
        float toxicLevel = 0;
        for (AtmosphericGas gas : atmosphere.getListOfAtmosphericGases()) {
            toxicLevel = replaceToxicGasConcentrationIfHigher(toxicLevel, gas);
            mostToxicGas = gas;
        }
        return toxicLevel;
    }

    private float replaceToxicGasConcentrationIfHigher(float toxicLevel, AtmosphericGas gas) {
        float currentLevel;
        currentLevel = fuzzyToxicGasConcentration(gas);
        if(currentLevel > toxicLevel) {
            toxicLevel = currentLevel;
        }
        return toxicLevel;
    }

    private float fuzzyToxicGasConcentration(AtmosphericGas gas) {
        if(belowDangerousThesholdOf(gas)) {
            return 0;
        }
        if(belowLethalThresholdOf(gas)) {
            return (gas.partialPressure(atmosphere.getPressure()).asBar() - gas.getDangerousThreshold().asBar())/gas.getLethalThreshold().asBar();
        }
        return 1f;
    }

    private boolean belowDangerousThesholdOf(AtmosphericGas gas) {
        if( null == gas.getDangerousThreshold()) {
            return true;
        }
        return gas.partialPressure(atmosphere.getPressure()).asBar() < gas.getDangerousThreshold().asBar();
    }
    
    private boolean belowLethalThresholdOf(AtmosphericGas gas) {
        return gas.partialPressure(atmosphere.getPressure()).asBar() < gas.getLethalThreshold().asBar();
    }

    private String getLethalGas() {
        return mostToxicGas.getShortName() + " > " + formatValue(mostToxicGas.getLethalThreshold().asPascal()) + " Pa";
    }
    
    private String getDangerousGas() {
        return mostToxicGas.getShortName() + " > " + formatValue(mostToxicGas.getDangerousThreshold().asPascal()) + " Pa";
    }
    
    @Override
    public float optimal() {
        return (1-fuzzyValueTooHigh)*fuzzyValueOptimal;
    }

    @Override
    public FuzzyInformation displayFuzzyState() {
        
        if(null == atmosphere) {
            return new FuzzyInformation(atmoValueDisplay(), "None", Styles.MENUELABEL_RED);
        }
        if(null == atmosphere.getPressure()) {
            return new FuzzyInformation(atmoValueDisplay(), "Gas Giant", Styles.MENUELABEL_RED);   
        }       
        if(atmosphere.getPressure().asPascal() < 1f) {
            return new FuzzyInformation(atmoValueDisplay(), "Only Traces", Styles.MENUELABEL_RED); 
        }        
        if(fuzzyValueTooHigh > 0.75) {
            return new FuzzyInformation(atmoValueDisplay(), "Lethal", Styles.MENUELABEL_RED);
        }  
        if(fuzzyValueTooHigh > 0.05) {
            return new FuzzyInformation(atmoValueDisplay(), "Hazardous", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueOptimal > 0.95) {
            return new FuzzyInformation(atmoValueDisplay(), "Optimal", Styles.MENUELABEL_GREEN);
        }
        if(fuzzyValueTooLow == 1f) {
            return new FuzzyInformation(atmoValueDisplay(), "No O2", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueTooLow > 0.75) {
            return new FuzzyInformation(atmoValueDisplay(), "Trace O2", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueTooLow > 0.05) {
            return new FuzzyInformation(atmoValueDisplay(), "Low on O2", Styles.MENUELABEL_YELLOW);
        }
        return new FuzzyInformation(atmoValueDisplay(), "Unknown Anomaly", Styles.MENUELABEL_RED);
    }
    
    private String atmoValueDisplay() {
        return getAtmoValue();
//        return getAtmoValue() + " ( " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " )";
    }
       
    private String getAtmoValue() {
        if(null == atmosphere) {
            return "";
        }
        if(null == atmosphere.getPressure()) {
            return "";
        }
        if(fuzzyValueTooHigh > 0.75) {
            return getLethalGas();
        }
        if(fuzzyValueTooHigh > 0.05) {
            return getDangerousGas();
        }
        if(fuzzyValueTooLow == 1f) {
            return "";
        }
        return atmosphere.getOxygenPartialPressure().toString() + " O2";
    }
}
