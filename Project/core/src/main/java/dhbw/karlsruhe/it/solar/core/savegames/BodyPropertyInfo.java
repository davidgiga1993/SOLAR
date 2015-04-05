package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.BodyType;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

public class BodyPropertyInfo {
    
    @XmlElement(name = "Radius")
    private Length radius;
    @XmlElement(name = "Mass")
    private Mass mass;
    @XmlElement(name = "Type")
    private BodyType type;

    public BodyPropertyInfo(AstronomicalBody body) {
        this.radius = body.getRadius();
        this.mass = body.getMass();
        this.type = body.getBodyType();
    }

}
