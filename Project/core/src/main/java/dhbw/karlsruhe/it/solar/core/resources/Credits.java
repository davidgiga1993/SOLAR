package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * Treasury resource: Credits created by taxing the population and spent on a civilization's budget such as unit upkeep, colony constructions and projects.
 * @author Andi
 *
 */
public class Credits extends BaseResource {
    
    public Credits() {
        
    }

    public Credits(long value) {
        this.value = value;
    }

    @Override
    public long getMaximum() {
        return TRILLION * TRILLION;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("resource_placeholder");
    }

    @Override
    protected String getUnitName() {
        return "Credits";
    }

    @Override
    protected void updateProductionStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
            changeLastMonth = treasuryGrowthFormula();
        }
    }

    @Override
    protected void updateProduction(Time deltaT) {

    }

    @Override
    protected String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " " + getUnitName() + " ( " + changeLastMonth() + " " + getUnitName() + "/month )";
    }

    private float treasuryGrowthFormula() {
        return (float)(value - valuesOfLastMonth.get(0));
    }
    
    private String changeLastMonth() {
        String sign = "+ ";
        if(changeLastMonth < 0) {
            sign = "";
            changeLastMonth *= -1;
        }
        if(changeLastMonth > 0.1f * TRILLION) {
            return sign + formatValue(changeLastMonth / TRILLION) + " Trillion";
        }
        if(changeLastMonth > 0.1f * BILLION) {
            return sign + formatValue(changeLastMonth / BILLION) + " Billion";
        }
        if(changeLastMonth > 0.1f * MILLION) {
            return sign + formatValue(changeLastMonth / MILLION) + " Million";
        }
        if(changeLastMonth > 0.1f * THOUSAND) {
            return sign + formatValue(changeLastMonth / THOUSAND) + " k";
        }
        return sign + formatValue(changeLastMonth); 
    }

    public void updateStatistics() {
        updateProductionStatistic();
    }
}
