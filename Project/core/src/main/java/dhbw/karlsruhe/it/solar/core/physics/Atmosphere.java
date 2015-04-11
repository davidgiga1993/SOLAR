package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Pressure.class})
public class Atmosphere {
    
    @XmlElement(name = "Surface_Pressure")
    private Pressure surfacePressure;
    @XmlElement(name = "Atmospheric_Composition")
    private AtmosphericComposition composition;
    
    public Atmosphere() {
        
    }
    
    public Atmosphere(Pressure surfacePressure, AtmosphericComposition composition) {
        this.surfacePressure = surfacePressure;
        this.composition = composition;
    }

    public Pressure getPressure() {
        return surfacePressure;
    }

    public AtmosphericComposition getComposition() {
        return composition;
    }
}
