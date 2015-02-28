package dhbw.karlsruhe.it.solar.core.physics;

public class OrbitalProperties
{
	private static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;
	
	private Length orbitalRadius;
	private float angle;
	private float orbitalPeriodInDays;
	private BodyProperties originProperties;
    
    public OrbitalProperties(Length orbitalRadius, float angle, BodyProperties originProperties)
    {
        this.orbitalRadius = orbitalRadius;
        this.angle = angle;
        this.originProperties = originProperties;
        calculateOrbitalPeriodUnit();
    }
    
    public OrbitalProperties(Mass mass, Length orbitalRadius, float angle, BodyProperties originProperties)
    {
        this.orbitalRadius = orbitalRadius;
        this.angle = angle;
        this.originProperties = originProperties;
        calculateOrbitalPeriodBody(mass);
    }
    
    /**
     * Variant for sufficiently massive bodies - used for astronomical objects.
     * Calculates and sets the orbital period based on Kepler's Third Law of Planetary Motion.
     */
    private void calculateOrbitalPeriodBody(Mass mass) {
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
    private void calculateOrbitalPeriodUnit() {
        if(originProperties == null) {
            orbitalPeriodInDays = 1;
            return;
        }
        orbitalPeriodInDays = (float) (Math.sqrt( (PI_SQUARE_TIMES_FOUR * Math.pow(orbitalRadius.asKilometres() * 1000,3)) / (PhysicalConstants.GRAVITATIONAL_CONSTANT * (originProperties.getMass().getAsKilogram())) ) / (3600*24));
    }
    
    public float getOrbitalAngle()
    {
    	return angle;
    }
    
    public float getOrbitalPeriodInDays()
    {
    	return orbitalPeriodInDays;
    }
    
    public Length getOrbitalRadius()
    {
    	return orbitalRadius;
    }

}
