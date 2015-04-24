package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all asteroids. Governs the type of asteroid.
 * @author Andi
 * Created 2015-04-05
 */
public class AsteroidType extends BodyType {
    
    @XmlElement(name = "Type")
    private SpectralType spectralType;
    
    public AsteroidType() {
        
    }
    
    public AsteroidType(SpectralType classification) {
        this.spectralType = classification;
    }
    
    public SpectralType getSpectralType() {
        return spectralType;
    }

    public enum SpectralType {
        BTYPE,
        FTYPE,
        GTYPE,
        CTYPE,
        STYPE,
        MTYPE,
        ETYPE,
        PTYPE,
        ATYPE,
        DTYPE,
        QTYPE,
        RTYPE,
        VTYPE
    }

    public String resolveTypeName() {
        switch(spectralType)        {
        case DTYPE:
            return "D-Type Asteroid";
        default:
            return "Anomaly: Unknown Type of Asteroid";
        }
    }

    @Override
    public boolean hasSurface() {
        return true;
    }
    
}
