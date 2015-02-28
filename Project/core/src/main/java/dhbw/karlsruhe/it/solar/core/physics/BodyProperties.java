package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties
{
    private Mass mass;
    private Length radius;
    private OrbitalProperties orbit;

    public BodyProperties(AstronomicalBody orbitPrimary, Mass mass, Length radius, Length orbitalRadius, float angle) {
        this.mass = mass;
        this.radius = radius;
        this.orbit = new OrbitalProperties(orbitPrimary, mass, orbitalRadius, angle);
    }
    
    public Mass getMass()
    {
    	return mass;
    }
    
    public float getOrbitalAngleInDegree()
    {
    	return orbit.getOrbitalAngleInDegree();
    }
    
    public float getOrbitalPeriodInDays()
    {
    	return orbit.getOrbitalPeriodInDays();
    }
    
    public Length getOrbitalRadius()
    {
    	return orbit.getOrbitalRadius();
    }
    
    public Length getRadius()
    {
    	return radius;
    }
    
    /**
     * Increases the angleInDegree value of the Orbital Properties by the amount given in the parameter.
     * Checks for the overflow condition (resets at 360 degrees).
     * @param degreeOfAngleIncrease Degrees to be added to the orbital angle of the object.
     */
    public void updateOrbitalAngle(float degreeOfAngleIncrease)
    {
    	orbit.updateOrbitalAngle(degreeOfAngleIncrease);
    }
    
	public float getPrimaryX()
	{
		return orbit.getPrimaryX();
	}
	
	public float getPrimaryY()
	{
		return orbit.getPrimaryY();
	}
	
	public float getPrimaryWidth()
	{
		return orbit.getPrimaryWidth();
	}
    
	public float getPrimaryHeight()
	{
		return orbit.getPrimaryHeight();
	}

	public void setNewOrbitPrimary(AstronomicalBody body) {
		orbit.setNewOrbitPrimary(body);		
	}
}
