package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class FuzzyHydrosphere extends FuzzyValue {
    
    private Hydrosphere hydrosphere;

    public FuzzyHydrosphere(Hydrosphere hydrosphere) {
        this.hydrosphere = hydrosphere;
        this.fuzzyValueOptimal = hydrosphereOptimal();
        this.fuzzyValueTooHigh = 0;
        this.fuzzyValueTooLow = 1 - fuzzyValueOptimal;
    }
    
    private float hydrosphereOptimal() {
        if(null==hydrosphere) {            
            return 0;
        }
        if(hydrosphere.hasLiquidWater()) {
            return calculateHighFuzzyValue(hydrosphere.getWaterCover() + hydrosphere.getIceCover()/2, FuzzyLogic.OPTIMAL_HYDROSPHERE.getWaterCover() + FuzzyLogic.OPTIMAL_HYDROSPHERE.getIceCover()/2, 0);            
        }
        return calculateHighFuzzyValue(hydrosphere.getIceCover()/4, FuzzyLogic.OPTIMAL_HYDROSPHERE.getWaterCover() + FuzzyLogic.OPTIMAL_HYDROSPHERE.getIceCover()/2, 0);
    }
    
    private String getHydroValue() {
        if(null!=hydrosphere) {            
            return hydrosphere.toString();
        }
        return "";
    }

    @Override
    public FuzzyInformation displayFuzzyState() {
        if(null==hydrosphere) {
            return new FuzzyInformation(hydroValueDisplay(), "None", Styles.MENUELABEL_RED);
        }
        if(fuzzyValueOptimal > 0.9) {
            return new FuzzyInformation(hydroValueDisplay(), "Optimal", Styles.MENUELABEL_GREEN);
        }
        if(hydrosphere.getSubsurfaceOcean()) {
            return new FuzzyInformation(hydroValueDisplay(), "SS Ocean", Styles.MENUELABEL_YELLOW);         
        }
        if(hydrosphere.hasLiquidWater() && fuzzyValueOptimal < 0.9) {
            return new FuzzyInformation(hydroValueDisplay(), "Too Arid", Styles.MENUELABEL_YELLOW);          
        }
        if(!hydrosphere.hasLiquidWater()) {
            return new FuzzyInformation(hydroValueDisplay(), "Frozen", Styles.MENUELABEL_ORANGE);         
        }
        return new FuzzyInformation(hydroValueDisplay(), "Unknown Anomaly", Styles.MENUELABEL_RED);
    }
    
    private String hydroValueDisplay() {
        return getHydroValue();
//        return getHydroValue() + " ( " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " )";
    }
}
