package dhbw.karlsruhe.it.solar.core.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

class GameOptionsStage extends HUDStage {

    public GameOptionsStage(final SolarEngine se) {
        super(se, "GameOptions");

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }

        Label labelOption1 = new MenuButton("Option 1", se) {
            @Override
            protected void onClick() {
            }
        };

        Label labelBackground = new MenuButton("Choose Background", se) {
            @Override
            protected void onClick() {
                se.swapCurrentStage(new GameOptionsBackgroundStage(se));
            }
        };

        Label labelExit = new MenuButton("Return", se) {
            @Override
            protected void onClick() {
                se.swapCurrentStage(new StartStage(se));
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
