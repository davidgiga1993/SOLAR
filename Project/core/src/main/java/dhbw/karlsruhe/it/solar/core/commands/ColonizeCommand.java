package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

public class ColonizeCommand implements Command {

    private SpaceUnit unit;
    
    public ColonizeCommand(SpaceUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public void execute() {
        if(colonizeConditionsAreMet()) {
            unit.establishColony();            
        }
    }

    private boolean colonizeConditionsAreMet() {
        return (unit.isInOrbit() && unit.isPlayerAlsoShipOwner());
    }
}
