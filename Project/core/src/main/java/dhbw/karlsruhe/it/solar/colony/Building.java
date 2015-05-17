package dhbw.karlsruhe.it.solar.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

public interface Building {
       
    /**
     * Defines the running costs in Credits this building causes.
     * @param deltaT
     * @return Upkeep costs over the time interval
     */
    public Credits payUpKeep(Time deltaT);
    
    /**
     * States how many instances of that building type are currently in operation in the colony.
     * Buildings can be off-line for various reasons, for example due to lack of electricity.
     * @return
     */
    public String getNumberOfBuildingsBuilt();
    
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
    
    /**
     * Orders the game to add a new building of that type to the colony.
     */
    public void build();
}
