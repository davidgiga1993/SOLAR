package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class BodyNavigationTable extends BaseNavigationTable {

    public BodyNavigationTable() {
        super();
    }

    protected void init() {
        // Find all stars
        List<AstronomicalBody> stars = new ArrayList<AstronomicalBody>();
        GameStartStage gameStage = (GameStartStage) SolarEngine.get().stageManager.getStage("GameStartStage");
        for (Actor actor : gameStage.getActors()) {
            if (actor instanceof Star) {
                stars.add((AstronomicalBody) actor);
            }
        }

        buildHierarchy(stars, 0);
        buildTable();
        invalidate();
    }

    protected List<BaseNavigationLabel> buildHierarchy(List<AstronomicalBody> group, int depth) {
        List<BaseNavigationLabel> result = new ArrayList<BaseNavigationLabel>();
        String tab = "";
        for (int i = 0; i < depth; i++) {
            tab += "    ";
        }

        for (AstronomicalBody child : group) {
            if(child instanceof PlanetaryRing) {
                continue;
            }
            // Process current level
            BodyNavigationLabel label = new BodyNavigationLabel(child.getName(), tab, child, this);
            allLabels.add(label);

            // Proceed with next level
            List<BaseNavigationLabel> children = buildHierarchy(child.getSatellites(), depth+1);
            label.setChildren(children);

            if(depth > 0) {
                label.toggleChildren();
            }

            // add processed child to the result
            result.add(label);
        }

        return result;
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.extraInfo instanceof AstronomicalBody) {
            allLabels.clear();
            clear();
            init();
            return true;
        }
        return false;
    }
}
