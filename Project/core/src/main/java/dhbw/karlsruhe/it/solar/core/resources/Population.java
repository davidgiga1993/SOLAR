package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * Population Resource: Each colony has a certain number of inhabitants. Behavior of population is governed by this class.
 * @author Andi
 * Th, 19. March 2015
 */
public class Population extends StandardResource {

    private PopulationNeeds needs = new PopulationNeeds();
    
    public Population() {
        
    }

    public Population(long numberOfColonists) {
        this.value = numberOfColonists;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("population");
    }
 
    @Override
    protected void updateProductionStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
            changeLastMonth = populationGrowthFormula();
        }
    }
    
    @Override
    protected void updateProduction(Time deltaT, ResourceDepot livingSpace) {
        valueRemainingFraction += (float)value * growthRateFormula(deltaT, livingSpace);
        addWholeFractionsToValue();
    }

    private void addWholeFractionsToValue() {
        value += (long)valueRemainingFraction;
        valueRemainingFraction -= (long)valueRemainingFraction; 
    }

    private float growthRateFormula(Time deltaT, ResourceDepot livingSpace) {
        return deltaT.inYears() * needs.calculateGrowthRate(livingSpace);
    }
    
    private float populationGrowthFormula() {
        return (float)((double)((value - valuesOfLastMonth.get(0))) / (double)((valuesOfLastMonth.get(0) * ((double)valuesOfLastMonth.size()) / ((double)Time.DAYS_PER_YEAR))));
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

    /**
     * Populations generate taxes for the player.
     * @param deltaT Time interval over which potential taxes have accumulated
     * @param livingSpace Place from which taxes are being raised
     * @return
     */
    public Credits payTaxes(Time deltaT, ResourceDepot livingSpace) {
        return new Credits(((long)(value * taxRateFormula(deltaT, livingSpace))));
    }

    private float taxRateFormula(Time deltaT, ResourceDepot livingSpace) {
        return deltaT.inYears() * needs.calculateTaxRate(livingSpace);
    }

    public void addColonistsToPopulation(Population population) {
        this.value += population.value;
    }
}
