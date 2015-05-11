package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Time {
    
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;
    public static final float DAYS_PER_YEAR = 365.25f;
    private static final int ONE_THOUSAND = 1000;
    private static final int ONE_MILLION = ONE_THOUSAND*ONE_THOUSAND;
    
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
    
    public float inSeconds() {
        switch (unit) {
            case SECONDS:
                return value;
            case MINUTES:
                return value * SECONDS_PER_MINUTE;
            case HOURS:
                return value * SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
            case DAYS:
                return value * SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY;
            case YEARS:
                return value * SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_YEAR;
            default:
                return Float.NaN;
        }
    }
    
    public float inMinutes() {
        switch (unit) {
            case SECONDS:
                return value / SECONDS_PER_MINUTE;
            case MINUTES:
                return value;
            case HOURS:
                return value * MINUTES_PER_HOUR;
            case DAYS:
                return value * MINUTES_PER_HOUR * HOURS_PER_DAY;
            case YEARS:
                return value * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_YEAR;
            default:
                return Float.NaN;
        }
    }

    public float inHours() {
        switch (unit) {
            case SECONDS:
                return value / SECONDS_PER_MINUTE / MINUTES_PER_HOUR;
            case MINUTES:
                return value / MINUTES_PER_HOUR;
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
        case SECONDS:
            return value / SECONDS_PER_MINUTE / MINUTES_PER_HOUR / HOURS_PER_DAY;
        case MINUTES:
            return value / MINUTES_PER_HOUR / HOURS_PER_DAY;
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
        case SECONDS:
            return value / DAYS_PER_YEAR / HOURS_PER_DAY / SECONDS_PER_MINUTE / MINUTES_PER_HOUR;
        case MINUTES:
            return value / DAYS_PER_YEAR / HOURS_PER_DAY / MINUTES_PER_HOUR;
        case HOURS:
            return value / DAYS_PER_YEAR / HOURS_PER_DAY;
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
        if(this.value < 0) {
            return "-" + new Time(-value, unit).toString();
        }
        if( this.inYears() > ONE_MILLION) {
            return formatValue(this.inYears()/ONE_MILLION) + " mi Years";
        }
        if( this.inYears() > ONE_THOUSAND ) {
            return formatValue(this.inYears()/ONE_THOUSAND) + " k Years";
        }
        if( this.inDays() > DAYS_PER_YEAR ) {
            return formatValue(this.inYears()) + " Years";
        }
        if( this.inHours() > HOURS_PER_DAY + 1 ) {
            return formatValue(this.inDays()) + " Days";
        }   
        if( this.inMinutes() > MINUTES_PER_HOUR ) {
            return formatValue(this.inHours()) + " Hours";
        }  
        if( this.inSeconds() > SECONDS_PER_MINUTE ) {
            return formatValue(this.inMinutes()) + " Minutes";
        }  
        return formatValue(this.inSeconds()) + " Seconds";
    }
    
    private String formatValue(float value) {
        return String.format("%.01f", value);
    }
    
    public enum TimeUnit {
        SECONDS,
        MINUTES,
        HOURS,
        DAYS,
        YEARS
    }

    public Time absolute() {
        if(value < 0 ) {
            return new Time(-value, unit);
        }
        return this;
    }

    public void add(Time timeToAdd) {
        switch (unit) {
            case HOURS:
                value += timeToAdd.inHours();
            case DAYS:
                value += timeToAdd.inDays();
            case YEARS:
                value += timeToAdd.inYears();
            default:
                return;
        }
    }
}
