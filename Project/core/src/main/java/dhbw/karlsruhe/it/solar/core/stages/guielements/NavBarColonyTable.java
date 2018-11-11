package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.ArrayList;
import java.util.List;

public class NavBarColonyTable extends NavBarBaseTable {

    public NavBarColonyTable() {
        super();
    }

    /**
     * Constructs a hierarchy of players and the colonies they own.
     */
    public void buildColonyList() {
        allLabels.clear();
        for (Player player : ((GameStartStage) SolarEngine.get().getStage("GameStartStage")).getPlayers()) {
            NavBarBaseLabel playerLabel = createPlayerLabel(player, this);
            allLabels.add(playerLabel);
            playerLabel.setChildren(createPlayerColonyLabels(player));
        }
        buildTable();
    }

    private List<NavBarBaseLabel> createPlayerColonyLabels(Player player) {
        List<NavBarBaseLabel> playerColonies = new ArrayList<>();
        for (Colony colony : player.getColonies()) {
            playerColonies.add(produceColonyLabel(colony));
        }
        return playerColonies;
    }

    private NavBarBaseLabel produceColonyLabel(Colony colony) {
        NavBarBaseLabel label = new NavBarBaseLabel(colony.getName(), TAB, colony.getColonySite(), this);
        allLabels.add(label);
        return label;
    }

    private void removeSingleColony(Actor actor) {
        allLabels.remove(getLabelOfColony(((AstronomicalBody) actor).getColonyName()));
    }

    private NavBarBaseLabel getLabelOfColony(String colonyName) {
        for (NavBarBaseLabel colonyLabel : allLabels) {
            if (colonyLabel.name.equals(colonyName)) {
                return colonyLabel;
            }
        }
        return null;
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if (telegram.extraInfo instanceof AstronomicalBody && SolarMessageType.ACTOR_REMOVED == telegram.message) {
            removeSingleColony((AstronomicalBody) telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }
}
