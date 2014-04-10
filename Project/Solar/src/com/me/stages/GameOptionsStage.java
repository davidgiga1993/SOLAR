package com.me.stages;

import Actions.LabelFontScalerAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.solar.SolarEngine;

public class GameOptionsStage extends BaseStage {

	private Label labelOption1;
	private Label labelOption2;
	private Label labelExit;
	
	public GameOptionsStage(final SolarEngine SE) {
		super(SE);
		labelOption1 = new Label("Option1", SE.styles.defaultLabelStyle);
		labelOption1.setPosition(-labelOption1.getWidth() / 2, 80);
		labelOption1.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelOption1);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelOption1);
            }
        });
        
		labelOption2 = new Label("Option2", SE.styles.defaultLabelStyle);
		labelOption2.setPosition(-labelOption1.getWidth() / 2, 50);
		labelOption2.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelOption2);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelOption2);
            }
        });
		
		labelExit = new Label("Exit", SE.styles.defaultLabelStyle);
		labelExit.setPosition(-labelOption1.getWidth() / 2, 20);
		labelExit.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	SE.stageManager.swapCurrentStage(new StartStage(SE));
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
        addActor(labelOption1);
        addActor(labelOption2);
        addActor(labelExit);
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
