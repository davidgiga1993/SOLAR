package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Temperature.class})
public class SurfaceTemperatures {
    
    @XmlElement(name = "Min_Temperature")
    private Temperature tempMinimum;
    @XmlElement(name = "Mean_Temperature")
    private Temperature meanTemperature;
    @XmlElement(name = "Max_Temperature")
    private Temperature tempMaximum;
    
    public SurfaceTemperatures() {
        
    }
    
    public SurfaceTemperatures(Temperature tempMinimum, Temperature meanTemperature, Temperature tempMaximum) {
        this.tempMinimum = tempMinimum;
        this.meanTemperature = meanTemperature;
        this.tempMaximum = tempMaximum;
    }

    public SurfaceTemperatures(Temperature temperature) {
        this.tempMinimum = null;
        this.meanTemperature = temperature;
        this.tempMaximum = null;
    }

}
