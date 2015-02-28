package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody
{
	private static final String TEXTURE_NAME = "IrregularSatellite";

	public Asteroid(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree, AstronomicalBody origin) {
		this(name, new BodyProperties(mass, radius, orbitalRadius, angleInDegree, origin.physicalProperties), origin);
	}
	
	public Asteroid(String name, BodyProperties properties, AstronomicalBody origin) {
		super(name, properties, origin, ConfigurationConstants.SCALE_FACTOR_ASTEROID, TEXTURE_NAME);
	}
}