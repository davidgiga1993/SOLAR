package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Time {
    
    private static final float HOURS_PER_DAY = 24f;
    private static final float DAYS_PER_YEAR = 365.25f;
    private static final float ONE_THOUSAND = 1000f;
    private static final float ONE_MILLION = 1000000f;
    
    @XmlElement(name = "Time_Value")
    private float value;
    @XmlElement(name = "Time_Unit")
    private TimeUnit unit;
    
    public Time(float value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
    }
    
    public Time() {

    }

    public float inHours() {
        switch (unit) {
            case HOURS:
                return value;
            case DAYS:
                return value * HOURS_PER_DAY;
            case YEARS:
                return value * HOURS_PER_DAY * DAYS_PER_YEAR;
            default:
                return Float.NaN;
        }
    }
    
    public float inDays() {
        switch (unit) {
            case HOURS:
                return value / HOURS_PER_DAY;
            case DAYS:
                return value;
            case YEARS:
                return value * DAYS_PER_YEAR;
            default:
                return Float.NaN;
        }
    }
    
    public float inYears() {
        switch (unit) {
            case HOURS:
                return value / ( DAYS_PER_YEAR * HOURS_PER_DAY );
            case DAYS:
                return value / DAYS_PER_YEAR;
            case YEARS:
                return value;
            default:
                return Float.NaN;
        }
    }
    
    @Override
    public String toString() {
        if( this.inYears() > ONE_MILLION) {
            return formatValue(this.inYears()/ONE_MILLION) + " M Years";
        }
        if( this.inYears() > ONE_THOUSAND ) {
            return formatValue(this.inYears()/ONE_THOUSAND) + " k Years";
        }
        if( this.inDays() > DAYS_PER_YEAR ) {
            return formatValue(this.inYears()) + " Years";
        }
        if( this.inHours() > HOURS_PER_DAY ) {
            return formatValue(this.inDays()) + " Days";
        }      
        return formatValue(this.inHours()) + " Hours";
    }
    
    private String formatValue(float value) {
        return String.format("%.01f", value);
    }
    
    public enum TimeUnit {
        HOURS,
        DAYS,
        YEARS
    }

}
