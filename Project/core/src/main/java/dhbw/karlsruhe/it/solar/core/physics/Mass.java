package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Created by Arga on 25.11.2014.
 */
public class Mass {

    public static final float SOLAR_MASS_IN_KILOGRAM = 1.98855e30f;
    public static final float EARTH_MASS_IN_KILOGRAM = 5.97219e24f;
    public static final float SOLAR_MASS_IN_EARTH_MASS = 333000;
    protected float value;
    protected Unit unit;

    public Mass(float value, Unit unit) {
        setValue(value, unit);
    }

    public void setValue(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns only the value with no regard to the unit.
     * @return
     */
    public float getValue() {
        return value;
    }

    public float asKilogram() {
        switch (unit) {
            case SOLAR_MASS:
                return value * SOLAR_MASS_IN_KILOGRAM;
            case EARTH_MASS:
                return value * EARTH_MASS_IN_KILOGRAM;
            case KILOGRAM:
            default:
                return value;
        }
    }

    public float asEarthMass() {
        switch(unit) {

            case KILOGRAM:
                return value / EARTH_MASS_IN_KILOGRAM;
            case SOLAR_MASS:
                return value * SOLAR_MASS_IN_EARTH_MASS;
            case EARTH_MASS:
            default:
                return value;
        }
    }

    public float asSolarMass() {
        switch(unit) {
            case KILOGRAM:
                return value / SOLAR_MASS_IN_KILOGRAM;
            case SOLAR_MASS:
                return value;
            case EARTH_MASS:
            default:
                return value / SOLAR_MASS_IN_EARTH_MASS;
        }
    }

    public float getAsGM() {
        return asKilogram() * PhysicalConstants.GRAVITATIONAL_CONSTANT;
    }


    public enum Unit {
        KILOGRAM,
        SOLAR_MASS,
        EARTH_MASS,
    }


    public void addMass(Mass massToBeAddedToTheBody) {
        switch(unit) {
        case KILOGRAM:
            value += massToBeAddedToTheBody.asKilogram();
            break;
        case SOLAR_MASS:
            value += massToBeAddedToTheBody.asSolarMass();
            break;
        case EARTH_MASS:
            value += massToBeAddedToTheBody.asEarthMass();
            break;
        default:
            break;
    }        
    }
}
