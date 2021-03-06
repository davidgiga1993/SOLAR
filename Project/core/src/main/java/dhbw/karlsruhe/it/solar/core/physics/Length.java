package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Arga on 21.11.2014.
 */
public class Length {

    private static final int LUNAR_DISTANCE_IN_KILOMETRES = 384400;
    private static final float ASTRONOMICAL_UNIT_IN_KILOMETRES = 149597870.7f;
    private static final float LIGHT_YEAR_IN_KILOMETERS = (float) (9.4605284 * Math.pow(10, 12));
    private static final float ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES = 389.17240036f;
    private static final float LIGHT_YEAR_IN_LUNAR_DISTANCES = 24611681.77050156f;
    private static final float PARSEC_IN_LUNAR_DISTANCES = 80272569.66167685f;
    private static final float LIGHT_YEARS_IN_ASTRONOMICAL_UNITS = 63241.0770842f;
    private static final float PARSEC_IN_ASTRONOMICAL_UNITS = 206264.80599999f;
    private static final float PARSEC_IN_LIGHT_YEARS = 3.2615637732f;
    private static final float KILOMETER_IN_METERS = 1000;
    private static final float MILLION = 1000000;
    private static final float THOUSAND = 1000;

    @XmlElement
    private float value = 0f;
    @XmlElement(name = "distance_unit")
    private DistanceUnit unit = DistanceUnit.ASTRONOMICAL_UNITS;

    public Length() {
        this(0, DistanceUnit.KILOMETERS);
    }

    public Length(float value, DistanceUnit unit) {
        set(value, unit);
    }

    public Length(double value, DistanceUnit unit) {
        set((float) value, unit);
    }

    public static Length calculateDistance(SolarActor actorOne, SolarActor actorTwo) {
        double directDistance = Math.sqrt(Math.pow(actorTwo.getX() - actorOne.getX(), 2) + Math.pow(actorTwo.getY() - actorOne.getY(), 2));

        return new Length((float) directDistance, DistanceUnit.KILOMETERS);
    }

    private void set(float value, DistanceUnit unit) {
        if (0 <= value) {
            this.value = value;
            this.unit = unit;
            return;
        }
        throw new IllegalArgumentException("Length must be positive value or zero!");
    }

    public float asKilometers() {
        switch (unit) {
            case KILOMETERS:
                return value;
            case LUNAR_DISTANCE:
                return value * LUNAR_DISTANCE_IN_KILOMETRES;
            case ASTRONOMICAL_UNITS:
                return value * ASTRONOMICAL_UNIT_IN_KILOMETRES;
            case LIGHTYEAR:
                return value * LIGHT_YEAR_IN_KILOMETERS;
            default:
                return Float.NaN;
        }
    }

    public float asLunarDistance() {
        switch (unit) {
            case KILOMETERS:
                return value / LUNAR_DISTANCE_IN_KILOMETRES;
            case LUNAR_DISTANCE:
                return value;
            case ASTRONOMICAL_UNITS:
                return value * ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES;
            case LIGHTYEAR:
                return value * LIGHT_YEAR_IN_LUNAR_DISTANCES;
            case PARSEC:
                return value * PARSEC_IN_LUNAR_DISTANCES;
            default:
                return Float.NaN;
        }
    }

    public float asAstronomicalUnit() {
        switch (unit) {
            case KILOMETERS:
                return value / ASTRONOMICAL_UNIT_IN_KILOMETRES;
            case LUNAR_DISTANCE:
                return value / ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES;
            case ASTRONOMICAL_UNITS:
                return value;
            case LIGHTYEAR:
                return value * LIGHT_YEARS_IN_ASTRONOMICAL_UNITS;
            case PARSEC:
                return value * PARSEC_IN_ASTRONOMICAL_UNITS;
            default:
                return Float.NaN;
        }
    }

    private float asLightYear() {
        switch (unit) {
            case KILOMETERS:
                return value / LIGHT_YEAR_IN_KILOMETERS;
            case LUNAR_DISTANCE:
                return value / LIGHT_YEAR_IN_LUNAR_DISTANCES;
            case ASTRONOMICAL_UNITS:
                return value / LIGHT_YEARS_IN_ASTRONOMICAL_UNITS;
            case LIGHTYEAR:
                return value;
            case PARSEC:
                return value * PARSEC_IN_LIGHT_YEARS;
            default:
                return Float.NaN;
        }
    }

    public float asMeters() {
        return this.asKilometers() * KILOMETER_IN_METERS;
    }

    @Override
    public String toString() {
        if (this.asLightYear() > THOUSAND) {
            return formatValue(this.asLightYear() / THOUSAND) + " k l.y.";
        }
        if (this.asLightYear() > 0.1f) {
            return formatValue(this.asLightYear()) + " l.y.";
        }
        if (this.asAstronomicalUnit() > 0.1f) {
            return formatValue(this.asAstronomicalUnit()) + " AU";
        }
        if (this.asKilometers() > MILLION) {
            return formatValue(this.asKilometers() / MILLION) + " mi km";
        }
        if (this.asKilometers() > 10 * THOUSAND) {
            return formatValue(this.asKilometers() / THOUSAND) + " k km";
        }
        return formatValueNoDecimal(this.asKilometers()) + " km";
    }

    private String formatValueNoDecimal(float value) {
        return String.format("%.00f", value);
    }

    private String formatValue(float value) {
        return String.format("%.01f", value);
    }

    public enum DistanceUnit {
        KILOMETERS,
        LUNAR_DISTANCE,
        ASTRONOMICAL_UNITS,
        LIGHTYEAR,
        PARSEC
    }
}
