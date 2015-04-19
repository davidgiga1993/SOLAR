package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceGravity.GravUnit;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

public class FuzzyLogic {
       
    private static final float LIFERATING_NEUTRAL_VALUE = 0.1f;
    private static final float LIFERATING_POSITIVE_WEIGHT = 0.9f;
    private static final SurfaceGravity OPTIMAL_GRAVITY = new SurfaceGravity(1f,GravUnit.G);
    private static final Hydrosphere OPTIMAL_HYDROSPHERE = new Hydrosphere(0.75f, true);
    private static final SurfaceTemperatures OPTIMAL_TEMPERATURES = new SurfaceTemperatures(new Temperature(248f,TempUnit.KELVIN),new Temperature(288f,TempUnit.KELVIN),new Temperature(328f,TempUnit.KELVIN));
    private static final Pressure OPTIMAL_SURFACE_PRESSURE = new Pressure(1f, PressureUnit.STANDARDATMOSPHERE);
    private static final Pressure OPTIMAL_OXYGEN_PARTIAL_PRESSURE = new Pressure(0.22f, PressureUnit.BAR);
    
    private SurfaceGravity gravity;
    private Atmosphere atmosphere;
    private SurfaceTemperatures temperatures;
    private Hydrosphere hydrosphere;
    private Biosphere biosphere;
    
    private FuzzyGravity fuzzyGravity;
    private float gravityOptimal;
    private float gravityTooHigh;
    private FuzzyAtmosphere fuzzyAtmo;
    private float atmosphereBreathable;
    private FuzzyTemperature fuzzyTemp;
    private float temperatureOptimal;
    private FuzzyHydrosphere fuzzyHydro;
    private float hydrosphereOptimal;
    private FuzzyBiosphere fuzzyBio;
    private float biosphereOptimal;
 
    public FuzzyLogic(SurfaceGravity gravity, Atmosphere atmosphere,
            SurfaceTemperatures temperatures, Hydrosphere hydro, Biosphere bio) {
        this.gravity = gravity;
        this.atmosphere = atmosphere;
        this.temperatures = temperatures;
        this.hydrosphere = hydro;
        this.biosphere = bio;
    }

    public LifeRating calculateLifeRating() {
        
        calculateFuzzyGravity();
        calculateFuzzyTemperature();
        calculateFuzzyAtmosphere();
        calculateFuzzyHydrosphere();
        calculateFuzzyBiosphere();
        
        LifeRating rating = new LifeRating(lifeRatingFormula());
        rating.setFuzzyEnums(fuzzyGravity, fuzzyTemp, fuzzyAtmo, fuzzyHydro, fuzzyBio);
        
        return rating;
    }

    private float lifeRatingFormula() {
        return (1 - negativeLRInfluences()) * ( LIFERATING_NEUTRAL_VALUE + LIFERATING_POSITIVE_WEIGHT*positiveLRInfluences());
    }
    
    private void calculateFuzzyBiosphere() {
        if(null==biosphere) {
            fuzzyBio = FuzzyBiosphere.LIFELESS;
            biosphereOptimal = 0;
            return;
        }
        biosphereOptimal = biosphereOptimal();

        if(biosphereOptimal < 0.5) {
            fuzzyBio = FuzzyBiosphere.SPARSE;
        }
        if(biosphereOptimal < 0) {
            fuzzyBio = FuzzyBiosphere.DANGEROUS_ALIEN;
        }
        if(biosphereOptimal > 0.5) {
            fuzzyBio = FuzzyBiosphere.LUSH;
        }
    }

    private void calculateFuzzyHydrosphere() {
        if(null==hydrosphere) {
            fuzzyHydro = FuzzyHydrosphere.NONE;
            hydrosphereOptimal = 0;
            return;
        }
        hydrosphereOptimal = hydrosphereOptimal();
        
        if(hydrosphereOptimal < 0.5) {
            fuzzyHydro = FuzzyHydrosphere.ARID;            
        }
        if(hydrosphereOptimal > 0.5) {
            fuzzyHydro = FuzzyHydrosphere.HUMID;            
        }
        
    }

    private void calculateFuzzyTemperature() {
        float tempTooCold = temperatureTooCold();
        temperatureOptimal = temperatureOptimal();
        float tempTooHot = temperatureTooHot();
        
        if(tempTooCold > 0.05) {
            fuzzyTemp = FuzzyTemperature.COLD;
        }
        if(tempTooCold > 0.75) {
            fuzzyTemp = FuzzyTemperature.TOO_COLD;
        }
        if(tempTooCold == 1) {
            fuzzyTemp = FuzzyTemperature.EXTREMELY_COLD;
        }
        if(temperatureOptimal > 0.95) {
            fuzzyTemp = FuzzyTemperature.OPTIMAL;
        }
        if(tempTooHot > 0.05) {
            fuzzyTemp = FuzzyTemperature.HOT;
        }
        if(tempTooHot > 0.75) {
            fuzzyTemp = FuzzyTemperature.TOO_HOT;
        }
        if(tempTooHot == 1) {
            fuzzyTemp = FuzzyTemperature.EXTREMELY_HOT;
        }
    }

