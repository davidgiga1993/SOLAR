package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class, BodyPropertyInfo.class})
public class AstroBodyInfo {
    
    @XmlElement(name = "Name")
    private String name;
    @XmlElement (name ="Body Properties")
    private BodyPropertyInfo body;
    @XmlElement (name ="Orbital Properties")
    private OrbitalPropertyInfo orbit;

    public AstroBodyInfo(AstronomicalBody body) {
        this.name = body.getName();
        this.orbit = new OrbitalPropertyInfo(body);
        this.body = new BodyPropertyInfo(body);
    }

}
