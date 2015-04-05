package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

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
