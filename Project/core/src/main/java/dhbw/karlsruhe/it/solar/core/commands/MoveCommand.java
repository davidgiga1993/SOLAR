package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.player.Player;
import mikera.vectorz.Vector2;

import java.util.List;

public class MoveCommand implements Command {

    private List<SpaceUnit> units;
    private Vector2 destination;
    private Player commander;

    public MoveCommand(List<SpaceUnit> units, float x, float y, Player commander) {
        this(units, (double) x, (double) y, commander);
    }

    public MoveCommand(List<SpaceUnit> units, double x, double y, Player commander) {
        this.units = units;
        destination = new Vector2(x, y);
        this.commander = commander;
    }

    @Override
    public void execute() {
        for (SpaceUnit unit : units) {
            if (unit.isOwnedBy(commander)) {
                action(unit);
            }
        }
    }

    void action(SpaceUnit unit) {
        unit.setDestination(destination);
    }
}
