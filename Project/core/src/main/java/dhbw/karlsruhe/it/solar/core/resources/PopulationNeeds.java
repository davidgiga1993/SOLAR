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
            return BASE_POPULATION_GROWTH_RATE * lifeSupportImpact(livingSpace);            
        }
        return 0;
    }

    private float lifeSupportImpact(ResourceDepot livingSpace) {
        long population = livingSpace.getPopulation().getNumber();
        long capacity = livingSpace.getLifeSupport().value;
        
        if(0 == capacity) {
            return 0;
        }
        if(population > 0.95 * capacity) {
            return 10 - 10 * capacityRatio(livingSpace);
        }
        if(population > 0.8 * capacity) {
            return 11/3 - 10/3 * capacityRatio(livingSpace);
        }
        return 1;
    }
    
    private float capacityRatio(ResourceDepot livingSpace) {
        return ((float)(livingSpace.getPopulation().getNumber()))/((float)livingSpace.getLifeSupport().value);
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
