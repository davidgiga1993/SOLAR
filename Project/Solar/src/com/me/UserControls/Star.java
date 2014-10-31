package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Star extends AstronomicalBody
{	
	public Star(String name, double massInSolarMasses, double orbitalRadiusInMeters, double angleInDegree, AstronomicalBody origin)
	{
		super(name, orbitalRadiusInMeters, angleInDegree, origin);
		this.setSize(1000, 1000);
		this.massInKilogram = convertSolarMassesIntoKilogram(massInSolarMasses);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        displayOrbit();
        displayStar();    
	}

	private void displayStar() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(getX() + getWidth() / 2, getY() + getHeight() / 2, getHeight()/2);
        shapeRenderer.end();
	}
	
	
    /**
     * Adds a new planet with the specified parameters as a satellite orbiting the star.
     * @param name Desired name of the planet
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInAU Desired orbital radius around the star in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the planet's position on the map of the system
     * @return created planet object
     */
    public Planet placeNewPlanet(String name, double massInEarthMasses, double orbitalRadiusInAU, double angleInDegree)
    {
        Planet newObject = new Planet(name, massInEarthMasses, orbitalRadiusInAU, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
}
