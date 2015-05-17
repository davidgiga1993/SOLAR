package dhbw.karlsruhe.it.solar.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.resources.Credits;

public interface Building {
       
    /**
     * Defines the yearly running cost in Credits this building causes.
     * @return Yearly Upkeep Costs.
     */
    public Credits payUpKeep();
    
    /**
     * States how many instances of that building type are currently in operation in the colony.
     * Buildings can be off-line for various reasons, for example due to lack of electricity.
     * @return
     */
    public long getNumberOfBuildingsOnline();
    
    /**
     * Returns the Texture's icon as a TextureRegion
     * @return
     */
    public TextureRegion getIcon();

}
