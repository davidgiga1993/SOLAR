package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

public class OrbitalInsertionCommand implements Command {

    private SpaceUnit unit;
    
    public OrbitalInsertionCommand(SpaceUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public void execute() {
        unit.enterOrbit();
    }
}
