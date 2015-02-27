package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

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
    public void draw(Batch batch, float parentAlpha) {
        return;
    }

	@Override
	public void act(float delta) {

	}

    @Override
    public void updateScale() {
        // nothing to do
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
    		planet = star.placeNewPlanet("Mercury", new Length(4879.4f/2, Length.Unit.kilometres), 0.055, 0.38709893, 170, PlanetType.MERCURIAN);
			planet = star.placeNewPlanet("Venus", new Length(12103.6f/2, Length.Unit.kilometres), 0.815, 0.72333199, -45, PlanetType.VENUSIAN);
				planet.setColor(new Color(.7f,.55f,.3f,1f));
    		planet = star.placeNewPlanet("Earth", new Length(12756.32f/2, Length.Unit.kilometres), 1, 1, -120, PlanetType.TERRAN);
    			planet.placeNewMoon("Moon", new Length(3476f/2, Length.Unit.kilometres), 0.0123, 384399, -30, MoonType.LUNAR);
    		planet = star.placeNewPlanet("Mars", new Length(6792.4f/2, Length.Unit.kilometres), 0.107, 1.52366231, -140, PlanetType.MARTIAN);
    			planet.placeNewMoon("Phobos", new Length(23f / 2, Length.Unit.kilometres), 1.0659 * Math.pow(10, 16), 9367, 90, MoonType.IRREGULAR);
    			planet.placeNewMoon("Deimos", new Length(13f/2, Length.Unit.kilometres), 1.4762 * Math.pow(10, 15), 23463, 90, MoonType.IRREGULAR);
    		star.placeNewPlanet("Ceres", new Length(975f / 2, Length.Unit.kilometres), 0.00016, 2.766, 0, PlanetType.DWARFPLANET);
    		planet = star.placeNewPlanet("Jupiter", new Length(142984f/2, Length.Unit.kilometres), 318, 5.20336301, 120, PlanetType.JOVIAN);
    			planet.placeNewMoon("Io", new Length(3643f / 2, Length.Unit.kilometres), 0.015, 421600, 90, MoonType.IONIAN);
    			planet.placeNewMoon("Europa", new Length(3122f / 2, Length.Unit.kilometres), 0.008, 670900, 90, MoonType.EUROPAN);
    			planet.placeNewMoon("Ganymede", new Length(5262f/2, Length.Unit.kilometres), 0.025, 1070400, 90, MoonType.GANYMEDIAN);
    			planet.placeNewMoon("Callisto", new Length(4821 / 2, Length.Unit.kilometres), 0.018, 1882700, 90, MoonType.CALLISTOAN);
				planet.placeNewMoon("Himalia", new Length(170f/2, Length.Unit.kilometres), 0.00000112, 11460000, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Amalthea", new Length(167f/2, Length.Unit.kilometres), 0.00000035, 181365, 90, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Saturn", new Length(120536f/2, Length.Unit.kilometres), 95, 9.53707032, -130, PlanetType.SATURNIAN);
				planet.placeNewMoon("Mimas", new Length(396.4f/2, Length.Unit.kilometres), 0.000006, 185520, 90, MoonType.MIMANTEAN);
				planet.placeNewMoon("Enceladus", new Length(504.2f/2, Length.Unit.kilometres), 0.000018, 237948, 90, MoonType.ENCELADEAN);
				planet.placeNewMoon("Tethys", new Length(1066f / 2, Length.Unit.kilometres), 0.00132, 294619, 90, MoonType.TETHYAN);
				planet.placeNewMoon("Dione", new Length(1123.4f/2, Length.Unit.kilometres), 0.0003, 377396, 90, MoonType.DIONEAN);
				planet.placeNewMoon("Rhea", new Length(1529f/2, Length.Unit.kilometres), 0.0004, 527108, 90, MoonType.RHEAN);
				planet.placeNewMoon("Titan", new Length(5150f/2, Length.Unit.kilometres), 0.023, 1221870, 90, MoonType.TITANEAN);
				planet.placeNewMoon("Iapetus", new Length(1436f/2, Length.Unit.kilometres), 0.0003, 3560820, 90, MoonType.IAPETIAN);
				planet.placeNewMoon("Hyperion", new Length(266f/2, Length.Unit.kilometres), 0.00000094, 1481009, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Phoebe", new Length(212f/2, Length.Unit.kilometres), 0.00000139, 12955759, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Janus", new Length(179f/2, Length.Unit.kilometres), 0.00000032, 151410, 95, MoonType.IRREGULAR);
				planet.placeNewMoon("Epimetheus", new Length(116f/2, Length.Unit.kilometres), 0.00000009, 151410, 85, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Uranus", new Length(51118f/2, Length.Unit.kilometres), 14, 19.19126393, 20, PlanetType.URANIAN);
				planet.placeNewMoon("Miranda", new Length(472f/2, Length.Unit.kilometres), 0.00001,  129390, 90, MoonType.MIRANDAN);
				planet.placeNewMoon("Ariel", new Length(1158f/2, Length.Unit.kilometres), 0.00022, 190900, 90, MoonType.ARIELIAN);
				planet.placeNewMoon("Umbriel", new Length(1169f/2, Length.Unit.kilometres), 0.0002, 266000, 90, MoonType.UMBRELIAN);
				planet.placeNewMoon("Titania", new Length(1578f/2, Length.Unit.kilometres), 0.0006, 436300, 90, MoonType.TITANIAN);
				planet.placeNewMoon("Oberon", new Length(1523f/2, Length.Unit.kilometres), 0.00046, 583519, 90, MoonType.OBERONIAN);
				planet.placeNewMoon("Puck", new Length(162f/2, Length.Unit.kilometres), 0.00000049, 86004, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Sycorax", new Length(150f / 2, Length.Unit.kilometres), 0.00000039, 12179000, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Portia", new Length(134f/2, Length.Unit.kilometres), 0.00000028, 66097, 90, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Neptune", new Length(49528f/2, Length.Unit.kilometres), 17, 30.06896348, -30, PlanetType.NEPTUNIAN);
				planet.placeNewMoon("Triton", new Length(2707f / 2, Length.Unit.kilometres), 0.00358, 354759, 90, MoonType.TRITONIAN);
				planet.placeNewMoon("Proteus", new Length(420f/2, Length.Unit.kilometres), 0.0000074, 117647, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Nereid", new Length(340f / 2, Length.Unit.kilometres), 0.0000025, 5513787, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Larissa", new Length(194f / 2, Length.Unit.kilometres), 0.0000007, 73548, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Galatea", new Length(176f/2, Length.Unit.kilometres), 0.00000035, 61953, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Despina", new Length(180f/2, Length.Unit.kilometres), 0.00000037, 52526, 90, MoonType.IRREGULAR);
	    	planet = star.placeNewPlanet("Pluto", new Length(2310f/2, Length.Unit.kilometres), 0.0022, 39.482, -80, PlanetType.DWARFPLANET);
	    		planet.placeNewMoon("Charon", new Length(1207f/2, Length.Unit.kilometres), 0.00025, 17536, 90, MoonType.IRREGULAR);
	    	planet = star.placeNewPlanet("Haumea", new Length(1600f/2, Length.Unit.kilometres), 0.0007,  43.335, 0, PlanetType.DWARFPLANET);
	    		planet.placeNewMoon("Hi'iaka", new Length(380f/2, Length.Unit.kilometres), 0.000003, 49880, 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Namaka", new Length(200f/2, Length.Unit.kilometres), 0.0000003, 25657, 90, MoonType.IRREGULAR);
	    	star.placeNewPlanet("Makemake", new Length(1500f/2, Length.Unit.kilometres), 0.0003, 45.792, 0, PlanetType.DWARFPLANET);
	    	planet = star.placeNewPlanet("Eris", new Length(2326f/2, Length.Unit.kilometres), 0.0028, 67.668, 0, PlanetType.DWARFPLANET);
    			planet.placeNewMoon("Dysnomia", new Length(684f / 2, Length.Unit.kilometres), 0.000006, 37350, 90, MoonType.IRREGULAR);
    }

	@Override
	public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
		super.drawLines(libGDXShapeRenderer,solarShapeRenderer);
		diplaySystemCenter(libGDXShapeRenderer);
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
    public Star placeNewStar(String name, Length radius, double massInSolarMasses, double orbitalRadiusInAU, float angleInDegree)
    {
        Star newObject = new Star(name, radius, massInSolarMasses, orbitalRadiusInAU, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.add(newObject);
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
