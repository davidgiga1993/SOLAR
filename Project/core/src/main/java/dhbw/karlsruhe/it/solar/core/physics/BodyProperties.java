package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
import dhbw.karlsruhe.it.solar.core.solar.logic.Mass;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties {

    public static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;

    public Mass mass;
    public Length radius;
    public Length orbitalRadius;
    public float angle;

    protected BodyProperties originProperties;

    public float orbitalPeriodInDays;

    public BodyProperties(Mass mass, Length radius, Length orbitalRadius, float angle, BodyProperties originProperties) {
        this.mass = mass;
        this.radius = radius;
        this.orbitalRadius = orbitalRadius;
        this.angle = angle;
        this.originProperties = originProperties;

        calculateOrbitalPeriod();
    }

    /**
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion
     */
    public void calculateOrbitalPeriod() {
        if(originProperties == null || originProperties.mass == null) {
            orbitalPeriodInDays = 1;
            return;
        }
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (originProperties.mass.getAsKilogram() + mass.getAsKilogram())) ) / (3600*24));
    }
}
