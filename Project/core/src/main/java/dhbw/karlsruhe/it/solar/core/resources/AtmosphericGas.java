package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlElement;

public class AtmosphericGas {

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
        SULFUR_DIOXIDE,
        WATER_VAPOR
    }

}
