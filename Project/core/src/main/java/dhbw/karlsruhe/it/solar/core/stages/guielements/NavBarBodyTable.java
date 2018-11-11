package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Star;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavBarBodyTable extends NavBarBaseTable {

    public NavBarBodyTable() {
        super();
    }

    private void init() {
        // Find all stars
        List<AstronomicalBody> stars = new ArrayList<>();
        GameStartStage gameStage = (GameStartStage) SolarEngine.get().getStage("GameStartStage");
        for (Actor actor : gameStage.getActors()) {
            if (actor instanceof Star) {
                stars.add((AstronomicalBody) actor);
            }
        }

        buildHierarchy(stars, 0);
        buildTable();
        invalidate();
    }

    private List<NavBarBaseLabel> buildHierarchy(List<AstronomicalBody> group, int depth) {
        List<NavBarBaseLabel> result = new ArrayList<>();
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            tab.append(TAB);
        }

        for (AstronomicalBody child : group) {
            if (child instanceof PlanetaryRing) {
                continue;
            }
            // Process current level
            NavBarBaseLabel label = new NavBarBaseLabel(child.getName(), tab.toString(), child, this);
            allLabels.add(label);

            // Proceed with next level
            List<NavBarBaseLabel> children = buildHierarchy(child.getSatellites(), depth + 1);
            label.setChildren(children);

            if (depth > 0) {
                label.toggleChildren();
            }

            // add processed child to the result
            result.add(label);
        }

        return result;
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if (telegram.extraInfo instanceof AstronomicalBody) {
            allLabels.clear();
            clear();
            init();
            return true;
        }
        return false;
    }
}
