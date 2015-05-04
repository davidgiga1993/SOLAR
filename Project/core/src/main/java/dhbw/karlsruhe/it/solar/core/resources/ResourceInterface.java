package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Establishes communication between resources and other game logic.
 * Created by Arga on 29.11.2014.
 */
public interface ResourceInterface {
    
    /**
     * Returns the current discrete amount of the resource.
     * @return
     */
    public long getValue();
    
    /**
     * Gives the maximum allowed value that can be entered.
     * @return
     */
    public long getMaximum();

    /**
     * Returns the Texture's icon as a TextureRegion
     * @return
     */
    public TextureRegion getIcon();
    
    /**
     * Returns a string describing the current amount of that resource.
     * @return
     */
    public String toString();
}
