package dhbw.karlsruhe.it.solar.colony;

import java.util.ArrayList;
import java.util.List;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.CapacitiveResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.StandardResourceInterface;

public class ColonyResources {
    
    private final List<CapacitiveResourceInterface> capacitiveResources = new ArrayList<CapacitiveResourceInterface>();
    private final List<StandardResourceInterface> standardResources = new ArrayList<StandardResourceInterface>();
    
    public ColonyResources() {
        
    }

    public void init(Population colonists) {
        standardResources.add(ResourceDepot.POPULATION_RESOURCE_ID, colonists);
        capacitiveResources.add(ResourceDepot.LIFE_SUPPORT_ID, new LifeSupport());
    }

    public List<StandardResourceInterface> getResources() {
        return standardResources;
    }

    public Population getPopulation() {
        return (Population)standardResources.get(ResourceDepot.POPULATION_RESOURCE_ID);
    }

    public void updateProduction(Time deltaT, Colony place) {
        for (StandardResourceInterface resource : standardResources ) {
            resource.updateResource(deltaT, place);
        }
        for (CapacitiveResourceInterface resource : capacitiveResources ) {
            resource.updateConsumption(place);
        }
    }

    public LifeSupport getLifeSupport() {
        return (LifeSupport)capacitiveResources.get(ResourceDepot.LIFE_SUPPORT_ID);
    }
}
