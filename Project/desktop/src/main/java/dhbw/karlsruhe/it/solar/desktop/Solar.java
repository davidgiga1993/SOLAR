package dhbw.karlsruhe.it.solar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.configelements.ScalePresetButton;

public class Solar {
    private static SolarEngine engine;
    
    private Solar() {
    	
    }
    
    public static void main(String[] args)    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Solar";
        cfg.useGL30 = false;
        // cfg.useGL20 = false;
        cfg.width = 900;
        cfg.height = 600;
        cfg.foregroundFPS = 120;
        cfg.vSyncEnabled = false;
        // cfg.samples = 16;
        
        engine = new SolarEngine();
        new LwjglApplication(engine, cfg);
        ScalePresetButton.loadPreset1();
    }
}
