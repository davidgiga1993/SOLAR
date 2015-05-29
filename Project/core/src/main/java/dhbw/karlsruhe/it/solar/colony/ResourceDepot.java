package dhbw.karlsruhe.it.solar.colony;

import java.util.List;

import dhbw.karlsruhe.it.solar.core.physics.Power;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.CapacitiveResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.StandardResourceInterface;

/**
 * Interface for in-game objects containing local resources of any kind.
 * @author Andi
 * created 2015-05-09
 */
public interface ResourceDepot {
    
    public final static int POPULATION_RESOURCE_ID = 0;
    public final static int LIFE_SUPPORT_ID = 0;
    
    /**
     * Returns the total list of resources stored in this resource depot.
     * @return
     */
    public List<StandardResourceInterface> getResources();
    
    /**
     * Returns the number of people living on this ResourceDepot, if any.
     * @return
     */
    public Population getPopulation();
    
    /**
     * Adds Colonists to the population.
     * @return
     */
    public void addColonists(Population population);

    /**
     * Indicates whether the depot has a permanent living space designed as the residence of its inhabitants or whether it is a temporary accommodation such as passenger ship cabins.
     * @return
     */
    public boolean isPermanentHabitat();

    /**
     * Determines the life support capacity produced by the units in working condition at this depot.
     * To be in working condition, the units need to be supplied with electricity and their running costs have to covered by the treasury.
     * @return
     */
    public long getCurrentLifeSupportCapacity();
    
    /**
     * Resource production at this site is updated depending on the time interval since the last update.
     * @param deltaT
     */
    public void updateProduction(Time deltaT);
    
    /**
     * Indicates how many people the infrastructure of this resource depot can support indefinitely.
     * Life Support systems provide -among other things- breathable air, drinkable water and acceptable temperatures for human beings.
     * @return
     */
    public LifeSupport getLifeSupport();

    /**
     * Notifies the game that demand at this resource depot exceeds supply for one of the capacitive resources.
     */
    public void alertCapacityExceeded(CapacitiveResourceInterface resource);

    /**
     * Notifies the game that demand at this resource depot is met by the supply of the capacitive resource.
     */
    public void capacitySufficient(CapacitiveResourceInterface capacitiveResource);

    /**
     * Calculates the total electric power consumptions of all buildings/machinery of this resource depot.
     * @return
     */
    public Power getElectricPowerConsumption();
}
