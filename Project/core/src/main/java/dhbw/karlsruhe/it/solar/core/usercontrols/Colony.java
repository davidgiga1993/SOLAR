package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.CapacitiveResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.resources.StandardResourceInterface;
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
    private final List<CapacitiveResourceInterface> capacitiveResources = new ArrayList<CapacitiveResourceInterface>();
    private final List<StandardResourceInterface> resources = new ArrayList<StandardResourceInterface>();
    
    public Colony(String colonyName, AstronomicalBody colonyPlace, Player colonyFounder, Population colonists)    {
        name = colonyName;
        owner = colonyFounder;
        primary = colonyPlace;
        resources.add(POPULATION_RESOURCE_ID, colonists);
        capacitiveResources.add(LIFE_SUPPORT_ID, new LifeSupport());
    }
    
    public String getPopulationInformation() {
        return getPopulation().toString();
    }

    @Override
    public boolean isOwnedBy(Player player) {
        return owner.equals(player);
    }

    @Override
    public boolean isPermanentHabitat() {
        return true;
    }

    @Override
    public List<StandardResourceInterface> getResources() {
        return resources;
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }
    
    @Override
    public Population getPopulation() {
        return (Population)resources.get(POPULATION_RESOURCE_ID);
    }
    
    @Override
    public void addColonists(Population population) {
        getPopulation().addColonistsToPopulation(population);
    }
    
    @Override
    public void updateProduction(Time deltaT) {
        for (StandardResourceInterface resource : resources ) {
            resource.updateResource(deltaT, this);
        }
        for (CapacitiveResourceInterface resource : capacitiveResources ) {
            resource.updateConsumption(this);
        }
    }
    
    @Override
    public long getNumberOfWorkingLifeSupportUnits() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * Running costs of the colony generated by buildings and other expenses.
     * @param deltaT
     * @return
     */
    public Credits payUpKeep(Time deltaT) {
     // TODO Auto-generated method stub
        return new Credits(0);
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

    public AstronomicalBody getColonySite() {
        return primary;
    }

    public Credits raiseTaxes(Time deltaT) {
        return getPopulation().payTaxes(deltaT, this);
    }

    public String getLifeSupportInformation() {
        return getLifeSupport().toString();
    }

    private LifeSupport getLifeSupport() {
        return (LifeSupport)capacitiveResources.get(LIFE_SUPPORT_ID);
    }

    public LabelStyle getLifeSupportDisplayStyle() {
        return getLifeSupport().getDisplayStyle();
    }
}
