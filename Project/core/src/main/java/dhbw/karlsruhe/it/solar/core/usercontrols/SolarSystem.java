package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon.MoonType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet.PlanetType;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing.RingType;

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

    	star = placeNewStar("Sun", new Length(1392684f/2, Length.Unit.kilometres), new Mass(1, Mass.Unit.SOLAR_MASS), new Length(0, Length.Unit.kilometres), new Angle(0));
    		planet = star.placeNewPlanet("Mercury", new Length(4879.4f/2, Length.Unit.kilometres), new Mass(0.055f, Mass.Unit.EARTH_MASS), new Length(0.38709893f, Length.Unit.astronomicalUnit), new Angle(170), PlanetType.MERCURIAN, false, null, null, null);
			planet = star.placeNewPlanet("Venus", new Length(12103.6f/2, Length.Unit.kilometres), new Mass(0.815f, Mass.Unit.EARTH_MASS), new Length(0.72333199f, Length.Unit.astronomicalUnit), new Angle(-45), PlanetType.VENUSIAN, false, null, null, null);
    		planet = star.placeNewPlanet("Earth", new Length(12756.32f/2, Length.Unit.kilometres), new Mass(1f, Mass.Unit.EARTH_MASS), new Length(1f, Length.Unit.astronomicalUnit), new Angle(-120), PlanetType.TERRAN, false, null, null, null);
    			planet.placeNewMoon("Moon", new Length(3476f/2, Length.Unit.kilometres), new Mass(0.0123f, Mass.Unit.EARTH_MASS), new Length(384399, Length.Unit.kilometres), new Angle(-30), MoonType.LUNAR, false, null, null, null);
    		planet = star.placeNewPlanet("Mars", new Length(6792.4f/2, Length.Unit.kilometres), new Mass(0.107f, Mass.Unit.EARTH_MASS), new Length(1.52366231f, Length.Unit.astronomicalUnit), new Angle(-140), PlanetType.MARTIAN, false, null, null, null);
    			planet.placeNewMoon("Phobos", new Length(23f / 2, Length.Unit.kilometres), new Mass((float) (1.0659 * Math.pow(10, 16)), Mass.Unit.KILOGRAM), new Length(9367, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Deimos", new Length(13f/2, Length.Unit.kilometres), new Mass((float) (1.4762 * Math.pow(10, 15)), Mass.Unit.KILOGRAM), new Length(23463, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    		star.placeNewPlanet("Ceres", new Length(975f / 2, Length.Unit.kilometres), new Mass(0.00016f, Mass.Unit.EARTH_MASS), new Length(2.766f, Length.Unit.astronomicalUnit), new Angle(0), PlanetType.DWARFPLANET, false, null, null, null);
    		planet = star.placeNewPlanet("Jupiter", new Length(142984f/2, Length.Unit.kilometres), new Mass(318f, Mass.Unit.EARTH_MASS), new Length(5.20336301f, Length.Unit.astronomicalUnit), new Angle(120), PlanetType.JOVIAN, true, new Mass((float)(0.22*Math.pow(10, 16)),Mass.Unit.KILOGRAM), new Length(226000,Length.Unit.kilometres), RingType.JOVIAN);
				planet.placeNewMoon("Metis", new Length(43f/2, Length.Unit.kilometres), new Mass(1.25f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(127969, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Adrastea", new Length(16.4f/2, Length.Unit.kilometres), new Mass(6.03f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(128980, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Amalthea", new Length(167f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS), new Length(181365, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Thebe", new Length(98f/2, Length.Unit.kilometres), new Mass(0.000000064f, Mass.Unit.EARTH_MASS), new Length(221900, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Io", new Length(3643f / 2, Length.Unit.kilometres), new Mass(0.015f, Mass.Unit.EARTH_MASS), new Length(421600, Length.Unit.kilometres), new Angle(90), MoonType.IONIAN, false, null, null, null);
    			planet.placeNewMoon("Europa", new Length(3122f / 2, Length.Unit.kilometres), new Mass(0.008f, Mass.Unit.EARTH_MASS), new Length(670900, Length.Unit.kilometres), new Angle(90), MoonType.EUROPAN, false, null, null, null);
    			planet.placeNewMoon("Ganymede", new Length(5262f/2, Length.Unit.kilometres), new Mass(0.025f, Mass.Unit.EARTH_MASS), new Length(1070400, Length.Unit.kilometres), new Angle(90), MoonType.GANYMEDIAN, false, null, null, null);
    			planet.placeNewMoon("Callisto", new Length(4821 / 2, Length.Unit.kilometres), new Mass(0.018f, Mass.Unit.EARTH_MASS), new Length(1882700, Length.Unit.kilometres), new Angle(90), MoonType.CALLISTOAN, false, null, null, null);
    			planet.placeNewMoon("Leda", new Length(20f/2, Length.Unit.kilometres), new Mass(1.09f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(11165000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Himalia", new Length(170f/2, Length.Unit.kilometres), new Mass(0.00000112f, Mass.Unit.EARTH_MASS), new Length(11460000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Lysithea", new Length(36f/2, Length.Unit.kilometres), new Mass(6.3f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(11717000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Elara", new Length(86f/2, Length.Unit.kilometres), new Mass(8.66f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(11741000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Ananke", new Length(28f/2, Length.Unit.kilometres), new Mass(3.0f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(21276000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Carme", new Length(46f/2, Length.Unit.kilometres), new Mass(1.3f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(23404000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Pasiphea", new Length(56f/2, Length.Unit.kilometres), new Mass(1.92f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(23624000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Sinope", new Length(38f/2, Length.Unit.kilometres), new Mass(7.77f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(23939000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    		planet = star.placeNewPlanet("Saturn", new Length(120536f/2, Length.Unit.kilometres), new Mass(95f, Mass.Unit.EARTH_MASS), new Length(9.53707032f, Length.Unit.astronomicalUnit), new Angle(-130), PlanetType.SATURNIAN, true, new Mass((float)(3*Math.pow(10, 19)),Mass.Unit.KILOGRAM), new Length(140550,Length.Unit.kilometres), RingType.SATURNIAN);
    			planet.placeNewMoon("Pan", new Length(28.4f/2, Length.Unit.kilometres), new Mass(4.95f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(133584, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Atlas", new Length(30.6f/2, Length.Unit.kilometres), new Mass(6.6f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(137670, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Prometheus", new Length(100f/2, Length.Unit.kilometres), new Mass(0.000000013f, Mass.Unit.EARTH_MASS), new Length(139400, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Pandora", new Length(84f/2, Length.Unit.kilometres), new Mass(0.000000012f, Mass.Unit.EARTH_MASS), new Length(141700, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Epimetheus", new Length(116f/2, Length.Unit.kilometres), new Mass(0.00000009f, Mass.Unit.EARTH_MASS), new Length(151410, Length.Unit.kilometres), new Angle(85), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Janus", new Length(179f/2, Length.Unit.kilometres), new Mass(0.00000032f, Mass.Unit.EARTH_MASS), new Length(151410, Length.Unit.kilometres), new Angle(95), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Mimas", new Length(396.4f/2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS), new Length(185520, Length.Unit.kilometres), new Angle(90), MoonType.MIMANTEAN, false, null, null, null);
				planet.placeNewMoon("Enceladus", new Length(504.2f/2, Length.Unit.kilometres), new Mass(0.000018f, Mass.Unit.EARTH_MASS), new Length(237948, Length.Unit.kilometres), new Angle(90), MoonType.ENCELADEAN, false, null, null, null);
				planet.placeNewMoon("Tethys", new Length(1066f / 2, Length.Unit.kilometres), new Mass(0.00132f, Mass.Unit.EARTH_MASS), new Length(294619, Length.Unit.kilometres), new Angle(90), MoonType.TETHYAN, false, null, null, null);
				planet.placeNewMoon("Telesto", new Length(24.8f/2, Length.Unit.kilometres), new Mass(4.0464f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(294619, Length.Unit.kilometres), new Angle(150), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Calypso", new Length(21.4f/2, Length.Unit.kilometres), new Mass(2.5477f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(294619, Length.Unit.kilometres), new Angle(30), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Dione", new Length(1123.4f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(377396, Length.Unit.kilometres), new Angle(90), MoonType.DIONEAN, false, null, null, null);
				planet.placeNewMoon("Helene", new Length(35.2f/2, Length.Unit.kilometres), new Mass(11f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(377420, Length.Unit.kilometres), new Angle(150), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Rhea", new Length(1529f/2, Length.Unit.kilometres), new Mass(0.0004f, Mass.Unit.EARTH_MASS), new Length(527108, Length.Unit.kilometres), new Angle(90), MoonType.RHEAN, true, new Mass((float)(1*Math.pow(10, 13)),Mass.Unit.KILOGRAM), new Length(7.7f*1529f/2, Length.Unit.kilometres), RingType.NEPTUNIAN);
				planet.placeNewMoon("Titan", new Length(5150f/2, Length.Unit.kilometres), new Mass(0.023f, Mass.Unit.EARTH_MASS), new Length(1221870, Length.Unit.kilometres), new Angle(90), MoonType.TITANEAN, false, null, null, null);
				planet.placeNewMoon("Hyperion", new Length(266f/2, Length.Unit.kilometres), new Mass(0.00000094f, Mass.Unit.EARTH_MASS), new Length(1481009, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Iapetus", new Length(1436f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(3560820, Length.Unit.kilometres), new Angle(90), MoonType.IAPETIAN, false, null, null, null);
				planet.placeNewMoon("Kiviuq", new Length(16f/2, Length.Unit.kilometres), new Mass(3.3f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(11110000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Ijiraq", new Length(12f/2, Length.Unit.kilometres), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(11125000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Phoebe", new Length(212f/2, Length.Unit.kilometres), new Mass(0.00000139f, Mass.Unit.EARTH_MASS), new Length(12955759, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Paaliaq", new Length(22f/2, Length.Unit.kilometres), new Mass(8.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(15198000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Albiorix", new Length(32f/2, Length.Unit.kilometres), new Mass(2.1f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(16182000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Erriapus", new Length(10f/2, Length.Unit.kilometres), new Mass(7.6f*(float)(Math.pow(10,14)), Mass.Unit.KILOGRAM), new Length(17342000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Tarvos", new Length(15f/2, Length.Unit.kilometres), new Mass(2.7f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(17982000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Siarnaq", new Length(40f/2, Length.Unit.kilometres), new Mass(3.9f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(18180000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Ymir", new Length(18f/2, Length.Unit.kilometres), new Mass(4.9f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(23130000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    		planet = star.placeNewPlanet("Uranus", new Length(51118f/2, Length.Unit.kilometres), new Mass(14f, Mass.Unit.EARTH_MASS), new Length(19.19126393f, Length.Unit.astronomicalUnit), new Angle(20), PlanetType.URANIAN, true, new Mass((float)(0.9*Math.pow(10, 16)),Mass.Unit.KILOGRAM), new Length(100000,Length.Unit.kilometres), RingType.URANIAN);
	    		planet.placeNewMoon("Cordelia", new Length(40.2f/2, Length.Unit.kilometres), new Mass(4.4960f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(49751, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Ophelia", new Length(42.8f/2, Length.Unit.kilometres), new Mass(5.3952f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(53763, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Bianca", new Length(51.4f/2, Length.Unit.kilometres), new Mass(9.2917f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(59165, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Cressida", new Length(79.6f/2, Length.Unit.kilometres), new Mass(3.432f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(61766, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Desdemona", new Length(64.0f/2, Length.Unit.kilometres), new Mass(1.7834f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(62658, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Juliet", new Length(93f/2, Length.Unit.kilometres), new Mass(0.000000047f, Mass.Unit.EARTH_MASS), new Length(64358, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Portia", new Length(134f/2, Length.Unit.kilometres), new Mass(0.00000028f, Mass.Unit.EARTH_MASS), new Length(66097, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Rosalind", new Length(72.0f/2, Length.Unit.kilometres), new Mass(2.5477f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(69926, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Cupid", new Length(18f/2, Length.Unit.kilometres), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(74392, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Belinda", new Length(80.6f/2, Length.Unit.kilometres), new Mass(3.5668f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(75255, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Perdita", new Length(30.0f/2, Length.Unit.kilometres), new Mass(1.8f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(76417, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Puck", new Length(162f/2, Length.Unit.kilometres), new Mass(0.00000049f, Mass.Unit.EARTH_MASS), new Length(86004, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Mab", new Length(16f/2, Length.Unit.kilometres), new Mass(4f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(97736, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Miranda", new Length(472f/2, Length.Unit.kilometres), new Mass(0.00001f, Mass.Unit.EARTH_MASS),  new Length(129390, Length.Unit.kilometres), new Angle(90), MoonType.MIRANDAN, false, null, null, null);
				planet.placeNewMoon("Ariel", new Length(1158f/2, Length.Unit.kilometres), new Mass(0.00022f, Mass.Unit.EARTH_MASS), new Length(190900, Length.Unit.kilometres), new Angle(90), MoonType.ARIELIAN, false, null, null, null);
				planet.placeNewMoon("Umbriel", new Length(1169f/2, Length.Unit.kilometres), new Mass(0.0002f, Mass.Unit.EARTH_MASS), new Length(266000, Length.Unit.kilometres), new Angle(90), MoonType.UMBRELIAN, false, null, null, null);
				planet.placeNewMoon("Titania", new Length(1578f/2, Length.Unit.kilometres), new Mass(0.0006f, Mass.Unit.EARTH_MASS), new Length(436300, Length.Unit.kilometres), new Angle(90), MoonType.TITANIAN, false, null, null, null);
				planet.placeNewMoon("Oberon", new Length(1523f/2, Length.Unit.kilometres), new Mass(0.00046f, Mass.Unit.EARTH_MASS), new Length(583519, Length.Unit.kilometres), new Angle(90), MoonType.OBERONIAN, false, null, null, null);
				planet.placeNewMoon("Francisco", new Length(22f/2, Length.Unit.kilometres), new Mass(7.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(4275910, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Caliban", new Length(72f/2, Length.Unit.kilometres), new Mass(7.3f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(7164900, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Stephano", new Length(32f/2, Length.Unit.kilometres), new Mass(6.0f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(7952320, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Trinculo", new Length(18f/2, Length.Unit.kilometres), new Mass(3.9f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(8501260, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Sycorax", new Length(150f / 2, Length.Unit.kilometres), new Mass(0.00000039f, Mass.Unit.EARTH_MASS), new Length(12179000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Margaret", new Length(20f/2, Length.Unit.kilometres), new Mass(5.5f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(14420340, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Prospero", new Length(50f/2, Length.Unit.kilometres), new Mass(8.5f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(16162240, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Setebos", new Length(47f/2, Length.Unit.kilometres), new Mass(7.5f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(17419270, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Ferdinand", new Length(21f/2, Length.Unit.kilometres), new Mass(5.4f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(20507100, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    		planet = star.placeNewPlanet("Neptune", new Length(49528f/2, Length.Unit.kilometres), new Mass(17f, Mass.Unit.EARTH_MASS), new Length(30.06896348f, Length.Unit.astronomicalUnit), new Angle(-30), PlanetType.NEPTUNIAN, true, new Mass((float)(0.8*Math.pow(10, 15)),Mass.Unit.KILOGRAM), new Length(63732,Length.Unit.kilometres), RingType.NEPTUNIAN);
    			planet.placeNewMoon("Naiad", new Length(66f/2, Length.Unit.kilometres), new Mass(1.9f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(48227, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
    			planet.placeNewMoon("Thalassa", new Length(82f/2, Length.Unit.kilometres), new Mass(3.7f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(50075, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);	
    			planet.placeNewMoon("Despina", new Length(180f/2, Length.Unit.kilometres), new Mass(0.00000037f, Mass.Unit.EARTH_MASS), new Length(52526, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Galatea", new Length(176f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS), new Length(61953, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Larissa", new Length(194f / 2, Length.Unit.kilometres), new Mass(0.0000007f, Mass.Unit.EARTH_MASS), new Length(73548, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Polyphemus", new Length(20f/2, Length.Unit.kilometres), new Mass(1.25f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(105283, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Proteus", new Length(420f/2, Length.Unit.kilometres), new Mass(0.0000074f, Mass.Unit.EARTH_MASS), new Length(117647, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Triton", new Length(2707f / 2, Length.Unit.kilometres), new Mass(0.00358f, Mass.Unit.EARTH_MASS), new Length(354759, Length.Unit.kilometres), new Angle(90), MoonType.TRITONIAN, false, null, null, null);
				planet.placeNewMoon("Nereid", new Length(340f / 2, Length.Unit.kilometres), new Mass(0.0000025f, Mass.Unit.EARTH_MASS), new Length(5513787, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Halimede", new Length(62f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(16589670, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Sao", new Length(44f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(22182010, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Laomedeia", new Length(42f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(23464130, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Psamathe", new Length(38f/2, Length.Unit.kilometres), new Mass(1.4987f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(46695000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
				planet.placeNewMoon("Neso", new Length(60f/2, Length.Unit.kilometres), new Mass(2.6485f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM), new Length(49285000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    	planet = star.placeNewPlanet("Pluto", new Length(2310f/2, Length.Unit.kilometres), new Mass(0.0022f, Mass.Unit.EARTH_MASS), new Length(39.482f, Length.Unit.astronomicalUnit), new Angle(-80), PlanetType.DWARFPLANET, false, null, null, null);
	    		planet.placeNewMoon("Charon", new Length(1207f/2, Length.Unit.kilometres), new Mass(0.00025f, Mass.Unit.EARTH_MASS), new Length(17536, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Styx", new Length(25f/2, Length.Unit.kilometres), new Mass(5.25f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM), new Length(42000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Nix", new Length(90f/2, Length.Unit.kilometres), new Mass(0.000000017f, Mass.Unit.EARTH_MASS), new Length(48708, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Kerberos", new Length(40f/2, Length.Unit.kilometres), new Mass(7.8f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM), new Length(59000, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Hydra", new Length(120f/2, Length.Unit.kilometres), new Mass(0.00000017f, Mass.Unit.EARTH_MASS), new Length(64780, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    	planet = star.placeNewPlanet("Haumea", new Length(1600f/2, Length.Unit.kilometres), new Mass(0.0007f, Mass.Unit.EARTH_MASS),  new Length(43.335f, Length.Unit.astronomicalUnit), new Angle(0), PlanetType.DWARFPLANET, false, null, null, null);
		    	planet.placeNewMoon("Namaka", new Length(200f/2, Length.Unit.kilometres), new Mass(0.0000003f, Mass.Unit.EARTH_MASS), new Length(25657, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    		planet.placeNewMoon("Hi'iaka", new Length(380f/2, Length.Unit.kilometres), new Mass(0.000003f, Mass.Unit.EARTH_MASS), new Length(49880, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
	    	star.placeNewPlanet("Makemake", new Length(1500f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS), new Length(45.792f, Length.Unit.astronomicalUnit), new Angle(0), PlanetType.DWARFPLANET, false, null, null, null);
	    	planet = star.placeNewPlanet("Eris", new Length(2326f/2, Length.Unit.kilometres), new Mass(0.0028f, Mass.Unit.EARTH_MASS), new Length(67.668f, Length.Unit.astronomicalUnit), new Angle(0), PlanetType.DWARFPLANET, false, null, null, null);
    			planet.placeNewMoon("Dysnomia", new Length(684f / 2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS), new Length(37350, Length.Unit.kilometres), new Angle(90), MoonType.IRREGULAR, false, null, null, null);
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
     * @param orbitalAngle Desired angle of the star's position on the map of the system
     * @return created Star object
     */
    public Star placeNewStar(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle)
    {
    	OrbitalProperties orbit = new OrbitalProperties(mass, this, orbitalRadius, orbitalAngle);
		BodyProperties body = new BodyProperties(mass, radius, null);
        Star newObject = new Star(name, orbit, body);
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        addMass(mass);
        return newObject;
	}
}
