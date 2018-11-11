package dhbw.karlsruhe.it.solar.core.colony;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.*;

import java.util.ArrayList;
import java.util.List;

class ColonyResources {

    private final List<CapacitiveResourceInterface> capacitiveResources = new ArrayList<>();
    private final List<StandardResourceInterface> standardResources = new ArrayList<>();

    public ColonyResources() {

    }

    public void init(Colony colony, Population colonists) {
        standardResources.add(ResourceDepot.POPULATION_RESOURCE_ID, colonists);
        capacitiveResources.add(ResourceDepot.LIFE_SUPPORT_ID, new LifeSupport());
        capacitiveResources.add(ResourceDepot.ELECTRIC_POWER_ID, new ElectricPower());
    }

    public List<StandardResourceInterface> getResources() {
        return standardResources;
    }

    public Population getPopulation() {
        return (Population) standardResources.get(ResourceDepot.POPULATION_RESOURCE_ID);
    }

    public void updateProduction(Time deltaT, Colony place) {
        for (StandardResourceInterface resource : standardResources) {
            resource.updateResource(deltaT, place);
        }
        for (CapacitiveResourceInterface resource : capacitiveResources) {
            resource.updateConsumption(place);
        }
    }

    public LifeSupport getLifeSupport() {
        return (LifeSupport) capacitiveResources.get(ResourceDepot.LIFE_SUPPORT_ID);
    }

    public void updateCapacities(ResourceDepot productionSite) {
        for (CapacitiveResourceInterface resource : capacitiveResources) {
            resource.updateCapacity(productionSite);
        }
    }

    public ElectricPower getPower() {
        return (ElectricPower) capacitiveResources.get(ResourceDepot.ELECTRIC_POWER_ID);
    }
}
