package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SolarSystem extends AstronomicalBody {
	
	private String name;
	
    public SolarSystem(String name)
    {
		super(name);
		setPosition(0, 0);
    }
    
    public void createSolarSystem()
    {
    	Star star;
    	Planet planet;
    	
    	star = placeNewStar("Sun", 0, 0);
    		star.placeNewPlanet("Mercury", 100, 0);
   		star.placeNewPlanet("Venus", 200, 0);
    		planet = star.placeNewPlanet("Earth", 300, 0);
    			planet.placeNewMoon("Moon", 50, 90);
//    		planet = star.placeNewPlanet("Mars", 400, 0);
//    			planet.placeNewAsteroid("Phobos", 50, 90);
//    			planet.placeNewAsteroid("Deimos", 100, 90);
//    		star.placeNewPlanet("Jupiter", 500, 0);
//    		star.placeNewPlanet("Saturn", 600, 0);
//    		star.placeNewPlanet("Uranus", 700, 0);
//    		star.placeNewPlanet("Neptune", 800, 0);
    }
    
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        diplaySystemCenter();    
	}
	
	private void diplaySystemCenter() {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(getX(), getY(), 10);
        shapeRenderer.end();  
	}
        
    public String getSystemName()
    {
    	return name;
    }

    public Star placeNewStar(String name, int orbitalRadius, int angleInDegree)
    {
        Star newObject = new Star(name, orbitalRadius, angleInDegree);
        // setPosition ist relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
	}
    
}
