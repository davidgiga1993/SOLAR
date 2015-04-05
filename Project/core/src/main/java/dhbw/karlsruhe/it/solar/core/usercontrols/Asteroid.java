package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.AsteroidType;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody  {    
    public Asteroid(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_ASTEROID, getTextureFromTypeOf((AsteroidType)body.getBodyType()));
    }
    
    private static String getTextureFromTypeOf(AsteroidType asteroid) {
        switch(asteroid.getSpectralType())        {
        case DTYPE:
            return "IrregularSatellite";
        default:
            return "IrregularSatellite";
        }
    }
}