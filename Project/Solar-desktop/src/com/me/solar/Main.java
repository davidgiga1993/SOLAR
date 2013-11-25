package com.me.solar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main
{
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Solar";
        cfg.useGL20 = false;
        cfg.width = 900;
        cfg.height = 600;
        cfg.foregroundFPS = 0;
        
        new LwjglApplication(new SolarEngine(), cfg);
    }
}
