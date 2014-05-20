package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet extends AstronomicalBody
{
	public Planet(String name, int orbitalRadius, int angleInDegree)
	{
		super(name, orbitalRadius, angleInDegree);
		this.setSize(50, 50);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        displayOrbit();
        displayPlanet();  
	}

	private void displayPlanet() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(getX(), getY(), getHeight()/2);
        shapeRenderer.end();
	}
	
    public Moon placeNewMoon(String name, int radius, int angle)
    {
        Moon newObject = new Moon(name, radius, angle);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
	
}
