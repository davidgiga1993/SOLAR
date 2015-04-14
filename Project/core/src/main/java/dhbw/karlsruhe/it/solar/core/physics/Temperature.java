package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

public class Temperature {

    private static final float KELVIN_TO_CELSIUS = 273.15f;
    
    @XmlElement(name = "Temperature_Value")
    private float value;
    @XmlElement(name = "Temperature_Unit")
    private TempUnit unit;
    
    public Temperature() {
        
    }
   
    public Temperature(float value, TempUnit unit) {
        this.value = value;
        this.unit = unit;
    }
   
    public float inKelvin() {
       switch(unit) {
           case KELVIN:
               return value;
           case CELSIUS:
               return value + KELVIN_TO_CELSIUS;
           default:
               return Float.NaN;
       }
    }
    
    public float inCelsius() {
        switch(unit) {
            case KELVIN:
                return value - KELVIN_TO_CELSIUS;
            case CELSIUS:
                return value;
            default:
                return Float.NaN;
        }
     }
   
    public enum TempUnit {
        KELVIN,
        CELSIUS
    }
}
