package dhbw.karlsruhe.it.solar.core.resources;

/**
 * Handles the outward behavior for resources with capacitive logic which behave differently from normally depleting resources.
 * @author Andi
 * created 2015-05-16
 */
public interface CapacitiveResourceInterface {
    
    /**
     * Gives the maximum value which can theoretically be provided at this resource depot.
     * Contrast with the actually consumed value of that resource needed by a colony.
     * @return
     */
    public long getMaximumNumber();
    
    /**
     * Gives the amount of the resource which is currently being consumed at this resource depot.
     * Contrast with the maximum resource value limit.
     * @param consumptionPlace
     * @return
     */
    public long getCurrentConsumption(ResourceDepot consumptionPlace);
    
    /**
     * Determines whether the resource consumption at the resource depot has reached maximum production.
     * @return
     */
    public boolean demandExceedsSupply();
}
