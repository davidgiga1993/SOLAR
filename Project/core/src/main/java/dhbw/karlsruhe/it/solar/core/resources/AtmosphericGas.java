package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Pressure;
import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;

public class AtmosphericGas {
    
    private static final Pressure ASPHYXIATION_ONLY = null;
    private static final Pressure CARBON_DIOXIDE_POISONING_SYMPTOMS = new Pressure( 0.01f, PressureUnit.BAR);
    private static final Pressure CARBON_DIOXIDE_POISONING_FATAL = new Pressure( 0.1f, PressureUnit.BAR);
    private static final Pressure CARBON_MONOXIDE_POISONING_SYMPTOMS = new Pressure( 35/1000000f, PressureUnit.BAR);
    private static final Pressure CARBON_MONOXIDE_POISONING_FATAL = new Pressure( 667/1000000f, PressureUnit.BAR);
    private static final Pressure METHANE_FLAMMABLE = new Pressure( 0.05f, PressureUnit.BAR);
    private static final Pressure METHANE_EXPLOSIVE = new Pressure( 0.15f, PressureUnit.BAR);
    private static final Pressure OXYGEN_TOXICITY = new Pressure( 0.5f, PressureUnit.BAR);
    private static final Pressure ACUTE_OXYGEN_TOXICITY = new Pressure( 1.6f, PressureUnit.BAR);
    private static final Pressure SULFUR_DIOXIDE_TOXICITY = new Pressure( 5f/1000000f, PressureUnit.BAR);
    private static final Pressure ACUTE_SULFUR_DIOXIDE_TOXICITY = new Pressure( 500f/1000000f, PressureUnit.BAR);
    private static final Pressure ALKALI_METAL_LOW = new Pressure( 0f, PressureUnit.BAR);
    private static final Pressure ALKALI_METAL_HIGH = new Pressure( 5f/1000000f, PressureUnit.BAR);
    private static final float CO2_SATURATION_LEVEL = 557f;
    private static final float CO2_SCALAR = 1.2f;
    private static final float H2O_CO2_RELATION = 3700f;
    private static final float H2O_SATURATION_LEVEL = 300f;
    private static final float H2O_SCALAR = 8.9f;
    private static final float CH4_SATURATION_LEVEL = 300f;
    private static final float CH4_SCALAR = 1.54f;
    private static final float N2O_SATURATION_LEVEL = 300f;
    private static final float N2O_SCALAR = 30f;

    @XmlElement(name = "Type")
    private GasType type;
    @XmlElement(name = "Volume_Percentage")
    private float percentage;
    
    public AtmosphericGas() {
        
    }
    
    public AtmosphericGas(GasType type, float percentage) {
        this.type = type;
        if(percentage < 1 && percentage > 0) {
            this.percentage = percentage;
            return;
        }
        this.percentage = 0;
    }
    
    public enum GasType {
        ARGON,
        CARBON_DIOXIDE,
        CARBON_MONOXIDE,
        HELIUM,
        HYDROGEN,
        METHANE,
        NITROGEN,
        NITROUS_OXIDE,
        OXYGEN,
        POTASSIUM,
        SODIUM,
        SULFUR_DIOXIDE,
        WATER_VAPOR
    }
    
    /**
     * The danger threshold is the partial pressure at which that gas takes on dangerous qualities.
     * This can extend from toxicity to humans to explosion risk if exposed to oxidizers/halogen/etc...
     * @return The dangerous partial pressure of that gas in bar.
     */
    public Pressure getDangerousThreshold() {
        switch(type) {
        case CARBON_DIOXIDE:
            return CARBON_DIOXIDE_POISONING_SYMPTOMS;
        case CARBON_MONOXIDE:
            return CARBON_MONOXIDE_POISONING_SYMPTOMS;    
        case METHANE:
            return METHANE_FLAMMABLE;
        case OXYGEN:
            return OXYGEN_TOXICITY;
        case POTASSIUM:
            return ALKALI_METAL_LOW;
        case SODIUM:
            return ALKALI_METAL_LOW;
        case SULFUR_DIOXIDE:
            return SULFUR_DIOXIDE_TOXICITY;
        default:
            return ASPHYXIATION_ONLY;
        }       
    }

    /**
     * The lethal threshold is the partial pressure at which breathing that gas becomes fatal within minutes.
     * @return The lethal partial pressure of that gas in bar.
     */   
    public Pressure getLethalThreshold() {
        switch(type) {
        case CARBON_DIOXIDE:
            return CARBON_DIOXIDE_POISONING_FATAL;
        case CARBON_MONOXIDE:
            return CARBON_MONOXIDE_POISONING_FATAL;  
        case METHANE:
            return METHANE_EXPLOSIVE;
        case OXYGEN:
            return ACUTE_OXYGEN_TOXICITY;
        case POTASSIUM:
            return ALKALI_METAL_HIGH;
        case SODIUM:
            return ALKALI_METAL_HIGH;
        case SULFUR_DIOXIDE:
            return ACUTE_SULFUR_DIOXIDE_TOXICITY;
        default:
            return ASPHYXIATION_ONLY;
        }       
    }

