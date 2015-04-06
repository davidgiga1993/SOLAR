package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.physics.StarType;

/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody  {

    public Star(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, getTextureFromTypeOf((StarType)body.getBodyType()));
    }
    
    private static String getTextureFromTypeOf(StarType star)    {
        switch(star.getStarType())        {
            case GTYPE:
                return "GTypeMainSequence";
            default:
                return "GTypeMainSequence";
        }
    }

    @Override
    protected boolean previewEnabled() {
        return false;
    }
}
