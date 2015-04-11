package dhbw.karlsruhe.it.solar.core.physics;

public class SurfaceGravity {
    
    private float value;
    private GravUnit unit;

    public SurfaceGravity(Mass mass, Length radius) {
        calculateSurfaceGravity(mass, radius);
    }
    
    private void calculateSurfaceGravity(Mass mass, Length radius) {
        this.value = ( (PhysicalConstants.GRAVITATIONAL_CONSTANT * mass.asKilogram()) / ((float)Math.pow(radius.asMetres(), 2) ) );
        this.unit = GravUnit.MS2;
    }
    
    public float inG() {
        switch(unit) {
        case G:
            return value;
        case MS2:
            return value / PhysicalConstants.EARTH_SURFACE_GRAVITY;
        default:
            return Float.NaN;
        }
    }
    
    public float inMS2() {
        switch(unit) {
        case G:
            return value * PhysicalConstants.EARTH_SURFACE_GRAVITY;
        case MS2:
            return value;
        default:
            return Float.NaN;
        }
    }

    public enum GravUnit {
        G,
        MS2
    }

}
