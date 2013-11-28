package stages;

import Actions.LabelFontScalerAction;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.solar.SolarEngine;

public class StartStage extends BaseStage
{
    private Label labelStart;
    private Label labelSettings;
    private Label labelExit;

    public StartStage(final SolarEngine SE)
    {
        super(SE);

        labelStart = new Label("Start game", SE.styles.defaultLabelStyle);
        labelStart.setPosition(-labelStart.getWidth() / 2, 80);
        labelStart.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.swapCurrentStage(new GameStartStage(SE));
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelStart);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelStart);
            }
        });

        labelSettings = new Label("Settings", SE.styles.defaultLabelStyle);
        labelSettings.setPosition(-labelStart.getWidth() / 2, 50);
        labelSettings.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelIn(labelSettings);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                AnimateLabelOut(labelSettings);
            }
        });
        
        labelExit = new Label("Exit", SE.styles.defaultLabelStyle);
        labelExit.setPosition(-labelStart.getWidth() / 2, 20);
        labelExit.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
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
        addActor(labelExit);
        addActor(labelStart);
        addActor(labelSettings);
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
        ac.setInterpolation(Interpolation.exp5);
        label.addAction(ac);
    }

}
