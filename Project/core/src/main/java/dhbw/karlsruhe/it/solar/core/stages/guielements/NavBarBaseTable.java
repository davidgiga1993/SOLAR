package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
abstract class NavBarBaseTable extends Table implements Telegraph {

    static final String TAB = "    ";

    List<NavBarBaseLabel> allLabels = new ArrayList<>();

    NavBarBaseTable() {
        super();
        top();
        left();
        pad(5);
        defaults().expandX().fillX();
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.NEW_ACTOR_ADDED);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.ACTOR_REMOVED);
    }

    public void buildTable() {
        this.clearChildren();
        for (NavBarBaseLabel label : allLabels) {
            if (label.isVisible()) {
                this.add(label).row();
            }
        }
    }


    public abstract boolean handleMessage(Telegram telegram);

    NavBarBaseLabel getLabelOfActor(Actor actor) {
        for (NavBarBaseLabel unitLabel : allLabels) {
            if(unitLabel.isOfActor(actor)) {
                return unitLabel;
            }
        }
        return null;
    }


    NavBarBaseLabel createPlayerLabel(Player player, NavBarBaseTable container) {
        return new NavBarBaseLabel(player.getName(), "", player.getCapitalWorld(), container);
    }
}
