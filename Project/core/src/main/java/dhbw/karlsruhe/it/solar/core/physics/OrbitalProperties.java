package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

public class OrbitalProperties
{
	private static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;
	
	private AstronomicalBody orbitPrimary;
	private Length orbitalRadius;
	private float angleInDegree;
	private float orbitalPeriodInDays;
    
    public OrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, float angle)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.angleInDegree = angle;
    	if (orbitPrimary != null)
    	{
            calculateOrbitalPeriod();
    	}
    }
    
    public OrbitalProperties(AstronomicalBody orbitPrimary, Mass mass, Length orbitalRadius, float angle)
    {
    	this.orbitPrimary = orbitPrimary;
        this.orbitalRadius = orbitalRadius;
        this.angleInDegree = angle;
    	if (orbitPrimary != null)
    	{
            calculateOrbitalPeriod(mass);
    	}
    }
    
    /**
     * Variant for sufficiently massive bodies - used for astronomical objects.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod(Mass mass) {
    	BodyProperties originProperties = orbitPrimary.getBodyProperties();
        if(originProperties == null || originProperties.getMass() == null) {
            orbitalPeriodInDays = 1;
            return;
        }
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (originProperties.getMass().getAsKilogram() + mass.getAsKilogram())) ) / (3600*24));
    }
    
    /**
     * Variant for small bodies with insignificant mass - used for player units.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriod() {
    	BodyProperties originProperties = orbitPrimary.getBodyProperties();
        if(originProperties == null) {
            orbitalPeriodInDays = 1;
            return;
        }
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (originProperties.getMass().getAsKilogram())) ) / (3600*24));
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

}
