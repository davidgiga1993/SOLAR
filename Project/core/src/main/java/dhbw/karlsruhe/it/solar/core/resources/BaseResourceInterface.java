package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Describes the public behavior which all resources have to follow.
 * @author Andi
 * created 2015-05-16
 */
interface BaseResourceInterface {

    /**
     * Returns the Texture's icon as a TextureRegion
     * @return
     */
    TextureRegion getIcon();
    
    /**
     * Returns a table cell containing the resource icon.
     * @return
     */
    Table loadIcon();
    
    /**
     * Returns a string for the GUI describing the current amount of that resource.
     * @return
     */
    String toString();
}
