package dhbw.karlsruhe.it.solar.core.resources;

public class PopulationNeeds {
 
    private final static float BASE_POPULATION_GROWTH_RATE = 0.02f;
    private ResourceDepot livingSpace;
    
    public PopulationNeeds(ResourceDepot livingSpace) {
        this.livingSpace = livingSpace;
    }

    /**
     * Calculates the growth rate of a population based on the degree to which its needs are met.
     * @return
     */
    public float calculateGrowthRate() {
        if(livingSpace.isPermanentHabitat()) {
            return BASE_POPULATION_GROWTH_RATE;            
        }
        return 0;
    }
}
