package dhbw.karlsruhe.it.solar.core.physics;

public class SurfaceGravity {
    
    private float value;
    private GravUnit unit;

    public SurfaceGravity(Mass mass, Length radius) {
        calculateSurfaceGravity(mass, radius);
    }
    
    public SurfaceGravity(float value, GravUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private void calculateSurfaceGravity(Mass mass, Length radius) {
        this.value = ( (PhysicalConstants.GRAVITATIONAL_CONSTANT * mass.asKilogram()) / ((float)Math.pow(radius.asMetres(), 2) ) );
        this.unit = GravUnit.MS2;
    }
    
    @Override
    public String toString() {
        return formatValue() + " g";
    }

    private String formatValue() {
        return String.format("%.02f", inG());
    }
    
    public float inG() {
        switch(unit) {
        case G:
            return value;
        case MS2:
            return value / PhysicalConstants.EARTH_SURFACE_GRAVITY.value;
        default:
            return Float.NaN;
        }
    }
    
    public float inMS2() {
        switch(unit) {
        case G:
            return value * PhysicalConstants.EARTH_SURFACE_GRAVITY.value;
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
