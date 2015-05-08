package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
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
    private AstronomicalBody primary;
    private Population population;
    
    public Colony(String colonyName, AstronomicalBody colonyPlace, Player colonyFounder, Population colonists)    {
        name = colonyName;
        owner = colonyFounder;
        population = colonists;
        primary = colonyPlace;
    }
    
    public String getPopulationNumbers() {
        return population.toString();
    }

    @Override
    public boolean isOwnedBy(Player player) {
        return owner.equals(player);
    }
    
    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayerAlsoColonyOwner() {
        return primary.isPlayerAlsoColonyOwner();
    }

    public void abandonColony() {
        primary.abandonColony();
        owner.abandonColony(this);
    }

    public Population getPopulation() {
        return population;
    }

    public AstronomicalBody getColonySite() {
        return primary;
    }
}
