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
    private String defaultBackground = "hintergrund01.png";

    public BackgroundStage(SolarEngine SE)
    {
        super(SE, "Background");

        ChangeBackgroundImage(defaultBackground);        
    }

    public void ChangeBackgroundImage(String backgroundImage)
    {
        Texture texture = new Texture(Gdx.files.internal("data/" + backgroundImage));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int height = texture.getHeight();
        int width = texture.getWidth();
        TextureRegion region = new TextureRegion(texture, 0, 0, width, height);

        Image actor = new Image(region);
        actor.setScaling(Scaling.fill);
        int x = -Gdx.graphics.getWidth() / 2;
        int y = -Gdx.graphics.getHeight() / 2;
        actor.setPosition(x, y);
        actor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(actor);
    }
}
