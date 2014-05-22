package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet extends AstronomicalBody
{
	public Planet(String name, double massInEarthMasses, double orbitalRadiusInAU, double angleInDegree, AstronomicalBody origin)
	{
		super(name, convertAUIntoKilometer(orbitalRadiusInAU), angleInDegree, origin);
		this.setSize(50, 50);
		this.massInKilogram = convertEarthMassesIntoKilogram(massInEarthMasses);
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
	
    public Moon placeNewMoon(String name, double massInEarthMasses, double orbitalRadiusInKilometers, double angleInDegree)
    {
        Moon newObject = new Moon(name, massInEarthMasses, orbitalRadiusInKilometers, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }	
}
