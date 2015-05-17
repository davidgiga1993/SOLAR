package dhbw.karlsruhe.it.solar.colony;

import java.util.ArrayList;
import java.util.List;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

public class ColonyBuildings {
    
    private final List<Building> buildings = new ArrayList<Building>();
    
    public ColonyBuildings() {
        
    }

    public Credits payUpKeep(Time deltaT) {
        Credits totalUpKeep = new Credits();
        for(Building building : buildings) {
            totalUpKeep.addRevenueToTreasury(building.payUpKeep(deltaT));
        }
        return totalUpKeep;
    }

    public int getNumberOfWorkingLifeSupportUnits() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                return building.getNumberOfBuildingsOnline();
            }
        }
        return 0;
    }

    public int getNumberOfBuiltInfrastructure() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                return building.getNumberOfBuildingsBuilt();
            }
        }
        return 0;
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

    public void initBuildings(List<Building> newBuildings) {
        buildings.clear();
        for(Building building : newBuildings) {
            buildings.add(building);
        }
    }

    public List<Building> getListOfBuildings() {
        return buildings;
    }

    public void initInfrastructure(int initialInfrastructure) {
        buildings.add(new Infrastructure(initialInfrastructure));
    }

    public List<BaseBuilding> getListOfColonyBuildings() {
        List<BaseBuilding> list = new ArrayList<BaseBuilding>();
        for(Building building : buildings) {
            list.add((BaseBuilding)building);
        }
        return list;
    }
}
