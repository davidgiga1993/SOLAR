package dhbw.karlsruhe.it.solar.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.colony.ColonyAlerts;
import dhbw.karlsruhe.it.solar.core.resources.LifeSupport;

/**
 * Contains information for the player about problems in his colonies which will also be displayed in the top resource bar.
 * @author Andi
 * created 2015-05-18
 */
public class PlayerAlerts {
    
    private final List<Colony> coloniesWithLackOfLifeSupport = new ArrayList<Colony>();

    public Table getPopulationAlertTable() {
        Table alerts = new Table();
        if(coloniesWithLackOfLifeSupport.size() > 0) {
            alerts.add(new LifeSupport(null).loadAlertIcon());            
        }
        return alerts;
    }

    public void checkForColonyAlerts(Colony colony) {
        checkForLackOfLifeSupportAlert(colony);
    }

    private void checkForLackOfLifeSupportAlert(Colony colony) {
        ColonyAlerts alerts = colony.getAlerts();
        if(coloniesWithLackOfLifeSupport.contains(colony)) {
            if(!alerts.lackOfLifeSupport()) {
                removeFromLifeSupportList(colony);                
            }
            return;
        }
        if(alerts.lackOfLifeSupport()) {
            addToLifeSupportList(colony);
        }
    }
    
    private void removeFromLifeSupportList(Colony colony) {
        coloniesWithLackOfLifeSupport.remove(colony);
    }

    private void addToLifeSupportList(Colony colonyWithLackOfLifeSupport) {
        coloniesWithLackOfLifeSupport.add(colonyWithLackOfLifeSupport);
    }
}
