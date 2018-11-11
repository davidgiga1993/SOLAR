package dhbw.karlsruhe.it.solar.core.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

class ExitStage extends HUDStage {

    public ExitStage(final SolarEngine se) {
        super(se, "Exit");

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }

        Label labelAreYouSure = new Label("Are you sure?", Styles.DEFAULT_LABEL_STYLE);

        Label labelYes = new MenuButton("Yes", se) {
            @Override
            protected void onClick() {
                Gdx.app.exit();
            }
        };

        Label labelNo = new MenuButton("No", se) {
            @Override
            protected void onClick() {
                se.swapCurrentStage(new StartStage(se));
            }
        };

        menuTable.add(labelAreYouSure).expandX().pad(10f).height(25).colspan(2);
        menuTable.row();
        menuTable.add(labelYes).pad(10f).height(25);
        menuTable.add(labelNo).pad(10f).height(25);

        addActor(menuTable);
    }


}
