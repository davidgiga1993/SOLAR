package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

public class OrbitalInsertionCommand implements Command {

    private SpaceUnit unit;
    
    public OrbitalInsertionCommand(SpaceUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public void execute() {
        if(orbitalInsertionConditionsAreMet()) {
        unit.enterOrbit();
        }
    }

    private boolean orbitalInsertionConditionsAreMet() {
        return (unit.isPlayerAlsoShipOwner() && !unit.isInOrbit());
    }
}
