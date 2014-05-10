package com.me.UserControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dummy extends SolarActor {
	
	public Dummy()
	{
	solarActorTexture = new Texture(Gdx.files.internal("data/Cruiser.png"));
	}
	
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {  
      	batch.draw(solarActorTexture,getX(),getY());
	}
}