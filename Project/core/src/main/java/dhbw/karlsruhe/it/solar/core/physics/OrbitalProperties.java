package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

public class OrbitalProperties
{
	private static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;
	
	private AstronomicalBody orbitPrimary;
	private Length orbitalRadius;
	private float angleInDegree;
	private float orbitalPeriodInDays;
	private float periodicConstant;
    
    public OrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, float angle)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.angleInDegree = angle;
    	if (orbitPrimary != null)
    	{
            calculateOrbitalPeriod();
    	}
    	calculatePeriodicConstant();
    }
    
	public OrbitalProperties(Mass mass, AstronomicalBody orbitPrimary, Length orbitalRadius, float angle)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.angleInDegree = angle;
    	if (orbitPrimary != null)
    	{
            calculateOrbitalPeriod(mass);
    	}
    	calculatePeriodicConstant();
    }
    
    /**
     * Variant for sufficiently massive bodies - used for astronomical objects.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod(Mass mass) {
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (orbitPrimary.getMass().getAsKilogram() + mass.getAsKilogram())) ) / (3600*24));
    }
    
    /**
     * Variant for small bodies with insignificant mass - used for player units.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod() {
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (orbitPrimary.getMass().getAsKilogram())) ) / (3600*24));
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
    		periodicConstant = 360 / orbitalPeriodInDays;
    		return;
    	}
    	periodicConstant = 0;
	}
    
    public float getOrbitalAngleInDegree()
    {
    	return angleInDegree;
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
    public void updateOrbitalAngle(float degreeOfAngleIncrease)
    {
    	angleInDegree += degreeOfAngleIncrease;
		// make sure that no overflow happens.
    	angleInDegree = angleInDegree < 360 ? angleInDegree : angleInDegree - 360;
    }
    
	public float getPrimaryX()
	{
		return orbitPrimary.getX();
	}  
	
	public float getPrimaryY()
	{
		return orbitPrimary.getY();
	}
	
	public float getPrimaryWidth()
	{
		return orbitPrimary.getWidth();
	}
    
	public float getPrimaryHeight()
	{
		return orbitPrimary.getHeight();
	}

	public void setNewOrbitPrimary(AstronomicalBody body) {
		orbitPrimary = body;		
	}
	
	/**
	 * @return Calculates the X-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	public float calculateCenterOfOrbitX() {
		return getPrimaryX() + getPrimaryWidth() / 2;
	}
	
	/**
	 * @return Calculates the Y-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	public float calculateCenterOfOrbitY() {
		// Position ist immer relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
		return getPrimaryY() + getPrimaryHeight() / 2;
	}
	
	public float getPeriodicConstant() {
		return periodicConstant;
	}
	
	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the X-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     * @param angleInDegree current angle
	 * @return current X-axis position of the body
	 */
	public float calculateOrbitalPositionX(float orbitalRadiusInPixels, float deltaAlphaInDegree) {
		return (float) (calculateCenterOfOrbitX() + Math.cos(Math.toRadians(angleInDegree + deltaAlphaInDegree)) * orbitalRadiusInPixels);
	}

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     * @param angleInDegree current angle
	 * @return current Y-axis position of the body
	 */
	public float calculateOrbitalPositionY(float orbitalRadiusInPixels, float deltaAlphaInDegree) {
		return (float) (calculateCenterOfOrbitY() + Math.sin(Math.toRadians(angleInDegree + deltaAlphaInDegree))  * orbitalRadiusInPixels);
	}
	
    public Vector2 calculateFuturePosition(float orbitalRadiusInPixels, float delta) {
        float deltaAlpha = periodicConstant * delta;
        return new Vector2(calculateOrbitalPositionX(orbitalRadiusInPixels, deltaAlpha), calculateOrbitalPositionY(orbitalRadiusInPixels, deltaAlpha));
    }
    
	/**
	 * Returns a Vector2 containing the position of this objects center of orbit.
	 * @return Vector2
	 */
	public Vector2 getCenterOfOrbit() {
		return new Vector2(calculateCenterOfOrbitX(), calculateCenterOfOrbitY());
	}
}
