package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spacestation;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class})
public class SpaceUnitInfo {
    
    @XmlElement(name = "UnitName")
    private String name;
    @XmlElement(name = "ShipType")
    private String type;
    @XmlElement(name = "UnitOwner")
    private String ownerName;
    @XmlElement(name = "OrbitalProperties")
    private OrbitalPropertyInfo orbit;
    @XmlElement(name = "AbsolutePosition")
    private Vector2 position;

    public SpaceUnitInfo() {
        
    }
    
    public void fillSpaceUnitInfo(SpaceUnit unit) {
        this.name = unit.getName();
        this.ownerName = unit.getOwner().getName();
        if(unit.isInOrbit()) {
            OrbitalPropertyInfo orbitalInfo = new OrbitalPropertyInfo();
            orbitalInfo.fillOrbitalPropertyInfo(unit);
            this.orbit = orbitalInfo;
            return;
        }
        this.position = new Vector2(unit.getX(),unit.getY());
        if(unit instanceof Spaceship) {
            type = "Ship";
        }
        if(unit instanceof Spacestation) {
            type = "Station";
        }
        
    }

}
