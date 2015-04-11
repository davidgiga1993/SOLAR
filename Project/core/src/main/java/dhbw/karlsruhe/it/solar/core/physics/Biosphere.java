package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;


public class Biosphere {

    @XmlElement(name = "Biosphere_Coverage")
    private float bioCover;
    @XmlElement(name = "Type_of_Biosphere")
    private BiosphereType type;
    
    public Biosphere() {
        
    }   
    
    public Biosphere(BiosphereType bioType, float biosphereCoverage) {
        this.bioCover = biosphereCoverage;
        this.type = bioType;
    }
    
    public BiosphereType getBioType() {
        return type;
    }
    
    public float getBioCover() {
        return bioCover;
    }

    public enum BiosphereType {
        TERRAN,
        ALIEN
    }
}
