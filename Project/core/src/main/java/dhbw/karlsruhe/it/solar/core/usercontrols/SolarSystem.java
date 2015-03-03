package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon.MoonType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet.PlanetType;

/**
 * @author Andi
 *
 */
public class SolarSystem extends AstronomicalBody {
	
    public SolarSystem(String name)
    {
		super(name);
		setPosition(0, 0);
		orbitalProperties.setNewOrbitPrimary(new SystemRoot(0,0));
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {

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

    	star = placeNewStar("Sun", new Length(1392684f/2, Length.Unit.kilometres), new Mass(1, Mass.Unit.SOLAR_MASS), new Length(0, Length.Unit.kilometres), 0);
    		planet = star.placeNewPlanet("Mercury", new Length(4879.4f/2, Length.Unit.kilometres), new Mass(0.055f, Mass.Unit.EARTH_MASS), new Length(0.38709893f, Length.Unit.astronomicalUnit), 170, PlanetType.MERCURIAN);
			planet = star.placeNewPlanet("Venus", new Length(12103.6f/2, Length.Unit.kilometres), new Mass(0.815f, Mass.Unit.EARTH_MASS), new Length(0.72333199f, Length.Unit.astronomicalUnit), -45, PlanetType.VENUSIAN);
    		planet = star.placeNewPlanet("Earth", new Length(12756.32f/2, Length.Unit.kilometres), new Mass(1f, Mass.Unit.EARTH_MASS), new Length(1f, Length.Unit.astronomicalUnit), -120, PlanetType.TERRAN);
    			planet.placeNewMoon("Moon", new Length(3476f/2, Length.Unit.kilometres), new Mass(0.0123f, Mass.Unit.EARTH_MASS), new Length(384399, Length.Unit.kilometres), -30, MoonType.LUNAR);
    		planet = star.placeNewPlanet("Mars", new Length(6792.4f/2, Length.Unit.kilometres), new Mass(0.107f, Mass.Unit.EARTH_MASS), new Length(1.52366231f, Length.Unit.astronomicalUnit), -140, PlanetType.MARTIAN);
    			planet.placeNewMoon("Phobos", new Length(23f / 2, Length.Unit.kilometres), new Mass((float) (1.0659 * Math.pow(10, 16)), Mass.Unit.KILOGRAM), new Length(9367, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    			planet.placeNewMoon("Deimos", new Length(13f/2, Length.Unit.kilometres), new Mass((float) (1.4762 * Math.pow(10, 15)), Mass.Unit.KILOGRAM), new Length(23463, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    		star.placeNewPlanet("Ceres", new Length(975f / 2, Length.Unit.kilometres), new Mass(0.00016f, Mass.Unit.EARTH_MASS), new Length(2.766f, Length.Unit.astronomicalUnit), 0, PlanetType.DWARFPLANET);
    		planet = star.placeNewPlanet("Jupiter", new Length(142984f/2, Length.Unit.kilometres), new Mass(318f, Mass.Unit.EARTH_MASS), new Length(5.20336301f, Length.Unit.astronomicalUnit), 120, PlanetType.JOVIAN);
    			planet.placeNewMoon("Amalthea", new Length(167f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS), new Length(181365, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    			planet.placeNewMoon("Thebe", new Length(98f/2, Length.Unit.kilometres), new Mass(0.000000064f, Mass.Unit.EARTH_MASS), new Length(221900, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    			planet.placeNewMoon("Io", new Length(3643f / 2, Length.Unit.kilometres), new Mass(0.015f, Mass.Unit.EARTH_MASS), new Length(421600, Length.Unit.kilometres), 90, MoonType.IONIAN);
    			planet.placeNewMoon("Europa", new Length(3122f / 2, Length.Unit.kilometres), new Mass(0.008f, Mass.Unit.EARTH_MASS), new Length(670900, Length.Unit.kilometres), 90, MoonType.EUROPAN);
    			planet.placeNewMoon("Ganymede", new Length(5262f/2, Length.Unit.kilometres), new Mass(0.025f, Mass.Unit.EARTH_MASS), new Length(1070400, Length.Unit.kilometres), 90, MoonType.GANYMEDIAN);
    			planet.placeNewMoon("Callisto", new Length(4821 / 2, Length.Unit.kilometres), new Mass(0.018f, Mass.Unit.EARTH_MASS), new Length(1882700, Length.Unit.kilometres), 90, MoonType.CALLISTOAN);
				planet.placeNewMoon("Himalia", new Length(170f/2, Length.Unit.kilometres), new Mass(0.00000112f, Mass.Unit.EARTH_MASS), new Length(11460000, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Saturn", new Length(120536f/2, Length.Unit.kilometres), new Mass(95f, Mass.Unit.EARTH_MASS), new Length(9.53707032f, Length.Unit.astronomicalUnit), -130, PlanetType.SATURNIAN);
				planet.placeNewMoon("Prometheus", new Length(100f/2, Length.Unit.kilometres), new Mass(0.000000013f, Mass.Unit.EARTH_MASS), new Length(139400, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Pandora", new Length(84f/2, Length.Unit.kilometres), new Mass(0.000000012f, Mass.Unit.EARTH_MASS), new Length(141700, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Epimetheus", new Length(116f/2, Length.Unit.kilometres), new Mass(0.00000009f, Mass.Unit.EARTH_MASS), new Length(151410, Length.Unit.kilometres), 85, MoonType.IRREGULAR);
    			planet.placeNewMoon("Janus", new Length(179f/2, Length.Unit.kilometres), new Mass(0.00000032f, Mass.Unit.EARTH_MASS), new Length(151410, Length.Unit.kilometres), 95, MoonType.IRREGULAR);
				planet.placeNewMoon("Mimas", new Length(396.4f/2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS), new Length(185520, Length.Unit.kilometres), 90, MoonType.MIMANTEAN);
				planet.placeNewMoon("Enceladus", new Length(504.2f/2, Length.Unit.kilometres), new Mass(0.000018f, Mass.Unit.EARTH_MASS), new Length(237948, Length.Unit.kilometres), 90, MoonType.ENCELADEAN);
				planet.placeNewMoon("Tethys", new Length(1066f / 2, Length.Unit.kilometres), new Mass(0.00132f, Mass.Unit.EARTH_MASS), new Length(294619, Length.Unit.kilometres), 90, MoonType.TETHYAN);
				planet.placeNewMoon("Dione", new Length(1123.4f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(377396, Length.Unit.kilometres), 90, MoonType.DIONEAN);
				planet.placeNewMoon("Rhea", new Length(1529f/2, Length.Unit.kilometres), new Mass(0.0004f, Mass.Unit.EARTH_MASS), new Length(527108, Length.Unit.kilometres), 90, MoonType.RHEAN);
				planet.placeNewMoon("Titan", new Length(5150f/2, Length.Unit.kilometres), new Mass(0.023f, Mass.Unit.EARTH_MASS), new Length(1221870, Length.Unit.kilometres), 90, MoonType.TITANEAN);
				planet.placeNewMoon("Hyperion", new Length(266f/2, Length.Unit.kilometres), new Mass(0.00000094f, Mass.Unit.EARTH_MASS), new Length(1481009, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Iapetus", new Length(1436f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(3560820, Length.Unit.kilometres), 90, MoonType.IAPETIAN);
				planet.placeNewMoon("Phoebe", new Length(212f/2, Length.Unit.kilometres), new Mass(0.00000139f, Mass.Unit.EARTH_MASS), new Length(12955759, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Uranus", new Length(51118f/2, Length.Unit.kilometres), new Mass(14f, Mass.Unit.EARTH_MASS), new Length(19.19126393f, Length.Unit.astronomicalUnit), 20, PlanetType.URANIAN);
    			planet.placeNewMoon("Juliet", new Length(93f/2, Length.Unit.kilometres), new Mass(0.000000047f, Mass.Unit.EARTH_MASS), new Length(64358, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    			planet.placeNewMoon("Portia", new Length(134f/2, Length.Unit.kilometres), new Mass(0.00000028f, Mass.Unit.EARTH_MASS), new Length(66097, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
       			planet.placeNewMoon("Puck", new Length(162f/2, Length.Unit.kilometres), new Mass(0.00000049f, Mass.Unit.EARTH_MASS), new Length(86004, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Miranda", new Length(472f/2, Length.Unit.kilometres), new Mass(0.00001f, Mass.Unit.EARTH_MASS),  new Length(129390, Length.Unit.kilometres), 90, MoonType.MIRANDAN);
				planet.placeNewMoon("Ariel", new Length(1158f/2, Length.Unit.kilometres), new Mass(0.00022f, Mass.Unit.EARTH_MASS), new Length(190900, Length.Unit.kilometres), 90, MoonType.ARIELIAN);
				planet.placeNewMoon("Umbriel", new Length(1169f/2, Length.Unit.kilometres), new Mass(0.0002f, Mass.Unit.EARTH_MASS), new Length(266000, Length.Unit.kilometres), 90, MoonType.UMBRELIAN);
				planet.placeNewMoon("Titania", new Length(1578f/2, Length.Unit.kilometres), new Mass(0.0006f, Mass.Unit.EARTH_MASS), new Length(436300, Length.Unit.kilometres), 90, MoonType.TITANIAN);
				planet.placeNewMoon("Oberon", new Length(1523f/2, Length.Unit.kilometres), new Mass(0.00046f, Mass.Unit.EARTH_MASS), new Length(583519, Length.Unit.kilometres), 90, MoonType.OBERONIAN);
				planet.placeNewMoon("Sycorax", new Length(150f / 2, Length.Unit.kilometres), new Mass(0.00000039f, Mass.Unit.EARTH_MASS), new Length(12179000, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
    		planet = star.placeNewPlanet("Neptune", new Length(49528f/2, Length.Unit.kilometres), new Mass(17f, Mass.Unit.EARTH_MASS), new Length(30.06896348f, Length.Unit.astronomicalUnit), -30, PlanetType.NEPTUNIAN);
	    		planet.placeNewMoon("Despina", new Length(180f/2, Length.Unit.kilometres), new Mass(0.00000037f, Mass.Unit.EARTH_MASS), new Length(52526, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Galatea", new Length(176f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS), new Length(61953, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Larissa", new Length(194f / 2, Length.Unit.kilometres), new Mass(0.0000007f, Mass.Unit.EARTH_MASS), new Length(73548, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Proteus", new Length(420f/2, Length.Unit.kilometres), new Mass(0.0000074f, Mass.Unit.EARTH_MASS), new Length(117647, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
				planet.placeNewMoon("Triton", new Length(2707f / 2, Length.Unit.kilometres), new Mass(0.00358f, Mass.Unit.EARTH_MASS), new Length(354759, Length.Unit.kilometres), 90, MoonType.TRITONIAN);
				planet.placeNewMoon("Nereid", new Length(340f / 2, Length.Unit.kilometres), new Mass(0.0000025f, Mass.Unit.EARTH_MASS), new Length(5513787, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    	planet = star.placeNewPlanet("Pluto", new Length(2310f/2, Length.Unit.kilometres), new Mass(0.0022f, Mass.Unit.EARTH_MASS), new Length(39.482f, Length.Unit.astronomicalUnit), -80, PlanetType.DWARFPLANET);
	    		planet.placeNewMoon("Charon", new Length(1207f/2, Length.Unit.kilometres), new Mass(0.00025f, Mass.Unit.EARTH_MASS), new Length(17536, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Nix", new Length(90f/2, Length.Unit.kilometres), new Mass(0.000000017f, Mass.Unit.EARTH_MASS), new Length(48708, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Hydra", new Length(120f/2, Length.Unit.kilometres), new Mass(0.00000017f, Mass.Unit.EARTH_MASS), new Length(64780, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    	planet = star.placeNewPlanet("Haumea", new Length(1600f/2, Length.Unit.kilometres), new Mass(0.0007f, Mass.Unit.EARTH_MASS),  new Length(43.335f, Length.Unit.astronomicalUnit), 0, PlanetType.DWARFPLANET);
		    	planet.placeNewMoon("Namaka", new Length(200f/2, Length.Unit.kilometres), new Mass(0.0000003f, Mass.Unit.EARTH_MASS), new Length(25657, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    		planet.placeNewMoon("Hi'iaka", new Length(380f/2, Length.Unit.kilometres), new Mass(0.000003f, Mass.Unit.EARTH_MASS), new Length(49880, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
	    	star.placeNewPlanet("Makemake", new Length(1500f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(45.792f, Length.Unit.astronomicalUnit), 0, PlanetType.DWARFPLANET);
	    	planet = star.placeNewPlanet("Eris", new Length(2326f/2, Length.Unit.kilometres), new Mass(0.0028f, Mass.Unit.EARTH_MASS), new Length(67.668f, Length.Unit.astronomicalUnit), 0, PlanetType.DWARFPLANET);
    			planet.placeNewMoon("Dysnomia", new Length(684f / 2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS), new Length(37350, Length.Unit.kilometres), 90, MoonType.IRREGULAR);
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
    
    /**
     * Adds a new star with the specified parameters as a satellite orbiting the center of the solar system.
     * @param name Desired name of the star
     * @param mass Desired mass of the planet in multiples of Solar Masses
     * @param orbitalRadius Desired orbital radius around the center of the solar system in multiples of Astronomical Units
     * @param angleInDegree Desired angle of the star's position on the map of the system
     * @return created Star object
     */
    public Star placeNewStar(String name, Length radius, Mass mass, Length orbitalRadius, float angleInDegree)
    {
    	OrbitalProperties orbit = new OrbitalProperties(mass, this, orbitalRadius, angleInDegree);
		BodyProperties body = new BodyProperties(mass, radius);
        Star newObject = new Star(name, orbit, body);
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
	}
}
