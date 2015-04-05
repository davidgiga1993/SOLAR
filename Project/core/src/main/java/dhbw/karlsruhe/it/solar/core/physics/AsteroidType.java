package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Handles the characteristics common to all asteroids. Governs the type of asteroid.
 * @author Andi
 * Created 2015-04-05
 */
public class AsteroidType extends BodyType {
    
    private SpectralType spectralType;
    
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
    
}
