package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
import dhbw.karlsruhe.it.solar.core.solar.logic.Mass;

import java.util.List;

/**
 * @author Andi
 *
 */
public abstract class AstronomicalBody extends SolarActor implements ShapeRenderable, KinematicObject {

	protected BodyProperties physicalProperties;

	protected double orbitalRadiusInKilometers;
	protected double orbitalPeriodInDays;
	protected double massInKilogram;
	protected float angleInDegree;
	protected AstronomicalBody origin;
	protected Group satellites;



	protected float periodicConstant;

	protected Kinematic kinematic;

	float orbitalRadiusInPixels;
	//protected Color color = Color.WHITE;

	public AstronomicalBody(String name)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = 0;
		this.angleInDegree = 0;
		this.origin = null;
		this.orbitalPeriodInDays = -1;
		this.massInKilogram = 1;
	}
	
	public AstronomicalBody(String name, Length radius, double orbitalRadiusInKilometres, double massInKilograms, float angleInDegree, AstronomicalBody origin, SolarActorScale scaleFactor)
	{
		super(name);
		setActorScale(scaleFactor);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = orbitalRadiusInKilometres;
		this.massInKilogram = massInKilograms;
		this.angleInDegree = angleInDegree;
		this.origin = origin;
		this.physicalProperties = new BodyProperties(new Mass((float) massInKilograms, Mass.Unit.KILOGRAM), radius, new Length((float) orbitalRadiusInKilometres, Length.Unit.kilometres), angleInDegree, origin.physicalProperties);
		// this remains here in order to allow the modification of the simulated world without altering the physics behind them.
		this.orbitalPeriodInDays = calculateOrbitalPeriod();
		if (orbitalPeriodInDays != 0) {
			this.periodicConstant = 360 / (float) orbitalPeriodInDays;
		} else {
			this.periodicConstant = 0;
		}
		changeScale();
		float speed = (float) ((2 * Math.PI * scaleDistanceToStage(orbitalRadiusInKilometres)) / orbitalPeriodInDays);
		this.kinematic = new Kinematic(new Vector2(getX(), getY()), 0, speed);
		this.kinematic.velocity = new Vector2(1,0).scl(speed);
	}

	private void changeScale() {
		float tSize = scaleDistanceToStage(physicalProperties.radius.asKilometres()) * actorScale.shapeScale * 2;
		this.setSize(tSize, tSize);
		orbitalRadiusInPixels = scaleDistanceToStage(orbitalRadiusInKilometers) * actorScale.orbitScale;
	}

	@Override
	public void updateScale() {
		orbitalRadiusInPixels *= actorScale.orbitScale / currentOrbitScale;
		super.updateScale();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		angleInDegree += periodicConstant * delta;
		calculateOrbitalPositionTotal();

		kinematic.position.x = getX()+getOriginX();
		kinematic.position.y = getY()+getOriginY();
		kinematic.rotation = angleInDegree + 90f;
		kinematic.velocity.rotate(kinematic.rotation);
	}

	@Override
	public void drawLines(ShapeRenderer shapeRenderer) {
		displayOrbit(shapeRenderer);
		drawBody(shapeRenderer);
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
	 * @param radius Desired radius of the Asteroid
     * @param massInKilogram Desired mass of the Asteroid in kilogram
     * @param orbitalRadiusInKilometers Desired orbital radius around the parent body in kilometers
     * @param angleInDegree Desired angle of the Asteroid's position on the map of the system relative to its parent body
     * @return created Asteroid object
     */
    public Asteroid placeNewAsteroid(String name, Length radius, double massInKilogram, double orbitalRadiusInKilometers, int angleInDegree)
    {
        Asteroid newObject = new Asteroid(name, radius, massInKilogram, orbitalRadiusInKilometers, angleInDegree, this);
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
		return (float) (calculateCenterOfOrbitX() + (float) Math.cos(Math.toRadians(angleInDegree)) * orbitalRadiusInPixels);
	}

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
	 * @return current Y-axis position of the body
	 */
	protected float calculateOrbitalPositionY() {
		return (float) (calculateCenterOfOrbitY() + (float) Math.sin(Math.toRadians(angleInDegree))  * orbitalRadiusInPixels);
	}
	    
    protected void displayOrbit(ShapeRenderer shapeRenderer)
    {
    	if (scaleDistanceToStage(orbitalRadiusInKilometers) < getParent().getWidth())
    		return;


        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.circle(calculateCenterOfOrbitX(), calculateCenterOfOrbitY(), orbitalRadiusInPixels);
    }

	/**
	 * This method will draw the Body of this object using a shapeRenderer and the object's color field.
	 * @param shapeRenderer
	 */
	protected void drawBody(ShapeRenderer shapeRenderer) {
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
		shapeRenderer.setColor(this.getColor());
		shapeRenderer.circle(getX() + getWidth() / 2, getY() + getHeight() / 2, getHeight() / 2);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
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
		return this.physicalProperties.orbitalPeriodInDays;
    }
    
    protected static double convertEarthMassesIntoKilogram( double massInEarthMasses)
    {
    	return massInEarthMasses * 5.97219 * Math.pow(10, 24);
    }
    
    protected static double convertSolarMassesIntoKilogram( double massInSolarMasses)
    {
    	return massInSolarMasses * 1.98855 * Math.pow(10, 30);
    }

	@Override
	public Kinematic getKinematic() {
		return kinematic;
	}

}
