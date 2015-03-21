package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody  {

    public Star(String name, OrbitalProperties orbit, BodyProperties body, StarType star) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, getTextureFromTypeOf(star));
    }
    
    private static String getTextureFromTypeOf(StarType star)    {
        switch(star)        {
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

    public enum StarType {
        OTYPE,
        BTYPE,
        ATYPE,
        FTYPE,
        GTYPE,
        KTYPE,
        MTYPE,
    }
}
