package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class AstronomicalBody extends SolarActor {
	
	protected Group satellites;
	protected int orbitalRadius;
	protected int angleInDegree;
	protected AstronomicalBody origin;
	
	public AstronomicalBody(String name)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadius = 0;
		this.angleInDegree = 0;
		this.origin = null;
	}
	
	public AstronomicalBody(String name, int orbitalRadius, int angleInDegree, AstronomicalBody origin)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadius = orbitalRadius;
		this.angleInDegree = angleInDegree;
		this.origin = origin;
	}
	
    public Group getSatellites()
    {
    	return satellites;
    }
    
    public int getNumberOfSatellites()
    {
    	if (satellites == null )
    		return 0;
    	else
    		return satellites.getChildren().size;
    }
    
    protected Asteroid placeNewAsteroid(String name, int orbitalRadius, int angle)
    {
        Asteroid newObject = new Asteroid(name, orbitalRadius, angle, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
    
    protected void calculateOrbitalPositionTotal()
    {
    		this.setPosition(calculateOrbitalPositionX(), calculateOrbitalPositionY());
    }

	protected int calculateOrbitalPositionX() {
		return (int) (origin.getX() + (float) Math.cos(Math.toRadians(angleInDegree)) * orbitalRadius);
	}
	
	protected int calculateOrbitalPositionY() {
		return (int) (origin.getY() + (float) Math.sin(Math.toRadians(angleInDegree))  * orbitalRadius);
	}
    
    protected void displayOrbit()
    {
    	if (orbitalRadius < getParent().getWidth())
    		return;
		shapeRenderer.begin(ShapeType.Line);             
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(origin.getX(), origin.getY(), orbitalRadius);
        shapeRenderer.end();
    }
}
