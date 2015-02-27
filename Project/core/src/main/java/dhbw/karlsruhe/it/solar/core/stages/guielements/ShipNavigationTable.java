package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class ShipNavigationTable extends BaseNavigationTable {

    protected List<Spaceship> allShips = new ArrayList<Spaceship>();

    public ShipNavigationTable() {
        super();
    }


    public void buildShipList() {
        GameStartStage gameStartStage = (GameStartStage) SolarEngine.get().stageManager.getStage("GameStartStage");
        for (Actor actor : gameStartStage.getActors()) {
            if (actor instanceof Spaceship && ((Spaceship) actor).isOwnedBy(gameStartStage.getHumanPlayer())) {
                allShips.add((Spaceship) actor);
            }
        }
    }

    private void buildHierarchy() {
        allLabels.clear();
        for (Spaceship ship : allShips) {
            allLabels.add(new BaseNavigationLabel(ship.getName(), "", ship));
        }
    }

    private void addSingleShip(Spaceship ship) {
        allShips.add(ship);
        allLabels.add(new BaseNavigationLabel(ship.getName(), "", ship));
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.extraInfo instanceof Spaceship) {
            addSingleShip((Spaceship) telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }

}
