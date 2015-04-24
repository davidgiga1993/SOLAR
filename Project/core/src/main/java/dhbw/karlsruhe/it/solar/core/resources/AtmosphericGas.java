package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Pressure;
import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;

public class AtmosphericGas {
    
    private static final Pressure ASPHYXIATION_ONLY = null;
    private static final Pressure CARBON_DIOXIDE_POISONING_SYMPTOMS = new Pressure( 0.01f, PressureUnit.BAR);
    private static final Pressure CARBON_DIOXIDE_POISONING_FATAL = new Pressure( 0.1f, PressureUnit.BAR);
    private static final Pressure METHANE_FLAMMABLE = new Pressure( 0.05f, PressureUnit.BAR);
    private static final Pressure METHANE_EXPLOSIVE = new Pressure( 0.15f, PressureUnit.BAR);
    private static final Pressure OXYGEN_TOXICITY = new Pressure( 0.5f, PressureUnit.BAR);
    private static final Pressure ACUTE_OXYGEN_TOXICITY = new Pressure( 1.6f, PressureUnit.BAR);
    private static final Pressure SULFUR_DIOXIDE_TOXICITY = new Pressure( 5f/1000000f, PressureUnit.BAR);
    private static final Pressure ACUTE_SULFUR_DIOXIDE_TOXICITY = new Pressure( 500f/1000000f, PressureUnit.BAR);
    private static final Pressure ALKALI_METAL_LOW = new Pressure( 0f, PressureUnit.BAR);
    private static final Pressure ALKALI_METAL_HIGH = new Pressure( 5f/1000000f, PressureUnit.BAR);

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
        HELIUM,
        HYDROGEN,
        METHANE,
        NITROGEN,
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
        return new Pressure(percentage * pressure.asBar(), PressureUnit.BAR);
    }
    
    public boolean isOxygen() {
        if(type == GasType.OXYGEN) {
            return true;
        }
        return false;
    }
    
    private String typeToString() {
        switch(type) {
            case ARGON:
                return "Argon";
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

}
