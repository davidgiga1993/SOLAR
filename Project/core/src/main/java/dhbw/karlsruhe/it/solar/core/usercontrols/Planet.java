package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{
	private AstronomicalBody outermostMoon;

	public Planet(String name, OrbitalProperties orbit, BodyProperties body, PlanetType planet) {
		super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_PLANET, getTextureFromTypeOf(planet));

		this.segments = 2000;
		preview.color = Color.TEAL;
	}

	private static String getTextureFromTypeOf(PlanetType planet)
	{
		switch(planet)
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

	@Override
	public void addSatellite(AstronomicalBody newSatellite) {
		super.addSatellite(newSatellite);
		if(outermostMoon == null || outermostMoon.orbitalProperties.getOrbitalRadius().asKilometres() < newSatellite.orbitalProperties.getOrbitalRadius().asKilometres()) {
			outermostMoon = newSatellite;
		}
	}

	@Override
	protected boolean canBeSeen() {
		float size = getWidth();
		if(outermostMoon != null) {
			size = outermostMoon.orbitalRadiusInPixels * 2;
		}
		return (size / SolarEngine.get().camera.zoom) > 1f;
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
