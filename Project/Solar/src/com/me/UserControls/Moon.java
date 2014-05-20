package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Moon extends SolarActor
{
	public Moon(String name)
	{
		this.setSize(75, 75);
		this.shapeRenderer = new ShapeRenderer();
		this.setName(name);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle(getX() + getWidth()/2, getY()+getHeight()/2, getHeight()/2);
        shapeRenderer.end();    
	}
}
