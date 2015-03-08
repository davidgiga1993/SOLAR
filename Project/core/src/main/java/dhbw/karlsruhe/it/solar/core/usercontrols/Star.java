package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody
{

    public Star(String name, OrbitalProperties orbit, BodyProperties body, StarType type) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, getTextureNameForStarType(type));
    }
    
	private static String getTextureNameForStarType(StarType type)
	{
		switch(type)
		{
			case GTypeMainSequence:
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
    	OTypeMainSequence,
    	BTypeMainSequence,
    	ATypeMainSequence,
    	FTypeMainSequence,
		GTypeMainSequence,
		KTypeMainSequence,
		MTypeMainSequence,
	}
}
