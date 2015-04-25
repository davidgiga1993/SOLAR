package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Temperature.class})
public class SurfaceTemperature {
    
    @XmlElement(name = "Min_Temperature")
    private Temperature tempMinimum;
    @XmlElement(name = "Mean_Temperature")
    private Temperature meanTemperature;
    @XmlElement(name = "Max_Temperature")
    private Temperature tempMaximum;
    
    public SurfaceTemperature() {
        
    }
    
    public SurfaceTemperature(Temperature tempMinimum, Temperature meanTemperature, Temperature tempMaximum) {
        this.tempMinimum = tempMinimum;
        this.meanTemperature = meanTemperature;
        this.tempMaximum = tempMaximum;
    }

    public SurfaceTemperature(Temperature temperature) {
        this.tempMinimum = null;
        this.meanTemperature = temperature;
        this.tempMaximum = null;
    }

    public Temperature getMeanTemperature() {
        return meanTemperature;
    }

    public Temperature getMinimumTemperature() {
        if(null!=tempMinimum) {
            return tempMinimum;
        }
        return meanTemperature;
    }

    public Temperature getMaximumTemperature() {
        if(null!=tempMaximum) {
            return tempMaximum;
        }
        return meanTemperature;
    }
    
    @Override
    public String toString() {
        return formatValue(getMeanTemperature()) + " °C";
    }

    private String formatValue(Temperature temp) {
        return String.format("%.00f", temp.inCelsius());
    }

}
