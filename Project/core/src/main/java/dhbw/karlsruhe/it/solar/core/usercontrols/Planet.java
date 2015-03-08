package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{

    protected PreviewActor preview;

	public Planet(String name, OrbitalProperties orbit, BodyProperties body, PlanetType type) {
		super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_PLANET, getTextureNameForPlanetType(type));
        preview = new PreviewActor(this, getWidth(), 5.5f);
		this.segments = 2000;
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
				return "Saturn";
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
     * @param radius Desired relativeRadius of the Moon
     * @param mass Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadius Desired orbital relativeRadius around the planet in kilometers
     * @param orbitalAngle Desired angle of the moon's position on the map of the system relative to its planet
     * @return created Moon object
     */
    public Moon placeNewMoon(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle, Moon.MoonType type, boolean planetaryRings, Mass massRings, Length radiusRings, PlanetaryRing.RingType ringType)
    {
    	OrbitalProperties orbit = new OrbitalProperties(this, orbitalRadius, orbitalAngle);
		BodyProperties body = new BodyProperties(mass, radius, null);
        Moon newObject = new Moon(name, orbit, body, type);
        if(planetaryRings)
        {
        newObject.setUpRings(new PlanetaryRing(newObject, massRings, radiusRings, ringType));
        }
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        if(SolarEngine.get().camera.zoom > 5.5) {
            preview.drawLines(libGDXShapeRenderer, solarShapeRenderer);
        }
        super.drawLines(libGDXShapeRenderer, solarShapeRenderer);
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
