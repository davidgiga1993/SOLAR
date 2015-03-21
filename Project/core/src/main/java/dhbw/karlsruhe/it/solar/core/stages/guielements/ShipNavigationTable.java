package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class ShipNavigationTable extends BaseNavigationTable {

    protected List<SpaceUnit> allShips = new ArrayList<SpaceUnit>();

    public ShipNavigationTable() {
        super();
    }


    public void buildShipList() {
        GameStartStage gameStartStage = (GameStartStage) SolarEngine.get().stageManager.getStage("GameStartStage");
        for (Actor actor : gameStartStage.getActors()) {
            if (actor instanceof SpaceUnit && ((SpaceUnit) actor).isOwnedBy(gameStartStage.getHumanPlayer())) {
                allShips.add((SpaceUnit) actor);
            }
        }
    }

    private void addSingleShip(SpaceUnit ship) {
        allShips.add(ship);
        allLabels.add(new BaseNavigationLabel(ship.getName(), "", ship));
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.extraInfo instanceof SpaceUnit) {
            addSingleShip((SpaceUnit) telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }

}
