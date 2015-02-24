package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{

	public Planet(String name, Length radius, double massInEarthMasses, double orbitalRadiusInAU, float angleInDegree, AstronomicalBody origin)
	{
		super(name, radius, convertAUIntoKilometer(orbitalRadiusInAU), convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_PLANET);
		//this.setSize(250, 250);
        //this.color = Color.BLUE;
	}

	
    /**
     * Adds a new moon with the specified parameters as a satellite orbiting the planet.
     * @param name Desired name of the Moon
     * @param radius Desired radius of the Moon
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInKilometers Desired orbital radius around the star in kilometers
     * @param angleInDegree Desired angle of the moon's position on the map of the system relative to its planet
     * @return created Moon object
     */
    public Moon placeNewMoon(String name, Length radius, double massInEarthMasses, double orbitalRadiusInKilometers, float angleInDegree)
    {
        Moon newObject = new Moon(name, radius, massInEarthMasses, orbitalRadiusInKilometers, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }

    //TODO: Alle Klassen die von AstronomicalBody ableiten müssen die Konstruktoren auf Length angepasst kriegen...

}
