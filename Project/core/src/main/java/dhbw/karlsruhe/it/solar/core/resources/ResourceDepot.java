package dhbw.karlsruhe.it.solar.core.resources;

import java.util.List;

/**
 * Interface for in-game objects containing game resources of any kind.
 * @author Andi
 * created 2015-05-09
 */
public interface ResourceDepot {
    
    public final static int POPULATION_RESOURCE_ID = 0;
    public final static int TREASURY_RESOURCE_ID = 1;
    
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

    /**
     * Indicates whether the depot has a permanent living space designed as the residence of its inhabitants or whether it is a temporary accommodation such as passenger ship cabins.
     * @return
     */
    public boolean isPermanentHabitat();
}
