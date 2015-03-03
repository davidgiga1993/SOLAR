package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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
	protected Kinematic kinematic;	
	
	public Orbiter(String name)
	{
		super(name);
		this.orbitalProperties = null;
	}
	
	public Orbiter(String name, OrbitalProperties orbit, SolarActorScale scaleFactor)
	{
		super(name);
		setActorScale(scaleFactor);
		this.orbitalProperties = orbit;		
		setKinematicValues();
		changeOrbitScale();
	}

	protected void setKinematicValues() {
		float orbitalSpeed = calculateOrbitalSpeed();
		this.kinematic = new Kinematic(new Vector2(getX(), getY()), 0, orbitalSpeed);
		this.kinematic.velocity = new Vector2(1,0).scl(orbitalSpeed);
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
	private void changeOrbitScale() {
			orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}
		
	@Override
	public void updateScale() {
		orbitalRadiusInPixels *= actorScale.orbitScale / currentOrbitScale;
		super.updateScale();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if( null != orbitalProperties) {
			actOrbitalMovement(delta);
		}
	}

	private void actOrbitalMovement(float delta) {
		orbitalProperties.updateOrbitalAngle(delta);
		setOrbitalPositionTotal();

        adjustLabelPosition();

		kinematic.position.x = getX()+getOriginX();
		kinematic.position.y = getY()+getOriginY();
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
    	this.setPosition(orbitalProperties.calculateOrbitalPositionX(orbitalRadiusInPixels, new Angle()) - getWidth() / 2, orbitalProperties.calculateOrbitalPositionY(orbitalRadiusInPixels, new Angle()) - getHeight() / 2);
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
		shapeRenderer.setColor(Color.TEAL);
		shapeRenderer.orbit(orbitalProperties.calculateCenterOfOrbitX(), orbitalProperties.calculateCenterOfOrbitY(), orbitalRadiusInPixels, 250);
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
}
