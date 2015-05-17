package dhbw.karlsruhe.it.solar.core.resources;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.player.ResourceDepot;


/**
 * Establishes communication between resources and other game logic.
 * Created by Arga on 29.11.2014.
 */
public interface StandardResourceInterface {

    /**
     * Resource value is updated based on the passage of time since the last update based on the production at the resource depot.
     */
    public void updateResource(Time deltaT, ResourceDepot productionPlace);
    
    /**
     * Returns the current discrete exact value of the resource at this resource depot.
     * @return
     */
    public long getNumber();
}
