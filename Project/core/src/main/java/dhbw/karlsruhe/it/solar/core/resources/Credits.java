package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.core.solar.TextureCache;

/**
 * Treasury resource: Credits created by taxing the population and spent on a civilization's budget such as unit upkeep, colony constructions and projects.
 *
 * @author Andi
 */
public class Credits extends GlobalResource {

    public Credits() {

    }

    public Credits(long value) {
        this.value = value;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCache.GAME_ATLAS.findRegion("credits");
    }

    @Override
    public void updateStatistic() {
        if (isANewDay()) {
            updateValuesOfLastMonthList();
            changeLastMonth = treasuryGrowthFormula();
        }
    }

    @Override
    protected String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " ( " + changeLastMonth() + " / month )";
    }

    @Override
    public String toString() {
        if (inTrillions(value) > 0.1f || -inTrillions(value) > 0.1f) {
            return constructResourceStatement("tr", inTrillions(value));
        }
        if (inBillions(value) > 0.1f || -inBillions(value) > 0.1f) {
            return constructResourceStatement("bi", inBillions(value));
        }
        if (inMillions(value) > 0.1f || -inMillions(value) > 0.1f) {
            return constructResourceStatement("mi", inMillions(value));
        }
        if (inThousands(value) > 0.1f || -inThousands(value) > 0.1f) {
            return constructResourceStatement("k", inThousands(value));
        }
        return constructResourceStatement("", value);
    }

    @Override
    protected String getNameOfResourceBarTitle() {
        return "Treasury";
    }

    private float treasuryGrowthFormula() {
        return (float) (value - valuesOfLastMonth.get(0));
    }

    protected String constructChangeStatement() {
        return changeLastMonth() + " / month )";
    }

    private String changeLastMonth() {
        String sign = "";
        if (changeLastMonth > 0) {
            sign = "+";
        }
        if (inTrillions(changeLastMonth) > 0.1f || -inTrillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / TRILLION) + " tr";
        }
        if (inBillions(changeLastMonth) > 0.1f || -inBillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / BILLION) + " bi";
        }
        if (inMillions(changeLastMonth) > 0.1f || -inMillions(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / MILLION) + " mi";
        }
        if (inThousands(changeLastMonth) > 0.1f || -inThousands(changeLastMonth) > 0.1f) {
            return sign + formatValue(changeLastMonth / THOUSAND) + " k";
        }
        return sign + formatValue(changeLastMonth);
    }

    /**
     * Treasury is reduced by the budgeted expenses such as unit upkeep, etc.
     *
     * @param expenses Costs to be covered by the treasury.
     */
    public void subtractExpensesFromTreasury(Credits expenses) {
        value -= expenses.getNumber();
    }

    /**
     * Treasury is increased by revenue from taxes, etc.
     *
     * @param revenue Income to be added to the treasury
     */
    public void addRevenueToTreasury(Credits revenue) {
        this.value += revenue.value;
    }
}
