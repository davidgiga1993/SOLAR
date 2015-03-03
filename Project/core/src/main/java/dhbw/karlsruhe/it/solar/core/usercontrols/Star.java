package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;

/**
 * @author Andi
 *
 */
public class Star extends AstronomicalBody
{
    private static final String TEXTURE_NAME = "GTypeMainSequence";

    public Star(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree, AstronomicalBody orbitPrimary) {
        this(name, new OrbitalProperties(mass, orbitPrimary, orbitalRadius, angleInDegree), new BodyProperties(mass, radius));
    }

    public Star(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, TEXTURE_NAME);
    }
		
    /**
     * Adds a new planet with the specified parameters as a satellite orbiting the star.
     * @param name Desired name of the planet
     * @param radius Desired radius of the planet
     * @param mass Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadius Desired orbital radius around the star in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the planet's position on the map of the system relative to its star
     * @return created Planet object
     */
    public Planet placeNewPlanet(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree, Planet.PlanetType type)
    {
    	OrbitalProperties orbit = new OrbitalProperties(mass, this, orbitalRadius, angleInDegree);
		BodyProperties body = new BodyProperties(mass, radius);
        Planet newObject = new Planet(name, orbit, body, type);
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }
}
