package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

import javax.xml.bind.annotation.XmlElement;

public class CoOrbital {

    @XmlElement(name = "NameOfDominantBody")
    private String nameOfDominantBody;
    @XmlElement(name = "AngularDeviation")
    private Angle angularDeviation;

    public CoOrbital() {

    }

    public CoOrbital(Orbiter dominantBody, Angle angularDeviation) {
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
