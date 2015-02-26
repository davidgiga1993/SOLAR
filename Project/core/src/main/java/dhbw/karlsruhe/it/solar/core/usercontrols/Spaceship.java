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
import dhbw.karlsruhe.it.solar.player.Ownable;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * @author Andi
 *
 */
public class Spaceship extends SolarActor implements ShapeRenderable, Ownable, KinematicObject
{

    private Vector2 destination;
    private float speed = 1000f;
    protected Player owner;

    protected Kinematic kinematic;

    AIModule aiModule;
    AIOutput aiOutput;

    public Spaceship(String name, Length width, Length length, Player owner)
    {
        super(name);
        setActorScale(ConfigurationConstants.SCALE_FACTOR_UNITS);
	    this.selected = false;

	    setupSolarActorSprite("Cruiser");

        this.setSize(width, length);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.owner = owner;

        // initialize AI Module
        this.destination = new Vector2(getX(), getY());
        this.kinematic = new Kinematic(new Vector2(getX()+getOriginX(), getY()+getOriginY()), getRotation(), speed);
        this.aiModule = new AISpaceshipModule(this);
        aiModule.setTarget(destination);
        aiModule.addEventListener(new TargetReachedListener() {
            @Override
            public void handle(TargetReachedEvent event) {
                System.out.println("Target reached");
            }
        });
    }

    @Override
    public Kinematic getKinematic() {
        return kinematic;
    }

    @Override
    public void act(float delta) {
        aiOutput = aiModule.act(delta);
        setPosition(aiOutput.position.x-getOriginX(), aiOutput.position.y-getOriginY());
        // TODO: fix rotation offset of spaceship... +90° necessary atm.
        setRotation(aiOutput.rotation + 90);
        super.act(delta);
    }

    /**
     * Resizes the Spaceship
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
    public void drawLines(ShapeRenderer shapeRenderer) {
        shapeRenderer.identity();

        displaySelectionBox(shapeRenderer);
        displayCourseAndDestination(shapeRenderer);
    }

    private void displayCourseAndDestination(ShapeRenderer shapeRenderer)
    {
        // Anzeige des Kurses und Markierung des Ziels
        if (destination != null && this.aiModule.isMoving())
        {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.line(getX() + getWidth() / 2, getY() + getHeight() / 2, destination.x, destination.y);
            shapeRenderer.circle(destination.x, destination.y, 10);

            displaySelectedDestination(shapeRenderer);
        }
    }

    private void displaySelectedDestination(ShapeRenderer shapeRenderer)
    {
        // Besondere Hervorhebung des Ziels wenn selektiert
        if (selected)
        {
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(destination.x - 13, destination.y - 13, 26, 26);
        }
    }

    private void displaySelectionBox(ShapeRenderer shapeRenderer)
    {
        // Anzeige Selektions Box
        if (selected)
        {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
        }
    }

    /**
     * The spaceship object receives a new destination for its course. The old destination is discarded.
     * @param destination Desired destination coordinates
     */
    public void setDestination(Vector2 destination)
    {
        this.aiModule.setTarget(destination);
        this.destination = destination;
        System.out.println("Neues Ziel gesetzt für " + this.getName() + " bei X= " + destination.x + ", Y= " + destination.y);
    }

    public void setDestination(KinematicObject destination) {
        this.aiModule.setTarget(destination.getKinematic());
        this.destination = destination.getKinematic().position;
        System.out.println("Neues Ziel gesetzt für " + this.getName());
    }

    public void setDestination(AstronomicalBody destination) {
        this.aiModule.setTarget(destination);
        this.destination = destination.getKinematic().position;
    }

    /**
     * @return Destination coordinates the spaceship object is heading to
     */
    public Vector2 getDestination()
    {
        return destination;
    }

    @Override
    public boolean isOwnedBy(Player player) {
        return player.equals(owner);
    }


    /**
     * Adds a new spaceship object to the game.
     * @param name Desired name of the spaceship.
     * @param startlocation Desired location at which the ship is to appear.
     */
    public static Spaceship placeNewShip(String name, Vector2 startlocation, Player owner)
    {
        Spaceship newShip = new Spaceship(name, new Length(1, Length.Unit.kilometres), new Length(1, Length.Unit.kilometres), owner);
        newShip.setPosition(startlocation.x, startlocation.y);
        newShip.kinematic.position = startlocation;
        newShip.setDestination(startlocation);
        return newShip;
    }

}
