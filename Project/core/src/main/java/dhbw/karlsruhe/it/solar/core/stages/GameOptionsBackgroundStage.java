package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

public class GameOptionsBackgroundStage extends HUDStage {

    private Label background1;
    private Label background2;
    private Label labelExit;

    public GameOptionsBackgroundStage(final SolarEngine se)    {
        super(se, "GameOptionsBackgroundStage");

        final Table menuTable = new Table();
        menuTable.setFillParent(true);

        background1 = new MenuButton("Milky Way", se) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund01.png");
                menuTable.toFront();
            }
        };

        background2 = new MenuButton("Black", se) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund02.png");
                menuTable.toFront();
            }
        };

        labelExit = new MenuButton("Return", se) {
            @Override
            protected void onClick() {
                se.stageManager.removeStage("GameOptionsBackgroundStage");
                se.stageManager.addStage(new GameOptionsStage(se));
            }
        };


        menuTable.add(background1).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(background2).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelExit).expandX().pad(10f).height(25);

        addActor(menuTable);
    }

    public void changeBackground(String name) {
        BackgroundStage backgroundStage = (BackgroundStage) SolarEngine.get().stageManager.getStage("Background");
        if(backgroundStage == null) {
            System.out.println(this.getClass().getCanonicalName() + "change Background failed; backgroundStage not found");
        }
        backgroundStage.changeBackground(name);
    }
}

