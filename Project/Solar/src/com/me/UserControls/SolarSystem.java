package com.me.UserControls;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class SolarSystem {
	
	private String name;
	private Group mainBodies;
	
    public SolarSystem(String name)
    {
    	this.name = name;
    }
    
    public void createSolarSystem()
    {
    	mainBodies = new Group();
    	mainBodies.addActor(placeNewStar("Sol", new GridPoint2(-300, -300)));
    	mainBodies.addActorAt(1, placeNewPlanet("Earth", new GridPoint2(300, -300)));
    	mainBodies.addActorAt(2, placeNewMoon("Moon", new GridPoint2(500, -375)));
    	mainBodies.addActorAt(3, placeNewAsteroid("Vesta", new GridPoint2(-250, 50))); 
    	mainBodies.addActorAt(4, placeNewAsteroid("Vesta2", new GridPoint2(-250, 50)));  
    }
    
    public Group getSolarSystem()
    {
    	return mainBodies;
    }
    
    public int getNumberOfMainBodies()
    {
    	if (mainBodies.hasChildren())
    		return mainBodies.getChildren().size;
    	else
    		return 0;
    }
    
    public String getSystemName()
    {
    	return name;
    }

    private Asteroid placeNewAsteroid(String name, GridPoint2 startlocation)
    {
        Asteroid newObject = new Asteroid(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        return newObject;
    }

    private Moon placeNewMoon(String name, GridPoint2 startlocation)
    {
        Moon newObject = new Moon(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        return newObject;
    }

    private Planet placeNewPlanet(String name, GridPoint2 startlocation)
    {
        Planet newObject = new Planet(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        return newObject;
    }

    public Star placeNewStar(String name, GridPoint2 startlocation)
    {
        Star newObject = new Star(name);
        // setPosition ist relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        return newObject;
	}
    
}
