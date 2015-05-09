package dhbw.karlsruhe.it.solar.player;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.resources.ResourceInterface;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Created by Arga on 29.11.2014.
 */
public class Player implements ResourceDepot {

    private final int id;
    private final String name;
    private final Color playerColor;
    private final List<ResourceInterface> resources = new ArrayList<ResourceInterface>();
    private final List<Colony> colonies = new ArrayList<Colony>();
    private final List<SpaceUnit> units = new ArrayList<SpaceUnit>();

    Player(int id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.playerColor = color;
        this.resources.add(POPULATION_RESOURCE_ID, new Population(0));
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

    public void updateProduction() {
        for(Colony colony : colonies) {
            colony.updateProduction();
        }
    }

    @Override
    public Population getPopulation() {
        updateTotalPopulation();
        return (Population)resources.get(POPULATION_RESOURCE_ID);
    }

    private void updateTotalPopulation() {
        ((Population)resources.get(POPULATION_RESOURCE_ID)).empty();
        for(Colony colony : colonies) {
            ((Population)resources.get(POPULATION_RESOURCE_ID)).addColonists(colony.getPopulation());
        }
        resources.get(POPULATION_RESOURCE_ID).updateResource();
    }
}
