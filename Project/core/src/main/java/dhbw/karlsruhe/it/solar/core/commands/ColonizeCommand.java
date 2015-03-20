package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

public class ColonizeCommand implements Command {

	private SpaceUnit unit;
	private AstronomicalBody destination;
	private Population colonists;
	
	public ColonizeCommand(SpaceUnit unit, AstronomicalBody destination, Population colonists) {
		this.unit = unit;
		this.destination = destination;
		this.colonists = colonists;
	}
	
	@Override
	public void execute() {
    	unit.establishColony(destination, colonists);
	}
}
