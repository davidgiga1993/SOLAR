package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Andi
 * created 2015-05-04
 */
public abstract class BaseResource implements ResourceInterface {
    
    public final static long THOUSAND = 1000;
    public final static long MILLION = THOUSAND * THOUSAND;
    public final static long BILLION = THOUSAND * MILLION;
    public final static long TRILLION = THOUSAND * BILLION;
    
    @XmlElement(name = "Value")
    protected long value;
    protected float changeLastMonth;
    
    protected abstract String getUnitName();
    
    public long getNumber() {
        return value;
    }

    @Override
    public String toString() {
        if(inTrillions() > 0.1f) {
            return constructResourceStatement("Trillion", inTrillions());
        }
        if(inBillions() > 0.1f) {
            return constructResourceStatement("Billion", inBillions());
        }
        if(inMillions() > 0.1f) {
            return constructResourceStatement("Million", inMillions());
        }
        if(inThousands() > 0.1f) {
            return constructResourceStatement("k", inThousands());
        }
        return constructResourceStatement("", value);   
    }

    private String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " " + getUnitName() + " ( " + changeLastMonth() + " %)";
    }
    
    private String changeLastMonth() {
        if(changeLastMonth >= 0) {
            return "+" + formatValue(changeLastMonth * 100);            
        }
        return formatValue(changeLastMonth * 100);  
    }

    private String formatValue(float number) {
        return String.format("%.02f", number);
    }
    
    private float inThousands() {
        return (float)value / (float)THOUSAND;
    }
    
    private float inMillions() {
        return (float)value / (float)MILLION;
    }
    
    private float inBillions() {
        return (float)value / (float)BILLION;
    }
    
    private float inTrillions() {
        return (float)value / (float)TRILLION;
    }
    
    public void updateResource() {
        updateProductionStatistic();
    }
    
    protected abstract void updateProductionStatistic();
}
