package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.*;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Angle.AngularUnit;
import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;
import dhbw.karlsruhe.it.solar.core.usercontrols.SystemRoot;

import java.math.BigDecimal;

public class OrbitalProperties {
    private CenterOfOrbit orbitPrimary;
    private Length orbitalRadius;
    private Angle orbitalAngle;
    private Time orbitalPeriod;
    private Angle periodicConstant;
    private boolean retrograde;
    private CoOrbital coOrbital;

    private static BigDecimal maxDelta = BigDecimal.ZERO;

    public OrbitalProperties(CenterOfOrbit orbitPrimary, Length orbitalRadius, Angle angle) {
        this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.orbitalAngle = angle;
        this.retrograde = false;
        this.coOrbital = null;
        if (orbitPrimary != null) {
            calculateOrbitalPeriod();
            calculatePeriodicConstant();
        }
    }

    /**
     * Special constructor used for the solar system - describes orbit around a galactic core!
     *
     * @param time
     * @param length
     */
    public OrbitalProperties(Time time, Length length) {
        this.orbitPrimary = new SystemRoot(0, 0);
        this.orbitalRadius = length;
        this.orbitalAngle = new Angle();
        this.retrograde = false;
        this.coOrbital = null;
        this.orbitalPeriod = time;
        this.periodicConstant = new Angle();
    }

    public static SolarActorScale getOrbitalSpaceUnitScaleFactor(Orbiter orbitPrimary) {
        if (orbitPrimary instanceof Star) {
            return ConfigurationConstants.SCALE_FACTOR_PLANET;
        }
        if (orbitPrimary instanceof Planet) {
            return ConfigurationConstants.SCALE_FACTOR_MOON;
        }
        if (orbitPrimary instanceof Moon) {
            return ConfigurationConstants.SCALE_FACTOR_MOON;
        }
        if (orbitPrimary instanceof Asteroid) {
            return ConfigurationConstants.SCALE_FACTOR_ASTEROID;
        }
        return ConfigurationConstants.SCALE_FACTOR_PLANET;
    }

    /**
     * Variant for small bodies with insignificant mass - used as approximation for all units.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod() {
        orbitalPeriod = new Time((float) (Math.sqrt((PhysicalConstants.PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometers() * 1000, 3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (orbitPrimary.getMass().asKilogram()))) / (3600 * 24)), TimeUnit.DAYS);
    }

    /**
     * The Periodic Constant denotes the rate of (angle change in degree) per (time increment), as implied by the Orbital Period in Days value.
     * One full revolution, accomplished in the orbital period time, corresponds to an angle change of 360 degrees.
     * The Periodic Constant is consequently the fraction of those values.
     */
    private void calculatePeriodicConstant() {
        if (0 != orbitalPeriod.inDays()) {
            periodicConstant = new Angle(360f / orbitalPeriod.inDays(), AngularUnit.DEGREE);
            return;
        }
        periodicConstant = new Angle();
    }

    public Angle getOrbitalAngle() {
        return orbitalAngle;
    }

    public Time getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Length getOrbitalRadius() {
        return orbitalRadius;
    }

    /**
     * Increases the angleInDegree value of the Orbital Properties by the amount given in the parameter.
     * Checks for the overflow condition (resets at 360 degrees).
     */
    private void updateOrbitalAngle(Angle change) {
        orbitalAngle.changeBy(change);
    }

    public void updateOrbitalAngle(float increment) {
        float change = increment;
        if (retrograde) {
            change = -increment;
        }
        updateOrbitalAngle(new Angle(periodicConstant.inDegrees() * change, AngularUnit.DEGREE));
    }

    private double getPrimaryX() {
        return orbitPrimary.getXDouble() + orbitPrimary.getWidth() / 2;
    }

    private double getPrimaryY() {
        return orbitPrimary.getYDouble() + orbitPrimary.getHeight() / 2;
    }

    public void setNewOrbitPrimary(CenterOfOrbit body) {
        orbitPrimary = body;
    }

    /**
     * @return Calculates the X-axis point around which the astronomical body orbits based on its Origin attribute.
     */
    public double calculateCenterOfOrbitX() {
        return getPrimaryX();
    }

    /**
     * @return Calculates the Y-axis point around which the astronomical body orbits based on its Origin attribute.
     */
    public double calculateCenterOfOrbitY() {
        return getPrimaryY();
    }

    public Angle getPeriodicConstant() {
        return periodicConstant;
    }

    /**
     * Part of the calculateOrbitalPositionTotal method, calculates the X-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     *
     * @param orbitalRadiusInPixels In-Game radius of the body caused by scaling.
     * @param deltaAlpha            Additional change in orbital angle which will be taken into account during the calculation.
     * @return X-axis position of the body.
     */
    private double calculateOrbitalPositionX(double orbitalRadiusInPixels, Angle deltaAlpha) {
        return calculateCenterOfOrbitX() + Math.cos(Math.toRadians(orbitalAngle.inDegrees() + deltaAlpha.inDegrees())) * orbitalRadiusInPixels;
    }

