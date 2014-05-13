package com.me.UserControls;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Star extends SolarActor
{
	private List<SolarActor> Satellites;
	
	public Star(String name)
	{
		this.setSize(500, 500);
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
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(getX() + getWidth()/2, getY()+getHeight()/2, getHeight()/2);
        shapeRenderer.end();    
	}
}
