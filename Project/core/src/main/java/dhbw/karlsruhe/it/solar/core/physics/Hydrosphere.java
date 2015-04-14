package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Hydrosphere {
    
    @XmlElement(name = "Water_Cover")
    private float waterCover;
    @XmlElement(name = "Liquid_Water")
    private boolean liquidWater;
    
    public Hydrosphere() {
        
    }
    
    public Hydrosphere(float waterCover) {
        this.waterCover = waterCover;
        this.liquidWater = false;
    }
    
    public Hydrosphere(float waterCover, boolean liquid) {
        this.waterCover = waterCover;
        this.liquidWater = liquid;
    }

    public float getWaterCover() {
        return waterCover;
    }
    
    @Override
    public String toString() {
        return formatValue() + " % Water Cover" + liquidMsg();
    }

    private String liquidMsg() {
        if(liquidWater) {
            return ", occurence of liquid water";
        }
        return "";
    }

    private String formatValue() {
        return String.format("%.02f", waterCover);
    }

}
