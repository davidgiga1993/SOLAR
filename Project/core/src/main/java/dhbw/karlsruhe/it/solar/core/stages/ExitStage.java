package dhbw.karlsruhe.it.solar.core.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.actions.LabelFontScalerAction;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

public class ExitStage extends HUDStage
{

    private Label labelAreYouSure;
    private Label labelYes;
    private Label labelNo;

    public ExitStage(final SolarEngine SE)
    {
        super(SE, "Exit");

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setPosition(0, 0);
        menuTable.setWidth(Gdx.graphics.getWidth());
        menuTable.setHeight(Gdx.graphics.getHeight());
        menuTable.center().left();

        if (SolarEngine.DEBUG) {
            menuTable.debug();
        }

        labelAreYouSure = new Label("Are you sure?", SE.styles.defaultLabelStyle);

        labelYes = new MenuButton("Yes", SE) {
            @Override
            protected void onClick() {
                Gdx.app.exit();
            }
        };

        labelNo = new MenuButton("No", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.swapCurrentStage(new StartStage(SE));
            }
        };

        menuTable.add(labelAreYouSure).expandX().pad(10f).height(25).colspan(2);
        menuTable.row();
        menuTable.add(labelYes).pad(10f).height(25);
        menuTable.add(labelNo).pad(10f).height(25);

        addActor(SE.Service.AddBackgroundImage());
        addActor(menuTable);
    }


}
