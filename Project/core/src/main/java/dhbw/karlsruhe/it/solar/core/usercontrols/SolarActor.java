package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * @author Andi
 */
public abstract class SolarActor extends Actor {

    protected boolean selected;
    protected TextureRegion solarActorTexture;
    protected Sprite solarActorSprite;

    protected SolarActorScale actorScale;
    protected float currentShapeScale;
    protected float currentOrbitScale;
    
    final static double stageScalingFactor = 2 * Math.pow(10, 4);

    public SolarActor(String name) {
        this.setName(name);
    }

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

    /**
     * Converts a distance measurement given in Astronomical Units (1 AU = distance Earth-Sun) to kilometers.
     *
     * @param AU Original distance in Astronomical Units
     * @return Same distance converted into kilometers
     */
    protected static double convertAUIntoKilometer(double AU) {
        return AU * 1.4960 * Math.pow(10, 8);
    }

    /**
     * Calculates distances with a scaling factor intended to govern the distances in the solar system. Only a placeholder method right now.
     *
     * @param distance original distance
     * @return scaled down distance
     */
    protected static float scaleDistanceToStage(double distance) {
        //TODO: Scaling-Faktor (distance in km to pixel on screen) muss wahrscheinlich noch viel formaler irgendwo eingebunden werden. Die Implementierung hier ist noch nichtmal gegen zu große Eingaben geschützt
        return (float) (distance / stageScalingFactor);
    }
    
    protected static float inverseStagescaling(float stageDistance) {
    	return stageDistance * (float)stageScalingFactor;
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
        float width = getWidth() / currentShapeScale * actorScale.shapeScale;
        float height = getHeight() / currentShapeScale * actorScale.shapeScale;
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

    public void setActorScale(SolarActorScale scale) {
        actorScale = scale;
        currentOrbitScale = scale.orbitScale;
        currentShapeScale = scale.shapeScale;
    }

    public TextureRegion getSolarActorTexture() {
        return solarActorTexture;
    }
    
    /**
     * Creates a graphics sprite for the actor object from the texture atlas region identified by the textureName.
     * Unless specifically overridden by the subclass, the draw method of the solar actor class will expect that a sprite has been set up.
     * @param textureName Identifier used by the texture atlas to locate the texture.
     */
    protected void setupSolarActorSprite(String textureName) {
	    solarActorTexture = TextureCacher.gameAtlas.findRegion(textureName);
        solarActorSprite = new Sprite(solarActorTexture);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        solarActorSprite.setPosition(getX(), getY());
        solarActorSprite.draw(batch);
    } 
}
