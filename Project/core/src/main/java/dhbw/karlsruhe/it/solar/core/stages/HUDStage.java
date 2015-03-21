package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import dhbw.karlsruhe.it.solar.core.solar.FontCacher;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

public class HUDStage extends BaseGUIStage {
	
    private Label fpsLabel;
    private Label zoomLabel;
    private Timer timer;
    private boolean logData = true;
    
    public HUDStage(SolarEngine se, String tag)    {
        super(se, tag);

        if (SolarEngine.DEBUG) {
            timer = new Timer();
            BitmapFont font = FontCacher.getFont("default");
            fpsLabel = new Label("DEBUG MODE", new LabelStyle(font, new Color(125, 125, 125, 255)));
            fpsLabel.setPosition(SolarEngine.WidthHalf-100, SolarEngine.HeightHalf - 18);
            zoomLabel = new Label("Zoom: ", se.styles.defaultLabelStyle);
            zoomLabel.setPosition(SolarEngine.WidthHalf-100, SolarEngine.HeightHalf - 45);
            addActor(fpsLabel);
            addActor(zoomLabel);
            buildTimer();
        }
    }

    public void stop()   {
        logData = false;
    }

    public void start()    {
        logData = true;
        buildTimer();
    }

    public void buildTimer()    {
        timer.scheduleTask(new Timer.Task()       {
            public void run()            {
                fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
                zoomLabel.setText("Zoom: " + SE.camera.zoom);

                if (logData) {
                	buildTimer();                	
                }
            }
        }, 1);
    }

    @Override
    public boolean keyTyped(char character)    {
    	if ('?' == character) {
    		if (logData) {
    			stop();
    			return true;
    		}
    		start();
    		return true;
    	}
    	return false;
    }
}
