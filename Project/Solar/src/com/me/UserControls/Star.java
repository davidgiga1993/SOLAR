package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;

public class Star extends AstronomicalBody
{	
	public Star(String name)
	{
		super(name);
		this.setSize(100, 100);
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
	
    public Planet placeNewPlanet(String name, GridPoint2 startlocation)
    {
        Planet newObject = new Planet(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        satellites.addActor(newObject);
        return newObject;
    }
}