    private void calculateFuzzyAtmosphere() {
        if(null == atmosphere) {
            fuzzyAtmo = FuzzyAtmosphere.NONE;
            atmosphereBreathable = 0;
            return;
        }
        float oxygenOptimal = oxygenConcentrationOptimal();
        float toxicGas = toxicGasConcentration();
        float oxygenTooLow = oxygenConcentrationLow();
        
        atmosphereBreathable = (1-toxicGas)*oxygenOptimal;
        
        if(oxygenTooLow > 0.05) {
            fuzzyAtmo = FuzzyAtmosphere.SLIGHTLY_LOW_OXYGEN;
        }
        if(oxygenTooLow > 0.75) {
            fuzzyAtmo = FuzzyAtmosphere.LOW_OXYGEN;
        }
        if(oxygenOptimal > 0.95) {
            fuzzyAtmo = FuzzyAtmosphere.OPTIMAL_BREATHABLE;
        }
        if(toxicGas > 0.05) {
            fuzzyAtmo = FuzzyAtmosphere.DANGEROUS_GAS_CONCENTRATION;
        }
        if(toxicGas > 0.75) {
            fuzzyAtmo = FuzzyAtmosphere.LETHAL_GAS_CONCENTRATION;
        }       
    }

    private void calculateFuzzyGravity() {
        gravityOptimal = gravityOptimal();
        gravityTooHigh = gravityTooHigh();
        float gravityLow = 1 - (gravityOptimal + gravityTooHigh);
        
        if(gravityLow > 0.05) {
            fuzzyGravity = FuzzyGravity.SLIGHTLY_LOW;
        }
        if(gravityLow > 0.75) {
            fuzzyGravity = FuzzyGravity.TOO_LOW;
        }
        if(gravityOptimal > 0.95) {
            fuzzyGravity = FuzzyGravity.OPTIMAL;
        }
        if(gravityTooHigh > 0.05) {
            fuzzyGravity = FuzzyGravity.SLIGHTLY_HIGH;
        }
        if(gravityTooHigh > 0.75) {
            fuzzyGravity = FuzzyGravity.TOO_HIGH;
        }
    }
    
    private float positiveLRInfluences() {
        return 0.2f*( gravityOptimal + atmosphereBreathable + temperatureOptimal + hydrosphereOptimal + biosphereOptimal );
    }
    
    private float negativeLRInfluences() {
        float value = gravityTooHigh + temperaturesExtreme();
        if(value < 1)
            return value;
        return 1;
    }

