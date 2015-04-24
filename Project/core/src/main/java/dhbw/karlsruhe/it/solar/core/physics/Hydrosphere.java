package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Hydrosphere {
    
    @XmlElement(name = "Water_Cover")
    private float waterCover;
    @XmlElement(name = "Ice_Cover")
    private float iceCover;
    @XmlElement(name = "Subsurface_Ocean")
    private boolean subsurfaceOcean;
    
    public Hydrosphere() {
        
    }
    
    public Hydrosphere(float waterCover, float iceCover, boolean subsurfaceOcean) {
        this.waterCover = waterCover;
        this.iceCover = iceCover;
        this.subsurfaceOcean = subsurfaceOcean;
    }

    public float getWaterCover() {
        return waterCover;
    }
    
    @Override
    public String toString() {
        if(waterCover > iceCover) {
            return formatValue((waterCover+iceCover)*100) + " % Ocean";
        }
        return formatValue((iceCover+waterCover)*100) + " % Ice Cap";            
    }

    private String formatValue(float value) {
        return String.format("%.00f", value);
    }

    public float getIceCover() {
        return iceCover;
    }

    public boolean getSubsurfaceOcean() {
        return subsurfaceOcean;
    }

}
