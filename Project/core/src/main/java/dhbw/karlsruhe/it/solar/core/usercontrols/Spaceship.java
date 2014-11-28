package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * @author Andi
 *
 */
public class Spaceship extends SolarActor implements ShapeRenderable
{

    private Vector2 destination;
    private float speed = 100f;

    public Spaceship(String name)
    {
        super(name);
	      this.selected = false;
	      this.destination = null;
	      this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
	      solarActorTexture = TextureCacher.gameAtlas.findRegion("Cruiser");
	      this.setSize(solarActorTexture.getRegionWidth(), solarActorTexture.getRegionHeight());
	      createShipSprite();
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
        shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
        shapeRenderer.identity();

        displaySelectionBox(shapeRenderer);
        displayCourseAndDestination(shapeRenderer);
    }

    private void displaySpaceship(Batch batch)
    {
    	batch.end();
    	batch.begin();
        solarActorSprite.setPosition(getX(), getY());
        solarActorSprite.draw(batch);
        batch.end();
        batch.begin();
    }

    private void displayCourseAndDestination(ShapeRenderer shapeRenderer)
    {
        // Anzeige des Kurses und Markierung des Ziels
        if (destination != null && this.getActions().size != 0)
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
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
     * The spaceship object receives a new destination for its course. The old destination is discarded.
     * @param destination Desired destination coordinates
     */
    public void setDestination(Vector2 destination)
    {
        this.destination = destination;
        System.out.println("Neues Ziel gesetzt für " + this.getName() + " bei X= " + destination.x + ", Y= " + destination.y);
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
        solarActorSprite.setRotation(calculateRotationAngle());
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


}
