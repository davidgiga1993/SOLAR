package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * @author Andi
 *
 */
public abstract class AstronomicalBody extends SolarActor {
	
	protected double orbitalRadiusInKilometers;
	protected double orbitalPeriodInDays;
	protected double massInKilogram;
	protected double angleInDegree;
	protected AstronomicalBody origin;
	protected Group satellites;
	
	public AstronomicalBody(String name)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = 0;
		this.angleInDegree = 0;
		this.origin = null;
		this.orbitalPeriodInDays = -1;
		this.massInKilogram = 0;
	}
	
	public AstronomicalBody(String name, double orbitalRadiusInMeters, double angleInDegree, AstronomicalBody origin)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = orbitalRadiusInMeters;
		this.angleInDegree = angleInDegree;
		this.origin = origin;
		this.orbitalPeriodInDays = calculateOrbitalPeriod();
	}
	
    public Group getSatellites()
    {
    	return satellites;
    }
    
    public int getNumberOfSatellites()
    {
    	if (satellites == null )
    		return 0;
    	else
    		return satellites.getChildren().size;
    }

    /**
     * Adds a new Asteroid with the specified parameters as a satellite orbiting the astronomical body.
     * @param name Desired name of the Asteroid.
     * @param massInKilogram Desired mass of the Asteroid in kilogram
     * @param orbitalRadiusInKilometers Desired orbital radius around the parent body in kilometers
     * @param angleInDegree Desired angle of the Asteroid's position on the map of the system relative to its parent body
     * @return created Asteroid object
     */
    public Asteroid placeNewAsteroid(String name, double massInKilogram, double orbitalRadiusInKilometers, int angleInDegree)
    {
        Asteroid newObject = new Asteroid(name, massInKilogram, orbitalRadiusInKilometers, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
    
    /**
     * Calculates the current position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     */
    protected void calculateOrbitalPositionTotal()
    {
    		this.setPosition(calculateOrbitalPositionX() - getWidth() / 2, calculateOrbitalPositionY() - getHeight() / 2);
    }

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the X-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
	 * @return current X-axis position of the body
	 */
	protected float calculateOrbitalPositionX() {
		return (float) (calculateCenterOfOrbitX() + (float) Math.cos(Math.toRadians(angleInDegree)) * scaleDistanceToStage(orbitalRadiusInKilometers));
	}

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
	 * @return current Y-axis position of the body
	 */
	protected float calculateOrbitalPositionY() {
		return (float) (calculateCenterOfOrbitY() + (float) Math.sin(Math.toRadians(angleInDegree))  * scaleDistanceToStage(orbitalRadiusInKilometers));
	}
	    
    protected void displayOrbit()
    {
    	if (scaleDistanceToStage(orbitalRadiusInKilometers) < getParent().getWidth())
    		return;
		shapeRenderer.begin(ShapeType.Line);             
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(calculateCenterOfOrbitX(), calculateCenterOfOrbitY(), scaleDistanceToStage(orbitalRadiusInKilometers));
        shapeRenderer.end();
    }
    
    
	/**
	 * Searches the satellites of a parent astronomical object for their names so that they can be added to a list.
	 * @param listOfSatellites List to which names are to be added.
	 */
	protected void addNamesOfSatellitesToList(List<String> listOfSatellites)
	{
		String name;
		for(int index = 0; index < satellites.getChildren().size; index++)
    	{
    		name = satellites.getChildren().get(index).getName();
    		listOfSatellites.add(name);
    	}
	}
	
	/**
	 * Searches the satellites of a parent astronomical object for a satellite with a matching name.
	 * @param name Searched for key word
	 * @return Satellite object with matching name
	 */
	public Actor findSatelliteByName(String name)
	{
		for(int index = 0; index < satellites.getChildren().size; index++)
    	{
			Actor object =  satellites.getChildren().get(index);
    		if ( object.getName().equals(name) )
    			return object;
    	}	
		return null;
	}

	/**
	 * @return Calculates the Y-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	private float calculateCenterOfOrbitY() {
        // Position ist immer relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
		return origin.getY() + origin.getHeight() / 2;
	}

	/**
	 * @return Calculates the X-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	private float calculateCenterOfOrbitX() {
		return origin.getX() + origin.getWidth() / 2;
	}
    
    /**
     * Determines the correct orbital period of the astronomical body around its parent object based on Kepler's Third Law of Planetary Motion.
     * @return Orbital period in days.
     */
    protected double calculateOrbitalPeriod()
    {
    	return Math.sqrt( 4 * Math.pow((Math.PI), 2) * Math.pow(orbitalRadiusInKilometers * 1000, 3) / (origin.getMass()+massInKilogram) / gravitationalConstant() ) / 24 / 3600;
    }
    
    private static double gravitationalConstant()
    {
    	return 6.67 * Math.pow(10, -11);
    }
    
    public double getMass()
    {
    	return massInKilogram;
    }
    
    protected static double convertEarthMassesIntoKilogram( double massInEarthMasses)
    {
    	return massInEarthMasses * 5.97219 * Math.pow(10, 24);
    }
    
    protected static double convertSolarMassesIntoKilogram( double massInSolarMasses)
    {
    	return massInSolarMasses * 1.98855 * Math.pow(10, 30);
    }
}