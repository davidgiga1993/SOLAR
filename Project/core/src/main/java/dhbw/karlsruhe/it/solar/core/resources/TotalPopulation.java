package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * Special treatment of the population resource: Total population of all player colonies is counted.
 * Total population is displayed separately and values are saved as a global resource.
 * @author Andi
 *
 */
public class TotalPopulation extends GlobalResource {
    
    public TotalPopulation() {
        
    }

    @Override
    public void updateStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
            changeLastMonth = populationGrowthFormula();
        }
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("population");
    }

    @Override
    protected String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " ( " + changeLastMonth() + " % / year )";
    }
    
    private String changeLastMonth() {
        if(changeLastMonth >= 0) {
            return "+" + formatValue(changeLastMonth * 100);            
        }
        return formatValue(changeLastMonth * 100);  
    }
    
    private float populationGrowthFormula() {
        return (float)((double)((value - valuesOfLastMonth.get(0))) / (double)((valuesOfLastMonth.get(0) * ((double)valuesOfLastMonth.size()) / ((double)Time.DAYS_PER_YEAR))));
    }

    /**
     * Add additional populations of colonists to be counted among the total population.
     * @param population Colonists to be added to the sum.
     */
    public void addToTotalPopulation(Population population) {
        this.value += population.value;
    }
}
