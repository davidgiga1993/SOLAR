package dhbw.karlsruhe.it.solar.player.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Arga on 29.11.2014.
 */
public interface Resource {
    int getValue();
    int getMaximum();

    /**
     * Returns the Texture's icon as a TextureRegion
     * @return
     */
    TextureRegion getIcon();
}
