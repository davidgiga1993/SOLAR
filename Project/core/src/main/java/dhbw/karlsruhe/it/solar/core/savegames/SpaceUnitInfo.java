package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class})
public class SpaceUnitInfo {
    
    @XmlElement
    private String name;
    @XmlElement
    private OrbitalPropertyInfo orbit;

    public SpaceUnitInfo(SpaceUnit unit) {
        this.name = unit.getName();
        this.orbit = new OrbitalPropertyInfo(unit);
    }

}
