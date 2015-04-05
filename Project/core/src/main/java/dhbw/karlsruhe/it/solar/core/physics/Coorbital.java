package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

public class Coorbital {
    
    private Orbiter dominantBody;
    @XmlElement(name = "Name of Dominant Body")
    private String nameOfDominantBody;
    @XmlElement(name = "AngularDeviation")
    private Angle angularDeviation;
    
    public Coorbital(Orbiter dominantBody, Angle angularDeviation) {
        this.dominantBody = dominantBody;
        this.nameOfDominantBody = dominantBody.getName();
        this.angularDeviation = angularDeviation;
    }
}