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
    		star.placeNewPlanet("Mercury", 387, 170);
    		star.placeNewPlanet("Venus", 723, -45);
    		planet = star.placeNewPlanet("Earth", 1000, -120);
    			planet.placeNewMoon("Moon", 100, -30);
    		planet = star.placeNewPlanet("Mars", 1524, -140);
    			planet.placeNewAsteroid("Phobos", 50, 90);
    			planet.placeNewAsteroid("Deimos", 100, 90);
    		star.placeNewPlanet("Ceres", 2766, 0);
    		planet = star.placeNewPlanet("Jupiter", 5203, 120);
    			planet.placeNewMoon("Io", 50, 90);
    			planet.placeNewMoon("Europa", 100, 90);
    			planet.placeNewMoon("Ganymede", 150, 90);
    			planet.placeNewMoon("Callisto", 200, 90);
    		planet = star.placeNewPlanet("Saturn", 9537, -130);
				planet.placeNewMoon("Mimas", 50, 90);
				planet.placeNewMoon("Enceladus", 100, 90);
				planet.placeNewMoon("Tethys", 150, 90);
				planet.placeNewMoon("Dione", 200, 90);
				planet.placeNewMoon("Rhea", 250, 90);
				planet.placeNewMoon("Titan", 300, 90);
				planet.placeNewMoon("Iapetus", 350, 90);
    		planet = star.placeNewPlanet("Uranus", 19191, 20);
				planet.placeNewMoon("Miranda", 50, 90);
				planet.placeNewMoon("Ariel", 100, 90);
				planet.placeNewMoon("Umbriel", 150, 90);
				planet.placeNewMoon("Titania", 200, 90);
				planet.placeNewMoon("Oberon", 250, 90);
    		planet = star.placeNewPlanet("Neptune", 30069, -30);
				planet.placeNewMoon("Triton", 50, 90);
	    	planet = star.placeNewPlanet("Pluto", 39482, -80);
	    		planet.placeNewMoon("Charon", 50, 90);
	    	star.placeNewPlanet("Haumea", 43335, 0);
	    	star.placeNewPlanet("Makemake", 45792, 0);
	    	star.placeNewPlanet("Eris", 67668, 0);
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
        Star newObject = new Star(name, orbitalRadius, angleInDegree, this);
        // setPosition ist relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
	}
    
}
