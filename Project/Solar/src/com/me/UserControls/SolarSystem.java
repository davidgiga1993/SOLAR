package com.me.UserControls;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Andi
 * Contains the model of the structure of all astronomical objects in the solar system.
 */
/**
 * @author Andi
 *
 */
public class SolarSystem extends AstronomicalBody {
	
	private String name;
	
    public SolarSystem(String name)
    {
		super(name);
		setPosition(0, 0);
    }
    
    
    /**
     * Creation of a new solar system containing the specified number of astronomical objects.
     * Current implementation creates the fixed home system of our Sun, the eight planets and various dwarf planets.
     */
    public void createSolarSystem()
    {
    	Star star;
    	Planet planet;
    	
    	star = placeNewStar("Sun", 1, 0, 0);
    		star.placeNewPlanet("Mercury", 0.055, 0.38709893, 170);
    		star.placeNewPlanet("Venus", 0.815, 0.72333199, -45);
    		planet = star.placeNewPlanet("Earth", 1, 1, -120);
    			planet.placeNewMoon("Moon", 0.0123, 384399, -30);
    		planet = star.placeNewPlanet("Mars", 0.107, 1.52366231, -140);
    			planet.placeNewAsteroid("Phobos", 1.0659 * Math.pow(10, 16), 9367, 90);
    			planet.placeNewAsteroid("Deimos", 1.4762 * Math.pow(10, 15), 23463, 90);
    		star.placeNewPlanet("Ceres", 0.00016, 2.766, 0);
    		planet = star.placeNewPlanet("Jupiter", 318, 5.20336301, 120);
    			planet.placeNewMoon("Io", 0.015, 421600, 90);
    			planet.placeNewMoon("Europa", 0.008, 670900, 90);
    			planet.placeNewMoon("Ganymede", 0.025, 1070400, 90);
    			planet.placeNewMoon("Callisto", 0.018, 1882700, 90);
    		planet = star.placeNewPlanet("Saturn", 95, 9.53707032, -130);
				planet.placeNewMoon("Mimas", 0.000006, 185520, 90);
				planet.placeNewMoon("Enceladus", 0.000018, 237948, 90);
				planet.placeNewMoon("Tethys", 0.00132, 294619, 90);
				planet.placeNewMoon("Dione", 0.0003, 377396, 90);
				planet.placeNewMoon("Rhea", 0.0004, 527108, 90);
				planet.placeNewMoon("Titan", 0.023, 1221870, 90);
				planet.placeNewMoon("Iapetus", 0.0003, 3560820, 90);
    		planet = star.placeNewPlanet("Uranus", 14, 19.19126393, 20);
				planet.placeNewMoon("Miranda", 0.00001,  129390, 90);
				planet.placeNewMoon("Ariel", 0.00022, 190900, 90);
				planet.placeNewMoon("Umbriel", 0.0002, 266000, 90);
				planet.placeNewMoon("Titania", 0.0006, 436300, 90);
				planet.placeNewMoon("Oberon", 0.00046, 583519, 90);
    		planet = star.placeNewPlanet("Neptune", 17, 30.06896348, -30);
				planet.placeNewMoon("Triton", 0.00358, 354759, 90);
	    	planet = star.placeNewPlanet("Pluto", 0.0022, 39.482, -80);
	    		planet.placeNewMoon("Charon", 0.00025, 17536, 90);
	    	star.placeNewPlanet("Haumea", 0.0007,  43.335, 0);
	    	star.placeNewPlanet("Makemake", 0.0003, 45.792, 0);
	    	star.placeNewPlanet("Eris", 0.0028, 67.668, 0);
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

    
    /**
     * A new actor of class Star with the specified parameters is added to the solar system.
     * @param name
     * @param massInSolarMasses
     * @param orbitalRadiusInMeters
     * @param angleInDegree
     * @return
     */
    public Star placeNewStar(String name, double massInSolarMasses, double orbitalRadiusInMeters, double angleInDegree)
    {
        Star newObject = new Star(name, massInSolarMasses, orbitalRadiusInMeters, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        massInKilogram += convertSolarMassesIntoKilogram(massInSolarMasses);
        return newObject;
	}
     
    /**
     * @return List containing the names of all astronomical objects placed in the system 
     */
    public List<String> getSatelliteNames()
    {
    	List <String> listOfSatellites = new ArrayList <String>();
		
    	addNamesOfSatellitesToList(listOfSatellites);
    	    	
    	return listOfSatellites;
    }    
}
