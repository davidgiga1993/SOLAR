package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.math.Vector2;

public class MissionInfoExtended {
    
    private MissionInfo mission;
    private String nameOfSpaceUnit;
    
    public MissionInfoExtended(String name, MissionInfo mission) {
        this.mission = mission;
        this.nameOfSpaceUnit = name;
    }

    public String getUnitName() {
        return nameOfSpaceUnit;
    }
    
    public boolean isMissionTargetAnObject() {
        return mission.isMissionTargetAnObject();
    }

    public Vector2 getDestinationCoordinates() {
        return mission.getDestinationCoordinates();
    }

    public String getDestinationName() {
        return mission.getDestinationName();
    }

}
