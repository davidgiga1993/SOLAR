package dhbw.karlsruhe.it.solar.colony;

import java.util.ArrayList;
import java.util.List;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

public class ColonyBuildings {
    
    private List<Building> buildings;
    
    public ColonyBuildings() {
        this.buildings = new ArrayList<Building>();  
    }

    public Credits payUpKeep(Time deltaT) {
        Credits totalUpKeep = new Credits();
        for(Building building : buildings) {
            totalUpKeep.addRevenueToTreasury(building.payUpKeep(deltaT));
        }
        return totalUpKeep;
    }

    public long getNumberOfWorkingLifeSupportUnits() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                return building.getNumberOfBuildingsOnline();
            }
        }
        return 0;
    }

    public String getNumberOfBuiltLifeSupportUnits() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                return building.getNumberOfBuildingsBuilt();
            }
        }
        return "0";
    }

    public void buildInfrastructure() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                building.build();
                return;
            }
        }
        buildings.add(new Infrastructure(1));
    }

}