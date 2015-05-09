package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.util.ArrayList;
import java.util.List;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.BaseResource;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.resources.ResourceInterface;
import dhbw.karlsruhe.it.solar.player.Ownable;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Object governing the working of a player colony on an astronomical body.
 * @author Andi
 * Th, 19. March, 2015
 */
public class Colony implements Ownable, ResourceDepot {
    
    private String name;
    private Player owner;
    private AstronomicalBody primary;
    private final List<BaseResource> resources = new ArrayList<BaseResource>();
    
    public Colony(String colonyName, AstronomicalBody colonyPlace, Player colonyFounder, Population colonists)    {
        name = colonyName;
        owner = colonyFounder;
        resources.add(POPULATION_RESOURCE_ID, colonists);
        primary = colonyPlace;
    }
    
    public String getPopulationNumbers() {
        return getPopulation().toString();
    }

    @Override
    public boolean isOwnedBy(Player player) {
        return owner.equals(player);
    }
    
    public List<BaseResource> getResources() {
        return resources;
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
        return (Population)resources.get(POPULATION_RESOURCE_ID);
    }

    public AstronomicalBody getColonySite() {
        return primary;
    }

    public void updateProduction(Time deltaT) {
        for (ResourceInterface resource : resources ) {
            resource.updateResource(deltaT);
        }
    }
}