    /**
     * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     *
     * @return current Y-axis position of the body
     */
    private double calculateOrbitalPositionY(double orbitalRadiusInPixels, Angle deltaAlpha) {
        return calculateCenterOfOrbitY() + Math.sin(Math.toRadians(orbitalAngle.inDegrees() + deltaAlpha.inDegrees())) * orbitalRadiusInPixels;
    }

    public mikera.vectorz.Vector2 calculateFuturePosition(double orbitalRadiusInPixels, double delta) {
        Angle deltaAlpha = predictedChangeInOrbitalAngle(delta);
        return new mikera.vectorz.Vector2(calculateOrbitalPositionX(orbitalRadiusInPixels, deltaAlpha), calculateOrbitalPositionY(orbitalRadiusInPixels, deltaAlpha));
    }

    private Angle predictedChangeInOrbitalAngle(double delta) {
        return new Angle(periodicConstant.inDegrees() * (float) delta, AngularUnit.DEGREE);
    }

    /**
     * Returns a Vector2 containing the position of this objects center of orbit.
     *
     * @return Vector2
     */
    public mikera.vectorz.Vector2 getCenterOfOrbit() {
        return new mikera.vectorz.Vector2(calculateCenterOfOrbitX(), calculateCenterOfOrbitY());
    }

    public CenterOfOrbit getPrimary() {
        return orbitPrimary;
    }

    /**
     * Calculates the maximum possible orbital radius for a valid orbit around the target astronomical body.
     * This max radius is derived from the Hill sphere formula. An astronomical body's Hill sphere is the region in which it dominates the attraction of satellites.
     * This Hill sphere radius is approximately: r = R * CubicRoot( m / 3M ) with R-distance between two objects, m - smaller Mass, M - heavier Mass
     *
     * @param massOfSatellite Mass of the body for which the hill radius is supposed to be calculated.
     * @return Distance value of the maximum orbital radius in physical units.
     */
    public Length calculateMaxOrbitalRadius(Mass massOfSatellite) {
        if (orbitPrimary instanceof SolarSystem) {
            float primaryMass = orbitPrimary.getMass().asSolarMass();
            float satelliteMass = massOfSatellite.asSolarMass();
            float difference = primaryMass - satelliteMass;
            // if the difference is below a threshold we can safely assume it's not a twin star system
            // TODO: For a more precise check, avoid this calculation by having an attribute which knows the number of stars added during system creation?
            if (difference < 0.01f) {
                return new Length(1, Length.DistanceUnit.LIGHTYEAR);
            }
        }
        return new Length(orbitalRadius.asKilometers() * (float) (Math.cbrt(massOfSatellite.asEarthMass() / (3 * orbitPrimary.getMass().asEarthMass()))), Length.DistanceUnit.KILOMETERS);
    }

    /**
     * Calculates the gravitational potential of the parameter astronomical body at the location of the space unit.
     * Gravitational Potential in the circular gravitational field of a point mass is: g(r) = - G * M / r� with G - Gravitational Constant, M - Mass of the point mass, r - radius to point mass
     * Since this method compares to values relative to each other, this can be reduced to g1(r) = M1 / r�, g2(r) = M2 / r�
     *
     * @param mass Mass of the circular gravitational field.
     * @return Value of the gravitational potential.
     */
    public float gravitationalPotential(Mass mass, Length radius) {
        return (float) (mass.asKilogram() / Math.pow(radius.asKilometers(), 2));
    }

    public boolean isRetrograde() {
        return retrograde;
    }

    public void setRetrograde() {
        retrograde = true;
    }

    public boolean isCoOrbital() {
        return null != coOrbital;
    }

    public void setCoOrbital(Orbiter dominantBody, Angle angularDeviation) {
        coOrbital = new CoOrbital(dominantBody, angularDeviation);
        orbitalAngle.changeBy(angularDeviation);
    }

    public mikera.vectorz.Vector2 getOrbitalPositionTotal(double orbitalRadiusInPixels, Angle deltaAlpha) {
        return new mikera.vectorz.Vector2(calculateOrbitalPositionX(orbitalRadiusInPixels, deltaAlpha), calculateOrbitalPositionY(orbitalRadiusInPixels, deltaAlpha));
    }

    public CoOrbital getCoOrbitalInformation() {
        return coOrbital;
    }

    public boolean orbitingStar() {
        return orbitPrimary instanceof Star;
    }

    private CenterOfOrbit getOrbitPrimary() {
        return orbitPrimary;
    }
}
