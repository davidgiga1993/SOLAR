package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Andi
 *
 */
public class Angle {

    private static final float DEGREE_IN_RADIANS = (float) (180f / Math.PI);
    private static final float RADIANS_CIRCLE = (float) (2 * Math.PI);
    
    @XmlElement
    private double value;
    @XmlElement(name = "angular_unit")
    private AngularUnit angularUnit;
    
    public Angle()    {
        this(0, AngularUnit.DEGREE);
    }
    
    public Angle (float value, AngularUnit unit)    {
        this.angularUnit = unit;
        this.value = value;
        preventOverflow();
    }
    
    public float inDegrees() {
        switch(angularUnit) {
            case DEGREE:
                return (float) value;
            case RADIANS:
                return (float) value * DEGREE_IN_RADIANS;
            default:
                return Float.NaN;
        }
    }

    private float inRadians() {
        switch(angularUnit) {
            case DEGREE:
                return (float) value / DEGREE_IN_RADIANS;
            case RADIANS:
                return (float) value;
            default:
                return Float.NaN;
        }
    }

    public void changeBy(Angle change) {
        switch(angularUnit) {
        case DEGREE:
            value += change.inDegrees();
            break;
        case RADIANS:
            value += change.inRadians();
            break;
        default:
            value = Float.NaN;
        }
        preventOverflow();
    }

    /**
     * Make sure that no overflow happens.
     */
    private void preventOverflow() {
        switch(angularUnit) {
        case DEGREE:
            value = value < 360 ? value : value - 360;
            value = value > - 360 ? value : value + 360;
            break;
        case RADIANS:
            value = value < RADIANS_CIRCLE ? value : value - RADIANS_CIRCLE;
            value = value > - RADIANS_CIRCLE ? value : value + RADIANS_CIRCLE;
            break;
        default:
                value = Float.NaN;
        }
    }

    public enum AngularUnit {
        DEGREE,
        RADIANS
    }
}
