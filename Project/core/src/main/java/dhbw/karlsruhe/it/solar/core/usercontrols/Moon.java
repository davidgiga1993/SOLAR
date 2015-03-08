package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle, AstronomicalBody orbitPrimary, MoonType type) {
		this(name, new OrbitalProperties(orbitPrimary, orbitalRadius, orbitalAngle), new BodyProperties(mass, radius, null), type);
	}

	public Moon(String name, OrbitalProperties orbit, BodyProperties body, MoonType type) {
		super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_MOON, getTextureNameForMoonType(type));
		label.setThreshold(0.4f);
		this.orbitColor = Color.GRAY;
		preview = new PreviewActor(this, getWidth(), 10, Color.GRAY);
	}

	@Override
	protected boolean previewEnabled() {
		// this needs to be refined in the future
		return SolarEngine.get().camera.zoom < .3f;
	}

	private static String getTextureNameForMoonType(MoonType type)
	{
		switch(type)
		{
			case LUNAR:
				return "Lunar";
			case IONIAN:
				return "Ionian";
			case EUROPAN:
				return "Europan";
			case GANYMEDIAN:
				return "Ganymedian";
			case CALLISTOAN:
				return "Callistoan";
			case MIMANTEAN:
				return "Mimantean";
			case ENCELADEAN:
				return "Enceladean";
			case TETHYAN:
				return "Tethyan";
			case DIONEAN:
				return "Dionean";
			case RHEAN:
				return "Rhean";
			case TITANEAN:
				return "Titanean";
			case IAPETIAN:
				return "Iapetian";
			case MIRANDAN:
				return "Mirandan";
			case ARIELIAN:
				return "Arielian";
			case UMBRELIAN:
				return "Umbrelian";
			case TITANIAN:
				return "Titanian";
			case OBERONIAN:
				return "Oberonian";
			case TRITONIAN:
				return "Tritonian";
			default:
				return "IrregularSatellite";
		}
	}

	public enum MoonType {
		IRREGULAR,
		LUNAR,
		IONIAN,
		EUROPAN,
		GANYMEDIAN,
		CALLISTOAN,
		MIMANTEAN,
		ENCELADEAN,
		TETHYAN,
		DIONEAN,
		RHEAN,
		TITANEAN,
		IAPETIAN,
		MIRANDAN,
		ARIELIAN,
		UMBRELIAN,
		TITANIAN,
		OBERONIAN,
		TRITONIAN
	}

}
