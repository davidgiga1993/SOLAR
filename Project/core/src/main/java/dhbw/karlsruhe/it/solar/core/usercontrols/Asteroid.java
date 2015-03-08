package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody
{
	private static final String TEXTURE_NAME = "IrregularSatellite";

	public Asteroid(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle, AstronomicalBody orbitPrimary) {
		this(name, new OrbitalProperties(orbitPrimary, orbitalRadius, orbitalAngle), new BodyProperties(mass, radius, null));
	}
	
	public Asteroid(String name, OrbitalProperties orbit, BodyProperties body) {
		super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_ASTEROID, TEXTURE_NAME);
	}
}