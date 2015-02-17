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

    public StartStage(final SolarEngine SE) {
        super(SE, "StartStage");

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }


        labelStart = new MenuButton("Start game", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.removeStage("StartStage");
                GameStartStage.startGame();
            }
        };

        labelSettings = new MenuButton("Settings", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.removeStage("StartStage");
                SE.stageManager.addStage(new GameOptionsStage(SE));
            }
        };

        labelExit = new MenuButton("Exit", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.removeStage("StartStage");
                SE.stageManager.addStage(new ExitStage(SE));
            }
        };

        menuTable.add(labelStart).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelSettings).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelExit).expandX().pad(10f).height(25);

        addActor(SE.Service.AddBackgroundImage());
        addActor(menuTable);
    }


}
