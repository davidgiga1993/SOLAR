package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Hydrosphere {
    
    @XmlElement(name = "Water_Cover")
    private float waterCover;
    @XmlElement(name = "Ice_Cover")
    private float iceCover;
    @XmlElement(name = "Liquid_Water")
    private boolean liquidWater;
    @XmlElement(name = "Subsurface_Ocean")
    private boolean subsurfaceOcean;
    
    public Hydrosphere() {
        
    }
    
    public Hydrosphere(float waterCover, float iceCover, boolean liquidWater, boolean subsurfaceOcean) {
        if(0 <= waterCover + iceCover && waterCover + iceCover <= 1) {
            this.waterCover = waterCover;
            this.iceCover = iceCover;
            this.liquidWater = liquidWater;
            this.subsurfaceOcean = subsurfaceOcean;
            return;
        }
        throw new IllegalArgumentException("Bond Albedo value can only lie between 0 and 1");
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

    public boolean hasLiquidWater() {
        if(subsurfaceOcean || liquidWater) {
            return true;
        }
        return false;
    }

}
