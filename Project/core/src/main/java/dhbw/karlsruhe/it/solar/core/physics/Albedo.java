package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Albedo {
    
    @XmlElement(name = "Albedo")
    private float value;
    
    public Albedo() {
        
    }
    
    public Albedo(float value) {
        if(0 <= value && value <= 1) {
            this.value = value;
            return;
        }
        throw new IllegalArgumentException("Bond Albedo value can only lie between 0 and 1");
    }

    public float getAlbedoValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return formatValue(value*100) + " % Reflection";
    }
   
    private String formatValue(float value) {
        return String.format("%.01f", value);
    }

}
