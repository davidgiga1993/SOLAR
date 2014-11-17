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

        navigationBar.add(new GUILabel("NavBar", labelStyle, stage)).expand().top().left();
        navigationBar.row();
    }

}
