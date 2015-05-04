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

    protected abstract String getUnitName();
    
    @Override
    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        if(inTrillions() > 0.1f) {
            return formatValue(inTrillions()) + " Trillion " + getUnitName();
        }
        if(inBillions() > 0.1f) {
            return formatValue(inBillions()) + " Billion " + getUnitName();
        }
        if(inMillions() > 0.1f) {
            return formatValue(inMillions()) + " Million " + getUnitName();
        }
        if(inThousands() > 0.1f) {
            return formatValue(inThousands()) + " k " + getUnitName();
        }
        return formatValue(value) + " " + getUnitName();      
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
}
