package com.me.UserControls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Spaceship extends Actor {
	
	private String shipName;
	private ShapeRenderer shapeRenderer;
	private boolean selected;
	
	public Spaceship ( String name )
	{
		this.setSize(33, 33);
		this.shapeRenderer = new ShapeRenderer();
		this.selected = false;
		this.shipName = name;
	}


	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(0,1,0,0);
        shapeRenderer.triangle(getX() + getWidth() / 2, getY(), getX(), getY() + getHeight(), getX() + getWidth(), getY() + getHeight());
        shapeRenderer.end(); 
        
        if ( selected )
        {
            shapeRenderer.begin(ShapeType.Line);  
            shapeRenderer.rect(getX() - 5, getY() - 5, getWidth() + 10, getHeight() + 10);
        }
        
        shapeRenderer.end(); 
	}
	
	public void select()
	{
		selected = true;
	}	
	
	public void deselect()
	{
		selected = false;
	}	
	
	@Override
	public String toString()
	{
		return shipName;	
	}
}
