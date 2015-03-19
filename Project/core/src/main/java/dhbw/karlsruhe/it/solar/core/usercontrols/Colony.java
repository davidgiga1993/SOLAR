package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Object governing the working of a player colony on an astronomical body.
 * @author Andi
 * Th, 19. March, 2015
 */
public class Colony {
	
	private Player owner;
	private Population population;
	
	public Colony(Player colonyFounder, Population colonists)
	{
		owner = colonyFounder;
		population = colonists;
	}
	
	public String getPopulationNumbers() {
		return population.toString();
	}
}
