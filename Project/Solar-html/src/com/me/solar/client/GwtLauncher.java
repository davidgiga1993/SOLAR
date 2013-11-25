package com.me.solar.client;

import com.me.solar.SolarEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication
{
    @Override
    public GwtApplicationConfiguration getConfig()
    {
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(900, 600);
        return cfg;
    }

    @Override
    public ApplicationListener getApplicationListener()
    {
        return new SolarEngine();
    }
}