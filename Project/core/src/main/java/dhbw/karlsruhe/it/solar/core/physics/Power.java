package dhbw.karlsruhe.it.solar.core.physics;

public class Power {
 
    public static final float WATT_PER_KILOWATT = 1000;
    public static final float KILOWATT_PER_MEGAWATT = 1000;
    public static final float MEGAWATT_PER_GIGAWATT = 1000;
    public static final float GIGAWATT_PER_TERAWATT = 1000;
    
    private float value;
    private PowerUnit unit;
    
    public Power (float value, PowerUnit unit)    {
        set(value, unit);
    }
    
    private void set(float value, PowerUnit unit) {
        if(0<=value) {
            this.value = value;
            this.unit = unit;
            return;
        }
        throw new IllegalArgumentException("Electric Power must be positive value or zero!");
    }
    
    public float inWatt() {
        switch(unit) {
            case WATT:
                return value;
            case KILOWATT:
                return value * WATT_PER_KILOWATT;
            case MEGAWATT:
                return value * WATT_PER_KILOWATT * KILOWATT_PER_MEGAWATT;
            case GIGAWATT:
                return value * WATT_PER_KILOWATT * KILOWATT_PER_MEGAWATT * MEGAWATT_PER_GIGAWATT;
            case TERAWATT:
                return value * WATT_PER_KILOWATT * KILOWATT_PER_MEGAWATT * MEGAWATT_PER_GIGAWATT * GIGAWATT_PER_TERAWATT;
            default:
                return Float.NaN;
        }
    }
    
    public float inKilowatt() {
        switch(unit) {
            case WATT:
                return value / WATT_PER_KILOWATT;
            case KILOWATT:
                return value;
            case MEGAWATT:
                return value * KILOWATT_PER_MEGAWATT;
            case GIGAWATT:
                return value * KILOWATT_PER_MEGAWATT * MEGAWATT_PER_GIGAWATT;
            case TERAWATT:
                return value * KILOWATT_PER_MEGAWATT * MEGAWATT_PER_GIGAWATT * GIGAWATT_PER_TERAWATT;
            default:
                return Float.NaN;
        }
    }
    
    public float inMegawatt() {
        switch(unit) {
            case WATT:
                return value / WATT_PER_KILOWATT / KILOWATT_PER_MEGAWATT;
            case KILOWATT:
                return value / KILOWATT_PER_MEGAWATT;
            case MEGAWATT:
                return value;
            case GIGAWATT:
                return value * MEGAWATT_PER_GIGAWATT;
            case TERAWATT:
                return value * MEGAWATT_PER_GIGAWATT * GIGAWATT_PER_TERAWATT;
            default:
                return Float.NaN;
        }
    }
    
    public float inGigawatt() {
        switch(unit) {
            case WATT:
                return value / WATT_PER_KILOWATT / KILOWATT_PER_MEGAWATT / MEGAWATT_PER_GIGAWATT;
            case KILOWATT:
                return value / KILOWATT_PER_MEGAWATT / MEGAWATT_PER_GIGAWATT;
            case MEGAWATT:
                return value / MEGAWATT_PER_GIGAWATT;
            case GIGAWATT:
                return value;
            case TERAWATT:
                return value * GIGAWATT_PER_TERAWATT;
            default:
                return Float.NaN;
        }
    }   
    
    public float inTerawatt() {
        switch(unit) {
            case WATT:
                return value / WATT_PER_KILOWATT / KILOWATT_PER_MEGAWATT / MEGAWATT_PER_GIGAWATT / GIGAWATT_PER_TERAWATT;
            case KILOWATT:
                return value / KILOWATT_PER_MEGAWATT / MEGAWATT_PER_GIGAWATT / GIGAWATT_PER_TERAWATT;
            case MEGAWATT:
                return value / MEGAWATT_PER_GIGAWATT / GIGAWATT_PER_TERAWATT;
            case GIGAWATT:
                return value / GIGAWATT_PER_TERAWATT;
            case TERAWATT:
                return value;
            default:
                return Float.NaN;
        }
    }   
    
    public void addPower(Power additionalPower) {
        switch(unit) {
            case WATT:
                value += additionalPower.inWatt();
            case KILOWATT:
                value += additionalPower.inKilowatt();
            case MEGAWATT:
                value += additionalPower.inMegawatt();
            case GIGAWATT:
                value += additionalPower.inGigawatt();
            case TERAWATT:
                value += additionalPower.inTerawatt();
            default:
                value = Float.NaN;
        }       
    }
    
    @Override
    public String toString() {
        if( this.inGigawatt() > 1f ) {
            return formatValue(this.inGigawatt()) + " GW";
        }
        if( this.inMegawatt() > 1f ) {
            return formatValue(this.inMegawatt()) + " MW";
        }
        if( this.inKilowatt() > 1f ) {
            return formatValue(this.inKilowatt()) + " kW";
        }
        return formatValueNoDecimal(this.inWatt()) + " W";
    }
    
    private String formatValueNoDecimal(float value) {
        return String.format("%.00f", value);
    }

    private String formatValue(float value) {
        return String.format("%.01f", value);
    }
    
    public enum PowerUnit {
        WATT,
        KILOWATT,
        MEGAWATT,
        GIGAWATT,
        TERAWATT
    }
}
