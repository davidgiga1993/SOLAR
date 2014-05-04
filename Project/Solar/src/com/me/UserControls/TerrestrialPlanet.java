package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class TerrestrialPlanet extends SolarActor
{
	public TerrestrialPlanet(String name)
	{
		this.setSize(200, 200);
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
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(getX() + getWidth()/2, getY()+getHeight()/2, getHeight()/2);
        shapeRenderer.end();    
	}
	
	public void moveSpaceship() {
		MoveToAction mov = new MoveToAction();
        mov.setDuration(5);
		this.addAction(mov);
		
	}
}
