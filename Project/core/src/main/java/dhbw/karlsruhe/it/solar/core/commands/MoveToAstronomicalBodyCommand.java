package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.List;

/**
 * Created by Arga on 13.02.2015.
 */
public class MoveToAstronomicalBodyCommand extends MoveCommand {
    protected AstronomicalBody destination;

    public MoveToAstronomicalBodyCommand(List<Spaceship> units, AstronomicalBody target, Player commander) {
        super(units, 0, 0, commander);
        this.destination = target;
    }

    @Override
    public void action(Spaceship unit) {
        unit.setDestination(destination);
    }
}
