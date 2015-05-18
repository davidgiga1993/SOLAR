package dhbw.karlsruhe.it.solar.colony;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.LifeRating;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.CapacitiveResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;
import dhbw.karlsruhe.it.solar.core.resources.Population;
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
    private final ColonyResources resources = new ColonyResources();
    private final ColonyBuildings buildings = new ColonyBuildings();
    private final ColonyAlerts alerts = new ColonyAlerts();
    
    public Colony(String colonyName, AstronomicalBody colonyPlace, Player colonyFounder, Population colonists)    {
        name = colonyName;
        owner = colonyFounder;
        primary = colonyPlace;
        resources.init(this, colonists);
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
        return resources.getResources();
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }
    
    @Override
    public Population getPopulation() {
        return resources.getPopulation();
    }
    
    @Override
    public void addColonists(Population population) {
        getPopulation().addColonistsToPopulation(population);
    }
    
    @Override
    public void updateProduction(Time deltaT) {
        resources.updateProduction(deltaT, this);
        updateCapacities();
    }
    
    @Override
    public long getNumberOfWorkingLifeSupportUnits() {
        return buildings.getNumberOfWorkingLifeSupportUnits();
    }
    
    @Override
    public LifeSupport getLifeSupport() {
        return resources.getLifeSupport();
    }

    @Override
    public void alertCapacityExceeded(CapacitiveResourceInterface resource) {
        alerts.capacityExceeded(resource);
    }

    @Override
    public void capacitySufficient(CapacitiveResourceInterface resource) {
        alerts.capacitySufficient(resource);
    }
    
    /**
     * Running costs of the colony generated by buildings and other expenses.
     * @param deltaT
     * @return
     */
    public Credits payUpKeep(Time deltaT) {
         return buildings.payUpKeep(deltaT);
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

    public LabelStyle getLifeSupportDisplayStyle() {
        return getLifeSupport().getDisplayStyle();
    }

    public List<BaseBuilding> getListOfColonyBuildings() {
        return buildings.getListOfColonyBuildings();
    }

    public void updateCapacities() {
        resources.updateCapacities(this);
    }

    public LifeRating getLiferatingOfColony() {
        return primary.getLifeRating();
    }

    public void initBuildings(ColonyBuildings buildings) {
        this.buildings.initBuildings(buildings.getListOfBuildings());
    }

    public ColonyBuildings getColonyBuildings() {
        return buildings;
    }

    public ColonyAlerts getAlerts() {
        return alerts;
    }
}
