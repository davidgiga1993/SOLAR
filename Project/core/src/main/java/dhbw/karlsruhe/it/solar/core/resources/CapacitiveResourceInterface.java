package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.colony.ResourceDepot;

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
    public long getCapacity();
    
    /**
     * Gives the amount of the resource which is currently being consumed at this resource depot.
     * Contrast with the capacity limit of the maximum amount of the resource which can be provided.
     * @param consumptionSite
     * @return
     */
    public long getCurrentConsumption(ResourceDepot consumptionSite);
    
    /**
     * Determines whether the resource consumption at the resource depot has reached capacity (maximum production).
     * @param depot
     * @return
     */
    public boolean demandExceedsSupply();
    
    /**
     * Recalculates the maximim production capacity for this resource at the resource depot.
     * @param productionSite
     */
    public void updateCapacity(ResourceDepot productionSite);  
    
    /**
     * Recalculates the current resource consumption at the resource depot.
     * @param productionSite
     */
    public void updateConsumption(ResourceDepot productionSite);
    
    /**
     * Determines which style should be used to display the resource based on the consumption to max capacity ratio.
     * @return
     */
    public LabelStyle getDisplayStyle();
}
