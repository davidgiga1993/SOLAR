package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import dhbw.karlsruhe.it.solar.core.actions.LabelFontScalerAction;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.menuelements.MenuButton;

import java.util.HashMap;
import java.util.Map;

public class GameOptionsBackgroundStage extends HUDStage
{

    private Label background1;
    private Label background2;
    private Label labelExit;

    public GameOptionsBackgroundStage(final SolarEngine SE)
    {
        super(SE, "GameOptionsBackgroundStage");

        final Table menuTable = new Table();
        menuTable.setFillParent(true);

        background1 = new MenuButton("Background 1", SE) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund01.png");
                menuTable.toFront();
            }
        };

        background2 = new MenuButton("Background 2", SE) {
            @Override
            protected void onClick() {
                changeBackground("Hintergrund02.png");
                menuTable.toFront();
            }
        };

        labelExit = new MenuButton("Return", SE) {
            @Override
            protected void onClick() {
                SE.stageManager.removeStage("GameOptionsBackgroundStage");
                SE.stageManager.addStage(new GameOptionsStage(SE));
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

