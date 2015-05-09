package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

public class Coorbital {
    
    @XmlElement(name = "NameOfDominantBody")
    private String nameOfDominantBody;
    @XmlElement(name = "AngularDeviation")
    private Angle angularDeviation;
    
    public Coorbital() {
        
    }
    
    public Coorbital(Orbiter dominantBody, Angle angularDeviation) {
        this.nameOfDominantBody = dominantBody.getName();
        this.angularDeviation = angularDeviation;
    }

    public Angle getAngularDeviation() {
        return angularDeviation;
    }

    public String getNameOfDominantBody() {
        return nameOfDominantBody;
    }
}
