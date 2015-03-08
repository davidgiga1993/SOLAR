package dhbw.karlsruhe.it.solar.core.physics;

/**
 * 
 * @author Andi
 *
 */
public class Angle {
	
    public static final float DEGREE_IN_RADIANS = (float)( 180f / Math.PI);
    public static final float RADIANS_CIRCLE = (float) (2 * Math.PI);
    
    private Unit unit;
    private float value;
    
    public Angle()
    {
    	this.unit = Unit.degree;
    	this.value = 0f;
    }
    
    public Angle(float value)
    {
    	this.unit = Unit.degree;
    	this.value = value;
    }
    
	public Angle (float value, Unit unit)
	{
		this.unit = unit;
		this.value = value;
	}
	
    public enum Unit {
        degree,
        radians
    }
    
    public float inDegrees() {
        switch(unit) {
            case degree:
                return value;
            case radians:
                return value * DEGREE_IN_RADIANS;
            default:
                return Float.NaN;
        }
    }
    
    public float inRadians() {
        switch(unit) {
            case degree:
                return value / DEGREE_IN_RADIANS;
            case radians:
                return value;
            default:
                return Float.NaN;
        }
    }

	public void changeBy(Angle change) {
        switch(unit) {
        case degree:
            value += change.inDegrees();
            break;
        case radians:
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
        switch(unit) {
        case degree:
            value = value < 360 ? value : value - 360;
            value = value > - 360 ? value : value + 360;
            break;
        case radians:
        	value = value < RADIANS_CIRCLE ? value : value - RADIANS_CIRCLE;
        	value = value > - RADIANS_CIRCLE ? value : value + RADIANS_CIRCLE;
        	break;
        default:
                value = Float.NaN;
        }
	}

}
