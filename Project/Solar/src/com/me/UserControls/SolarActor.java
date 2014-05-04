package com.me.UserControls;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class SolarActor extends Actor {
	
	protected ShapeRenderer shapeRenderer;
	protected boolean selected;

	@Override
	public String toString()
	{
		return getName();	
	}
	
	public void select()
	{
		selected = true;
	}	
	
	public void deselect()
	{
		selected = false;
	}	
	
	public boolean getSelected()
	{
		return selected;
	}
}
