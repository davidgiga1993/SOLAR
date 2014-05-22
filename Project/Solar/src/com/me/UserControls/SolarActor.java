package com.me.UserControls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class SolarActor extends Actor {
	
	protected ShapeRenderer shapeRenderer;
	protected boolean selected;
	protected Texture solarActorTexture;
	protected Sprite solarActorSprite;
	
	public SolarActor (String name)
	{
		this.setName(name);
		this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public String toString()
	{
		return getName() + " (" + getX() + "/" + getY() + ")";	
	}
	
	public void select()
	{
		selected = true;
	}	
	
	public void deselect()
	{
		selected = false;
	}	
	
	public boolean isSelected()
	{
		return selected;
	}
	
    protected static double convertAUIntoKilometer( double AU )
    {
    	return AU * 1.4960 * Math.pow(10, 8);
    }
    
	protected static float scaleDistanceToStage( double distance ) {
		double scalingFactor = Math.pow(10, 8);
		return (float) (distance / scalingFactor);
	}
}
