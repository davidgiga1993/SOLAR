package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.space_units.Spaceship;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceStation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class, MissionInfo.class})
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
    @XmlElement(name = "Destination")
    private MissionInfo mission;

    public SpaceUnitInfo() {

    }

    public void fillSpaceUnitInfo(SpaceUnit unit) {
        this.name = unit.getName();
        this.ownerName = unit.getOwner().getName();
        if (unit instanceof Spaceship) {
            type = "Ship";
        }
        if (unit instanceof SpaceStation) {
            type = "Station";
        }
        if (unit.isOnMission()) {
            MissionInfo missionInfo = new MissionInfo();
            missionInfo.fillMissionInfo(unit);
            this.mission = missionInfo;
        }
        if (unit.isInOrbit()) {
            OrbitalPropertyInfo orbitalInfo = new OrbitalPropertyInfo();
            orbitalInfo.fillOrbitalPropertyInfo(unit);
            this.orbit = orbitalInfo;
            return;
        }
        this.position = new Vector2(unit.getX(), unit.getY());
    }

    public String getName() {
        return name;
    }

    public Vector2 getLocation() {
        return position;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public OrbitalPropertyInfo getOrbitInfo() {
        return orbit;
    }

    public String getType() {
        return type;
    }

    public MissionInfo getMissionInfo() {
        return mission;
    }

}
