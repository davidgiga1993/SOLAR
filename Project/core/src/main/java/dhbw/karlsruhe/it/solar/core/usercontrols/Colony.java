package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.player.Ownable;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Object governing the working of a player colony on an astronomical body.
 * @author Andi
 * Th, 19. March, 2015
 */
public class Colony implements Ownable {
	
	private String name;
	private Player owner;
	private Population population;
	
	public Colony(String colonyName, Player colonyFounder, Population colonists)
	{
		name = colonyName;
		owner = colonyFounder;
		population = colonists;
	}
	
	public String getPopulationNumbers() {
		return population.toString();
	}

	@Override
	public boolean isOwnedBy(Player player) {
		return owner.equals(player);
	}

	public String getName() {
		return name;
	}
}
