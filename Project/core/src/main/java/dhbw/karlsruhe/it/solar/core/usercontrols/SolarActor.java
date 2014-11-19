package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Andi
 *
 */
public abstract class SolarActor extends Actor {
	
	protected ShapeRenderer shapeRenderer;
	protected boolean selected;
	protected TextureRegion solarActorTexture;
	protected Sprite solarActorSprite;
	
	public SolarActor (String name)
	{
		this.setName(name);
		this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public String toString()
	{
		return getName() + " (" + getX() + "/" + getY() + ")";	
	}
	
	public void select()
	{
		selected = true;
	}	
	
	public void deselect()
	{
		selected = false;
	}	
	
	public boolean isSelected()
	{
		return selected;
	}
	
    /**
     * Converts a distance measurement given in Astronomical Units (1 AU = distance Earth-Sun) to kilometers.
     * @param AU Original distance in Astronomical Units
     * @return Same distance converted into kilometers
     */
    protected static double convertAUIntoKilometer( double AU )
    {
    	return AU * 1.4960 * Math.pow(10, 8);
    }
        
	/**
	 * Calculates distances with a scaling factor intended to govern the distances in the solar system. Only a placeholder method right now.
	 * @param distance original distance
	 * @return scaled down distance
	 */
	protected static float scaleDistanceToStage( double distance ) {
		//TODO: Scaling-Faktor (distance in km to pixel on screen) muss wahrscheinlich noch viel formaler irgendwo eingebunden werden. Die Implementierung hier ist noch nichtmal gegen zu große Eingaben geschützt
		double scalingFactor = 2 * Math.pow(10, 4);
		return (float) (distance / scalingFactor);
	}

	public boolean insideRectangle(Rectangle rect) {
		Rectangle boundingRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		return boundingRect.overlaps(rect);
	}

	@Override
	public void setSize(float width, float height) {
		// this ensures, that the origin is the center of the SolarActor.
		setOrigin(width/2, height/2);
		super.setSize(width, height);
	}
}
