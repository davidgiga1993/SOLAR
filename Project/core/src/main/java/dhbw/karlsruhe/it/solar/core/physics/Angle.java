package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Andi
 */
public class Angle {

    private static final double DEGREE_IN_RADIANS = 180. / Math.PI;
    private static final double RADIANS_CIRCLE = 2. * Math.PI;

    @XmlElement
    private double value;
    @XmlElement(name = "angular_unit")
    private AngularUnit angularUnit;

    public Angle() {
        this(0, AngularUnit.DEGREE);
    }

    public Angle(double value, AngularUnit unit) {
        this((float) value, unit);
    }

    public Angle(float value, AngularUnit unit) {
        this.angularUnit = unit;
        this.value = value;
        // FIXME: specifically set whether an angle should be normalized or not
        // defaulting to normalization leads to weird results in calculating periodic constants for Orbiters
        // it's not disabled when calling the change function in order to prevent accumulating huge angles over time
        //preventOverflow();
    }

    public double inDegrees() {
        switch (angularUnit) {
            case DEGREE:
                return value;
            case RADIANS:
                return value * DEGREE_IN_RADIANS;
            default:
                return Double.NaN;
        }
    }

    private double inRadians() {
        switch (angularUnit) {
            case DEGREE:
                return value / DEGREE_IN_RADIANS;
            case RADIANS:
                return value;
            default:
                return Double.NaN;
        }
    }

    public void changeBy(Angle change) {
        switch (angularUnit) {
            case DEGREE:
                value += change.inDegrees();
                break;
            case RADIANS:
                value += change.inRadians();
                break;
            default:
                value = Double.NaN;
        }
        preventOverflow();
    }

    /**
     * Make sure that no overflow happens.
     */
    private void preventOverflow() {
        // FIXME: should be called normalize and should be able to cope with values > 2x max
        switch (angularUnit) {
            case DEGREE:
                value = value < 360 ? value : value - 360;
                value = value > -360 ? value : value + 360;
                break;
            case RADIANS:
                value = value < RADIANS_CIRCLE ? value : value - RADIANS_CIRCLE;
                value = value > -RADIANS_CIRCLE ? value : value + RADIANS_CIRCLE;
                break;
            default:
                value = Double.NaN;
        }
    }

    public enum AngularUnit {
        DEGREE,
        RADIANS
    }

    @Override
    public String toString() {
        switch (angularUnit) {
            case DEGREE:
                return value + "°deg";
            case RADIANS:
                return value + "°rad";
        }
        return value + "°n/a";
    }
}
