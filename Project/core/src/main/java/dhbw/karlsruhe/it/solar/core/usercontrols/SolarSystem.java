package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon.MoonType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet.PlanetType;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing.RingType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star.StarType;

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
	protected boolean previewEnabled() {
		return false;
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

    	star = CreateAstronomicalBody.named("Sun").withOrbitalProperties(this).andBodyProperties(new Length(1392684f/2, Length.Unit.kilometres), new Mass(1, Mass.Unit.SOLAR_MASS)).buildAs(StarType.GTypeMainSequence, this);
    		CreateAstronomicalBody.named("Mercury").withOrbitalProperties(star, new Length(0.38709893f, Length.Unit.astronomicalUnit), new Angle(170)).andBodyProperties(new Length(4879.4f/2, Length.Unit.kilometres), new Mass(0.055f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.MERCURIAN, this);
    		CreateAstronomicalBody.named("Venus").withOrbitalProperties(star, new Length(0.72333199f, Length.Unit.astronomicalUnit), new Angle(-45)).andBodyProperties(new Length(12103.6f/2, Length.Unit.kilometres), new Mass(0.815f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.VENUSIAN, this);
    		planet = CreateAstronomicalBody.named("Earth").withOrbitalProperties(star, new Length(1f, Length.Unit.astronomicalUnit), new Angle(-120)).andBodyProperties(new Length(12756.32f/2, Length.Unit.kilometres), new Mass(1f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.TERRAN, this);
    			CreateAstronomicalBody.named("Moon").withOrbitalProperties(planet, new Length(384399, Length.Unit.kilometres), new Angle(-30)).andBodyProperties(new Length(3476f/2, Length.Unit.kilometres), new Mass(0.0123f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.LUNAR, this);
     		planet = CreateAstronomicalBody.named("Mars").withOrbitalProperties(star, new Length(1.52366231f, Length.Unit.astronomicalUnit), new Angle(-140)).andBodyProperties(new Length(6792.4f/2, Length.Unit.kilometres), new Mass(0.107f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.MARTIAN, this);
     			CreateAstronomicalBody.named("Phobos").withOrbitalProperties(planet, new Length(9367, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(23f / 2, Length.Unit.kilometres), new Mass((float) (1.0659 * Math.pow(10, 16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);     			
     			CreateAstronomicalBody.named("Deimos").withOrbitalProperties(planet, new Length(23463, Length.Unit.kilometres), new Angle(-123)).andBodyProperties(new Length(13f/2, Length.Unit.kilometres), new Mass((float) (1.4762 * Math.pow(10, 15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
    		CreateAstronomicalBody.named("Ceres").withOrbitalProperties(star, new Length(2.766f, Length.Unit.astronomicalUnit), new Angle(-170)).andBodyProperties(new Length(975f / 2, Length.Unit.kilometres), new Mass(0.00016f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.DWARFPLANET, this);
    		planet = CreateAstronomicalBody.named("Jupiter").withOrbitalProperties(star, new Length(5.20336301f, Length.Unit.astronomicalUnit), new Angle(120)).andBodyProperties(new Length(142984f/2, Length.Unit.kilometres), new Mass(318f, Mass.Unit.EARTH_MASS)).includingRings(new Mass((float)(0.22*Math.pow(10, 16)),Mass.Unit.KILOGRAM), new Length(226000,Length.Unit.kilometres), RingType.JOVIAN).buildAs(PlanetType.JOVIAN, this);
    			CreateAstronomicalBody.named("Metis").withOrbitalProperties(planet, new Length(127969, Length.Unit.kilometres), new Angle(192)).andBodyProperties(new Length(43f/2, Length.Unit.kilometres), new Mass(1.25f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Adrastea").withOrbitalProperties(planet, new Length(128980, Length.Unit.kilometres), new Angle(23)).andBodyProperties(new Length(16.4f/2, Length.Unit.kilometres), new Mass(6.03f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Amalthea").withOrbitalProperties(planet, new Length(181365, Length.Unit.kilometres), new Angle(-34)).andBodyProperties(new Length(167f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Thebe").withOrbitalProperties(planet, new Length(221900, Length.Unit.kilometres), new Angle(45)).andBodyProperties(new Length(98f/2, Length.Unit.kilometres), new Mass(0.000000064f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Io").withOrbitalProperties(planet, new Length(421600, Length.Unit.kilometres), new Angle(100)).andBodyProperties(new Length(3643f / 2, Length.Unit.kilometres), new Mass(0.015f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IONIAN, this);
    			CreateAstronomicalBody.named("Europa").withOrbitalProperties(planet, new Length(670900, Length.Unit.kilometres), new Angle(115)).andBodyProperties(new Length(3122f / 2, Length.Unit.kilometres), new Mass(0.008f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.EUROPAN, this);
    			CreateAstronomicalBody.named("Ganymede").withOrbitalProperties(planet, new Length(1070400, Length.Unit.kilometres), new Angle(230)).andBodyProperties(new Length(5262f/2, Length.Unit.kilometres), new Mass(0.025f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.GANYMEDIAN, this);
    			CreateAstronomicalBody.named("Callisto").withOrbitalProperties(planet, new Length(1882700, Length.Unit.kilometres), new Angle(5)).andBodyProperties(new Length(4821 / 2, Length.Unit.kilometres), new Mass(0.018f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.CALLISTOAN, this);
    			CreateAstronomicalBody.named("Leda").withOrbitalProperties(planet, new Length(11165000, Length.Unit.kilometres), new Angle(56)).andBodyProperties(new Length(20f/2, Length.Unit.kilometres), new Mass(1.09f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Himalia").withOrbitalProperties(planet, new Length(11460000, Length.Unit.kilometres), new Angle(67)).andBodyProperties(new Length(170f/2, Length.Unit.kilometres), new Mass(0.00000112f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Lysithea").withOrbitalProperties(planet, new Length(11717000, Length.Unit.kilometres), new Angle(78)).andBodyProperties(new Length(36f/2, Length.Unit.kilometres), new Mass(6.3f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Elara").withOrbitalProperties(planet, new Length(11741000, Length.Unit.kilometres), new Angle(89)).andBodyProperties(new Length(86f/2, Length.Unit.kilometres), new Mass(8.66f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Ananke").withOrbitalProperties(planet, new Length(21276000, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(28f/2, Length.Unit.kilometres), new Mass(3.0f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Carme").withOrbitalProperties(planet, new Length(23404000, Length.Unit.kilometres), new Angle(-70)).andBodyProperties(new Length(46f/2, Length.Unit.kilometres), new Mass(1.3f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Pasiphea").withOrbitalProperties(planet, new Length(23624000, Length.Unit.kilometres), new Angle(-90)).andBodyProperties(new Length(56f/2, Length.Unit.kilometres), new Mass(1.92f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
    			CreateAstronomicalBody.named("Sinope").withOrbitalProperties(planet, new Length(23939000, Length.Unit.kilometres), new Angle(-115)).andBodyProperties(new Length(38f/2, Length.Unit.kilometres), new Mass(7.77f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
    		planet = CreateAstronomicalBody.named("Saturn").withOrbitalProperties(star, new Length(9.53707032f, Length.Unit.astronomicalUnit), new Angle(-130)).andBodyProperties(new Length(120536f/2, Length.Unit.kilometres), new Mass(95f, Mass.Unit.EARTH_MASS)).includingRings(new Mass((float)(3*Math.pow(10, 19)),Mass.Unit.KILOGRAM), new Length(140550,Length.Unit.kilometres), RingType.SATURNIAN).buildAs(PlanetType.SATURNIAN, this);
	    		CreateAstronomicalBody.named("Pan").withOrbitalProperties(planet, new Length(133584, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(28.4f/2, Length.Unit.kilometres), new Mass(4.95f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Atlas").withOrbitalProperties(planet, new Length(137670, Length.Unit.kilometres), new Angle(30)).andBodyProperties(new Length(30.6f/2, Length.Unit.kilometres), new Mass(6.6f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Prometheus").withOrbitalProperties(planet, new Length(139400, Length.Unit.kilometres), new Angle(-30)).andBodyProperties(new Length(100f/2, Length.Unit.kilometres), new Mass(0.000000013f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Pandora").withOrbitalProperties(planet, new Length(141700, Length.Unit.kilometres), new Angle(60)).andBodyProperties(new Length(84f/2, Length.Unit.kilometres), new Mass(0.000000012f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Epimetheus").withOrbitalProperties(planet, new Length(151410, Length.Unit.kilometres), new Angle(75)).andBodyProperties(new Length(116f/2, Length.Unit.kilometres), new Mass(0.00000009f, Mass.Unit.EARTH_MASS)).whichIsCoorbital().buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Janus").withOrbitalProperties(planet, new Length(151410, Length.Unit.kilometres), new Angle(105)).andBodyProperties(new Length(179f/2, Length.Unit.kilometres), new Mass(0.00000032f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Mimas").withOrbitalProperties(planet, new Length(185520, Length.Unit.kilometres), new Angle(-76)).andBodyProperties(new Length(396.4f/2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.MIMANTEAN, this);
	    		CreateAstronomicalBody.named("Enceladus").withOrbitalProperties(planet, new Length(237948, Length.Unit.kilometres), new Angle(100)).andBodyProperties(new Length(504.2f/2, Length.Unit.kilometres), new Mass(0.000018f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.ENCELADEAN, this);
	    		CreateAstronomicalBody.named("Tethys").withOrbitalProperties(planet, new Length(294619, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(1066f / 2, Length.Unit.kilometres), new Mass(0.00132f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.TETHYAN, this);
	    		CreateAstronomicalBody.named("Telesto").withOrbitalProperties(planet, new Length(294619, Length.Unit.kilometres), new Angle(150)).andBodyProperties(new Length(24.8f/2, Length.Unit.kilometres), new Mass(4.0464f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichIsCoorbital().buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Calypso").withOrbitalProperties(planet, new Length(294619, Length.Unit.kilometres), new Angle(30)).andBodyProperties(new Length(21.4f/2, Length.Unit.kilometres), new Mass(2.5477f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichIsCoorbital().buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Dione").withOrbitalProperties(planet, new Length(377396, Length.Unit.kilometres), new Angle(-13)).andBodyProperties(new Length(1123.4f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.DIONEAN, this);
	    		CreateAstronomicalBody.named("Helene").withOrbitalProperties(planet, new Length(377396, Length.Unit.kilometres), new Angle(47)).andBodyProperties(new Length(35.2f/2, Length.Unit.kilometres), new Mass(11f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichIsCoorbital().buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Rhea").withOrbitalProperties(planet, new Length(527108, Length.Unit.kilometres), new Angle(170)).andBodyProperties(new Length(1529f/2, Length.Unit.kilometres), new Mass(0.0004f, Mass.Unit.EARTH_MASS)).includingRings(new Mass((float)(1*Math.pow(10, 13)),Mass.Unit.KILOGRAM), new Length(7.7f*1529f/2, Length.Unit.kilometres), RingType.NEPTUNIAN).buildAs(MoonType.RHEAN, this);
	    		CreateAstronomicalBody.named("Titan").withOrbitalProperties(planet, new Length(1221870, Length.Unit.kilometres), new Angle(-130)).andBodyProperties(new Length(5150f/2, Length.Unit.kilometres), new Mass(0.023f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.TITANEAN, this);
	    		CreateAstronomicalBody.named("Hyperion").withOrbitalProperties(planet, new Length(1481009, Length.Unit.kilometres), new Angle(40)).andBodyProperties(new Length(266f/2, Length.Unit.kilometres), new Mass(0.00000094f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Iapetus").withOrbitalProperties(planet, new Length(3560820, Length.Unit.kilometres), new Angle(-160)).andBodyProperties(new Length(1436f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IAPETIAN, this);
	    		CreateAstronomicalBody.named("Kiviuq").withOrbitalProperties(planet, new Length(11110000, Length.Unit.kilometres), new Angle(20)).andBodyProperties(new Length(16f/2, Length.Unit.kilometres), new Mass(3.3f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Ijiraq").withOrbitalProperties(planet, new Length(11125000, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(12f/2, Length.Unit.kilometres), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Phoebe").withOrbitalProperties(planet, new Length(12955759, Length.Unit.kilometres), new Angle(-100)).andBodyProperties(new Length(212f/2, Length.Unit.kilometres), new Mass(0.00000139f, Mass.Unit.EARTH_MASS)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Paaliaq").withOrbitalProperties(planet, new Length(15198000, Length.Unit.kilometres), new Angle(10)).andBodyProperties(new Length(22f/2, Length.Unit.kilometres), new Mass(8.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Albiorix").withOrbitalProperties(planet, new Length(16182000, Length.Unit.kilometres), new Angle(150)).andBodyProperties(new Length(32f/2, Length.Unit.kilometres), new Mass(2.1f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Erriapus").withOrbitalProperties(planet, new Length(17342000, Length.Unit.kilometres), new Angle(170)).andBodyProperties(new Length(10f/2, Length.Unit.kilometres), new Mass(7.6f*(float)(Math.pow(10,14)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Tarvos").withOrbitalProperties(planet, new Length(17982000, Length.Unit.kilometres), new Angle(-25)).andBodyProperties(new Length(15f/2, Length.Unit.kilometres), new Mass(2.7f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Siarnaq").withOrbitalProperties(planet, new Length(18180000, Length.Unit.kilometres), new Angle(60)).andBodyProperties(new Length(40f/2, Length.Unit.kilometres), new Mass(3.9f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
	    		CreateAstronomicalBody.named("Ymir").withOrbitalProperties(planet, new Length(23130000, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(18f/2, Length.Unit.kilometres), new Mass(4.9f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
			planet = CreateAstronomicalBody.named("Uranus").withOrbitalProperties(star, new Length(19.19126393f, Length.Unit.astronomicalUnit), new Angle(20)).andBodyProperties(new Length(51118f/2, Length.Unit.kilometres), new Mass(14f, Mass.Unit.EARTH_MASS)).includingRings(new Mass((float)(0.9*Math.pow(10, 16)),Mass.Unit.KILOGRAM), new Length(100000,Length.Unit.kilometres), RingType.URANIAN).buildAs(PlanetType.URANIAN, this);
				CreateAstronomicalBody.named("Cordelia").withOrbitalProperties(planet, new Length(49751, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(40.2f/2, Length.Unit.kilometres), new Mass(4.4960f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Ophelia").withOrbitalProperties(planet, new Length(53763, Length.Unit.kilometres), new Angle(-145)).andBodyProperties(new Length(42.8f/2, Length.Unit.kilometres), new Mass(5.3952f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Bianca").withOrbitalProperties(planet, new Length(59165, Length.Unit.kilometres), new Angle(-30)).andBodyProperties(new Length(51.4f/2, Length.Unit.kilometres), new Mass(9.2917f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Cressida").withOrbitalProperties(planet, new Length(61766, Length.Unit.kilometres), new Angle(170)).andBodyProperties(new Length(79.6f/2, Length.Unit.kilometres), new Mass(3.432f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Desdemona").withOrbitalProperties(planet, new Length(62658, Length.Unit.kilometres), new Angle(10)).andBodyProperties(new Length(64.0f/2, Length.Unit.kilometres), new Mass(1.7834f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Juliet").withOrbitalProperties(planet, new Length(64358, Length.Unit.kilometres), new Angle(-25)).andBodyProperties(new Length(93f/2, Length.Unit.kilometres), new Mass(0.000000047f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Portia").withOrbitalProperties(planet, new Length(66097, Length.Unit.kilometres), new Angle(130)).andBodyProperties(new Length(134f/2, Length.Unit.kilometres), new Mass(0.00000028f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Rosalind").withOrbitalProperties(planet, new Length(69926, Length.Unit.kilometres), new Angle(-150)).andBodyProperties(new Length(72.0f/2, Length.Unit.kilometres), new Mass(2.5477f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Cupid").withOrbitalProperties(planet, new Length(74392, Length.Unit.kilometres), new Angle(45)).andBodyProperties(new Length(18f/2, Length.Unit.kilometres), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Belinda").withOrbitalProperties(planet, new Length(75255, Length.Unit.kilometres), new Angle(110)).andBodyProperties(new Length(80.6f/2, Length.Unit.kilometres), new Mass(3.5668f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Perdita").withOrbitalProperties(planet, new Length(76417, Length.Unit.kilometres), new Angle(-60)).andBodyProperties(new Length(30.0f/2, Length.Unit.kilometres), new Mass(1.8f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Puck").withOrbitalProperties(planet, new Length(86004, Length.Unit.kilometres), new Angle(-120)).andBodyProperties(new Length(162f/2, Length.Unit.kilometres), new Mass(0.00000049f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Mab").withOrbitalProperties(planet, new Length(97736, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(16f/2, Length.Unit.kilometres), new Mass(4f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Miranda").withOrbitalProperties(planet, new Length(129390, Length.Unit.kilometres), new Angle(110)).andBodyProperties(new Length(472f/2, Length.Unit.kilometres), new Mass(0.00001f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.MIRANDAN, this);
				CreateAstronomicalBody.named("Ariel").withOrbitalProperties(planet, new Length(190900, Length.Unit.kilometres), new Angle(-175)).andBodyProperties(new Length(1158f/2, Length.Unit.kilometres), new Mass(0.00022f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.ARIELIAN, this);
				CreateAstronomicalBody.named("Umbriel").withOrbitalProperties(planet, new Length(266000, Length.Unit.kilometres), new Angle(-30)).andBodyProperties(new Length(1169f/2, Length.Unit.kilometres), new Mass(0.0002f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.UMBRELIAN, this);
				CreateAstronomicalBody.named("Titania").withOrbitalProperties(planet, new Length(436300, Length.Unit.kilometres), new Angle(-80)).andBodyProperties(new Length(1578f/2, Length.Unit.kilometres), new Mass(0.0006f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.TITANIAN, this);
				CreateAstronomicalBody.named("Oberon").withOrbitalProperties(planet, new Length(583519, Length.Unit.kilometres), new Angle(95)).andBodyProperties(new Length(1523f/2, Length.Unit.kilometres), new Mass(0.00046f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.OBERONIAN, this);
				CreateAstronomicalBody.named("Francisco").withOrbitalProperties(planet, new Length(4275910, Length.Unit.kilometres), new Angle(15)).andBodyProperties(new Length(22f/2, Length.Unit.kilometres), new Mass(7.2f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Caliban").withOrbitalProperties(planet, new Length(7164900, Length.Unit.kilometres), new Angle(-70)).andBodyProperties(new Length(72f/2, Length.Unit.kilometres), new Mass(7.3f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Stephano").withOrbitalProperties(planet, new Length(7952320, Length.Unit.kilometres), new Angle(160)).andBodyProperties(new Length(32f/2, Length.Unit.kilometres), new Mass(6.0f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Trinculo").withOrbitalProperties(planet, new Length(8501260, Length.Unit.kilometres), new Angle(140)).andBodyProperties(new Length(18f/2, Length.Unit.kilometres), new Mass(3.9f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Sycorax").withOrbitalProperties(planet, new Length(12179000, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(150f / 2, Length.Unit.kilometres), new Mass(0.00000039f, Mass.Unit.EARTH_MASS)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Margaret").withOrbitalProperties(planet, new Length(14420340, Length.Unit.kilometres), new Angle(-90)).andBodyProperties(new Length(20f/2, Length.Unit.kilometres), new Mass(5.5f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Prospero").withOrbitalProperties(planet, new Length(16162240, Length.Unit.kilometres), new Angle(-110)).andBodyProperties(new Length(50f/2, Length.Unit.kilometres), new Mass(8.5f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Setebos").withOrbitalProperties(planet, new Length(17419270, Length.Unit.kilometres), new Angle(120)).andBodyProperties(new Length(47f/2, Length.Unit.kilometres), new Mass(7.5f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Ferdinand").withOrbitalProperties(planet, new Length(20507100, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(21f/2, Length.Unit.kilometres), new Mass(5.4f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
			planet = CreateAstronomicalBody.named("Neptune").withOrbitalProperties(star, new Length(30.06896348f, Length.Unit.astronomicalUnit), new Angle(-30)).andBodyProperties(new Length(49528f/2, Length.Unit.kilometres), new Mass(17f, Mass.Unit.EARTH_MASS)).includingRings(new Mass((float)(0.8*Math.pow(10, 15)),Mass.Unit.KILOGRAM), new Length(63732,Length.Unit.kilometres), RingType.NEPTUNIAN).buildAs(PlanetType.NEPTUNIAN, this);
				CreateAstronomicalBody.named("Naiad").withOrbitalProperties(planet, new Length(48227, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(66f/2, Length.Unit.kilometres), new Mass(1.9f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Thalassa").withOrbitalProperties(planet, new Length(50075, Length.Unit.kilometres), new Angle(-20)).andBodyProperties(new Length(82f/2, Length.Unit.kilometres), new Mass(3.7f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Despina").withOrbitalProperties(planet, new Length(52526, Length.Unit.kilometres), new Angle(25)).andBodyProperties(new Length(180f/2, Length.Unit.kilometres), new Mass(0.00000037f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Galatea").withOrbitalProperties(planet, new Length(61953, Length.Unit.kilometres), new Angle(60)).andBodyProperties(new Length(176f/2, Length.Unit.kilometres), new Mass(0.00000035f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Larissa").withOrbitalProperties(planet, new Length(73548, Length.Unit.kilometres), new Angle(-150)).andBodyProperties(new Length(194f / 2, Length.Unit.kilometres), new Mass(0.0000007f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Polyphemus").withOrbitalProperties(planet, new Length(105283, Length.Unit.kilometres), new Angle(40)).andBodyProperties(new Length(20f/2, Length.Unit.kilometres), new Mass(1.25f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Proteus").withOrbitalProperties(planet, new Length(117647, Length.Unit.kilometres), new Angle(130)).andBodyProperties(new Length(420f/2, Length.Unit.kilometres), new Mass(0.0000074f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Triton").withOrbitalProperties(planet, new Length(354759, Length.Unit.kilometres), new Angle(-130)).andBodyProperties(new Length(2707f / 2, Length.Unit.kilometres), new Mass(0.00358f, Mass.Unit.EARTH_MASS)).whichMovesRetrograde().buildAs(MoonType.TRITONIAN, this);
				CreateAstronomicalBody.named("Nereid").withOrbitalProperties(planet, new Length(5513787, Length.Unit.kilometres), new Angle(70)).andBodyProperties(new Length(340f / 2, Length.Unit.kilometres), new Mass(0.0000025f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Halimede").withOrbitalProperties(planet, new Length(16589670, Length.Unit.kilometres), new Angle(99)).andBodyProperties(new Length(62f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Sao").withOrbitalProperties(planet, new Length(22182010, Length.Unit.kilometres), new Angle(-45)).andBodyProperties(new Length(44f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Laomedeia").withOrbitalProperties(planet, new Length(23464130, Length.Unit.kilometres), new Angle(-90)).andBodyProperties(new Length(42f/2, Length.Unit.kilometres), new Mass(8.9920f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Psamathe").withOrbitalProperties(planet, new Length(46695000, Length.Unit.kilometres), new Angle(145)).andBodyProperties(new Length(38f/2, Length.Unit.kilometres), new Mass(1.4987f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Neso").withOrbitalProperties(planet, new Length(49285000, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(60f/2, Length.Unit.kilometres), new Mass(2.6485f*(float)(Math.pow(10,17)), Mass.Unit.KILOGRAM)).whichMovesRetrograde().buildAs(MoonType.IRREGULAR, this);
			planet = CreateAstronomicalBody.named("Pluto").withOrbitalProperties(star, new Length(39.482f, Length.Unit.astronomicalUnit), new Angle(-80)).andBodyProperties(new Length(2310f/2, Length.Unit.kilometres), new Mass(0.0022f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.DWARFPLANET, this);
				CreateAstronomicalBody.named("Charon").withOrbitalProperties(planet, new Length(17536, Length.Unit.kilometres), new Angle(30)).andBodyProperties(new Length(1207f/2, Length.Unit.kilometres), new Mass(0.00025f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Styx").withOrbitalProperties(planet, new Length(42000, Length.Unit.kilometres), new Angle(120)).andBodyProperties(new Length(25f/2, Length.Unit.kilometres), new Mass(5.25f*(float)(Math.pow(10,15)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Nix").withOrbitalProperties(planet, new Length(48708, Length.Unit.kilometres), new Angle(-90)).andBodyProperties(new Length(90f/2, Length.Unit.kilometres), new Mass(0.000000017f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Kerberos").withOrbitalProperties(planet, new Length(59000, Length.Unit.kilometres), new Angle(-150)).andBodyProperties(new Length(40f/2, Length.Unit.kilometres), new Mass(7.8f*(float)(Math.pow(10,16)), Mass.Unit.KILOGRAM)).buildAs(MoonType.IRREGULAR, this);
				CreateAstronomicalBody.named("Hydra").withOrbitalProperties(planet, new Length(64780, Length.Unit.kilometres), new Angle(20)).andBodyProperties(new Length(120f/2, Length.Unit.kilometres), new Mass(0.00000017f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    	planet = CreateAstronomicalBody.named("Haumea").withOrbitalProperties(star, new Length(43.335f, Length.Unit.astronomicalUnit), new Angle(-115)).andBodyProperties(new Length(1600f/2, Length.Unit.kilometres), new Mass(0.0007f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.DWARFPLANET, this);
		    	CreateAstronomicalBody.named("Namaka").withOrbitalProperties(planet, new Length(25657, Length.Unit.kilometres), new Angle(90)).andBodyProperties(new Length(200f/2, Length.Unit.kilometres), new Mass(0.0000003f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
		    	CreateAstronomicalBody.named("Hi'iaka").withOrbitalProperties(planet, new Length(49880, Length.Unit.kilometres), new Angle(-20)).andBodyProperties(new Length(380f/2, Length.Unit.kilometres), new Mass(0.000003f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
	    	CreateAstronomicalBody.named("Makemake").withOrbitalProperties(star, new Length(45.792f, Length.Unit.astronomicalUnit), new Angle(-125)).andBodyProperties(new Length(1500f/2, Length.Unit.kilometres), new Mass(0.0003f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.DWARFPLANET, this);
	    	planet = CreateAstronomicalBody.named("Eris").withOrbitalProperties(star, new Length(67.668f, Length.Unit.astronomicalUnit), new Angle(80)).andBodyProperties(new Length(2326f/2, Length.Unit.kilometres), new Mass(0.0028f, Mass.Unit.EARTH_MASS)).buildAs(PlanetType.DWARFPLANET, this);
	    		CreateAstronomicalBody.named("Dysnomia").withOrbitalProperties(planet, new Length(37350, Length.Unit.kilometres), new Angle(0)).andBodyProperties(new Length(684f / 2, Length.Unit.kilometres), new Mass(0.000006f, Mass.Unit.EARTH_MASS)).buildAs(MoonType.IRREGULAR, this);
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
}
