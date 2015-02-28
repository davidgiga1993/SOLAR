package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{

	public Planet(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree, AstronomicalBody orbitPrimary, PlanetType type) {
		this(name, new BodyProperties(orbitPrimary, mass, radius, orbitalRadius, angleInDegree), orbitPrimary, type);
	}

	public Planet(String name, BodyProperties properties, AstronomicalBody origin, PlanetType type) {
		super(name, properties, origin, ConfigurationConstants.SCALE_FACTOR_PLANET, getTextureNameForPlanetType(type));
	}

	private static String getTextureNameForPlanetType(PlanetType type)
	{
		switch(type)
		{
			case MARTIAN:
				return "Martian";
			case MERCURIAN:
				return "Mercurian";
			case DWARFPLANET:
				return "DwarfPlanet";
			case JOVIAN:
				return "Jovian";
			case VENUSIAN:
				return "Venusian";
			case NEPTUNIAN:
				return "Neptunian";
			case SATURNIAN:
				return "RingedClassIGasGiant";
			case URANIAN:
				return "Uranian";
			case TERRAN:
				return "Terran";
			default:
				return "DwarfPlanet";
		}
	}

    /**
     * Adds a new moon with the specified parameters as a satellite orbiting the planet.
     * @param name Desired name of the Moon
     * @param radius Desired radius of the Moon
     * @param mass Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadius Desired orbital radius around the planet in kilometers
     * @param angleInDegree Desired angle of the moon's position on the map of the system relative to its planet
     * @return created Moon object
     */
    public Moon placeNewMoon(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree, Moon.MoonType type)
    {
		BodyProperties properties = new BodyProperties(this, mass, radius, orbitalRadius, angleInDegree);
        Moon newObject = new Moon(name, properties, this, type);
        newObject.calculateOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }

	@Override
	protected void displayOrbit(SolarShapeRenderer shapeRenderer)
	{
		shapeRenderer.setColor(Color.TEAL);
		shapeRenderer.orbit(calculateCenterOfOrbitX(), calculateCenterOfOrbitY(), orbitalRadiusInPixels, 2000);

	}

	public enum PlanetType {
		MERCURIAN,
		VENUSIAN,
		TERRAN,
		MARTIAN,
		DWARFPLANET,
		JOVIAN,
		SATURNIAN,
		URANIAN,
		NEPTUNIAN
	}

}
