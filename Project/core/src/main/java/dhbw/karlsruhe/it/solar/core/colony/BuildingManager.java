package dhbw.karlsruhe.it.solar.core.colony;

import java.util.ArrayList;
import java.util.List;

public class BuildingManager {

    private ColonyBuildings newBuildings = new ColonyBuildings();
    private List<Building> buildings = new ArrayList<>();
    private int infraNumber;

    public CreatableColony createInfrastructure(int numberOfBuildings) {
        this.infraNumber = numberOfBuildings;
        return new CreatableColony();
    }

    public CreatableColony createFromBuildingList(List<BaseBuilding> baseBuildings) {
        buildings.addAll(baseBuildings);
        return new CreatableColony();
    }

    public class CreatableColony {
        private CreatableColony() {

        }

        public ColonyBuildings generateColonyBuildings() {
            setUpBuildings();
            return newBuildings;
        }

        private void setUpBuildings() {
            if (buildings.size() > 0) {
                newBuildings.initBuildings(buildings);
                return;
            }
            newBuildings.initInfrastructure(infraNumber);
        }
    }
}
