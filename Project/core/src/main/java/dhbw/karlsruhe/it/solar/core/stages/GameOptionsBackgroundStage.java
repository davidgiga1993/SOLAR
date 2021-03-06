package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

class GameOptionsBackgroundStage extends HUDStage {

    public GameOptionsBackgroundStage(final SolarEngine se) {
        super(se, "GameOptionsBackgroundStage");

        final Table menuTable = new Table();
        menuTable.setFillParent(true);

        Label background1 = new MenuButton("Milky Way", se) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund01.png");
                menuTable.toFront();
            }
        };

        Label background2 = new MenuButton("Black", se) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund02.png");
                menuTable.toFront();
            }
        };

        Label labelExit = new MenuButton("Return", se) {
            @Override
            protected void onClick() {
                se.removeStage("GameOptionsBackgroundStage");
                se.addStage(new GameOptionsStage(se));
            }
        };


        menuTable.add(background1).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(background2).expandX().pad(10f).height(25);
        menuTable.row();
        menuTable.add(labelExit).expandX().pad(10f).height(25);

        addActor(menuTable);
    }

    private void changeBackground(String name) {
        BackgroundStage backgroundStage = (BackgroundStage) SolarEngine.get().getStage("Background");
        if (backgroundStage == null) {
            System.out.println(this.getClass().getCanonicalName() + "change Background failed; backgroundStage not found");
        } else {
            backgroundStage.changeBackground(name);
        }
    }
}

