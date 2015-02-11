package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Arga on 16.11.2014.
 */
public class NavigationBarGUIElement {

    public Table navigationBar;

    public NavigationBarGUIElement(Label.LabelStyle labelStyle, Stage stage) {
        super();
        navigationBar = new Table();

        GUILabel label = new GUILabel("Navigation", labelStyle, stage);
        label.tooltip.setTitle("Navi Tooltip...");
        navigationBar.add(label).expand().top().left();
        navigationBar.row();
    }

}
