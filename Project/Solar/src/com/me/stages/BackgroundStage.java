package com.me.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.me.solar.SolarEngine;

public class BackgroundStage extends BaseBackgroundStage
{

    public BackgroundStage(SolarEngine SE)
    {
        super(SE, "Background");

        addActor(SE.Service.AddBackgroundImage());        
    }
}
