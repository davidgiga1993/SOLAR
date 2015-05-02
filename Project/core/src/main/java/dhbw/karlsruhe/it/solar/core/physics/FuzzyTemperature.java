package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class FuzzyTemperature extends FuzzyValue {
    
    private SurfaceTemperature temperature;
    private Pressure pressure;
    private float fuzzyValueExtremelyHigh;
    private float fuzzyValueExtremelyLow;
    
    public FuzzyTemperature (SurfaceTemperature temperature, Pressure pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.fuzzyValueOptimal = temperatureOptimal();
        this.fuzzyValueTooHigh = temperatureTooHot();
        this.fuzzyValueTooLow = 1 - fuzzyValueOptimal - fuzzyValueTooHigh;
        this.fuzzyValueExtremelyHigh = temperaturesExtremelyHigh();
        this.fuzzyValueExtremelyLow = temperaturesExtremelyLow();
    }
    
    private float temperatureOptimal() {
        return calculateFuzzyValue((temperature.getMeanTemperature().inKelvin() - FuzzyLogic.OPTIMAL_TEMPERATURE.getMeanTemperature().inKelvin()) * 0.5f*(FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar() + pressure.asBar()), 0, (FuzzyLogic.OPTIMAL_TEMPERATURE.getMinimumTemperature().inKelvin() - FuzzyLogic.OPTIMAL_TEMPERATURE.getMeanTemperature().inKelvin()) * FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar(), (FuzzyLogic.OPTIMAL_TEMPERATURE.getMaximumTemperature().inKelvin() - FuzzyLogic.OPTIMAL_TEMPERATURE.getMeanTemperature().inKelvin()) * FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar());
    }
    
    private float temperatureTooHot() {
        return calculateHighFuzzyValue((temperature.getMeanTemperature().inKelvin() - FuzzyLogic.OPTIMAL_TEMPERATURE.getMeanTemperature().inKelvin()) * 0.5f*(FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar() + pressure.asBar()), (FuzzyLogic.OPTIMAL_TEMPERATURE.getMaximumTemperature().inKelvin() - FuzzyLogic.OPTIMAL_TEMPERATURE.getMeanTemperature().inKelvin()) * FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar(), 0);
    }
      
    private float temperaturesExtremelyHigh() {
        return calculateHighFuzzyValue((temperature.getMaximumTemperature().inKelvin()-FuzzyLogic.OPTIMAL_TEMPERATURE.getMaximumTemperature().inKelvin()) * 0.5f*(FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar() + pressure.asBar()), FuzzyLogic.OPTIMAL_TEMPERATURE.getMaximumTemperature().inKelvin()*FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar(), 0);
    }
    
    private float temperaturesExtremelyLow() {
        return calculateLowFuzzyValue((temperature.getMinimumTemperature().inKelvin()-FuzzyLogic.OPTIMAL_TEMPERATURE.getMinimumTemperature().inKelvin()) * 0.5f*(FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar() + pressure.asBar()), -FuzzyLogic.OPTIMAL_TEMPERATURE.getMinimumTemperature().inKelvin()*FuzzyLogic.OPTIMAL_SURFACE_PRESSURE.asBar(), 0);            
    }
   
    public float extreme() {
        return fuzzyValueExtremelyHigh + fuzzyValueExtremelyLow;
    }

    @Override
    public FuzzyInformation displayFuzzyState() {
        if(fuzzyValueExtremelyLow > 0.5) {
            return new FuzzyInformation(tempValueDisplay(), "Extreme Cold", Styles.MENUELABEL_RED);
        }
        if(fuzzyValueExtremelyHigh > 0.5) {
            return new FuzzyInformation(tempValueDisplay(), "Extreme Heat", Styles.MENUELABEL_RED);
        }
        if(fuzzyValueOptimal > 0.9) {
            return new FuzzyInformation(tempValueDisplay(), "Optimal", Styles.MENUELABEL_GREEN);
        }
        if(fuzzyValueTooLow > 0.75) {
            return new FuzzyInformation(tempValueDisplay(), "Too Cold", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueTooHigh > 0.75) {
            return new FuzzyInformation(tempValueDisplay(), "Too Hot", Styles.MENUELABEL_ORANGE);
        }
        if(fuzzyValueTooHigh > 0.1) {
            return new FuzzyInformation(tempValueDisplay(), "Hot", Styles.MENUELABEL_YELLOW);
        }
        if(fuzzyValueTooLow > 0.1) {
            return new FuzzyInformation(tempValueDisplay(), "Cold", Styles.MENUELABEL_YELLOW);
        }
        return new FuzzyInformation(tempValueDisplay(), "Unknown Anomaly", Styles.MENUELABEL_RED);
    }
    

    private String tempValueDisplay() {
        return temperature.toString();
//        return temperature.toString() + " ( " + formatValue(fuzzyValueExtremelyLow) + " / " + formatValue(fuzzyValueTooLow) + " / " + formatValue(fuzzyValueOptimal) + " / " + formatValue(fuzzyValueTooHigh) + " / " + formatValue(fuzzyValueExtremelyHigh) + " )";
    }
}
