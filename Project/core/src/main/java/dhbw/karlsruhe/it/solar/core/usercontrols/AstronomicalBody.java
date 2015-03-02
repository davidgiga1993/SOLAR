package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.stages.guielements.BodyGameLabel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi
 *
 */
public abstract class AstronomicalBody extends SolarActor implements ShapeRenderable, KinematicObject {

	protected BodyProperties physicalProperties;
	protected List<AstronomicalBody> satellites = new ArrayList<AstronomicalBody>();
	protected Kinematic kinematic;
	
    protected BodyGameLabel label;
	protected float periodicConstant;
	float orbitalRadiusInPixels;

	public AstronomicalBody(String name)
	{
		super(name);
		this.physicalProperties = new BodyProperties(null, new Mass(1, Mass.Unit.KILOGRAM), new Length(1, Length.Unit.kilometres), new Length(1, Length.Unit.kilometres), 0);
	}

	public AstronomicalBody(String name, BodyProperties properties, SolarActorScale scaleFactor, String textureName)
	{
		super(name);
		setActorScale(scaleFactor);
		setupSolarActorSprite(textureName);

		this.physicalProperties = properties;
		this.label = new BodyGameLabel(name);

		float orbitalPeriodInDays = physicalProperties.getOrbitalPeriodInDays();
		if (orbitalPeriodInDays != 0) {
			this.periodicConstant = 360 / orbitalPeriodInDays;
		} else {
			this.periodicConstant = 0;
		}

		changeScale();

		float orbitalRadiusInKilometres = properties.getOrbitalRadius().asKilometres();
		float speed = (float) ((2 * Math.PI * scaleDistanceToStage(orbitalRadiusInKilometres)) / orbitalPeriodInDays);
		this.kinematic = new Kinematic(new Vector2(getX(), getY()), 0, speed);
		this.kinematic.velocity = new Vector2(1,0).scl(speed);
	}

	private void changeScale() {
		float tSize = scaleDistanceToStage(physicalProperties.getRadius().asKilometres()) * actorScale.shapeScale * 2;
		this.setSize(tSize, tSize);
		orbitalRadiusInPixels = scaleDistanceToStage(physicalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}

	@Override
	public void updateScale() {
		orbitalRadiusInPixels *= actorScale.orbitScale / currentOrbitScale;
		super.updateScale();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		physicalProperties.updateOrbitalAngle(periodicConstant * delta);
		calculateOrbitalPositionTotal();

        if(label.isVisible()) {
            Vector2 labelPos = new Vector2(getX()+getWidth(), getY() + getHeight());
            getStage().getViewport().project(labelPos);
            label.setPosition(labelPos.x, labelPos.y);
        }

		kinematic.position.x = getX()+getOriginX();
		kinematic.position.y = getY()+getOriginY();
		kinematic.rotation = physicalProperties.getOrbitalAngleInDegree() + 90f;
		kinematic.velocity.setAngle(kinematic.rotation);
	}

	@Override
	public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
		displayOrbit(solarShapeRenderer);
	}

	protected void displayOrbit(SolarShapeRenderer shapeRenderer)
	{
		shapeRenderer.setColor(Color.TEAL);
		shapeRenderer.orbit(calculateCenterOfOrbitX(), calculateCenterOfOrbitY(), orbitalRadiusInPixels, 250);
	}

    /**
     * Adds a new Asteroid with the specified parameters as a satellite orbiting the astronomical body.
     * @param name Desired name of the Asteroid.
	 * @param radius Desired radius of the Asteroid
     * @param mass Desired mass of the Asteroid in kilogram
     * @param orbitalRadius Desired orbital radius around the parent body in kilometers
     * @param angleInDegree Desired angle of the Asteroid's position on the map of the system relative to its parent body
     * @return created Asteroid object
     */
    public Asteroid placeNewAsteroid(String name, Length radius, Mass mass, Length orbitalRadius, int angleInDegree)
    {
        Asteroid newObject = new Asteroid(name, radius, mass, orbitalRadius, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }

    /**
     * Calculates the current position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     */
    protected void calculateOrbitalPositionTotal()
    {
    		float angleInDegree = physicalProperties.getOrbitalAngleInDegree();
    		this.setPosition(calculateOrbitalPositionX(angleInDegree) - getWidth() / 2, calculateOrbitalPositionY(angleInDegree) - getHeight() / 2);
    }

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the X-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     * @param angleInDegree current angle
	 * @return current X-axis position of the body
	 */
	protected float calculateOrbitalPositionX(float angleInDegree) {
		return (float) (calculateCenterOfOrbitX() + Math.cos(Math.toRadians(angleInDegree)) * orbitalRadiusInPixels);
	}

	/**
	 * Part of the calculateOrbitalPositionTotal method, calculates the Y-axis position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     * @param angleInDegree current angle
	 * @return current Y-axis position of the body
	 */
	protected float calculateOrbitalPositionY(float angleInDegree) {
		return (float) (calculateCenterOfOrbitY() + Math.sin(Math.toRadians(angleInDegree))  * orbitalRadiusInPixels);
	}

	/**
	 * @return Calculates the X-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	protected float calculateCenterOfOrbitX() {
		return physicalProperties.getPrimaryX() + physicalProperties.getPrimaryWidth() / 2;
	}

	/**
	 * @return Calculates the Y-axis point around which the astronomical body orbits based on its Origin attribute.
	 */
	protected float calculateCenterOfOrbitY() {
		// Position ist immer relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
		return physicalProperties.getPrimaryY() + physicalProperties.getPrimaryHeight() / 2;
	}

	/**
	 * Returns a Vector2 containing the position of this objects center of orbit.
	 * @return Vector2
	 */
	public Vector2 getCenterOfOrbit() {
		return new Vector2(calculateCenterOfOrbitX(), calculateCenterOfOrbitY());
	}

	/**
	 * Searches the satellites of a parent astronomical object for a satellite with a matching name.
	 * @param name Searched for key word
	 * @return Satellite object with matching name or null if no satellite was found
	 */
	public Actor findSatelliteByName(String name)
	{
		for (AstronomicalBody satellite : satellites) {
			if (satellite.getName().equals(name)) {
				return satellite;
			}
		}
		return null;
	}

    public Vector2 calculateFuturePosition(float delta) {
        float deltaAlpha = periodicConstant * delta;
        float angleInDegree = physicalProperties.getOrbitalAngleInDegree();
        return new Vector2(calculateOrbitalPositionX(deltaAlpha + angleInDegree), calculateOrbitalPositionY(deltaAlpha + angleInDegree));
    }

	public BodyProperties getPhysicalProperties() {
		return physicalProperties;
	}

    @Override
    public Kinematic getKinematic() {
        return this.kinematic;
    }

	public List<AstronomicalBody> getSatellites()
	{
		return satellites;
	}

	public int getNumberOfSatellites()
	{
		return satellites.size();
	}
	
    public BodyProperties getBodyProperties()
    {
    	return physicalProperties;
    }
}
