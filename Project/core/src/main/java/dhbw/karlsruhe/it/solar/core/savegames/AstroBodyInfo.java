package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class, BodyPropertyInfo.class, ColonyInfo.class})
public class AstroBodyInfo {
    
    @XmlElement(name = "Name")
    private String name;
    @XmlElement (name ="BodyProperties")
    private BodyPropertyInfo body;
    @XmlElement (name ="OrbitalProperties")
    private OrbitalPropertyInfo orbit;
    @XmlElement(name = "PlayerColony")
    private ColonyInfo colony;

    public AstroBodyInfo() {
        
    }
    
    public void fillAstroBodyInfo(AstronomicalBody body) {
        this.name = body.getName();
        OrbitalPropertyInfo orbitalInfo = new OrbitalPropertyInfo();
        orbitalInfo.fillOrbitalPropertyInfo(body);
        this.orbit = orbitalInfo;
        BodyPropertyInfo bodyInfo = new BodyPropertyInfo();
        bodyInfo.fillBodyPropertyInfo(body);
        this.body = bodyInfo;
        if(body.isColonized()) {
            ColonyInfo colonyInfo = new ColonyInfo();
            colonyInfo.fillColonyInfo(body.getColony());
            this.colony = colonyInfo;            
        }
    }

}
