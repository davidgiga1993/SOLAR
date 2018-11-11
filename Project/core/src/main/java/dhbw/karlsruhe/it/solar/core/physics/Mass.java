package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Arga on 25.11.2014.
 */
public class Mass {

    private static final float LUNAR_MASS_IN_KILOGRAM = 7.3477f * (float) Math.pow(10, 22);
    private static final float SOLAR_MASS_IN_KILOGRAM = 1.98855e30f;
    private static final float EARTH_MASS_IN_KILOGRAM = 5.97219e24f;
    private static final float SOLAR_MASS_IN_EARTH_MASS = 333000;
    private static final float QUADRILLION = (float) Math.pow(10, 15);
    private static final float QUINTILLION = (float) Math.pow(10, 18);

    @XmlElement(name = "Mass_Value")
    private float value;
    @XmlElement(name = "Mass_Unit")
    private MassUnit unit;

    public Mass() {

    }

    public Mass(float value, MassUnit unit) {
        setValue(value, unit);
    }

    private void setValue(float value, MassUnit unit) {
        if (0 <= value) {
            this.value = value;
            this.unit = unit;
            return;
        }
        throw new IllegalArgumentException("Length must be positive value or zero!");
    }

    /**
     * Returns only the value with no regard to the unit.
     *
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
            case LUNAR_MASS:
                return value * LUNAR_MASS_IN_KILOGRAM;
            case KILOGRAM:
                return value;
            default:
                return Float.NaN;
        }
    }

    private float asLunarMass() {
        switch (unit) {
            case SOLAR_MASS:
                return value * SOLAR_MASS_IN_KILOGRAM / LUNAR_MASS_IN_KILOGRAM;
            case EARTH_MASS:
                return value * EARTH_MASS_IN_KILOGRAM / LUNAR_MASS_IN_KILOGRAM;
            case LUNAR_MASS:
                return value;
            case KILOGRAM:
                return value / LUNAR_MASS_IN_KILOGRAM;
            default:
                return Float.NaN;
        }
    }

    public float asEarthMass() {
        switch (unit) {
            case SOLAR_MASS:
                return value * SOLAR_MASS_IN_EARTH_MASS;
            case EARTH_MASS:
                return value;
            case LUNAR_MASS:
                return value * LUNAR_MASS_IN_KILOGRAM / EARTH_MASS_IN_KILOGRAM;
            case KILOGRAM:
                return value / EARTH_MASS_IN_KILOGRAM;
            default:
                return Float.NaN;
        }
    }

    public float asSolarMass() {
        switch (unit) {
            case SOLAR_MASS:
                return value;
            case EARTH_MASS:
                return value / SOLAR_MASS_IN_EARTH_MASS;
            case LUNAR_MASS:
                return value * LUNAR_MASS_IN_KILOGRAM / SOLAR_MASS_IN_KILOGRAM;
            case KILOGRAM:
                return value / SOLAR_MASS_IN_KILOGRAM;
            default:
                return Float.NaN;
        }
    }

    public void addMass(Mass massToBeAddedToTheBody) {
        switch (unit) {
            case KILOGRAM:
                value += massToBeAddedToTheBody.asKilogram();
                break;
            case SOLAR_MASS:
                value += massToBeAddedToTheBody.asSolarMass();
                break;
            case EARTH_MASS:
                value += massToBeAddedToTheBody.asEarthMass();
                break;
            case LUNAR_MASS:
                value += massToBeAddedToTheBody.asLunarMass();
                break;
            default:
                value = Float.NaN;
                break;
        }
    }

    @Override
    public String toString() {
        if (this.asSolarMass() > 0.01) {
            return formatValue(this.asSolarMass()) + " Solar Masses";
        }
        if (this.asEarthMass() > 0.01) {
            return formatValue(this.asEarthMass()) + " Earth Masses";
        }
        if (this.asLunarMass() > 0.01) {
            return formatValue(this.asLunarMass()) + " Lunar Masses";
        }
        if (this.asKilogram() > 10 * QUINTILLION) {
            return formatValue(this.asKilogram() / (10 * QUINTILLION)) + " x10^19 kg";
        }
        if (this.asKilogram() > QUINTILLION) {
            return formatValue(this.asKilogram() / QUINTILLION) + " x10^18 kg";
        }
        if (this.asKilogram() > 100 * QUADRILLION) {
            return formatValue(this.asKilogram() / (100 * QUADRILLION)) + " x10^17 kg";
        }
        if (this.asKilogram() > 10 * QUADRILLION) {
            return formatValue(this.asKilogram() / (10 * QUADRILLION)) + " x10^16 kg";
        }
        if (this.asKilogram() > 0.01 * QUADRILLION) {
            return formatValue(this.asKilogram() / QUADRILLION) + " x10^15 kg";
        }
        return formatValue(this.asKilogram() / (0.1f * QUADRILLION)) + " x10^14 kg";
    }

    private String formatValue(float value) {
        return String.format("%.02f", value);
    }

    public enum MassUnit {
        KILOGRAM,
        SOLAR_MASS,
        EARTH_MASS,
        LUNAR_MASS
    }
}
