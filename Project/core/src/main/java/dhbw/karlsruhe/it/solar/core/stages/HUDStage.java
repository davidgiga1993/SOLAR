package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import dhbw.karlsruhe.it.solar.core.solar.FontCacher;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public class HUDStage extends BaseGUIStage
{
    public HUDStage(SolarEngine SE, String TAG)
    {
        super(SE, TAG);
        // setCamera(SE.HUDcamera);

        
        timer = new Timer();
        BitmapFont font = FontCacher.getFont("default");
        FPSLabel = new Label("DEBUG MODE", new LabelStyle(font, new Color(125, 125, 125, 255)));
        FPSLabel.setPosition(SolarEngine.WidthHalf-100, SolarEngine.HeightHalf - 18);
        zoomLabel = new Label("Zoom: ", SE.styles.defaultLabelStyle);
        zoomLabel.setPosition(SolarEngine.WidthHalf-100, SolarEngine.HeightHalf - 45);
        addActor(FPSLabel);
        addActor(zoomLabel);
        BuildTimer();
    }

    private Label FPSLabel;
    private Label zoomLabel;
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
                zoomLabel.setText("Zoom: " + SE.camera.zoom);

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
}
