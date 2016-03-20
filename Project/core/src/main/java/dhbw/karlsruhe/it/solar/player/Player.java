package dhbw.karlsruhe.it.solar.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.*;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 29.11.2014.
 */
public class Player {

    private final static int POPULATION_RESOURCE_ID = 0;
    private final static int TREASURY_RESOURCE_ID = 1;
    
    private final int id;
    private final String name;
    private final Color playerColor;
    private final List<GlobalResourceInterface> resources = new ArrayList<>();
    private final List<Colony> colonies = new ArrayList<>();
    private final List<SpaceUnit> units = new ArrayList<>();
    private final PlayerAlerts alerts = new PlayerAlerts();

    Player(int id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.playerColor = color;
        this.resources.add(POPULATION_RESOURCE_ID, new TotalPopulation());
        this.resources.add(TREASURY_RESOURCE_ID, new Credits());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return ((Player) obj).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
    
    public String getName() {
        return name;
    }

    public LabelStyle getColorStyle() {
        if(playerColor == Color.RED) {
            return Styles.MENUELABEL_RED;
        }
        if(playerColor == Color.YELLOW) {
            return Styles.MENUELABEL_YELLOW;
        }
        if(playerColor == Color.ORANGE) {
            return Styles.MENUELABEL_ORANGE;
        }
        if(playerColor == Color.GREEN) {
            return Styles.MENUELABEL_GREEN;
        }
        return Styles.MENUELABEL_STYLE;
    }
    
    /**
     * Ownership of a colony is assigned to the player.
     * @return 
     */
    public Colony establishColony(String colonyName, AstronomicalBody colonySite, Player player, Population colonists) {
        Colony newColony = new Colony(colonyName, colonySite, player, colonists);
        colonies.add(newColony);
        return newColony;
    }

    public List<Colony> getColonies() {
        return colonies;
    }

    public AstronomicalBody getCapitalWorld() {
        return largestColony();
    }

    private AstronomicalBody largestColony() {
        if( hasNoColonies()) {
            return null;
        }
        Colony largestColony = colonies.get(0);
        for(Colony colony : colonies) {
            if(colony.getPopulation().getNumber() > largestColony.getPopulation().getNumber()) {
                largestColony = colony;
            }
        }
        return largestColony.getColonySite();
    }

    private boolean hasNoColonies() {
        return 0 == colonies.size();
    }

    public List<SpaceUnit> getUnits() {
        return units;
    }

    public void assignNewUnit(SpaceUnit newUnit) {
        units.add(newUnit);
    }

    public void abandonColony(Colony colony) {
        colonies.remove(colony);
    }

    public void removeShip(SpaceUnit spaceUnit) {
        units.remove(spaceUnit);
    }

    public void updateProduction(Time deltaT) {
        for(Colony colony : colonies) {
            colony.updateProduction(deltaT);
            alerts.checkForColonyAlerts(colony);
        }
    }

    public TotalPopulation getTotalPopulation() {
        return (TotalPopulation)resources.get(POPULATION_RESOURCE_ID);
    }

    public void updateTotalPopulation() {
        resources.get(POPULATION_RESOURCE_ID).empty();
        for(Colony colony : colonies) {
            ((TotalPopulation)resources.get(POPULATION_RESOURCE_ID)).addToTotalPopulation(colony.getPopulation());
        }
        getTotalPopulation().updateStatistic();
    }

    public Credits getTreasury() {
        return (Credits)resources.get(TREASURY_RESOURCE_ID);
    }

    public void updateTreasury(Time deltaT) {
        for(Colony colony : colonies) {
            getTreasury().addRevenueToTreasury(colony.raiseTaxes(deltaT));
            getTreasury().subtractExpensesFromTreasury(colony.payUpKeep(deltaT));
        }
        for(SpaceUnit unit : units) {
            getTreasury().subtractExpensesFromTreasury(unit.payUpKeep(deltaT));
        }
        getTreasury().updateStatistic();
    }

    public List<GlobalResource> getResources() {
        List<GlobalResource> newList = new ArrayList<>();
        for(GlobalResourceInterface resource : resources) {
            newList.add((GlobalResource)resource);
        }
        return newList;
    }

    public void setResources(List<GlobalResourceInterface> resources) {
        this.resources.clear();
        for (GlobalResourceInterface resource : resources) {
            this.resources.add(resource);
        }
    }

    public Table getPopulationAlerts() {
        return alerts.getPopulationAlertTable();
    }
}
