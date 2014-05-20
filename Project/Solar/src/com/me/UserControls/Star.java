package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Star extends AstronomicalBody
{	
	public Star(String name, int orbitalRadius, int angleInDegree)
	{
		super(name, orbitalRadius, angleInDegree);
		this.setSize(100, 100);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        diplayStar();    
	}

	private void diplayStar() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(getX(), getY(), getHeight()/2);
        shapeRenderer.end();
	}
	
    public Planet placeNewPlanet(String name, int orbitalRadius, int angleInDegree)
    {
        Planet newObject = new Planet(name, orbitalRadius, angleInDegree);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
}
