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
    
    public Population() {
        
    }

    public Population(long numberOfColonists) {
        value = numberOfColonists;
    }

    public long getMaximum() {
        return THOUSAND * TRILLION;
    }

    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("resource_placeholder");
    }

    protected String getUnitName() {
        return "";
    }
    
    protected void updateProductionStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
        }
        changeLastMonth = populationGrowthFormula();
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
