package dhbw.karlsruhe.it.solar.core.commands;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.List;

public class MoveCommand implements Command {

	public List<Spaceship> units;
	public Vector2 destination;
	public Player commander;
	
	public MoveCommand(List<Spaceship> units, float x, float y, Player commander) {
		this.units = units;
		destination = new Vector2(x, y);
		this.commander = commander;
	}
	
	@Override
	public void execute() {
		for(Spaceship unit : units) {
			if (unit.isOwnedBy(commander)) {
				unit.setDestination(destination);
				unit.moveToDestination();
			}
		}
	}

}
