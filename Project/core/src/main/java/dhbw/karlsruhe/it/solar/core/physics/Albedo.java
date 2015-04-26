package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Albedo {
    
    @XmlElement(name = "Albedo")
    private float value;
    
    public Albedo() {
        
    }
    
    public Albedo(float value) {
        if(0 < value && value < 1) {
            this.value = value;
            return;
        }
        this.value = 0;
    }

    public float getAlbedoValue() {
        return value;
    }

}
