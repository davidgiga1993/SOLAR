package dhbw.karlsruhe.it.solar.core.usercontrols;

/**
 * Created by Arga on 11.02.2015.
 */
public class SolarActorScale {
    public float shapeScale;
    public float orbitScale;

    public SolarActorScale(float shapeScale, float orbitScale) {
        this.shapeScale = shapeScale;
        this.orbitScale = orbitScale;
    }

    public void set(float shapeScale, float orbitScale) {
        this.shapeScale = shapeScale;
        this.orbitScale = orbitScale;
    }
}
