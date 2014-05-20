package com.me.UserControls;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class SolarSystem extends AstronomicalBody {
	
	private String name;
	
    public SolarSystem(String name)
    {
    	this.name = name;
    	this.satellites = new Group();
    }
    
    public void createSolarSystem()
    {
    	Star star = placeNewStar("Sol", new GridPoint2(-300, -300));
    	star.placeNewPlanet("Venus", new GridPoint2(-200, 200));
    	star.placeNewPlanet("Earth", new GridPoint2(200, -200));

    	placeNewMoon("Moon", new GridPoint2(500, -375));
    	placeNewAsteroid("Vesta", new GridPoint2(-250, 50)); 
    }
        
    public String getSystemName()
    {
    	return name;
    }

    private void placeNewAsteroid(String name, GridPoint2 startlocation)
    {
        Asteroid newObject = new Asteroid(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        satellites.addActor(newObject);
    }

    private void placeNewMoon(String name, GridPoint2 startlocation)
    {
        Moon newObject = new Moon(name);
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        satellites.addActor(newObject);
    }

    public Star placeNewStar(String name, GridPoint2 startlocation)
    {
        Star newObject = new Star(name);
        // setPosition ist relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
        newObject.setPosition(startlocation.x - newObject.getWidth() / 2, startlocation.y - newObject.getHeight() / 2);
        satellites.addActor(newObject);
        return newObject;
	}
    
}
