package dhbw.karlsruhe.it.solar.colony;

import dhbw.karlsruhe.it.solar.core.resources.CapacitiveResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;

/**
 * Contains information about problems in this colony which will also be displayed in the top resource bar.
 *
 * @author Andi
 * created 2015-05-18
 */
public class ColonyAlerts {

    private boolean lackOfLifeSupport;

    public void capacityExceeded(CapacitiveResourceInterface resource) {
        if (resource instanceof LifeSupport) {
            lackOfLifeSupport = true;
        }

    }

    public boolean lackOfLifeSupport() {
        return lackOfLifeSupport;
    }

    public void capacitySufficient(CapacitiveResourceInterface resource) {
        if (resource instanceof LifeSupport) {
            lackOfLifeSupport = false;
        }
    }

}
