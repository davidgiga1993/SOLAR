package com.me.UserControls;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class AstronomicalBody extends SolarActor {
	
	protected Group satellites;
	
	public AstronomicalBody(String name)
	{
		super(name);
		this.satellites = new Group();
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
    
    protected Asteroid placeNewAsteroid(String name, GridPoint2 startlocation)
    {
        Asteroid newObject = new Asteroid(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        satellites.addActor(newObject);
        return newObject;
    }

}
