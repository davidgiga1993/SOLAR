package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.player.Player;

import java.util.List;

/**
 * Created by Arga on 13.02.2015.
 */
public class MoveToKineticObjectCommand extends MoveCommand {
    private KinematicObject destination;

    public MoveToKineticObjectCommand(List<SpaceUnit> units, KinematicObject target, Player commander) {
        super(units, 0, 0, commander);
        this.destination = target;
    }

    @Override
    public void action(SpaceUnit unit) {
        unit.setDestination(destination);
    }
}
