package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 */
public class Asteroid extends AstronomicalBody {

    private AsteroidType type;

    public Asteroid(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_ASTEROID, body.getTexture());
        this.type = (AsteroidType) body.getBodyType();
    }

    @Override
    public String getTypeName() {
        return type.resolveTypeName();
    }
}