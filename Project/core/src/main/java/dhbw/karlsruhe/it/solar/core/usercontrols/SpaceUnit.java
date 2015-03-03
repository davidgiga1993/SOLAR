package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.AIModule;
import dhbw.karlsruhe.it.solar.core.ai.AIOutput;
import dhbw.karlsruhe.it.solar.core.ai.AISpaceshipModule;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedEvent;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedListener;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.player.Ownable;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * 
 * @author Andi
 * SpaceUnit is supposed to define all shared properties of player unit objects such as ships or stations into one superclass
 * derived from SolarActor from which the individual unit subclasses can inherit.
 */
public class SpaceUnit extends Orbiter implements ShapeRenderable, Ownable, KinematicObject
{
    protected Player owner;
    protected Kinematic kinematic;
    protected Vector2 destination;
	protected float speed;
    AIModule aiModule;
    AIOutput aiOutput;
    
	public SpaceUnit(String name, Player owner, float speed)
	{
		super(name);
        setActorScale(ConfigurationConstants.SCALE_FACTOR_UNITS);
	    this.selected = false;
        this.owner = owner;
        this.speed = speed;
	}

	/**
	 * Shared constructor tasks for space units which have to come after their texture is added.
	 * @param width
	 * @param length
	 */
	protected void initSpaceUnit(Length width, Length length) {
		this.setSize(width, length);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
	    
        this.destination = new Vector2(getX(), getY());
        this.kinematic = new Kinematic(new Vector2(getX()+getOriginX(), getY()+getOriginY()), getRotation(), speed);
        
        // initialize AI Module
        this.aiModule = new AISpaceshipModule(this);
        aiModule.setTarget(destination);
        aiModule.addEventListener(new TargetReachedListener() {
            @Override
            public void handle(TargetReachedEvent event) {
                System.out.println("Target reached");
            }
        });
	}
	
    /**
     * The Space Unit object receives a new destination for its course. The old destination is discarded.
     * @param destination Desired destination coordinates
     */
    public void setDestination(Vector2 destination)
    {
    	//TODO: Entferne Debug-konsolenausgabe
        this.aiModule.setTarget(destination);
        this.destination = destination;
        System.out.println("Neues Ziel gesetzt f\u00fcr " + this.getName() + " (" + destination.x + "/" + destination.y  + ").");
    }
    
    public void setDestination(KinematicObject destination)
    {
    	//TODO: Entferne Debug-konsolenausgabe
        this.aiModule.setTarget(destination.getKinematic());
        this.destination = destination.getKinematic().position;
        System.out.println("Neues Ziel gesetzt f\u00fcr " + this.getName() + ": " + destination.toString() + ".");
    }

    @Override
    public boolean isOwnedBy(Player player) {
        return player.equals(owner);
    }

	@Override
	public Kinematic getKinematic() {
        return kinematic;
	}
	
    @Override
    public void act(float delta) {
        aiOutput = aiModule.act(delta);
        setPosition(aiOutput.position.x-getOriginX(), aiOutput.position.y-getOriginY());
        // TODO: fix rotation offset of space unit... +90° necessary atm.
        setRotation(aiOutput.rotation + 90);
        super.act(delta);
    }
	
    /**
     * Resizes the Space Unit
     * @param width
     * @param height
     */
    public void setSize(Length width, Length height) {
        // convert to pixels and scale with scale setting
        float pWidth = scaleDistanceToStage(width.asKilometres()) * ConfigurationConstants.SCALE_FACTOR_UNITS.shapeScale;
        float pHeight = scaleDistanceToStage(height.asKilometres()) * ConfigurationConstants.SCALE_FACTOR_UNITS.shapeScale;
        // call super
        super.setSize(pWidth , pHeight);
    }
    
    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        libGDXShapeRenderer.identity();

        displaySelectionBox(libGDXShapeRenderer);
        displayCourseAndDestination(libGDXShapeRenderer);
        super.drawLines(libGDXShapeRenderer, solarShapeRenderer);
    }

	/**
     * Methods regulates how the course of the unit is displayed 
     * and also how the destination of the movement order is to be marked.
     * @param shapeRenderer
     */
    private void displayCourseAndDestination(ShapeRenderer shapeRenderer)
    {
        if (destination != null && this.aiModule.isMoving())
        {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.line(getX() + getWidth() / 2, getY() + getHeight() / 2, destination.x, destination.y);
            shapeRenderer.circle(destination.x, destination.y, 10);

            displaySelectedDestination(shapeRenderer);
        }
    }

    /**
     * The destination of the movement order is highlighted differently if selected.
     * @param shapeRenderer
     */
    private void displaySelectedDestination(ShapeRenderer shapeRenderer)
    {
        if (selected)
        {
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(destination.x - 13, destination.y - 13, 26, 26);
        }
    }

    /**
     * If selected, the unit is highlighted by a selection box.
     * @param shapeRenderer
     */
    private void displaySelectionBox(ShapeRenderer shapeRenderer)
    {
        if (selected)
        {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
        }
    }

    public void setDestination(AstronomicalBody destination) {
    	//TODO: Entferne Debug-konsolenausgabe
        this.aiModule.setTarget(destination);
        this.destination = destination.getKinematic().position;
        System.out.println("Neues Ziel gesetzt f\u00fcr " + this.getName() + ": " + destination.getName() + " (" + destination.getX() + "/" + destination.getY()  + ").");
    }

    /**
     * @return Destination coordinates the spaceship object is heading to
     */
    public Vector2 getDestination()
    {
        return destination;
    }
}
