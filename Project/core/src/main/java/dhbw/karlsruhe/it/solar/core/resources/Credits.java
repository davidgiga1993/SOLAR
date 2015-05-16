package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * Treasury resource: Credits created by taxing the population and spent on a civilization's budget such as unit upkeep, colony constructions and projects.
 * @author Andi
 *
 */
public class Credits extends GlobalResource {
    
    public Credits() {
        
    }

    public Credits(long value) {
        this.value = value;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("credits");
    }

    @Override
    protected void updateProductionStatistic() {
        if(isANewDay()) {
            updateValuesOfLastMonthList();
            changeLastMonth = treasuryGrowthFormula();
        }
    }

    @Override
    protected void updateProduction(Time deltaT, ResourceDepot productionPlace) {

    }

    @Override
    protected String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " ( " + changeLastMonth() + " / month )";
    }
    
    @Override
    public String toString() {
        if(inTrillions(value) > 0.1f || -inTrillions(value) > 0.1f) {
            return constructResourceStatement("tr", inTrillions(value));
        }
        if(inBillions(value) > 0.1f || -inBillions(value) > 0.1f) {
            return constructResourceStatement("bi", inBillions(value));
        }
        if(inMillions(value) > 0.1f || -inMillions(value) > 0.1f) {
            return constructResourceStatement("mi", inMillions(value));
        }
        if(inThousands(value) > 0.1f || -inThousands(value) > 0.1f) {
            return constructResourceStatement("k", inThousands(value));
        }
        return constructResourceStatement("", value);   
    }

    private float treasuryGrowthFormula() {
        return (float)(value - valuesOfLastMonth.get(0));
    }
    
    private String changeLastMonth() {
        String sign = "";
        if(changeLastMonth > 0) {
            sign = "+";
        }
        if(inTrillions(changeLastMonth) > 0.1f || -inTrillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / TRILLION) + " tr";
        }
        if(inBillions(changeLastMonth) > 0.1f || -inBillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / BILLION) + " bi";
        }
        if(inMillions(changeLastMonth) > 0.1f || -inMillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / MILLION) + " mi";
        }
        if(inThousands(changeLastMonth) > 0.1f || -inThousands(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / THOUSAND) + " k";
        }
        return sign + formatValue(changeLastMonth); 
    }

    public void subtractFromValue(Credits upKeep) {
        value -= upKeep.getNumber();
    }

    @Override
    public void updateStatistic() {
        updateProductionStatistic();
    }
}
