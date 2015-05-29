package dhbw.karlsruhe.it.solar.core.resources;

import dhbw.karlsruhe.it.solar.colony.ResourceDepot;

public class PopulationNeeds {
 
    private final static float BASE_POPULATION_GROWTH_RATE = 0.02f;
    private final static float BASE_YEARLY_TAX_CREDITS_PER_CITIZEN = 1f;

    /**
     * Calculates the growth rate of a population based on the degree to which its needs are met.
     * @return
     */
    public float calculateGrowthRate(ResourceDepot livingSpace) {
        if(livingSpace.isPermanentHabitat()) {
            return BASE_POPULATION_GROWTH_RATE * lifeSupportImpact(livingSpace.getLifeSupport());            
        }
        return 0;
    }

    private float lifeSupportImpact(LifeSupport lifeSupport) {
        return lifeSupport.populationGrowthImpactOfResource();
    }

    /**
     * Calculates the amount of taxes which can be raised from a population based on its means.
     * (primitive implementation)
     * @return
     */
    public float calculateTaxRate(ResourceDepot livingSpace) {
        if(livingSpace.isPermanentHabitat()) {
            return BASE_YEARLY_TAX_CREDITS_PER_CITIZEN;            
        }
        return 0;
    }
}
