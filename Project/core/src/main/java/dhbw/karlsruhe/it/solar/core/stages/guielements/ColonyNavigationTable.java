package dhbw.karlsruhe.it.solar.core.stages.guielements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

public class ColonyNavigationTable extends BaseNavigationTable {

    private List<AstronomicalBody> allColonies = new ArrayList<AstronomicalBody>();

    public ColonyNavigationTable() {
        super();
    }

    public void buildColonyList() {
        GameStartStage gameStartStage = (GameStartStage) SolarEngine.get().stageManager.getStage("GameStartStage");
        for (Actor actor : gameStartStage.getActors()) {
            if (actor instanceof AstronomicalBody && ((AstronomicalBody) actor).isColonized()) {
            	addColonyToTable(actor);
            }
        }
        buildTable();
    }

	private void addColonyToTable(Actor actor) {
		if(!allColonies.contains(actor))	{
			allColonies.add((AstronomicalBody) actor);
			allLabels.add(new BaseNavigationLabel(((AstronomicalBody)actor).getName(), "", (SolarActor) actor));	
		}
	}

	@Override
	public boolean handleMessage(Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
}
