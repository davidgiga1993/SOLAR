package dhbw.karlsruhe.it.solar.colony;

import java.util.ArrayList;
import java.util.List;

import dhbw.karlsruhe.it.solar.core.physics.Power;
import dhbw.karlsruhe.it.solar.core.physics.Power.PowerUnit;
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

    public long getCurrentLifeSupportCapacityWithoutLifeRating() {
        for(Building building : buildings) {
            if(building instanceof Infrastructure) {
                return building.getNumberOfBuildingsOnline() * building.getCapacityPerBuilding();
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

    /**
     * Calculates the total electric power consumptions of all buildings of this colony.
     * @return
     */
    public Power getElectricPowerConsumption() {
        Power totalPowerConsumption = new Power(0, PowerUnit.MEGAWATT);
        for (Building building : buildings) {
            totalPowerConsumption.addPower(building.getElectricPowerConsumption());
        }
        return totalPowerConsumption;
    }

    public long getCurrentPowerCapacity() {
        for(Building building : buildings) {
            if(building instanceof PowerPlant) {
                return building.getNumberOfBuildingsOnline() * building.getCapacityPerBuilding();
            }
        }
        return 0;
    }
}
