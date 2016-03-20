package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.List;

/**
 * Created by Arga on 13.02.2015.
 */
public class MoveToAstronomicalBodyCommand extends MoveCommand {
    private AstronomicalBody destination;

    public MoveToAstronomicalBodyCommand(List<SpaceUnit> units, AstronomicalBody target, Player commander) {
        super(units, 0, 0, commander);
        this.destination = target;
    }

    @Override
    public void action(SpaceUnit unit) {
        unit.setDestination(destination);
    }
}
