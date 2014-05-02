package com.me.UserControls;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class SolarActor extends Actor {
	
	protected String name;
	protected ShapeRenderer shapeRenderer;
	protected boolean selected;

	@Override
	public String toString()
	{
		return name;	
	}
	
	public void select()
	{
		selected = true;
	}	
	
	public void deselect()
	{
		selected = false;
	}	
}
