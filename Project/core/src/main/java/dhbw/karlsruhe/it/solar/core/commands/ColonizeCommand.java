package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

public class ColonizeCommand implements Command {

    private SpaceUnit unit;
    
    public ColonizeCommand(SpaceUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public void execute() {
        unit.establishColony();
    }
}
