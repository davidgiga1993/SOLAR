package dhbw.karlsruhe.it.solar.core.commands;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarSystem;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

public class OrbitalInsertionCommand implements Command {

	private SpaceUnit unit;
	private AstronomicalBody orbitPrimary;
	
	public OrbitalInsertionCommand(SpaceUnit unit, AstronomicalBody orbitPrimary) {
		this.unit = unit;
		this.orbitPrimary = orbitPrimary;
	}
	
	public OrbitalInsertionCommand(SpaceUnit unit) {
		this.unit = unit;
	}
	
	@Override
	public void execute() {
    	unit.enterOrbit();
	}
}
