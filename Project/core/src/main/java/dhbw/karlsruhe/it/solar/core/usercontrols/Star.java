package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;


/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody
{	
	public Star(String name, double massInSolarMasses, double orbitalRadiusInAU, double angleInDegree, AstronomicalBody origin)
	{
		super(name, convertAUIntoKilometer(orbitalRadiusInAU), convertSolarMassesIntoKilogram(massInSolarMasses), angleInDegree, origin);
		this.setSize(1000, 1000);
        this.color = Color.YELLOW;
	}

	
    /**
     * Adds a new planet with the specified parameters as a satellite orbiting the star.
     * @param name Desired name of the planet
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInAU Desired orbital radius around the star in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the planet's position on the map of the system relative to its star
     * @return created Planet object
     */
    public Planet placeNewPlanet(String name, double massInEarthMasses, double orbitalRadiusInAU, double angleInDegree)
    {
        Planet newObject = new Planet(name, massInEarthMasses, orbitalRadiusInAU, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
}
