package dhbw.karlsruhe.it.solar.core.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

public class GameOptionsStage extends HUDStage
{

    private Label labelOption1;
    private Label labelBackground;
    private Label labelExit;

    public GameOptionsStage(final SolarEngine SE)
    {
        super(SE, "GameOptions");

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }

        labelOption1 = new MenuButton("Option 1", SE) {
            @Override
            protected void onClick() {
            }
        };

        labelBackground = new MenuButton("Choose Background", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.swapCurrentStage(new GameOptionsBackgroundStage(SE));
            }
        };

        labelExit = new MenuButton("Return", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.swapCurrentStage(new StartStage(SE));
            }
        };

        menuTable.add(labelOption1).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelBackground).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelExit).expandX().pad(10f).height(25);

        addActor(menuTable);
    }

}
