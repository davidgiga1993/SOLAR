package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceGravity.GravUnit;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

public class FuzzyLogic {
       
    private static final SurfaceGravity OPTIMAL_GRAVITY = new SurfaceGravity(1f,GravUnit.G);
    private static final Hydrosphere OPTIMAL_HYDROSPHERE = new Hydrosphere(0.75f, true);
    private static final SurfaceTemperatures OPTIMAL_TEMPERATURES = new SurfaceTemperatures(new Temperature(258f,TempUnit.KELVIN),new Temperature(288f,TempUnit.KELVIN),new Temperature(318f,TempUnit.KELVIN));
    private static final Pressure OPTIMAL_SURFACE_PRESSURE = new Pressure(1f, PressureUnit.STANDARDATMOSPHERE);
    private static final Pressure OPTIMAL_OXYGEN_PARTIAL_PRESSURE = new Pressure(0.23f, PressureUnit.BAR);
    private static final float MINIMUM_TEMPERATURE_THESHOLD = 145f;
    private static final float MAXIMUM_TEMPERATURE_THESHOLD = 155f;
    private static final float LR_EXTREME_TEMP_WEIGHT = 2f;
    private static final float LR_GRAV_WEIGHT = 1f;
    private static final float LR_TEMP_WEIGHT = 1f;
    private static final float LR_BIO_WEIGHT = 0.45f;
    private static final float LR_HYDRO_WEIGHT = 0.25f;
    private static final float LR_HAS_ATMO_WEIGHT = 0.3f;
    private static final float LR_ATMO_WEIGHT = 2f;
    private static final float LR_POSITIVE_NORMALIZATION = 0.208f;
    private static final float LR_NEUTRAL_VALUE = 0.04f;
    private static final float LR_POSITIVE_WEIGHT = 0.95f;
    private static final float LR_MINIMUM_VALUE = 0.01f;
    
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
        return LR_MINIMUM_VALUE + (1 - negativeLRInfluences()) * ( LR_NEUTRAL_VALUE + LR_POSITIVE_WEIGHT*positiveLRInfluences());
    }
    
    private float positiveLRInfluences() {
        return LR_POSITIVE_NORMALIZATION*(LR_ATMO_WEIGHT*atmosphereBreathable + LR_HAS_ATMO_WEIGHT*hasAtmosphere() + LR_GRAV_WEIGHT*gravityOptimal + LR_HYDRO_WEIGHT*hydrosphereOptimal + LR_BIO_WEIGHT*biosphereOptimal + LR_TEMP_WEIGHT*temperatureOptimal);
    }

    private float negativeLRInfluences() {
        float value = gravityTooHigh + LR_EXTREME_TEMP_WEIGHT*temperaturesExtreme();
        if(value < 1)
            return value;
        return 1;
    }
    
    private float hasAtmosphere() {
        if(null==atmosphere) {
            return 0;
        }
        return 1;
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
        float temperatureTooCold = temperatureTooCold();
        temperatureOptimal = temperatureOptimal();
        float temperatureTooHot = temperatureTooHot();
        float extreme = temperaturesExtreme();
        
        fuzzyTemp = FuzzyTemperature.COLD;
        if(temperatureTooCold > 0.75) {
            fuzzyTemp = FuzzyTemperature.TOO_COLD;
        }
        if(extreme < 0.5 && temperatureTooCold == 1) {
            fuzzyTemp = FuzzyTemperature.EXTREMELY_COLD;
        }
        if(temperatureTooHot > 0.1) {
            fuzzyTemp = FuzzyTemperature.HOT;
        }
        if(temperatureTooHot > 0.75) {
            fuzzyTemp = FuzzyTemperature.TOO_HOT;
        }
        if(extreme > 0.5 && temperatureTooHot == 1) {
            fuzzyTemp = FuzzyTemperature.EXTREMELY_HOT;
        }
        if(temperatureOptimal > 0.9) {
            fuzzyTemp = FuzzyTemperature.OPTIMAL;
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
        if(oxygenTooLow == 1f) {
            fuzzyAtmo = FuzzyAtmosphere.NO_OXYGEN;
        }
        if(oxygenOptimal > 0.90) {
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

    private float temperaturesExtreme() {
        float tpProductMinimum = (temperatures.getMinimumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        float tpProductMaximum = (temperatures.getMaximumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        
        if(tpProductMinimum < -2*MINIMUM_TEMPERATURE_THESHOLD) {
            return 1;
        }
        if(tpProductMinimum < -MINIMUM_TEMPERATURE_THESHOLD) {
            return -(tpProductMinimum + MINIMUM_TEMPERATURE_THESHOLD)/MINIMUM_TEMPERATURE_THESHOLD;
        }
        if(tpProductMaximum > MAXIMUM_TEMPERATURE_THESHOLD) {
            return (tpProductMaximum - MAXIMUM_TEMPERATURE_THESHOLD)/MAXIMUM_TEMPERATURE_THESHOLD;
        }
        if(tpProductMaximum > 2*MAXIMUM_TEMPERATURE_THESHOLD) {
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
        
        if(tpProduct <= -MINIMUM_TEMPERATURE_THESHOLD) {
            return 0;
        }
        if(tpProduct <= 0) {
            return 1+tpProduct/MINIMUM_TEMPERATURE_THESHOLD;
        }
        if(tpProduct < MAXIMUM_TEMPERATURE_THESHOLD) {
            return 1-tpProduct/MAXIMUM_TEMPERATURE_THESHOLD;
        }
        return 0;
    }
    
    private float temperatureTooCold() {
        float tpProduct = (temperatures.getMinimumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMinimumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        
        if(tpProduct < -MINIMUM_TEMPERATURE_THESHOLD) {
            return 1;
        }
        if(tpProduct < 0) {
            return -tpProduct/MINIMUM_TEMPERATURE_THESHOLD;
        }
        return 0;
    }
    
    private float temperatureTooHot() {
        float tpProduct = (temperatures.getMaximumTemperature().inKelvin()-OPTIMAL_TEMPERATURES.getMaximumTemperature().inKelvin()) * 0.5f*(OPTIMAL_SURFACE_PRESSURE.asBar() + getSurfacePressure().asBar());
        
        if(tpProduct > MAXIMUM_TEMPERATURE_THESHOLD) {
            return 1;
        }
        if(tpProduct > 0) {
            return tpProduct/MAXIMUM_TEMPERATURE_THESHOLD;
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
        NO_OXYGEN,
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
