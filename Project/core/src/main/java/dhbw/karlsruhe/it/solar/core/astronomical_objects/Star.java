package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.physics.Temperature;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;

/**
 * @author Andi
 */
public class Star extends AstronomicalBody {

    private static final float O_TYPE_TEMPERATURE_RANGE = 67800 / 9;
    private static final float B_TYPE_TEMPERATURE_RANGE = 19000 / 9;
    private static final float A_TYPE_TEMPERATURE_RANGE = 2175 / 9;
    private static final float F_TYPE_TEMPERATURE_RANGE = 987 / 9;
    private static final float G_TYPE_TEMPERATURE_RANGE = 595 / 9;
    private static final float K_TYPE_TEMPERATURE_RANGE = 1360 / 9;
    private static final float M_TYPE_TEMPERATURE_RANGE = 1350 / 9;

    private StarType type;

    public Star(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_STAR, body.getTexture());
        this.type = (StarType) body.getBodyType();
    }

    @Override
    protected boolean previewEnabled() {
        return false;
    }

    @Override
    public String getTypeName() {
        return type.resolveTypeName();
    }

    public static Temperature getBlackBodyTemperature(StarType star) {
        switch (star.getSpectralType()) {
            case O:
                return new Temperature(33200f + getSubdivisionTemperatureDifference(star, O_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case B:
                return new Temperature(10700f + getSubdivisionTemperatureDifference(star, B_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case A:
                return new Temperature(7323f + getSubdivisionTemperatureDifference(star, A_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case F:
                return new Temperature(6033f + getSubdivisionTemperatureDifference(star, F_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case G:
                return new Temperature(5316f + getSubdivisionTemperatureDifference(star, G_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case K:
                return new Temperature(3880f + getSubdivisionTemperatureDifference(star, K_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            case M:
                return new Temperature(2450f + getSubdivisionTemperatureDifference(star, M_TYPE_TEMPERATURE_RANGE), TempUnit.KELVIN);
            default:
                return new Temperature(1f, TempUnit.KELVIN);
        }
    }

    private static float getSubdivisionTemperatureDifference(StarType star, float temperatureRange) {
        switch (star.getSpectralSubdivision()) {
            case ZERO:
                return 9 * temperatureRange;
            case ONE:
                return 8 * temperatureRange;
            case TWO:
                return 7 * temperatureRange;
            case THREE:
                return 6 * temperatureRange;
            case FOUR:
                return 5 * temperatureRange;
            case FIVE:
                return 4 * temperatureRange;
            case SIX:
                return 3 * temperatureRange;
            case SEVEN:
                return 2 * temperatureRange;
            case EIGHT:
                return 1 * temperatureRange;
            case NINE:
                return 0;
            default:
                return 0;
        }
    }
}
