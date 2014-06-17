package com.me.stages;

import Actions.LabelFontScalerAction;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.me.solar.SolarEngine;

public class GameOptionsBackgroundStage extends HUDStage
{

    private Label Background1;
    private Label Background2;
    private Group group;
    private Image actor;
    private Label labelExit;

    public GameOptionsBackgroundStage(final SolarEngine SE)
    {
        super(SE, "GameOptionsBackgroundStage");

        Background1 = new Label("Background1", SE.styles.defaultLabelStyle);
        Background1.setPosition(-Background1.getWidth() / 2, 80);
        Background1.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.Service.setBackgroundImage("Hintergrund01.png");
                addActor(SE.Service.AddBackgroundImage());
                group.toFront();
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(Background1);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(Background1);
            }
        });

        Background2 = new Label("Background2", SE.styles.defaultLabelStyle);
        Background2.setPosition(-Background2.getWidth() / 2, 50);
        Background2.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.Service.setBackgroundImage("Hintergrund02.png");
                addActor(SE.Service.AddBackgroundImage());
                group.toFront();
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(Background2);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(Background2);
            }
        });

        labelExit = new Label("Exit", SE.styles.defaultLabelStyle);
        labelExit.setPosition(-labelExit.getWidth() / 2, 20);

        labelExit.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.removeStage("GameOptionsBackgroundStage");
                SE.stageManager.addStage(new GameOptionsStage(SE));
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelExit);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelExit);
            }
        });

        group = new Group();
        group.addActor(Background1);
        group.addActor(Background2);
        group.addActor(labelExit);

        addActor(SE.Service.AddBackgroundImage());
        addActor(group);
    }

    private void AnimateLabelIn(Label label)
    {
        AnimateLabel(label, 1.2f);
    }

    private void AnimateLabelOut(Label label)
    {
        AnimateLabel(label, 1);
    }

    private void AnimateLabel(Label label, float Scale)
    {
        LabelFontScalerAction ac = new LabelFontScalerAction(Scale, label.getFontScaleX());
        ac.setDuration(0.7f);
        ac.setInterpolation(Interpolation.exp10);
        label.addAction(ac);
    }

    /*
     * public void ChangeBackgroundImage(String backgroundImage)
     * {
     * Texture texture = new Texture(Gdx.files.internal("data/" + backgroundImage));
     * texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
     * int height = texture.getHeight();
     * int width = texture.getWidth();
     * TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
     * 
     * actor = new Image(region);
     * actor.setScaling(Scaling.fill);
     * int x = -Gdx.graphics.getWidth() / 2;
     * int y = -Gdx.graphics.getHeight() / 2;
     * actor.setPosition(x, y);
     * actor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
     * addActor(actor);
     * }
     */

}
