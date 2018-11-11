package dhbw.karlsruhe.it.solar.core.physics;

public class SurfaceGravity {

    private final static float MM_PER_M = 1000;

    private float value;
    private GravUnit unit;

    public SurfaceGravity(Mass mass, Length radius) {
        calculateSurfaceGravity(mass, radius);
    }

    public SurfaceGravity(float value, GravUnit unit) {
        if (0 <= value) {
            this.value = value;
            this.unit = unit;
            return;
        }
        throw new IllegalArgumentException("Gravity must be positive value or zero!");
    }

    private void calculateSurfaceGravity(Mass mass, Length radius) {
        this.value = ((PhysicalConstants.GRAVITATIONAL_CONSTANT * mass.asKilogram()) / ((float) Math.pow(radius.asMeters(), 2)));
        this.unit = GravUnit.MS2;
    }

    @Override
    public String toString() {
        if (this.inMS2() < 1f) {
            return formatValueNoDecimal(this.inMS2() * MM_PER_M) + " mm/s2";
        }
        if (this.inG() < 0.1f) {
            return formatValueNoDecimal(this.inMS2()) + " m/s2";
        }
        return formatValue(this.inG()) + " g";
    }

    private String formatValue(float value) {
        return String.format("%.02f", value);
    }

    private String formatValueNoDecimal(float value) {
        return String.format("%.00f", value);
    }

    public float inG() {
        switch (unit) {
            case G:
                return value;
            case MS2:
                return value / PhysicalConstants.EARTH_SURFACE_GRAVITY.value;
            default:
                return Float.NaN;
        }
    }

    private float inMS2() {
        switch (unit) {
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
