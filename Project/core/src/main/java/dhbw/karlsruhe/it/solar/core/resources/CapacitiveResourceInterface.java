package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.colony.ResourceDepot;

/**
 * Handles the outward behavior for resources with capacitive logic which behave differently from normally depleting resources.
 *
 * @author Andi
 * created 2015-05-16
 */
public interface CapacitiveResourceInterface {

    /**
     * Gives the maximum value which can theoretically be provided at this resource depot.
     * Contrast with the actually consumed value of that resource needed by a colony.
     *
     * @return
     */
    long getCapacity();

    /**
     * Gives the amount of the resource which is currently being consumed at this resource depot.
     * Contrast with the capacity limit of the maximum amount of the resource which can be provided.
     *
     * @param consumptionSite
     * @return
     */
    long getCurrentConsumption(ResourceDepot consumptionSite);

    /**
     * Determines whether the resource consumption at the resource depot has reached or overtaken capacity (maximum production).
     *
     * @return
     */
    boolean consumptionOverCapacity();

    /**
     * Determines whether the resource consumption at the resource depot is close to its capacity (maximum production),
     * with little or no free capacity remaining for further growth in consumption of this resource.
     *
     * @return
     */
    boolean consumptionCloseToCapacity();

    /**
     * Determines whether the resource consumption at the resource depot is approaching its capacity (maximum production),
     * with limited free capacity remaining for further growth in consumption of this resource.
     *
     * @return
     */
    boolean consumptionApproachesCapacity();

    /**
     * Calculates which impact on population growth, if any, is caused by this resource.
     * Returns a factor which will be multiplied with the base population growth rate to arrive at the expected yearly population growth.
     *
     * @return
     */
    float populationGrowthImpactOfResource();

    /**
     * Gives the ratio to which the maximum possible production for this resource is consumed by the resource depot.
     *
     * @return
     */
    float capacityRatio();

    /**
     * Recalculates the maximim production capacity for this resource at the resource depot.
     *
     * @param productionSite
     */
    void updateCapacity(ResourceDepot productionSite);

    /**
     * Recalculates the current resource consumption at the resource depot.
     */
    void updateConsumption(ResourceDepot consumptionPlace);

    /**
     * Determines which style should be used to display the resource based on the consumption to max capacity ratio.
     *
     * @return
     */
    LabelStyle getDisplayStyle();

    /**
     * Returns a table cell containing the resource alert icon.
     *
     * @return
     */
    Table loadAlertIcon();
}
