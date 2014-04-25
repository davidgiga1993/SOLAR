package com.me.UserControls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Spaceship extends Actor {
	
	private ShapeRenderer shapeRenderer;
	
	public Spaceship ()
	{
		this.setSize(20, 20);
		shapeRenderer = new ShapeRenderer();		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(0,1,0,0);
        shapeRenderer.triangle(getX(), getY(), getX() - getWidth() / 2, getY() - getHeight(), getX() + getWidth() / 2, getY() - getHeight());
        shapeRenderer.end(); 
	}

}
