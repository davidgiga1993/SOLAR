package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody  {    
    public Asteroid(String name, OrbitalProperties orbit, BodyProperties body, AsteroidType asteroid) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_ASTEROID, getTextureFromTypeOf(asteroid));
    }
    
    private static String getTextureFromTypeOf(AsteroidType asteroid) {
        switch(asteroid)        {
        case DTYPE:
            return "IrregularSatellite";
        default:
            return "IrregularSatellite";
        }
    }

    public enum AsteroidType {
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