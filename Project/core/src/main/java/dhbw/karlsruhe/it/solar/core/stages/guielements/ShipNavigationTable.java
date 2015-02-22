package dhbw.karlsruhe.it.solar.core.stages.guielements;

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
        init();
    }

    public void init() {
        buildShipList();
        buildHierarchy();
        buildTable();
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
        for (Spaceship ship : allShips) {
            allLabels.add(new BaseNavigationLabel(ship.getName(), "", ship));
        }
    }

}
