package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all asteroids. Governs the type of asteroid.
 * @author Andi
 * Created 2015-04-05
 */
public class AsteroidType extends BodyType {
    
    @XmlElement(name = "Type")
    private TypeOfAsteroid typeOfAsteroid;
    @XmlElement(name = "Texture")
    private TextureTypeOfAsteroid textureOfAsteroid;
    
    public AsteroidType() {
        
    }
    
    public AsteroidType(TypeOfAsteroid typeOfAsteroid, TextureTypeOfAsteroid textureOfAsteroid) {
        this.typeOfAsteroid = typeOfAsteroid;
        this.textureOfAsteroid = textureOfAsteroid;
    }
        
    @Override
    public boolean consistsPartiallyOfWaterIce() {
        return false;
    }
    
    @Override
    public boolean hasSurface() {
        return true;
    }
    
    public TypeOfAsteroid getSpectralType() {
        return typeOfAsteroid;
    }
    
    public String resolveTypeName() {
        switch(typeOfAsteroid)        {
        case DTYPE:
            return "D-Type Asteroid";
        default:
            return "Anomaly: Unknown Type of Asteroid";
        }
    }

    @Override
    public boolean isRounded() {
        return false;
    }

    @Override
    public String getTexture() {
        switch(textureOfAsteroid)        {
            case PHOEBE_DEFAULT_IMAGE:
                return "IrregularSatellite";
            default:
                return "IrregularSatellite";
            }
    }    
    
    public enum TypeOfAsteroid {
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
    
    public enum TextureTypeOfAsteroid {
        PHOEBE_DEFAULT_IMAGE
    }

    @Override
    public boolean isColonizable() {
        return true;
    }
}
