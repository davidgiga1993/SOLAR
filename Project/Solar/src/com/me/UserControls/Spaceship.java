package com.me.UserControls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;


public class Spaceship extends Actor {
	
	private String shipName;
	private boolean selected;
	private GridPoint2 target;
	private ShapeRenderer shapeRenderer;
	
	public Spaceship ( String name )
	{
		this.setSize(33, 33);
		this.shapeRenderer = new ShapeRenderer();
		this.selected = false;
		this.shipName = name;
		this.target = null;
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
	
	public void setTarget(GridPoint2 target)
	{
		this.target = target;
  	     System.out.println("Neues Ziel gesetzt für " + this.shipName + " bei X= " + target.x + ", Y= " + target.y);
	}
	
	public GridPoint2 getTarget()
	{
		return target;
	}


	public void moveSpaceship() {
		MoveToAction mov = new MoveToAction();
		mov.setPosition(this.target.x, this.target.y);
        mov.setDuration(5);
		this.addAction(mov);
		
	}
}
