package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlElement;

public abstract class BaseResource implements ResourceInterface {
    
    public final static long THOUSAND = 1000;
    public final static long MILLION = THOUSAND * THOUSAND;
    public final static long BILLION = THOUSAND * MILLION;
    public final static long TRILLION = THOUSAND * BILLION;
    
    @XmlElement(name = "Population_Numbers")
    protected long value;
    
    protected String formatValue(float number) {
        return String.format("%.02f", number);
    }
    
    public float inThousands() {
        return (float)value / (float)THOUSAND;
    }

    public float inMillions() {
        return (float)value / (float)MILLION;
    }

    public float inBillions() {
        return (float)value / (float)BILLION;
    }
    
    public float inTrillions() {
        return (float)value / (float)TRILLION;
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
    
    protected abstract String getUnitName();

}
