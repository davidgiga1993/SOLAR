package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class AstronomicalBody extends SolarActor {
	
	protected double orbitalRadiusInKilometers;
	protected double orbitalPeriodInDays;
	protected double massInKilogram;
	protected double angleInDegree;
	protected AstronomicalBody origin;
	protected Group satellites;
	
	public AstronomicalBody(String name)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = 0;
		this.angleInDegree = 0;
		this.origin = null;
		this.orbitalPeriodInDays = -1;
		this.massInKilogram = 0;
	}
	
	public AstronomicalBody(String name, double orbitalRadiusInMeters, double angleInDegree, AstronomicalBody origin)
	{
		super(name);
		this.satellites = new Group();
		this.orbitalRadiusInKilometers = orbitalRadiusInMeters;
		this.angleInDegree = angleInDegree;
		this.origin = origin;
		this.orbitalPeriodInDays = calculateOrbitalPeriod();
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
    
    protected Asteroid placeNewAsteroid(String name, double massInKilogram, double orbitalRadiusInKilometers, int angleInDegree)
    {
        Asteroid newObject = new Asteroid(name, massInKilogram, orbitalRadiusInKilometers, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }
    
    protected void calculateOrbitalPositionTotal()
    {
    		this.setPosition(calculateOrbitalPositionX(), calculateOrbitalPositionY());
    }

	protected float calculateOrbitalPositionX() {
		return (float) (origin.getX() + (float) Math.cos(Math.toRadians(angleInDegree)) * scaleDistanceToStage(orbitalRadiusInKilometers));
	}
	
	protected float calculateOrbitalPositionY() {
		return (float) (origin.getY() + (float) Math.sin(Math.toRadians(angleInDegree))  * scaleDistanceToStage(orbitalRadiusInKilometers));
	}
	    
    protected void displayOrbit()
    {
    	if (scaleDistanceToStage(orbitalRadiusInKilometers) < getParent().getWidth())
    		return;
		shapeRenderer.begin(ShapeType.Line);             
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(origin.getX(), origin.getY(), scaleDistanceToStage(orbitalRadiusInKilometers));
        shapeRenderer.end();
    }
    
    protected double calculateOrbitalPeriod()
    {
    	return Math.sqrt( 4 * Math.pow((Math.PI), 2) * Math.pow(orbitalRadiusInKilometers * 1000, 3) / origin.getMass() / getGravitationalConstant() ) /24 / 3600;
    }
    
    private static double getGravitationalConstant()
    {
    	return 6.67 * Math.pow(10, -11);
    }
    
    public double getMass()
    {
    	return massInKilogram;
    }
    
    protected static double convertEarthMassesIntoKilogram( double massInEarthMasses)
    {
    	return massInEarthMasses * 5.97219 * Math.pow(10, 24);
    }
    
    protected static double convertSolarMassesIntoKilogram( double massInSolarMasses)
    {
    	return massInSolarMasses * 1.98855 * Math.pow(10, 30);
    }
}
