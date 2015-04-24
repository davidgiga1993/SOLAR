package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody  {
    
    private StarType type;

    public Star(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, getTextureFromTypeOf((StarType)body.getBodyType()));
        this.type = (StarType)body.getBodyType();
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
    
    @Override
    public String getTypeName() {
        return type.resolveTypeName();
    }
}
