package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;

public class AbandonColonyCommand implements Command {

    private Colony colony;

    public AbandonColonyCommand(AstronomicalBody primary) {
        this.colony = primary.getColony();
    }

    @Override
    public void execute() {
        if (selfDestructConditionsAreMet()) {
            colony.abandonColony();
        }
    }

    private boolean selfDestructConditionsAreMet() {
        return colony.isPlayerAlsoColonyOwner();
    }

}
