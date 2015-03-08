package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Angle.Unit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Asteroid;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarSystem;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star;

public class OrbitalProperties
{
	private static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;
	
	private AstronomicalBody orbitPrimary;
	private Length orbitalRadius;
	private Angle orbitalAngle;
	private float orbitalPeriodInDays;
	private Angle periodicConstant;
    
    public OrbitalProperties(AstronomicalBody orbitPrimary)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = new Length();
        this.orbitalAngle = new Angle();
        this.orbitalPeriodInDays = Float.NaN;
        this.periodicConstant = new Angle(Float.NaN);
    }
    
    public OrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, Angle angle)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.orbitalAngle = angle;
    	if (orbitPrimary != null)
    	{
            calculateOrbitalPeriod();
    	}
    	calculatePeriodicConstant();
    }
    
    
    /**
     * Variant for small bodies with insignificant mass - used as approximation for all units.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod() {
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (orbitPrimary.getMass().asKilogram())) ) / (3600*24));
    }
    
    /**
     * The Periodic Constant denotes the rate of (angle change in degree) per (time increment), as implied by the Orbital Period in Days value.
     * One full revolution, accomplished in the orbital period time, corresponds to an angle change of 360 degrees.
     * The Periodic Constant is consequently the fraction of those values.
     */
    private void calculatePeriodicConstant()
    {
    	if ( 0 != orbitalPeriodInDays )
    	{
    		periodicConstant = new Angle(360 / orbitalPeriodInDays , Unit.degree);
    		return;
    	}
    	periodicConstant = new Angle();
	}
    
    public Angle getOrbitalAngle()
    {
    	return orbitalAngle;
    }
    
    public float getOrbitalPeriodInDays()
    {
    	return orbitalPeriodInDays;
    }
    
    public Length getOrbitalRadius()
    {
    	return orbitalRadius;
    }
    
    /**
     * Increases the angleInDegree value of the Orbital Properties by the amount given in the parameter.
     * Checks for the overflow condition (resets at 360 degrees).
     * @param degreeOfAngleIncrease Degrees to be added to the orbital angle of the object.
     */
    public void updateOrbitalAngle(Angle change)
    {
    	orbitalAngle.changeBy(change);
    }
    
    public void updateOrbitalAngle(float increment)
    {
    	updateOrbitalAngle( new Angle( periodicConstant.inDegrees()*increment, Unit.degree));
    }
    
	public float getPrimaryX()
	{
		return orbitPrimary.getX()  + orbitPrimary.getWidth() / 2;
	}  
	
	public float getPrimaryY()
	{
		return orbitPrimary.getY()  + orbitPrimary.getHeight() / 2;
	}

	public void setNewOrbitPrimary(AstronomicalBody body) {
		orbitPrimary = body;		
	}
	
	/**
	 * @return Calculates the X-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	public float calculateCenterOfOrbitX() {
		return getPrimaryX();
	}
	
	/**
	 * @return Calculates the Y-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	public float calculateCenterOfOrbitY() {
		return getPrimaryY();
	}
	
	public Angle getPeriodicConstant() {
		return periodicConstant;
	}
	
	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the X-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
	 * @param orbitalRadiusInPixels In-Game radius of the body caused by scaling.
	 * @param deltaAlpha Additional change in orbital angle which will be taken into account during the calculation.
	 * @return X-axis position of the body.
	 */
	public float calculateOrbitalPositionX(float orbitalRadiusInPixels, Angle deltaAlpha) {
		return (float) (calculateCenterOfOrbitX() + Math.cos(Math.toRadians(orbitalAngle.inDegrees() + deltaAlpha.inDegrees())) * orbitalRadiusInPixels);
	}

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     * @param orbitalAngle current angle
	 * @return current Y-axis position of the body
	 */
	public float calculateOrbitalPositionY(float orbitalRadiusInPixels, Angle deltaAlpha) {
		return (float) (calculateCenterOfOrbitY() + Math.sin(Math.toRadians(orbitalAngle.inDegrees() + deltaAlpha.inDegrees()))  * orbitalRadiusInPixels);
	}
	
    public Vector2 calculateFuturePosition(float orbitalRadiusInPixels, float delta) {
        Angle deltaAlpha = predictedChangeInOrbitalAngle (delta);
        return new Vector2(calculateOrbitalPositionX(orbitalRadiusInPixels, deltaAlpha), calculateOrbitalPositionY(orbitalRadiusInPixels, deltaAlpha));
    }
    
    private Angle predictedChangeInOrbitalAngle(float delta) {
    	return new Angle( periodicConstant.inDegrees() * delta, Unit.degree );
    }
    
	/**
	 * Returns a Vector2 containing the position of this objects center of orbit.
	 * @return Vector2
	 */
	public Vector2 getCenterOfOrbit() {
		return new Vector2(calculateCenterOfOrbitX(), calculateCenterOfOrbitY());
	}
	
	public SolarActorScale getOrbitalSpaceUnitScaleFactor() {
		if (orbitPrimary instanceof Star)
			return ConfigurationConstants.SCALE_FACTOR_PLANET;
		if (orbitPrimary instanceof Planet)
			return ConfigurationConstants.SCALE_FACTOR_MOON;
		if (orbitPrimary instanceof Moon)
			return ConfigurationConstants.SCALE_FACTOR_MOON;
		if (orbitPrimary instanceof Asteroid)
			return ConfigurationConstants.SCALE_FACTOR_ASTEROID;
		return ConfigurationConstants.SCALE_FACTOR_PLANET;
	}
		
	public AstronomicalBody getPrimary() {
		return orbitPrimary;
	}
	
    /**
     * Calculates the maximum possible orbital radius for a valid orbit around the target astronomical body.
     * This max radius is derived from the Hill sphere formula. An astronomical body's Hill sphere is the region in which it dominates the attraction of satellites. 
     * This Hill sphere radius is approximately: r = R * CubicRoot( m / 3M ) with R-distance between two objects, m - smaller Mass, M - heavier Mass
     * @param massOfSatellite Mass of the body for which the hill radius is supposed to be calculated.
     * @return Distance value of the maximum orbital radius in physical units.
     */
	public Length calculateMaxOrbitalRadius(Mass massOfSatellite)
	{
		if (orbitPrimary instanceof SolarSystem && orbitPrimary.getMass().asSolarMass() == massOfSatellite.asSolarMass())
		{
			return new Length(1, Length.Unit.lightYear);
		}
		return new Length(orbitalRadius.asKilometres() * (float)(Math.cbrt( massOfSatellite.asEarthMass() / ( 3 * orbitPrimary.getMass().asEarthMass()))), Length.Unit.kilometres);
	}
	
	
    /**
     * Calculates the gravitational potential of the parameter astronomical body at the location of the space unit.
     * Gravitational Potential in the circular gravitational field of a point mass is: g(r) = - G * M / r² with G - Gravitational Constant, M - Mass of the point mass, r - radius to point mass
	 * Since this method compares to values relative to each other, this can be reduced to g1(r) = M1 / r², g2(r) = M2 / r²
     * @param mass Mass of the circular gravitational field.
     * @return Value of the gravitational potential.
     */
	public float gravitationalPotential(Mass mass, Length radius) {
    	return (float) (mass.asKilogram() / Math.pow(radius.asKilometres(), 2)); 
	}

	public void addAsSatellite(AstronomicalBody newSatellite) {
        orbitPrimary.addSatellite(newSatellite);
		
	}
}
