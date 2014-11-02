package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import dhbw.karlsruhe.it.solar.core.actions.LabelFontScalerAction;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public class StartStage extends HUDStage
{
    private Label labelStart;
    private Label labelSettings;
    private Label labelExit;

    public StartStage(final SolarEngine SE)
    {
        super(SE, "StartStage");
               
        labelStart = new Label("Start game", SE.styles.defaultLabelStyle);
        labelStart.setPosition(SE.WidthHalf - labelStart.getWidth() / 2, SE.HeightHalf + 80);

        labelStart.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.removeStage("StartStage");
                SE.stageManager.addStage(new GameStartStage(SE));                
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
        labelSettings.setPosition(SE.WidthHalf - labelStart.getWidth() / 2, SE.HeightHalf + 50);
        labelSettings.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.removeStage("StartStage");
                SE.stageManager.addStage(new GameOptionsStage(SE));
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
        labelExit.setPosition(SE.WidthHalf - labelStart.getWidth() / 2,SE.HeightHalf +  20);
        labelExit.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                SE.stageManager.removeStage("StartStage");
                SE.stageManager.addStage(new ExitStage(SE));
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
        addActor(SE.Service.AddBackgroundImage());
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
        ac.setInterpolation(Interpolation.exp10);
        label.addAction(ac);
    }

}
