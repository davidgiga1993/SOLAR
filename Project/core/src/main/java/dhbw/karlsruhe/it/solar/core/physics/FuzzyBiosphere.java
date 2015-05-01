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
        if(null!=biosphere) {            
            return biosphere.toString();
        }
        return "";
    }
    
    private float biosphereOptimal() {
        if(null==biosphere) {            
            return 0;
        }
        return biosphere.getUseableBioCover();           
    } 
    
    @Override
    public FuzzyInformation displayFuzzyState() {
        if(null==biosphere) {
            return new FuzzyInformation(bioValueDisplay(), "None", Styles.MENUELABEL_RED);
        }
        if(fuzzyValueOptimal > 0.9) {
            return new FuzzyInformation(bioValueDisplay(), "Optimal", Styles.MENUELABEL_GREEN);
        }
        if(fuzzyValueOptimal < 0) {
            return new FuzzyInformation(bioValueDisplay(), "Too Dangerous", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueTooLow > 0.1) {
            return new FuzzyInformation(bioValueDisplay(), "Sparse", Styles.MENUELABEL_YELLOW);
        }
        return new FuzzyInformation(bioValueDisplay(), "Unknown Anomaly", Styles.MENUELABEL_RED);
    }
    
    private String bioValueDisplay() {
        return getBioValue() + " ( " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " )";
    }
}
