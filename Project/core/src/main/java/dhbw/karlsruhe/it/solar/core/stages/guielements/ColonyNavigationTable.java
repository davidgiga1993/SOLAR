package dhbw.karlsruhe.it.solar.core.stages.guielements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

public class ColonyNavigationTable extends BaseNavigationTable {

    private List<AstronomicalBody> allColonies = new ArrayList<AstronomicalBody>();

    public ColonyNavigationTable() {
        super();
    }

    public void buildColonyList() {
        GameStartStage gameStartStage = (GameStartStage) SolarEngine.get().getStage("GameStartStage");
        for (Actor actor : gameStartStage.getActors()) {
            if (actor instanceof AstronomicalBody && ((AstronomicalBody) actor).isColonized()) {
                addColonyToTable(actor);
            }
        }
        buildTable();
    }

    private void addColonyToTable(Actor actor) {
        if(!allColonies.contains(actor))    {
            allColonies.add((AstronomicalBody) actor);
            allLabels.add(new BaseNavigationLabel(((AstronomicalBody)actor).getColonyName(), "", (SolarActor) actor));    
        }
    }
    
    private void removeSingleColony(Actor actor) {
        allColonies.remove(actor);
        allLabels.remove(getLabelOfActor(actor));
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.extraInfo instanceof AstronomicalBody && SolarMessageType.ACTOR_REMOVED == telegram.message) {
            removeSingleColony((AstronomicalBody)telegram.extraInfo);
            buildTable();
            return true;
        }
        return false;
    }
}
