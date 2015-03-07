package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties
{
    private Mass mass;
    private Length radius;

    public BodyProperties(Mass mass, Length radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    public Mass getMass()
    {
    	return mass;
    }
    
    public Length getRadius()
    {
    	return radius;
    }

	public void addMass(Mass massToBeAddedToTheBody) {
		mass.addMass(massToBeAddedToTheBody);
		
	} 
}
