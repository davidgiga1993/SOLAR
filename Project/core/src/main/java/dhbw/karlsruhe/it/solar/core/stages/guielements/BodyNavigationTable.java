package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class BodyNavigationTable extends Table {

    List<NavigationLabel> allLabels = new ArrayList<NavigationLabel>();

    public BodyNavigationTable() {
        super();
        top();
        left();
        pad(5);
        defaults().expandX().fillX();

        init();
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

    protected List<NavigationLabel> buildHierarchy(List<AstronomicalBody> group, int depth) {
        List<NavigationLabel> result = new ArrayList<NavigationLabel>();
        String tab = "";
        for (int i = 0; i < depth; i++) {
            tab += "    ";
        }

        for (AstronomicalBody child : group) {
            // Process current level
            NavigationLabel label = new NavigationLabel(child.getName(), tab, child, this);
            allLabels.add(label);

            // Proceed with next level
            List<NavigationLabel> children = buildHierarchy(child.getSatellites(), depth+1);
            label.setChildren(children);

            if(depth > 0) {
                label.toggleChildren();
            }

            // add processed child to the result
            result.add(label);
        }

        return result;
    }

    public void buildTable() {
        this.clearChildren();
        for (NavigationLabel label : allLabels) {
            if (label.isVisible()) {
                this.add(label).row();
            }
        }
    }
}
