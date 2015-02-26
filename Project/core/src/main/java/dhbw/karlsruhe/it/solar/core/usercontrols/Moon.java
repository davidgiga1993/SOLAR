package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, Length radius, double massInEarthMasses, double orbitalRadiusInKilometers, float angleInDegree, AstronomicalBody origin)
	{
		super(name, radius, orbitalRadiusInKilometers, convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_MOON, "TerrestrialMoon");
	}
	
}