    private float temperaturesExtreme() {
        float tpProductMinimum = (temperatures.getMinimumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float tpProductMaximum = (temperatures.getMaximumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float minimumDeviation = OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()*OPTIMAL_SURFACE_PRESSURE.asBar();
        float maximumDeviation = OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()*OPTIMAL_SURFACE_PRESSURE.asBar();
        
        if(tpProductMinimum < -2*minimumDeviation) {
            return 1;
        }
        if(tpProductMinimum < -minimumDeviation) {
            return -(tpProductMinimum + minimumDeviation)/minimumDeviation;
        }
        if(tpProductMaximum > maximumDeviation) {
            return (tpProductMaximum - maximumDeviation)/maximumDeviation;
        }
        if(tpProductMaximum > 2*maximumDeviation) {
            return 1;
        }
        return 0;
    }
    
        private float biosphereOptimal() {
            return biosphere.getUseableBioCover();            
        }

    private float hydrosphereOptimal() {
        if( hydrosphere.getWaterCover() <= OPTIMAL_HYDROSPHERE.getWaterCover()) {
            return hydrosphere.getWaterCover() / OPTIMAL_HYDROSPHERE.getWaterCover();
        }
        return 1f;
    }
    
    private float temperatureOptimal() {
        float tpProduct = (temperatures.getMeanTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMeanTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float maximumDeviation = OPTIMAL_TEMPERATURES.getMeanTemperature().inKelvin()*OPTIMAL_SURFACE_PRESSURE.asBar();
        
        if(tpProduct <= -maximumDeviation) {
            return 0;
        }
        if(tpProduct <= 0) {
            return 1+tpProduct/maximumDeviation;
        }
        if(tpProduct < maximumDeviation) {
            return 1-tpProduct/maximumDeviation;
        }
        return 0;
    }
    
    private float temperatureTooCold() {
        float tpProduct = (temperatures.getMinimumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float maximumDeviation = OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()*OPTIMAL_SURFACE_PRESSURE.asBar();
        
        if(tpProduct < -maximumDeviation) {
            return 1;
        }
        if(tpProduct < 0) {
            return 1 + tpProduct/maximumDeviation;
        }
        return 0;
    }
    
    private float temperatureTooHot() {
        float tpProduct = (temperatures.getMaximumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float maximumDeviation = OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()*OPTIMAL_SURFACE_PRESSURE.asBar();
        
        if(tpProduct > maximumDeviation) {
            return 1;
        }
        if(tpProduct > 0) {
            return 1 - tpProduct/maximumDeviation;
        }
        return 0;      
    }

    private Pressure getSurfacePressure() {
        if(null!=atmosphere) {
            return atmosphere.getPressure();            
        }
        return new Pressure(0f,PressureUnit.BAR);
    }

    private float oxygenConcentrationLow() {
        if(atmosphere.getOxygenPartialPressure().asBar() < OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar()) {
            return 1 - atmosphere.getOxygenPartialPressure().asBar()/OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar();
        }
        return 0;
    }
    
    private float oxygenConcentrationOptimal() {
        if(atmosphere.getOxygenPartialPressure().asBar() < OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar()) {
            return atmosphere.getOxygenPartialPressure().asBar()/OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar();
        }
        if(atmosphere.getOxygenPartialPressure().asBar() < 2*OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar()) {
            return 1-(atmosphere.getOxygenPartialPressure().asBar()-OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar())/OPTIMAL_OXYGEN_PARTIAL_PRESSURE.asBar();
        }
        return 0;
    }

    private float toxicGasConcentration() {
        float toxicLevel = 0;
        for (AtmosphericGas gas : atmosphere.getListOfAtmosphericGases()) {
            toxicLevel = replaceToxicGasConcentrationIfHigher(toxicLevel, gas);
        }
        return toxicLevel;
    }

    private float replaceToxicGasConcentrationIfHigher(float toxicLevel, AtmosphericGas gas) {
        float currentLevel;
        currentLevel = toxicGasConcentration(gas);
        if(currentLevel > toxicLevel) {
            toxicLevel = currentLevel;
        }
        return toxicLevel;
    }

    private float toxicGasConcentration(AtmosphericGas gas) {
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
        if(gas.partialPressure(atmosphere.getPressure()).asBar() < gas.getDangerousThreshold().asBar()) {
            return true;
        }
        return false;
    }
    
    private boolean belowLethalThresholdOf(AtmosphericGas gas) {
        if(gas.partialPressure(atmosphere.getPressure()).asBar() < gas.getLethalThreshold().asBar()) {
            return true;
        }
        return false;
    }

    public float gravityOptimal() {
        if (gravity.inG() <= OPTIMAL_GRAVITY.inG()) {
            return gravity.inG()/OPTIMAL_GRAVITY.inG();
        }
        if (gravity.inG() < 3*OPTIMAL_GRAVITY.inG()) {
            return ( 1 - (gravity.inG() - OPTIMAL_GRAVITY.inG())/(2*OPTIMAL_GRAVITY.inG()));
        }
        return 0;
    }
    
    public float gravityTooHigh() {
        if (gravity.inG() < OPTIMAL_GRAVITY.inG()) {
            return 0;
        }
        if (gravity.inG() < 3*OPTIMAL_GRAVITY.inG()) {
            return (gravity.inG()-OPTIMAL_GRAVITY.inG())/(2*OPTIMAL_GRAVITY.inG());
        }
        return 1;
    }
    
    public enum FuzzyGravity {
        TOO_LOW,
        SLIGHTLY_LOW,
        OPTIMAL,
        SLIGHTLY_HIGH,
        TOO_HIGH
    }
    
    public enum FuzzyAtmosphere {
        NONE,
        LETHAL_GAS_CONCENTRATION,
        DANGEROUS_GAS_CONCENTRATION,
        LOW_OXYGEN,
        SLIGHTLY_LOW_OXYGEN,
        OPTIMAL_BREATHABLE,
    }
    
    public enum FuzzyTemperature {
        EXTREMELY_COLD,
        TOO_COLD,
        COLD,
        OPTIMAL,
        HOT,
        TOO_HOT,
        EXTREMELY_HOT
    }
    
    public enum FuzzyHydrosphere {
        NONE,
        ARID,
        HUMID
    }
    
    public enum FuzzyBiosphere {
        DANGEROUS_ALIEN,
        LIFELESS,
        SPARSE,
        LUSH
    }
}
