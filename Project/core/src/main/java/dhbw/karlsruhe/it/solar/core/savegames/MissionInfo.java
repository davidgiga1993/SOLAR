package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

import javax.xml.bind.annotation.XmlElement;

public class MissionInfo {

    @XmlElement(name = "TargetLocation")
    private Vector2 location;
    @XmlElement(name = "TargetActor")
    private String targetName;

    public MissionInfo() {

    }

    public void fillMissionInfo(SpaceUnit unit) {
        targetName = unit.getNameOfDestination();
        if (null == targetName) {
            mikera.vectorz.Vector2 destination = unit.getDestinationVector();
            location = new Vector2((float) destination.x, (float) destination.y);
        }
    }

    public boolean isMissionTargetAnObject() {
        return (null != targetName);
    }

    public Vector2 getDestinationCoordinates() {
        return location;
    }

    public String getDestinationName() {
        return targetName;
    }

}
