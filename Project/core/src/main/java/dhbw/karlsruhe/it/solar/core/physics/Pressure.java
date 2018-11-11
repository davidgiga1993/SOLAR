package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * Governs behavior of physical attribute of pressure.
 *
 * @author Andi
 * Created: 2015-04-11
 */
public class Pressure {

    private static final float STANDARDATMOSPHERE_IN_PASCAL = 101325f;
    private static final float KILOPASCAL_IN_PASCAL = 1000f;
    private static final float BAR_IN_PASCAL = 100000f;
    private static final float THOUSAND = 1000f;
    private static final float MILLION = 1000000f;

    @XmlElement(name = "Pressure_Value")
    private float value;
    @XmlElement(name = "Pressure_Unit")
    private PressureUnit unit;

    public Pressure() {

    }

    public Pressure(float value, PressureUnit unit) {
        if (0 <= value) {
            this.value = value;
            this.unit = unit;
            return;
        }
        throw new IllegalArgumentException("Pressure must be positive value or zero!");
    }

    public float asPascal() {
        switch (unit) {
            case PASCAL:
                return value;
            case KILOPASCAL:
                return value * KILOPASCAL_IN_PASCAL;
            case BAR:
                return value * BAR_IN_PASCAL;
            case STANDARDATMOSPHERE:
                return value * STANDARDATMOSPHERE_IN_PASCAL;
            default:
                return Float.NaN;
        }
    }

    public float asKiloPascal() {
        switch (unit) {
            case PASCAL:
                return value / KILOPASCAL_IN_PASCAL;
            case KILOPASCAL:
                return value;
            case BAR:
                return value * KILOPASCAL_IN_PASCAL / BAR_IN_PASCAL;
            case STANDARDATMOSPHERE:
                return value * STANDARDATMOSPHERE_IN_PASCAL / KILOPASCAL_IN_PASCAL;
            default:
                return Float.NaN;
        }
    }

    public float asBar() {
        switch (unit) {
            case PASCAL:
                return value / BAR_IN_PASCAL;
            case KILOPASCAL:
                return value * KILOPASCAL_IN_PASCAL / BAR_IN_PASCAL;
            case BAR:
                return value;
            case STANDARDATMOSPHERE:
                return value * STANDARDATMOSPHERE_IN_PASCAL / BAR_IN_PASCAL;
            default:
                return Float.NaN;
        }
    }

    public float asStandardatmospheres() {
        switch (unit) {
            case PASCAL:
                return value / STANDARDATMOSPHERE_IN_PASCAL;
            case KILOPASCAL:
                return value / STANDARDATMOSPHERE_IN_PASCAL * KILOPASCAL_IN_PASCAL;
            case BAR:
                return value / STANDARDATMOSPHERE_IN_PASCAL * BAR_IN_PASCAL;
            case STANDARDATMOSPHERE:
                return value;
            default:
                return Float.NaN;
        }
    }

    public enum PressureUnit {
        PASCAL,
        KILOPASCAL,
        BAR,
        STANDARDATMOSPHERE,
    }

    @Override
    public String toString() {
        if (0.01f < this.asBar()) {
            return formatValue(this.asBar()) + " bar";
        }
        if (0.01f < this.asPascal()) {
            return formatValue(this.asPascal()) + " Pa";
        }
        if (0.01f < this.asPascal() * THOUSAND) {
            return formatValue(this.asPascal() * THOUSAND) + " mPa";
        }
        if (0.01f < this.asPascal() * MILLION) {
            return formatValue(this.asPascal() * MILLION) + " microPa";
        }
        return formatValue(this.asPascal() * MILLION * THOUSAND) + " nPa";
    }

    private String formatValue(float value) {
        return String.format("%.02f", value);
    }

}
