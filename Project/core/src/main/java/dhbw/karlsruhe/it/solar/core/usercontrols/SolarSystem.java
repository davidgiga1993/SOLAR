package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;

import java.util.ArrayList;
import java.util.List;

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
		origin = new SystemRoot(0,0);
    }

	@Override
	public void act(float delta) {

	}

	/**
     * Creation of a new solar system containing the specified number of astronomical objects.
     * Current implementation creates a fixed prototpye system (our Sun, the eight planets, their moons, etc.).
     */
    public void createSolarSystem()
    {
    	Star star;
    	Planet planet;
    	
    	star = placeNewStar("Sun", new Length(1392684f/2, Length.Unit.kilometres), 1, 0, 0);
    		planet = star.placeNewPlanet("Mercury", new Length(4879.4f/2, Length.Unit.kilometres), 0.055, 0.38709893, 170);
				planet.setColor(Color.GRAY);
			planet = star.placeNewPlanet("Venus", new Length(12103.6f/2, Length.Unit.kilometres), 0.815, 0.72333199, -45);
				planet.setColor(new Color(.7f,.55f,.3f,1f));
    		planet = star.placeNewPlanet("Earth",new Length(12756.32f/2, Length.Unit.kilometres), 1, 1, -120);
				planet.setColor(Color.BLUE);
    			planet.placeNewMoon("Moon", new Length(3476f/2, Length.Unit.kilometres), 0.0123, 384399, -30);
    		planet = star.placeNewPlanet("Mars", new Length(6792.4f/2, Length.Unit.kilometres), 0.107, 1.52366231, -140);
				planet.setColor(new Color(0xc68c66ff));
    			planet.placeNewAsteroid("Phobos", new Length(23f/2, Length.Unit.kilometres), 1.0659 * Math.pow(10, 16), 9367, 90);
    			planet.placeNewAsteroid("Deimos", new Length(13f/2, Length.Unit.kilometres), 1.4762 * Math.pow(10, 15), 23463, 90);
    		star.placeNewPlanet("Ceres", new Length(975f/2, Length.Unit.kilometres), 0.00016, 2.766, 0);
    		planet = star.placeNewPlanet("Jupiter", new Length(142984f/2, Length.Unit.kilometres), 318, 5.20336301, 120);
				planet.setColor(new Color(0xa48060ff));
    			planet.placeNewMoon("Io", new Length(3643f/2, Length.Unit.kilometres), 0.015, 421600, 90);
    			planet.placeNewMoon("Europa", new Length(3122f/2, Length.Unit.kilometres), 0.008, 670900, 90);
    			planet.placeNewMoon("Ganymede", new Length(5262f/2, Length.Unit.kilometres), 0.025, 1070400, 90);
    			planet.placeNewMoon("Callisto", new Length(4821/2, Length.Unit.kilometres), 0.018, 1882700, 90);
    		planet = star.placeNewPlanet("Saturn", new Length(120536f/2, Length.Unit.kilometres), 95, 9.53707032, -130);
				planet.setColor(new Color(0xc1b289ff));
				planet.placeNewMoon("Mimas", new Length(396.4f/2, Length.Unit.kilometres), 0.000006, 185520, 90);
				planet.placeNewMoon("Enceladus", new Length(504.2f/2, Length.Unit.kilometres), 0.000018, 237948, 90);
				planet.placeNewMoon("Tethys", new Length(1066f/2, Length.Unit.kilometres), 0.00132, 294619, 90);
				planet.placeNewMoon("Dione", new Length(1123.4f/2, Length.Unit.kilometres), 0.0003, 377396, 90);
				planet.placeNewMoon("Rhea", new Length(1529f/2, Length.Unit.kilometres), 0.0004, 527108, 90);
				planet.placeNewMoon("Titan", new Length(5150f/2, Length.Unit.kilometres), 0.023, 1221870, 90);
				planet.placeNewMoon("Iapetus", new Length(1436f/2, Length.Unit.kilometres), 0.0003, 3560820, 90);
    		planet = star.placeNewPlanet("Uranus", new Length(51118f/2, Length.Unit.kilometres), 14, 19.19126393, 20);
				planet.setColor(new Color(0xbaeefcff));
				planet.placeNewMoon("Ariel", new Length(1158f/2, Length.Unit.kilometres), 0.00022, 190900, 90);
				planet.placeNewMoon("Umbriel", new Length(1169f/2, Length.Unit.kilometres), 0.0002, 266000, 90);
				planet.placeNewMoon("Titania", new Length(1578f/2, Length.Unit.kilometres), 0.0006, 436300, 90);
				planet.placeNewMoon("Oberon", new Length(1523f/2, Length.Unit.kilometres), 0.00046, 583519, 90);
				planet.placeNewMoon("Miranda", new Length(472f/2, Length.Unit.kilometres), 0.00001,  129390, 90);
    		planet = star.placeNewPlanet("Neptune", new Length(49528f/2, Length.Unit.kilometres), 17, 30.06896348, -30);
				planet.setColor(new Color(0x455facff));
				planet.placeNewMoon("Triton", new Length(2707f/2, Length.Unit.kilometres), 0.00358, 354759, 90);
	    	planet = star.placeNewPlanet("Pluto", new Length(2310f/2, Length.Unit.kilometres), 0.0022, 39.482, -80);
				planet.setColor(new Color(0x898989ff));
	    		planet.placeNewMoon("Charon", new Length(1207f/2, Length.Unit.kilometres), 0.00025, 17536, 90);
	    	star.placeNewPlanet("Haumea", new Length(1600f/2, Length.Unit.kilometres), 0.0007,  43.335, 0);
	    	star.placeNewPlanet("Makemake", new Length(1500f/2, Length.Unit.kilometres), 0.0003, 45.792, 0);
	    	star.placeNewPlanet("Eris", new Length(2326f/2, Length.Unit.kilometres), 0.0028, 67.668, 0);
    }

	@Override
	public void drawLines(ShapeRenderer shapeRenderer) {
		super.drawLines(shapeRenderer);
		diplaySystemCenter(shapeRenderer);
	}

	private void diplaySystemCenter(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(getX(), getY(), 10);
	}
        
    public String getSystemName()
    {
    	return name;
    }
    
    /**
     * Adds a new star with the specified parameters as a satellite orbiting the center of the solar system.
     * @param name Desired name of the star
     * @param massInSolarMasses Desired mass of the planet in multiples of Solar Masses
     * @param orbitalRadiusInAU Desired orbital radius around the center of the solar system in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the star's position on the map of the system
     * @return created Star object
     */
    public Star placeNewStar(String name, Length radius, double massInSolarMasses, double orbitalRadiusInAU, double angleInDegree)
    {
        Star newObject = new Star(name, radius, massInSolarMasses, orbitalRadiusInAU, angleInDegree, this);
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
