package com.me.UserControls;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class SolarSystem extends AstronomicalBody {
	
	private String name;
	
    public SolarSystem(String name)
    {
		super(name);
    }
    
    public void createSolarSystem()
    {
    	Star star;
    	Planet planet;
    	
    	star = placeNewStar("Sun", new GridPoint2(0, 0));
    		star.placeNewPlanet("Mercury", new GridPoint2(0, 200));
    		star.placeNewPlanet("Venus", new GridPoint2(0, 300));
    		planet = star.placeNewPlanet("Earth", new GridPoint2(0, 400));
    			planet.placeNewMoon("Moon", new GridPoint2(50, 400));
    		planet = star.placeNewPlanet("Mars", new GridPoint2(0, 500));
    			planet.placeNewAsteroid("Phobos", new GridPoint2(50, 500));
    			planet.placeNewAsteroid("Deimos", new GridPoint2(100, 500));
    		star.placeNewPlanet("Jupiter", new GridPoint2(0, 600));
    		star.placeNewPlanet("Saturn", new GridPoint2(0, 700));
    		star.placeNewPlanet("Uranus", new GridPoint2(0, 800));
    		star.placeNewPlanet("Neptune", new GridPoint2(0, 900));
    }
        
    public String getSystemName()
    {
    	return name;
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
