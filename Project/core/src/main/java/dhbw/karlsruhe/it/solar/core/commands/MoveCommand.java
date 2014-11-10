package dhbw.karlsruhe.it.solar.core.commands;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

public class MoveCommand implements Command {

	public List<Spaceship> units;
	public Vector2 destination;
	
	public MoveCommand(List<Spaceship> units, float x, float y) {
		this.units = units;
		destination = new Vector2(x, y);
	}
	
	@Override
	public void execute() {
		for(Spaceship unit : units) {
			unit.setDestination(destination);
			unit.moveToDestination();
		}
	}

}
