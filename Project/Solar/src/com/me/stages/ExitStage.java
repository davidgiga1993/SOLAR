package com.me.stages;

import Actions.LabelFontScalerAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.solar.SolarEngine;

public class ExitStage extends HUDStage
{

    private Label labelAreYouSure;
    private Label labelYes;
    private Label labelNo;

    public ExitStage(final SolarEngine SE)
    {
        super(SE, "Exit");

        labelAreYouSure = new Label("Are you sure?", SE.styles.defaultLabelStyle);
        labelAreYouSure.setPosition(-labelAreYouSure.getWidth() / 2, 80);

        labelAreYouSure.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            }
        });

        labelYes = new Label("Yes", SE.styles.defaultLabelStyle);
        labelYes.setPosition(-labelAreYouSure.getWidth() / 2, 50);

        labelYes.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelYes);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelYes);
            }
        });

        labelNo = new Label("No", SE.styles.defaultLabelStyle);
        labelNo.setPosition(-labelNo.getWidth() / 2, 50);

        labelNo.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.swapCurrentStage(new StartStage(SE));
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelNo);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelNo);
            }
        });

        addActor(labelAreYouSure);
        addActor(labelYes);
        addActor(labelNo);
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

}
