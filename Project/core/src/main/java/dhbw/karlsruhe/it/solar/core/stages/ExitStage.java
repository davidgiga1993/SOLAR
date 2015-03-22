package dhbw.karlsruhe.it.solar.core.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

public class ExitStage extends HUDStage  {

    private Label labelAreYouSure;
    private Label labelYes;
    private Label labelNo;

    public ExitStage(final SolarEngine se)    {
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

        labelAreYouSure = new Label("Are you sure?", se.getDefaultLabelStyle());

        labelYes = new MenuButton("Yes", se) {
            @Override
            protected void onClick() {
                Gdx.app.exit();
            }
        };

        labelNo = new MenuButton("No", se) {
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