    public Pressure partialPressure(Pressure pressure) {
        if(null==pressure) {
            return gasGiantPartialPressureAtOneBarDepthLevel(pressure);            
        }
        return new Pressure(percentage*pressure.asBar(), PressureUnit.BAR);
    }

    private Pressure gasGiantPartialPressureAtOneBarDepthLevel(Pressure pressure) {
        return new Pressure(percentage, PressureUnit.BAR);
    }
    
    public boolean isOxygen() {
        return type.equals(GasType.OXYGEN);
    }
    
    public boolean isWaterVapor() {
        return type.equals(GasType.WATER_VAPOR);
    }
    
    private String typeToString() {
        switch(type) {
            case ARGON:
                return "Argon";
            case CARBON_MONOXIDE:
                return "Carbon Monoxide";  
            case CARBON_DIOXIDE:
                return "Carbon Dioxide";
            case HELIUM:
                return "Helium";
            case HYDROGEN:
                return "Hydrogen";
            case METHANE:
                return "Methane";
            case NITROGEN:
                return "Nitrogen";
            case NITROUS_OXIDE:
                return "Nitrous Oxide";
            case OXYGEN:
                return "Oxygen";
            case POTASSIUM:
                return "Potassium";
            case SODIUM:
                return "Sodium";
            case SULFUR_DIOXIDE:
                return "Sulfur Dioxode";
            case WATER_VAPOR:
                return "Water Vapor";
            default:
                return "unknown";            
        }
    }

    private String formatValue(float value) {
        return String.format("%.01f", value);
    }

    public String getName() {
        return typeToString();
    }

    public String getCompositionPercentage() {
        return formatValue(percentage*100) + " %";
    }

    public String getShortName() {
        switch(type) {
            case ARGON:
                return "Ar";
            case CARBON_MONOXIDE:
                return "CO";
            case CARBON_DIOXIDE:
                return "CO2";
            case HELIUM:
                return "He";
            case HYDROGEN:
                return "H2";
            case METHANE:
                return "CH4";
            case NITROGEN:
                return "N2";
            case NITROUS_OXIDE:
                return "N2O";
            case OXYGEN:
                return "O2";
            case POTASSIUM:
                return "K";
            case SODIUM:
                return "Na";
            case SULFUR_DIOXIDE:
                return "SO2";
            case WATER_VAPOR:
                return "H2O";
            default:
                return "unknown";            
        }
    }

    /**
     * Calculates a fictional greenhouse effect value for that atmospheric gas.
     * Formula uses an unrealistic saturation principle with data points derived from the bodies of the solar system.
     * Not a scientifically correct calculation! Only a quick and dirty approximation.
     * @param surfacePressure
     * @return
     */
    public float greenhouseEffect(BodyProperties body) {
        switch(type) {
            case CARBON_DIOXIDE:
                return saturationCO2(body);
            case METHANE:
                return saturationCH4(partialPressure(body.getSurfacePressure()));
            case WATER_VAPOR:
                return saturationH2O(partialPressure(body.getSurfacePressure()));
            case NITROUS_OXIDE:
                return saturationN2O(partialPressure(body.getSurfacePressure()));
            default:
                return 0;            
        }
    }

    private float saturationN2O(Pressure partialPressureN2O) {
        return saturationFormula(N2O_SATURATION_LEVEL, N2O_SCALAR, partialPressureN2O);
    }

    private float saturationH2O(Pressure partialPressureH2O) {
        return saturationFormula(H2O_SATURATION_LEVEL, H2O_SCALAR, partialPressureH2O);
    }

    private float saturationCH4(Pressure partialPressureCH4) {
        return saturationFormula(CH4_SATURATION_LEVEL, CH4_SCALAR, partialPressureCH4);
    }

    private float saturationCO2(BodyProperties body) {
        return saturationFormula(CO2_SATURATION_LEVEL, CO2_SCALAR + H2O_CO2_RELATION * body.getH2OPartialPressure().asBar(), partialPressure(body.getSurfacePressure()));
    }
    
    private float saturationFormula(float saturationLevel, float scalar, Pressure partialPressure) {
        float zwischenergebnis = saturationLevel - saturationLevel/(scalar * partialPressure.asBar() + 1);
        return zwischenergebnis;
    }
}
