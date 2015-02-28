package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties
{
    private Mass mass;
    private Length radius;
    private OrbitalProperties orbit;

    public BodyProperties(Mass mass, Length radius, Length orbitalRadius, float angle, BodyProperties originProperties) {
        this.mass = mass;
        this.radius = radius;
        this.orbit = new OrbitalProperties(orbitalRadius, angle, originProperties);
    }
    
    public Mass getMass()
    {
    	return mass;
    }
    
    public float getOrbitalAngle()
    {
    	return orbit.getOrbitalAngle();
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
}
