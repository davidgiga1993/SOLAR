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
    
    @XmlElement(name = "Unit Name")
    private String name;
    @XmlElement(name = "ShipType")
    private String type;
    @XmlElement(name = "Unit Owner")
    private String ownerName;
    @XmlElement(name = "Orbital Properties")
    private OrbitalPropertyInfo orbit;
    @XmlElement(name = "Absolute Position")
    private Vector2 position;

    public SpaceUnitInfo(SpaceUnit unit) {
        this.name = unit.getName();
        this.ownerName = unit.getOwner().getName();
        if(unit.isInOrbit()) {
            this.orbit = new OrbitalPropertyInfo(unit);  
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
