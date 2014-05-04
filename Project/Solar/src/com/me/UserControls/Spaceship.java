package com.me.UserControls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;


public class Spaceship extends SolarActor {
	
	private GridPoint2 destination;
	
	public Spaceship ( String name )
	{
		this.setSize(33, 33);
		this.shapeRenderer = new ShapeRenderer();
		this.selected = false;
		this.destination = null;
		this.setName(name);
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
	
	public void setDestination(GridPoint2 destination)
	{
		this.destination = destination;
  	     System.out.println("Neues Ziel gesetzt für " + this.getName() + " bei X= " + destination.x + ", Y= " + destination.y);
	}
	
	public GridPoint2 getDestination()
	{
		return destination;
	}
	
	public void moveSpaceship() {
		MoveToAction mov = new MoveToAction();
		mov.setPosition(this.destination.x, this.destination.y);
        mov.setDuration(5);
		this.addAction(mov);
	}
}
