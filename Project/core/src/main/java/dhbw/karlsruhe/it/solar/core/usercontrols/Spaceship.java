package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.AIModule;
import dhbw.karlsruhe.it.solar.core.ai.AIOutput;
import dhbw.karlsruhe.it.solar.core.ai.AISpaceshipModule;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.movement.AstronomicalBodyKinematic;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
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

	    solarActorTexture = TextureCacher.gameAtlas.findRegion("Cruiser");
        createShipSprite();

        this.setSize(width, length);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.owner = owner;

        // initialize AI Module
        this.destination = new Vector2(getX(), getY());
        this.kinematic = new Kinematic(new Vector2(getX()+getOriginX(), getY()+getOriginY()), getRotation(), speed);
        this.aiModule = new AISpaceshipModule(this);
        aiModule.setTarget(destination);
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

    private void createShipSprite()
    {
        solarActorSprite = new Sprite(solarActorTexture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        displaySpaceship(batch);
    }

    @Override
    public void drawLines(ShapeRenderer shapeRenderer) {
        shapeRenderer.identity();

        displaySelectionBox(shapeRenderer);
        displayCourseAndDestination(shapeRenderer);
    }

    private void displaySpaceship(Batch batch)
    {
        solarActorSprite.setPosition(getX(), getY());
        solarActorSprite.draw(batch);
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

    public void setDestination(AstronomicalBodyKinematic destination) {
        this.aiModule.setTarget(destination);
        this.destination = destination.position;
    }

    /**
     * @return Destination coordinates the spaceship object is heading to
     */
    public Vector2 getDestination()
    {
        return destination;
    }

    /**
     * A simple movement is executed for the spaceship object. It rotates to face the destination and calls a constant MoveToAction.
     */
    public void moveToDestination()
    {
        getActions().clear();
        SequenceAction sequence = new SequenceAction();
        shipRotation();
        sequence.addAction(shipMovement());
        addAction(sequence);
    }

    /**
     * Calculation of the necessary MoveToAction for the simple spaceship movement model. Ship speed is dependent on spaceship's speed attribute.
     * The MoveToAction acts on the lower left corner of the spaceship object. A recalculation to take into account the ship's center point is necessary.
     * @return A correctly prepared MoveToAction that now can be applied to the spacheship.
     */
    private MoveToAction shipMovement()
    {
    	Vector2 adjDestination = new Vector2(destination.x - (getWidth() / 2), destination.y - (getHeight() / 2));
        MoveToAction mov = new MoveToAction();
        mov.setPosition(adjDestination.x, adjDestination.y);
        float deltaX = getX() - adjDestination.x;
        float deltaY = getY() - adjDestination.y;
        float moveTime = (float) ((Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2))) / speed);
        mov.setDuration(moveTime);
        return mov;
    }

    /**
     * Simple rotation model. Instantly turns the ship to face its current destination.
     */
    private void shipRotation()
    {
        this.setRotation(calculateRotationAngle());
    }

    /**
     * Calculates the angle between the current location of the spaceship and its destination point.
     * @return The rotation angle which will make the ship face its destination from its current position.
     */
    private float calculateRotationAngle()
    {
        return (float) -Math.toDegrees(Math.atan2(getX() + getWidth() / 2 - destination.x, getY() + getHeight() / 2 - destination.y));
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
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
