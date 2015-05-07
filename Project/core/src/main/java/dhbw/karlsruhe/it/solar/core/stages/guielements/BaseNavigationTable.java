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
public abstract class BaseNavigationTable extends Table implements Telegraph {
    
    protected static final String TAB = "    ";
    
    List<BaseNavigationLabel> allLabels = new ArrayList<BaseNavigationLabel>();

    public BaseNavigationTable() {
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
        for (BaseNavigationLabel label : allLabels) {
            if (label.isVisible()) {
                this.add(label).row();
            }
        }
    }


    public abstract boolean handleMessage(Telegram telegram);
    
    protected BaseNavigationLabel getLabelOfActor(Actor actor) {
        for (BaseNavigationLabel unitLabel : allLabels) {
            if(unitLabel.isOfActor(actor)) {
                return unitLabel;
            }
        }
        return null;
    }
    

    protected BaseNavigationLabel createPlayerLabel(Player player, BaseNavigationTable container) {
        return new BaseNavigationLabel(player.getName(), "", player.getCapitalWorld(), container);
    }
}
