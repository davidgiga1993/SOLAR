package dhbw.karlsruhe.it.solar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dhbw.karlsruhe.it.solar.core.solar.*;

public class Solar
{
    public static SolarEngine Engine;
    
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Solar";
        cfg.useGL30 = false;
        // cfg.useGL20 = false;
        cfg.width = 900;
        cfg.height = 600;
        cfg.foregroundFPS = 0;
        
        Engine = new SolarEngine();
        new LwjglApplication(Engine, cfg);
    }
}