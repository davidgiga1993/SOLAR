package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{
	public Planet(String name, Length radius, double massInEarthMasses, double orbitalRadiusInAU, float angleInDegree, AstronomicalBody origin, PlanetType type)
	{
		super(name, radius, convertAUIntoKilometer(orbitalRadiusInAU), convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_PLANET, getTextureNameForPlanetType(type));
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
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInKilometers Desired orbital radius around the planet in kilometers
     * @param angleInDegree Desired angle of the moon's position on the map of the system relative to its planet
     * @return created Moon object
     */
    public Moon placeNewMoon(String name, Length radius, double massInEarthMasses, double orbitalRadiusInKilometers, float angleInDegree, MoonType type)
    {
        Moon newObject = new Moon(name, radius, massInEarthMasses, orbitalRadiusInKilometers, angleInDegree, this, type);
        newObject.calculateOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }
    //TODO: Alle Klassen die von AstronomicalBody ableiten müssen die Konstruktoren auf Length angepasst kriegen...

	@Override
	protected void displayOrbit(SolarShapeRenderer shapeRenderer)
	{
		if (scaleDistanceToStage(orbitalRadiusInKilometers) < getParent().getWidth())
			return;


		shapeRenderer.setColor(Color.TEAL);
		shapeRenderer.orbit(calculateCenterOfOrbitX(), calculateCenterOfOrbitY(), orbitalRadiusInPixels, 2000);

	}

}
