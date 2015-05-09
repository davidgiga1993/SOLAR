package dhbw.karlsruhe.it.solar.core.resources;

import java.util.List;

/**
 * Interface for in-game objects containing game resources of any kind.
 * @author Andi
 * created 2015-05-09
 */
public interface ResourceDepot {
    
    public final static int POPULATION_RESOURCE_ID = 0;
    
    /**
     * Returns the total list of resources stored in this resource depot.
     * @return
     */
    public List<BaseResource> getResources();
    
    /**
     * Returns the number of people living on this ResourceDepot, if any.
     * @return
     */
    public Population getPopulation();
}
