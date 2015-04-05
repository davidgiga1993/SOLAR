package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

public class AstroBodyInfo {
    
    @XmlElement(name = "Name")
    private String name;
    @XmlElement (name ="Orbital Properties")
    private OrbitalPropertyInfo orbit;
    @XmlElement (name ="Body Properties")
    private BodyPropertyInfo body;

    public AstroBodyInfo(AstronomicalBody body) {
        this.name = body.getName();
        this.orbit = new OrbitalPropertyInfo(body);
        this.body = new BodyPropertyInfo(body);
    }

}
