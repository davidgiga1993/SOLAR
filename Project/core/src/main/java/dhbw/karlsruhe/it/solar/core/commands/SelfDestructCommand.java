package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

public class SelfDestructCommand implements Command {

    private SpaceUnit unit;
    
    public SelfDestructCommand(SpaceUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public void execute() {
        if(selfDestructConditionsAreMet()) {
            unit.removeShip();
        }
    }

    private boolean selfDestructConditionsAreMet() {
        return unit.isPlayerAlsoShipOwner();
    }
    
}
