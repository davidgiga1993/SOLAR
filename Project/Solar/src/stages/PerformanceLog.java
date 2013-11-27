package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.me.solar.SolarEngine;

public class PerformanceLog extends BaseStage
{
    public PerformanceLog(SolarEngine SE)
    {
        super(SE);
        timer = new Timer();
        BitmapFont font = new BitmapFont();
        FPSLabel = new Label("DEBUG MODE", new LabelStyle(font, new Color(125, 125, 125, 255)));
        FPSLabel.setPosition(-SolarEngine.WidthHalf, SolarEngine.HeightHalf - 18);
        
        addActor(FPSLabel);
        BuildTimer();
    }

    private Label FPSLabel;
    private Timer timer;

    private boolean LogData = true;

    public void Stop()
    {
        LogData = false;
    }

    public void Start()
    {
        LogData = true;
        BuildTimer();
    }

    public void BuildTimer()
    {
        timer.scheduleTask(new Timer.Task()
        {
            public void run()
            {
                FPSLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
                
                if (LogData)
                    BuildTimer();
            }
        }, 1);
    }

    @Override
    public void draw()
    {
        if (LogData)
        {
            super.draw();
        }
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        switch (character)
        {
        case '?':
            if (LogData)
                Stop();
            else
                Start();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        // TODO Auto-generated method stub
        return false;
    }
}
