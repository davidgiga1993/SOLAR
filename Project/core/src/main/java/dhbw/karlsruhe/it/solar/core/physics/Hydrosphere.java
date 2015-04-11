package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Hydrosphere {
    
    @XmlElement(name = "Water_Cover")
    private float waterCover;
    @XmlElement(name = "Liquid_Water")
    private boolean liquid;
    
    public Hydrosphere() {
        
    }
    
    public Hydrosphere(float waterCover) {
        this.waterCover = waterCover;
        this.liquid = false;
    }
    
    public Hydrosphere(float waterCover, boolean liquid) {
        this.waterCover = waterCover;
        this.liquid = liquid;
    }

}
