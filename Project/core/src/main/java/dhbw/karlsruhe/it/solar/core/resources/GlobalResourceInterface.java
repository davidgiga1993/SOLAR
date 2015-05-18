package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Handles the outward behavior of globally stored resources which are not bound to a specific place but to a player.
 * @author Andi
 * created 2015-05-16
 */
public interface GlobalResourceInterface {
    
    /**
     * Updates the statistics of the resource if enough time has passed.
     */
    public void updateStatistic();
    
    /**
     * Sets resource value to zero for recalculation.
     */
    public void empty();
    
    /**
     * Returns the current discrete exact value of the resource at this resource depot.
     * @return
     */
    public long getNumber();
    
    /**
     * Returns the table to be inserted as the title of the this global resource's part of the top Resource Bar
     * @return
     */
    public Table getResourceBarTitle();
}
