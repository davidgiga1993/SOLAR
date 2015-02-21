package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;


/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody
{	
	public Star(String name, Length radius, double massInSolarMasses, double orbitalRadiusInAU, float angleInDegree, AstronomicalBody origin)
	{
		super(name, radius, convertAUIntoKilometer(orbitalRadiusInAU), convertSolarMassesIntoKilogram(massInSolarMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_STAR);
		//this.setSize(1000, 1000);
        this.setColor(Color.YELLOW);      
	}

	
    /**
     * Adds a new planet with the specified parameters as a satellite orbiting the star.
     * @param name Desired name of the planet
     * @param radius Desired radius of the planet
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInAU Desired orbital radius around the star in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the planet's position on the map of the system relative to its star
     * @return created Planet object
     */
    public Planet placeNewPlanet(String name, Length radius, double massInEarthMasses, double orbitalRadiusInAU, float angleInDegree)
    {
        Planet newObject = new Planet(name, radius, massInEarthMasses, orbitalRadiusInAU, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
}
