package dhbw.karlsruhe.it.solar.core.resources;

public class PopulationNeeds {
 
    private final static float BASE_POPULATION_GROWTH_RATE = 0.02f;
    private final static float BASE_TAX_RATE = 1f;

    /**
     * Calculates the growth rate of a population based on the degree to which its needs are met.
     * @return
     */
    public float calculateGrowthRate() {
//        if(livingSpace.isPermanentHabitat()) {
//            return BASE_POPULATION_GROWTH_RATE;            
//        }
//        return 0;
        return BASE_POPULATION_GROWTH_RATE;
    }

    /**
     * Calculates the amount of taxes which can be raised from a population based on its means.
     * (primitive implementation)
     * @return
     */
    public float calculateTaxRate() {
//        if(livingSpace.isPermanentHabitat()) {
//            return BASE_TAX_RATE;            
//        }
//        return 0;
        return BASE_TAX_RATE;
    }
}
