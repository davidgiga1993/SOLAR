package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import dhbw.karlsruhe.it.solar.core.solar.FontCacher;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

class HUDStage extends BaseGUIStage {
    
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
            fpsLabel.setPosition(SolarEngine.HALF_WIDTH-100, SolarEngine.HALF_HEIGHT - 18);
            zoomLabel = new Label("Zoom: ", Styles.DEFAULTLABEL_STYLE);
            zoomLabel.setPosition(SolarEngine.HALF_WIDTH-100, SolarEngine.HALF_HEIGHT - 45);
            addActor(fpsLabel);
            addActor(zoomLabel);
            buildTimer();
        }
    }

    private void stop() {
        logData = false;
    }

    private void start() {
        logData = true;
        buildTimer();
    }

    private void buildTimer() {
        timer.scheduleTask(new Timer.Task()       {
            public void run()            {
                fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
                zoomLabel.setText("Zoom: " + se.getSolarCameraZoom());

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
