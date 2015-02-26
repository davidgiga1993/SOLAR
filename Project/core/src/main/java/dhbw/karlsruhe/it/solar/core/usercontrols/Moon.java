package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, Length radius, double massInEarthMasses, double orbitalRadiusInKilometers, float angleInDegree, AstronomicalBody origin, MoonType type)
	{
		super(name, radius, orbitalRadiusInKilometers, convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_MOON, getTextureNameForMoonType(type));
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
			default:
				return "Lunar";
		}
	}
	
}
