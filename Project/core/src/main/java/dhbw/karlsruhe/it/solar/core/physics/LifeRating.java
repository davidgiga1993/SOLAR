package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyAtmosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyBiosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyGravity;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyHydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyLogic.FuzzyTemperature;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class LifeRating {
    
    private float rating;
    private FuzzyGravity gravity;
    private FuzzyTemperature temperature;
    private FuzzyAtmosphere atmosphere;
    private FuzzyHydrosphere hydrosphere;
    private FuzzyBiosphere biosphere;
    private AtmosphericGas toxicGas;

    public LifeRating(float rating) {
        this.rating = rating;
    }

    public String inPercent() {
        return formatValue() + " %";
    }
    
    private String formatValue() {
        return String.format("%.00f", rating*100);
    }

    public void setFuzzyEnums(FuzzyGravity fuzzyGravity, FuzzyTemperature fuzzyTemp, FuzzyAtmosphere fuzzyAtmo, AtmosphericGas toxicGas, FuzzyHydrosphere fuzzyHydro, FuzzyBiosphere fuzzyBio) {
        this.gravity = fuzzyGravity;
        this.temperature = fuzzyTemp;
        this.atmosphere = fuzzyAtmo;
        this.toxicGas = toxicGas;
        this.hydrosphere = fuzzyHydro;
        this.biosphere = fuzzyBio;       
    }

    public FuzzyInformation atmosphereFuzzy(AstronomicalBody body) {
        String atmoValue = getAtmoValue(body);
        
        switch(atmosphere) {
            case NONE:
                return new FuzzyInformation("", "None", Styles.MENUELABEL_RED);
            case TRACE:
                return new FuzzyInformation("", "Only Traces", Styles.MENUELABEL_RED);     
            case GAS_GIANT:
                return new FuzzyInformation("", "Gas Giant", Styles.MENUELABEL_RED);   
            case LETHAL_GAS_CONCENTRATION:
                return new FuzzyInformation(getLethalGas(), "Lethal", Styles.MENUELABEL_RED);
            case DANGEROUS_GAS_CONCENTRATION:
                return new FuzzyInformation(getDangerousGas(), "Hazardous", Styles.MENUELABEL_ORANGE);
            case NO_OXYGEN:
                return new FuzzyInformation("", "No O2", Styles.MENUELABEL_ORANGE);
            case LOW_OXYGEN:
                return new FuzzyInformation(atmoValue, "Trace O2", Styles.MENUELABEL_ORANGE);
            case SLIGHTLY_LOW_OXYGEN:
                return new FuzzyInformation(atmoValue, "Low on O2", Styles.MENUELABEL_YELLOW);
            case OPTIMAL_BREATHABLE:
                return new FuzzyInformation(atmoValue, "Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyInformation(atmoValue, "Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    private String getLethalGas() {
        return toxicGas.getShortName() + " > " + formatValue(toxicGas.getLethalThreshold().asPascal()) + " Pa";
    }
    
    private String getDangerousGas() {
        return toxicGas.getShortName() + " > " + formatValue(toxicGas.getDangerousThreshold().asPascal()) + " Pa";
    }
    
    private String formatValue(float value) {
        return String.format("%.01f", value);
    }

    public FuzzyInformation temperatureFuzzy(AstronomicalBody body) {
        String temperatureValue = body.getTemperatures().toString();
    
        switch(temperature) {
            case EXTREMELY_COLD:
                return new FuzzyInformation(temperatureValue, "Extreme Cold", Styles.MENUELABEL_RED);
            case TOO_COLD:
                return new FuzzyInformation(temperatureValue, "Too Cold", Styles.MENUELABEL_ORANGE);
            case COLD:
                return new FuzzyInformation(temperatureValue, "Cold", Styles.MENUELABEL_YELLOW);
            case OPTIMAL:
                return new FuzzyInformation(temperatureValue, "Optimal", Styles.MENUELABEL_GREEN);
            case HOT:
                return new FuzzyInformation(temperatureValue, "Hot", Styles.MENUELABEL_YELLOW);
            case TOO_HOT:
                return new FuzzyInformation(temperatureValue, "Too Hot", Styles.MENUELABEL_ORANGE);
            case EXTREMELY_HOT:
                return new FuzzyInformation(temperatureValue, "Extreme Heat", Styles.MENUELABEL_RED);
            default:
                return new FuzzyInformation(temperatureValue, "Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public FuzzyInformation hydrosphereFuzzy(AstronomicalBody body) {
        String hydroValue = getHydroValue(body);

        switch(hydrosphere) {
            case NONE:
                return new FuzzyInformation(hydroValue, "None", Styles.MENUELABEL_RED);
            case FROZEN:
                return new FuzzyInformation(hydroValue, "Frozen", Styles.MENUELABEL_ORANGE);
            case SUBSURFACE_OCEAN:
                return new FuzzyInformation(hydroValue, "SS Ocean", Styles.MENUELABEL_YELLOW);
            case ARID:
                return new FuzzyInformation(hydroValue, "Too Arid", Styles.MENUELABEL_YELLOW);
            case HUMID:
                return new FuzzyInformation(hydroValue, "Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyInformation(hydroValue, "Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }

    public FuzzyInformation biosphereFuzzy(AstronomicalBody body) {
        String bioValue = getBioValue(body);
        
        switch(biosphere) {
            case LIFELESS:
                return new FuzzyInformation(bioValue, "None", Styles.MENUELABEL_RED);
            case DANGEROUS_ALIEN:
                return new FuzzyInformation(bioValue, "Too Dangerous", Styles.MENUELABEL_ORANGE);
            case SPARSE:
                return new FuzzyInformation(bioValue, "Too Sparse", Styles.MENUELABEL_YELLOW);
            case LUSH:
                return new FuzzyInformation(bioValue, "Optimal", Styles.MENUELABEL_GREEN);
            default:
                return new FuzzyInformation(bioValue, "Unknown Anomaly", Styles.MENUELABEL_RED);
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

    public FuzzyInformation gravityFuzzy(AstronomicalBody body) {
        String gravValue = body.getSurfaceGravity().toString();
        
        switch(gravity) {
            case TOO_HIGH:
                return new FuzzyInformation(gravValue, "Too High", Styles.MENUELABEL_RED);
            case SLIGHTLY_HIGH:
                return new FuzzyInformation(gravValue, "High", Styles.MENUELABEL_ORANGE);
            case OPTIMAL:
                return new FuzzyInformation(gravValue, "Optimal", Styles.MENUELABEL_GREEN);
            case SLIGHTLY_LOW:
                return new FuzzyInformation(gravValue, "Low", Styles.MENUELABEL_YELLOW);
            case TOO_LOW:
                return new FuzzyInformation(gravValue, "Too Low", Styles.MENUELABEL_ORANGE);
            default:
                return new FuzzyInformation(gravValue, "Unknown Anomaly", Styles.MENUELABEL_RED);
        }
    }
    
    public FuzzyInformation lifeRatingFuzzy(AstronomicalBody astronomicalBody) {
        return new FuzzyInformation("", inPercent(), determineStyle(rating));
    }
    
    private String getHydroValue(AstronomicalBody body) {
        Hydrosphere hydro = body.getHydrosphere();
        if(null!=hydro) {            
            return hydro.toString();
        }
        return "";
    }
    
    private String getBioValue(AstronomicalBody body) {
        Biosphere bio = body.getBiosphere();
        if(null!=bio) {            
            return bio.toString();
        }
        return "";
    }
    
    private String getAtmoValue(AstronomicalBody body) {
        Atmosphere atmo = body.getAtmosphere();
        if(null!=atmo) {            
            return atmo.getOxygenPartialPressure().toString() + " O2";
        }
        return "";
    }
}
