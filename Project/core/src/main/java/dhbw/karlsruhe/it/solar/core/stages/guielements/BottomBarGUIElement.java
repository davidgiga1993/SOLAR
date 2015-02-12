package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

/**
 * Created by Arga on 16.11.2014.
 */
public class BottomBarGUIElement {
    public Table root;
    public Label.LabelStyle labelStyle;
    private SolarEngine solarEngine = SolarEngine.get();

    public BottomBarGUIElement(Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;

        root = new Table();
        GameStartStage gameStartStage = (GameStartStage) solarEngine.stageManager.getStage("GameStartStage");
        Selection selection = gameStartStage.selectedActors;


        //root.add(new Label("Number of Selected Actors: " + selection.getNumberOfSelectedUnits(), labelStyle));
        //root.row();
    }
}
