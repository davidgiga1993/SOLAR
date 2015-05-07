package dhbw.karlsruhe.it.solar.core.stages.guielements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ai.msg.Telegram;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Created by Arga on 22.02.2015.
 */
public class ShipNavigationTable extends BaseNavigationTable {

    public ShipNavigationTable() {
        super();
    }

    /**
     * Constructs a hierarchy of players and the space units they own.
     */
    public void buildShipList() {
        allLabels.clear();
        for (Player player : ((GameStartStage)SolarEngine.get().getStage("GameStartStage")).getPlayers()) {
            BaseNavigationLabel playerLabel = createPlayerLabel(player, this);
            allLabels.add(playerLabel);  
            playerLabel.setChildren(createPlayerUnitLabels(player));
        }
        buildTable();
    }

    private List<BaseNavigationLabel> createPlayerUnitLabels(Player player) {
        List<BaseNavigationLabel> playerUnits = new ArrayList<BaseNavigationLabel>();
        for(SpaceUnit unit : player.getUnits()) {
            playerUnits.add(produceUnitLabel(unit));
        }
        return playerUnits;
    }

    private BaseNavigationLabel produceUnitLabel(SpaceUnit unit) {
        BaseNavigationLabel label = new BaseNavigationLabel(unit.getName(), TAB, unit, this);
        allLabels.add(label);
        return label;    
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.extraInfo instanceof SpaceUnit && SolarMessageType.ACTOR_REMOVED == telegram.message) {
            removeSingleShip((SpaceUnit)telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }

    private void removeSingleShip(SpaceUnit unit) {
        allLabels.remove(getLabelOfActor(unit));
    }
}
