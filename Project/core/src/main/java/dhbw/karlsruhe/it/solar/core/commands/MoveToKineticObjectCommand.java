package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.List;

/**
 * Created by Arga on 13.02.2015.
 */
public class MoveToKineticObjectCommand extends MoveCommand {
    protected KinematicObject destination;

    public MoveToKineticObjectCommand(List<Spaceship> units, KinematicObject target, Player commander) {
        super(units, 0, 0, commander);
        this.destination = target;
    }

    @Override
    public void action(Spaceship unit) {
        unit.setDestination(destination);
    }
}
