package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * 
 * @author Andi
 *
 * Orbiter contains all relevant information necessary for an actor to be able to orbit another Orbiter actor in the game.
 * Therefore it handles all methods and information relating to the game's orbital mechanics.
 */
public class Orbiter extends SolarActor implements ShapeRenderable, KinematicObject 
{
	protected OrbitalProperties orbitalProperties;
	float orbitalRadiusInPixels;
	protected final Kinematic kinematic;

	protected int segments = 250;
	protected Color orbitColor = Color.TEAL;

	public Orbiter(String name)
	{
		this(name, null, ConfigurationConstants.SCALE_FACTOR_STAR);
	}
	
	public Orbiter(String name, OrbitalProperties orbit, SolarActorScale scaleFactor)
	{
		super(name);
		setActorScale(scaleFactor);
		this.kinematic = new Kinematic(new Vector2(getX(), getY()), 0, 0);
		this.orbitalProperties = orbit;	
		if(null != orbitalProperties)
		{
			setKinematicValues();
			changeOrbitScale();
		}
	}

	protected void setKinematicValues() {
		float orbitalSpeed = calculateOrbitalSpeed();
		kinematic.position = new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
		kinematic.rotation = 0;
		kinematic.velocity = new Vector2(1,0).scl(orbitalSpeed);
		kinematic.maxSpeed = orbitalSpeed;
	}

	/**
	 * Calculates the actual movement speed on the game map relative to the scaling setting for the stage.
	 * Value is calculated according to the orbital circumference (derived from the scaled Orbital Radius value) divided by the Orbital Period in Days.
	 * @return Total Movement speed of the orbital object on the game map. Unit: Scale-Adjusted Kilometres per Day.
	 */
	private float calculateOrbitalSpeed() {
		return (float) ((2 * Math.PI * scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres())) / orbitalProperties.getOrbitalPeriodInDays());
	}
	
	/**
	 * Sets the orbital radius in pixels value relative to the scaling setting for the stage. 
	 */
	protected void changeOrbitScale() {
		if(orbitalProperties == null) {
			return;
		}
		orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}
		
	@Override
	public void updateScale() {
		changeOrbitScale();
		super.updateScale();
	}

	protected float calculateOrbitOffset() {
		AstronomicalBody primary = getPrimary();
		float radius = primary.getWidth()/2;
		float normRadius = (float) (primary.physicalProperties.getRadius().asKilometres() / SolarActor.stageScalingFactor);
		return radius - normRadius;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if( null != orbitalProperties) {
			actOrbitalMovement(delta);
		}
	}

	protected void actOrbitalMovement(float delta) {
		orbitalProperties.updateOrbitalAngle(delta);
		setOrbitalPositionTotal();

        adjustLabelPosition();

		kinematic.rotation = orbitalProperties.getOrbitalAngle().inDegrees() + 90f;
		kinematic.velocity.setAngle(kinematic.rotation);
	}

	/**
	 * Method implements change of position of labels for actors using the Orbiter superclass.
	 */
	protected void adjustLabelPosition() {
		return;
	}
	
    /**
     * Calculates the current position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     */
    protected void setOrbitalPositionTotal()
    {
		kinematic.position.x = orbitalProperties.calculateOrbitalPositionX(orbitalRadiusInPixels, new Angle());
		kinematic.position.y = orbitalProperties.calculateOrbitalPositionY(orbitalRadiusInPixels, new Angle());
    	this.setPosition(kinematic.position.x  - getWidth() / 2, kinematic.position.y  - getHeight() / 2);
    }
    
    public Vector2 calculateFuturePosition(float delta) {
        return orbitalProperties.calculateFuturePosition( orbitalRadiusInPixels, delta);
    }
    
	/**
	 * Returns a Vector2 containing the position of this objects center of orbit.
	 * @return Vector2
	 */
	public Vector2 getCenterOfOrbit() {
		return orbitalProperties.getCenterOfOrbit();
	}
    
	@Override
	public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
		if (null != orbitalProperties )
		{
			displayOrbit(solarShapeRenderer);
		}
	}
	
	protected void displayOrbit(SolarShapeRenderer shapeRenderer)
{
	shapeRenderer.setColor(orbitColor);
	shapeRenderer.orbit(orbitalProperties.calculateCenterOfOrbitX(), orbitalProperties.calculateCenterOfOrbitY(), orbitalRadiusInPixels, segments);
}
	
    @Override
    public Kinematic getKinematic() {
        return this.kinematic;
    }
    
	public Length getOrbitalRadius() {
		return orbitalProperties.getOrbitalRadius();
	}

	public float getOrbitalPeriodInDays() {
		return orbitalProperties.getOrbitalPeriodInDays();
	}
	
	public AstronomicalBody getPrimary() {
		return orbitalProperties.getPrimary();
	}
}
