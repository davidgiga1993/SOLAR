package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

public class StartStage extends HUDStage {
    private Label labelStart;
    private Label labelSettings;
    private Label labelExit;
    private static final String STAGE = "StartStage";

    public StartStage(final SolarEngine se) {
        super(se, STAGE);

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }


        labelStart = new MenuButton("Start game", se) {
            @Override
            protected void onClick() {
                se.removeStage(STAGE);
                GameStartStage.startGame();
            }
        };

        labelSettings = new MenuButton("Settings", se) {
            @Override
            protected void onClick() {
                se.removeStage(STAGE);
                se.addStage(new GameOptionsStage(se));
            }
        };

        labelExit = new MenuButton("Exit", se) {
            @Override
            protected void onClick() {
                se.removeStage(STAGE);
                se.addStage(new ExitStage(se));
            }
        };

        menuTable.add(labelStart).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelSettings).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelExit).expandX().pad(10f).height(25);

        addActor(menuTable);
    }


}
