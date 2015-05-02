package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceGravity.GravUnit;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;

public class FuzzyLogic {
       
    public static final SurfaceGravity OPTIMAL_GRAVITY = new SurfaceGravity(0.91f,GravUnit.G);
    public static final Hydrosphere OPTIMAL_HYDROSPHERE = new Hydrosphere(0.68f, 0.1f, true, false);
    public static final SurfaceTemperature MINIMUM_TEMPERATURE_THRESHOLD = new SurfaceTemperature(new Temperature(170f,TempUnit.KELVIN));
    public static final SurfaceTemperature MAXIMUM_TEMPERATURE_THRESHOLD = new SurfaceTemperature( new Temperature(318f,TempUnit.KELVIN));
    public static final SurfaceTemperature OPTIMAL_TEMPERATURE = new SurfaceTemperature(new Temperature(290f,TempUnit.KELVIN));
    public static final Pressure OPTIMAL_SURFACE_PRESSURE = new Pressure(1f, PressureUnit.STANDARDATMOSPHERE);
    public static final Pressure OPTIMAL_OXYGEN_PARTIAL_PRESSURE = new Pressure(0.223f, PressureUnit.BAR);
    private static final float LR_GRAV_WEIGHT = 1f;
    private static final float LR_TEMP_WEIGHT = 1f;
    private static final float LR_ATMO_WEIGHT = 1f;
    private static final float LR_HYDRO_WEIGHT = 1f;
    private static final float LR_BIO_WEIGHT = 1f;
    private static final float LR_POSITIVE_NORMALIZATION = 0.225f;
    private static final float LR_POSITIVE_WEIGHT = 0.95f;
    private static final float LR_NEUTRAL_VALUE = 0.04f;
    private static final float LR_MINIMUM_VALUE = 0.01f;
    
    private SurfaceGravity gravity;
    private Atmosphere atmosphere;
    private SurfaceTemperature temperature;
    private Hydrosphere hydrosphere;
    private Biosphere biosphere;
    
    private FuzzyGravity fuzzyGravity;
    private FuzzyAtmosphere fuzzyAtmo;
    private FuzzyTemperature fuzzyTemp;
    private FuzzyHydrosphere fuzzyHydro;
    private FuzzyBiosphere fuzzyBio;
 
    public FuzzyLogic(SurfaceGravity gravity, Atmosphere atmosphere, SurfaceTemperature temperature, Hydrosphere hydro, Biosphere bio) {
        this.gravity = gravity;
        this.atmosphere = atmosphere;
        this.temperature = temperature;
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
        rating.setFuzzyValues(fuzzyGravity, fuzzyTemp, fuzzyAtmo, fuzzyHydro, fuzzyBio);
        
        return rating;
    }

    private float lifeRatingFormula() {
        return LR_MINIMUM_VALUE + (1 - negativeLRInfluences()) * ( LR_NEUTRAL_VALUE + LR_POSITIVE_WEIGHT*positiveLRInfluences());
    }
    
    private float positiveLRInfluences() {
        return LR_POSITIVE_NORMALIZATION*(LR_ATMO_WEIGHT*fuzzyAtmo.optimal() + LR_GRAV_WEIGHT*fuzzyGravity.optimal() + LR_HYDRO_WEIGHT*fuzzyHydro.optimal() + LR_BIO_WEIGHT*fuzzyBio.optimal() + LR_TEMP_WEIGHT*fuzzyTemp.optimal());
    }

    private float negativeLRInfluences() {
        float value = fuzzyGravity.tooHigh() + fuzzyTemp.extreme();
        if(value < 1)
            return value;
        return 1;
    }

    private void calculateFuzzyGravity() {       
        fuzzyGravity = new FuzzyGravity(gravity);
    }

    private void calculateFuzzyTemperature() {
        fuzzyTemp = new FuzzyTemperature(temperature, getSurfacePressure());    
    }

    private void calculateFuzzyAtmosphere() {
        fuzzyAtmo = new FuzzyAtmosphere(atmosphere);     
    }

    private void calculateFuzzyHydrosphere() {
        fuzzyHydro = new FuzzyHydrosphere(hydrosphere);
    }
    
    private void calculateFuzzyBiosphere() {
        fuzzyBio = new FuzzyBiosphere(biosphere);
    }  

    private Pressure getSurfacePressure() {
        if(null!=atmosphere && null!=atmosphere.getPressure()) {
            return atmosphere.getPressure();            
        }
        return new Pressure(0f,PressureUnit.BAR);
    }
}
