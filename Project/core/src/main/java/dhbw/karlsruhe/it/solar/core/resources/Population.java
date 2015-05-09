package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;


/**
 * Populatio Resource: Each colony has a certain number of inhabitants. Behavior of population is governed by this class.
 * @author Andi
 * Th, 19. March 2015
 */
public class Population extends BaseResource {
    
    private final static float BASE_POPULATION_GROWTH_RATE = 0.02f;
    
    public Population() {
        
    }

    public Population(long numberOfColonists) {
        value = numberOfColonists;
    }

    @Override
    public long getMaximum() {
        return THOUSAND * TRILLION;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("resource_placeholder");
    }

    @Override
    protected String getUnitName() {
        return "";
    }
 
    @Override
    protected void updateProductionStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
        }
        changeLastMonth = populationGrowthFormula();
    }
    
    @Override
    protected void updateProduction(Time deltaT) {
        valueRemainingFraction += (float)value * growthRateFormula(deltaT);
        addWholeFractionsToValue();
    }

    private void addWholeFractionsToValue() {
        value += (long)valueRemainingFraction;
        valueRemainingFraction -= (long)valueRemainingFraction; 
    }

    private float growthRateFormula(Time deltaT) {
        if(needsAreMet()) {
            return deltaT.inYears() * BASE_POPULATION_GROWTH_RATE;
        }
        return 0;
    }

    private boolean needsAreMet() {
        return true;
    }

    private float populationGrowthFormula() {
        return (float)((double)((value - valuesOfLastMonth.get(0))) / (double)((valuesOfLastMonth.get(0) * ((double)valuesOfLastMonth.size()) / ((double)Time.DAYS_PER_YEAR))));
    }

    private void updateValuesOfLastMonthList() {
        valuesOfLastMonth.add(value);
        if(valuesOfLastMonth.size()>31) {
            valuesOfLastMonth.remove(0);
        }
    }

    /**
     * Sets population number to zero. Only to be used for total population calculation of player!
     */
    public void empty() {
        value = 0;
    }
}
