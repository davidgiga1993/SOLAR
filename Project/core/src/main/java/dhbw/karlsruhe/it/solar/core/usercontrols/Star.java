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
public class Star extends AstronomicalBody
{
    public Star(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle, AstronomicalBody orbitPrimary, StarType type) {
        this(name, new OrbitalProperties(orbitPrimary, orbitalRadius, orbitalAngle), new BodyProperties(mass, radius, null), type);
    }

    public Star(String name, OrbitalProperties orbit, BodyProperties body, StarType type) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, getTextureNameForStarType(type));
    }
		
    /**
     * Adds a new planet with the specified parameters as a satellite orbiting the star.
     * @param name Desired name of the planet
     * @param radius Desired radius of the planet
     * @param mass Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadius Desired orbital radius around the star in multiples of Astronomical Units
     * @param orbitalAngle Desired angle of the planet's position on the map of the system relative to its star
     * @return created Planet object
     */
    public Planet placeNewPlanet(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle, Planet.PlanetType type, boolean planetaryRings, Mass massRings, Length radiusRings, PlanetaryRing.RingType ringType)
    {
    	OrbitalProperties orbit = new OrbitalProperties(this, orbitalRadius, orbitalAngle);
		BodyProperties body = new BodyProperties(mass, radius, null);
        Planet newObject = new Planet(name, orbit, body, type);
        if(planetaryRings)
        {
        newObject.setUpRings(new PlanetaryRing(newObject, massRings, radiusRings, ringType));
        }
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
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
