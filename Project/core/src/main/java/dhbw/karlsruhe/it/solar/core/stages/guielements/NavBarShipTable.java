package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavBarShipTable extends NavBarBaseTable {

    public NavBarShipTable() {
        super();
    }

    /**
     * Constructs a hierarchy of players and the space units they own.
     */
    public void buildShipList() {
        allLabels.clear();
        for (Player player : ((GameStartStage) SolarEngine.get().getStage("GameStartStage")).getPlayers()) {
            NavBarBaseLabel playerLabel = createPlayerLabel(player, this);
            allLabels.add(playerLabel);
            playerLabel.setChildren(createPlayerUnitLabels(player));
        }
        buildTable();
    }

    private List<NavBarBaseLabel> createPlayerUnitLabels(Player player) {
        List<NavBarBaseLabel> playerUnits = new ArrayList<>();
        for (SpaceUnit unit : player.getUnits()) {
            playerUnits.add(produceUnitLabel(unit));
        }
        return playerUnits;
    }

    private NavBarBaseLabel produceUnitLabel(SpaceUnit unit) {
        NavBarBaseLabel label = new NavBarBaseLabel(unit.getName(), TAB, unit, this);
        allLabels.add(label);
        return label;
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if (telegram.extraInfo instanceof SpaceUnit && SolarMessageType.ACTOR_REMOVED == telegram.message) {
            removeSingleShip((SpaceUnit) telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }

    private void removeSingleShip(SpaceUnit unit) {
        allLabels.remove(getLabelOfActor(unit));
    }
}
