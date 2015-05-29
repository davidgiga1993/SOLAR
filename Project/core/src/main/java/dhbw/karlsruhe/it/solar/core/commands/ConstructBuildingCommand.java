package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.colony.ColonyBuildings;

public class ConstructBuildingCommand implements Command {
    
    private Colony colony;
    private ColonyBuildings buildings;

    public ConstructBuildingCommand(Colony colony) {
        this.colony = colony;
        this.buildings = colony.getColonyBuildings();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }

    public void infrastructure() {
        if(buildConditionsAreMet()) {
            buildings.buildInfrastructure();
        }
    }

    private boolean buildConditionsAreMet() {
        return colony.isPlayerAlsoColonyOwner();
    }

}
