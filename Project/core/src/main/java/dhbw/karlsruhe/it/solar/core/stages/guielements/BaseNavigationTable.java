package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public abstract class BaseNavigationTable extends Table {
    List<BaseNavigationLabel> allLabels = new ArrayList<BaseNavigationLabel>();

    public BaseNavigationTable() {
        super();
        top();
        left();
        pad(5);
        defaults().expandX().fillX();
    }

    public void buildTable() {
        this.clearChildren();
        for (BaseNavigationLabel label : allLabels) {
            if (label.isVisible()) {
                this.add(label).row();
            }
        }
    }
}
