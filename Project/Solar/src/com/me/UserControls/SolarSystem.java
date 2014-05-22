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
    	
    	star = placeNewStar("Sun", 1, 0, 0);
    		star.placeNewPlanet("Mercury", 0.055, 387, 170);
    		star.placeNewPlanet("Venus", 0.815, 723, -45);
    		planet = star.placeNewPlanet("Earth", 1, 1000, -120);
    			planet.placeNewMoon("Moon", 0.0123, 100, -30);
    		planet = star.placeNewPlanet("Mars", 0.107, 1524, -140);
    			planet.placeNewAsteroid("Phobos", 1.0659 * Math.pow(10, 16), 50, 90);
    			planet.placeNewAsteroid("Deimos", 1.4762 * Math.pow(10, 15), 100, 90);
    		star.placeNewPlanet("Ceres", 0.00016, 2766, 0);
    		planet = star.placeNewPlanet("Jupiter", 318, 5203, 120);
    			planet.placeNewMoon("Io", 0.015, 50, 90);
    			planet.placeNewMoon("Europa", 0.008, 100, 90);
    			planet.placeNewMoon("Ganymede", 0.025, 150, 90);
    			planet.placeNewMoon("Callisto", 0.018, 200, 90);
    		planet = star.placeNewPlanet("Saturn", 95, 9537, -130);
				planet.placeNewMoon("Mimas", 0.000006, 50, 90);
				planet.placeNewMoon("Enceladus", 0.000018, 100, 90);
				planet.placeNewMoon("Tethys", 0.00132, 150, 90);
				planet.placeNewMoon("Dione", 0.0003, 200, 90);
				planet.placeNewMoon("Rhea", 0.0004, 250, 90);
				planet.placeNewMoon("Titan", 0.023, 300, 90);
				planet.placeNewMoon("Iapetus", 0.0003, 350, 90);
    		planet = star.placeNewPlanet("Uranus", 14, 19191, 20);
				planet.placeNewMoon("Miranda", 0.00001,  50, 90);
				planet.placeNewMoon("Ariel", 0.00022, 100, 90);
				planet.placeNewMoon("Umbriel", 0.0002, 150, 90);
				planet.placeNewMoon("Titania", 0.0006, 200, 90);
				planet.placeNewMoon("Oberon", 0.00046, 250, 90);
    		planet = star.placeNewPlanet("Neptune", 17, 30069, -30);
				planet.placeNewMoon("Triton", 0.00358, 50, 90);
	    	planet = star.placeNewPlanet("Pluto", 0.0022, 39482, -80);
	    		planet.placeNewMoon("Charon", 0.00025, 50, 90);
	    	star.placeNewPlanet("Haumea", 0.0007,  43335, 0);
	    	star.placeNewPlanet("Makemake", 0.0003, 45792, 0);
	    	star.placeNewPlanet("Eris", 0.0028, 67668, 0);
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

    public Star placeNewStar(String name, double massInSolarMasses, double orbitalRadiusInMeters, double angleInDegree)
    {
        Star newObject = new Star(name, massInSolarMasses, orbitalRadiusInMeters, angleInDegree, this);
        // setPosition ist relativ zum linken unteren Rand. Koordinaten sind angepasst, damit die eingehenden Koordinaten den Kreismittelpunkt referenzieren
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        massInKilogram += convertSolarMassesIntoKilogram(massInSolarMasses);
        return newObject;
	}
    
}
