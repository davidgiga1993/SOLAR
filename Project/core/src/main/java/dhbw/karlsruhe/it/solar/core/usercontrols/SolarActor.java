package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.TextureCache;

/**
 * @author Andi
 */
public abstract class SolarActor extends Actor implements Telegraph {

    public static final double STAGE_SCALING_FACTOR = 2 * Math.pow(10, 4);
    protected boolean selected;
    protected TextureRegion solarActorTexture;
    protected Color selectionColor;
    protected SolarActorScale actorScale;
    protected float currentShapeScale;
    protected float currentOrbitScale;
    private Sprite solarActorSprite;

    SolarActor(String name) {
        this.setName(name);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.GAME_SCALE_CHANGED);
        selectionColor = Color.GREEN;
    }

    /**
     * Calculates distances with a scaling factor intended to govern the distances in the solar system. Only a placeholder method right now.
     *
     * @param distance original distance
     * @return scaled down distance
     */
    public static float scaleDistanceToStage(double distance) {
        // TODO: Should be refactored to something more formal, this implementation isn't protected against huge inputs. Represents distance in km to pixels on screen ratio.
        return (float) (distance / STAGE_SCALING_FACTOR);
    }

    public static float scaleDistanceToPhysical(float stageDistance) {
        return stageDistance * (float) STAGE_SCALING_FACTOR;
    }

    public abstract String getTypeName();

    @Override
    public String toString() {
        return getName() + " (" + getX() + "/" + getY() + ")";
    }

    public void select() {
        selected = true;
    }

    public void deselect() {
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean insideRectangle(Rectangle rect) {
        Rectangle boundingRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
        return boundingRect.overlaps(rect);
    }

    @Override
    public void setSize(float width, float height) {
        // this ensures, that the origin is the center of the SolarActor.
        setOrigin(width / 2, height / 2);
        super.setSize(width, height);
        if (solarActorSprite != null) {
            solarActorSprite.setSize(width, height);
        }
    }

    public void updateScale() {
        float width = getWidth() / currentShapeScale * actorScale.getShapeScale();
        float height = getHeight() / currentShapeScale * actorScale.getShapeScale();
        setSize(width, height);
        setActorScale(actorScale);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        if (solarActorSprite != null) {
            this.solarActorSprite.setOrigin(originX, originY);
        }
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        if (solarActorSprite != null) {
            this.solarActorSprite.setRotation(degrees);
        }
    }

    protected void setActorScale(SolarActorScale scale) {
        actorScale = scale;
        currentOrbitScale = scale.getOrbitScale();
        currentShapeScale = scale.getShapeScale();
    }

    public TextureRegion getSolarActorTexture() {
        return solarActorTexture;
    }

    /**
     * Creates a graphics sprite for the actor object from the texture atlas region identified by the textureName.
     * Unless specifically overridden by the subclass, the draw method of the solar actor class will expect that a sprite has been set up.
     *
     * @param textureName Identifier used by the texture atlas to locate the texture.
     */
    protected void setupSolarActorSprite(String textureName) {
        solarActorTexture = TextureCache.GAME_ATLAS.findRegion(textureName);
        solarActorSprite = new Sprite(solarActorTexture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        solarActorSprite.setPosition(getX(), getY());
        solarActorSprite.draw(batch);
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if (telegram.message == SolarMessageType.GAME_SCALE_CHANGED) {
            updateScale();
        }
        return false;
    }
}
