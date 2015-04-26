package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing.RingType;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere.BiosphereType;
import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;
import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas.GasType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * @author Andi
 *
 */
public class SolarSystem extends AstronomicalBody {
    
    public SolarSystem(String name)   {
        super(name, generateSolarSystemOrbitalInformation(),  generateSolarSystemBodyInformation(), ConfigurationConstants.SCALE_FACTOR_STAR, "GTypeMainSequence");
        setPosition(0, 0);
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

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        super.drawLines(libGDXShapeRenderer,solarShapeRenderer);
        diplaySystemCenter(libGDXShapeRenderer);
    }
    
    /**
     * Hard-coded values for the solar system. Would need to be expanded for custom-generated ones.
     * @return
     */
    private static OrbitalProperties generateSolarSystemOrbitalInformation() {
        return new OrbitalProperties(new Time(2.25f*(float)Math.pow(10,8), TimeUnit.YEARS), new Length(27200f, Length.DistanceUnit.LIGHTYEAR));
    }
    
    /**
     * 
     * @return
     */
    private static BodyProperties generateSolarSystemBodyInformation() {
        return new BodyProperties(new Mass(1, Mass.MassUnit.KILOGRAM), new Length(1, Length.DistanceUnit.KILOMETERS), new Albedo(1f));
    }

    private void diplaySystemCenter(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(getX(), getY(), 10);
    }
    
    /**
     * Old method retained for code maintenance purposes. Recreates the entire solar system body-by-body, line-for-line without the use of a save file or the SavegameManager.
     */
    public void createSolarSystem()    {
        //TODO: Remove old method once no longer needed.
        Star star;
        Planet planet;
        Moon moon;

        star = CreateAnAstronomicalBody.named("Sun").whichIsStationaryAt(this).andHasTheFollowingBodyProperties(new Length(1392684f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1, Mass.MassUnit.SOLAR_MASS), new Albedo(1f)).withAGasGiantAtmosphereOf(createPhotosphereOfSun()).buildAs(new StarType(StarType.MorganKeenanSpectralType.G, StarType.SpectralTypeSubdivision.TWO, StarType.LuminosityClass.MAIN_SEQUENCE_STAR), this);
            CreateAnAstronomicalBody.named("Mercury").whichHasTheFollowingOrbitalProperties(star, new Length(0.38709893f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(170)).andHasTheFollowingBodyProperties(new Length(4879.4f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.055f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.068f)).whichIsTidallyLockedToItsPrimary().withAnAtmosphereOf(new Pressure((float)Math.pow(10,-14), PressureUnit.BAR), createAtmosphereOfMercury()).buildAs(new PlanetType(PlanetType.TypeOfPlanet.MERCURIAN), this);
            CreateAnAstronomicalBody.named("Venus").whichHasTheFollowingOrbitalProperties(star, new Length(0.72333199f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-45)).andHasTheFollowingBodyProperties(new Length(12103.6f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.815f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.90f)).withAnAtmosphereOf(new Pressure(92f,PressureUnit.BAR), createAtmosphereOfVenus()).buildAs(new PlanetType(PlanetType.TypeOfPlanet.VENUSIAN), this);
            planet = CreateAnAstronomicalBody.named("Earth").whichHasTheFollowingOrbitalProperties(star, new Length(1f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-120)).andHasTheFollowingBodyProperties(new Length(12756.32f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.306f)).withOceanCoverAndIceCapOf(0.608f, 0.1f).withAnAtmosphereOf(new Pressure(101.325f,PressureUnit.KILOPASCAL), createAtmosphereOfEarth()).addBiosphere(BiosphereType.TERRAN, 0.9f).buildAs(new PlanetType(PlanetType.TypeOfPlanet.TERRAN), this);
                CreateAnAstronomicalBody.named("Moon").whichHasTheFollowingOrbitalProperties(planet, new Length(384399, Length.DistanceUnit.KILOMETERS), new Angle(-30)).andHasTheFollowingBodyProperties(new Length(3476f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0123f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.136f)).whichIsTidallyLockedToItsPrimary().buildAs(new MoonType(MoonType.TypeOfMoon.LUNAR), this);
             planet = CreateAnAstronomicalBody.named("Mars").whichHasTheFollowingOrbitalProperties(star, new Length(1.52366231f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-140)).andHasTheFollowingBodyProperties(new Length(6792.4f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.107f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.25f)).withOceanCoverAndIceCapOf(0.0f, 0.10f).withAnAtmosphereOf(new Pressure(0.636f,PressureUnit.KILOPASCAL), createAtmosphereOfMars()).buildAs(new PlanetType(PlanetType.TypeOfPlanet.MARTIAN), this);
                 CreateAnAstronomicalBody.named("Phobos").whichHasTheFollowingOrbitalProperties(planet, new Length(9367, Length.DistanceUnit.KILOMETERS), new Angle(0)).andHasTheFollowingBodyProperties(new Length(23f / 2, Length.DistanceUnit.KILOMETERS), new Mass((float) (1.0659 * Math.pow(10, 16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.071f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);                 
                 CreateAnAstronomicalBody.named("Deimos").whichHasTheFollowingOrbitalProperties(planet, new Length(23463, Length.DistanceUnit.KILOMETERS), new Angle(-123)).andHasTheFollowingBodyProperties(new Length(13f / 2, Length.DistanceUnit.KILOMETERS), new Mass((float) (1.4762 * Math.pow(10, 15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.068f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            CreateAnAstronomicalBody.named("Ceres").whichHasTheFollowingOrbitalProperties(star, new Length(2.766f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-170)).andHasTheFollowingBodyProperties(new Length(975f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00016f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.090f)).whichHasASubsurfaceOcean().buildAs(new PlanetType(PlanetType.TypeOfPlanet.DWARFPLANET), this);
            planet = CreateAnAstronomicalBody.named("Jupiter").whichHasTheFollowingOrbitalProperties(star, new Length(5.20336301f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(120)).andHasTheFollowingBodyProperties(new Length(142984f / 2, Length.DistanceUnit.KILOMETERS), new Mass(317.8f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.343f)).withAGasGiantAtmosphereOf(createAtmosphereOfJupiter()).includingRings(new Mass((float)(0.22*Math.pow(10, 16)),Mass.MassUnit.KILOGRAM), new Length(92000, Length.DistanceUnit.KILOMETERS), new Length(226000,Length.DistanceUnit.KILOMETERS), RingType.JOVIAN).buildAs(new PlanetType(PlanetType.TypeOfPlanet.JOVIAN), this);
                CreateAnAstronomicalBody.named("Metis").whichHasTheFollowingOrbitalProperties(planet, new Length(127969, Length.DistanceUnit.KILOMETERS), new Angle(192)).andHasTheFollowingBodyProperties(new Length(43f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.25f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.061f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Adrastea").whichHasTheFollowingOrbitalProperties(planet, new Length(128980, Length.DistanceUnit.KILOMETERS), new Angle(23)).andHasTheFollowingBodyProperties(new Length(16.4f / 2, Length.DistanceUnit.KILOMETERS), new Mass(6.03f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.10f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Amalthea").whichHasTheFollowingOrbitalProperties(planet, new Length(181365, Length.DistanceUnit.KILOMETERS), new Angle(-34)).andHasTheFollowingBodyProperties(new Length(167f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000035f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.090f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Thebe").whichHasTheFollowingOrbitalProperties(planet, new Length(221900, Length.DistanceUnit.KILOMETERS), new Angle(45)).andHasTheFollowingBodyProperties(new Length(98f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000000064f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.047f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Io").whichHasTheFollowingOrbitalProperties(planet, new Length(421600, Length.DistanceUnit.KILOMETERS), new Angle(100)).andHasTheFollowingBodyProperties(new Length(3643f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.015f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.63f)).withAnAtmosphereOf(new Pressure(3*(float)Math.pow(10,-4), PressureUnit.PASCAL), createAtmosphereOfIo()).buildAs(new MoonType(MoonType.TypeOfMoon.IONIAN), this);
                CreateAnAstronomicalBody.named("Europa").whichHasTheFollowingOrbitalProperties(planet, new Length(670900, Length.DistanceUnit.KILOMETERS), new Angle(115)).andHasTheFollowingBodyProperties(new Length(3122f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.008f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.67f)).withAnAtmosphereOf(new Pressure((float)Math.pow(10, -12), PressureUnit.BAR), createAtmosphereOfEuropa()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.EUROPAN), this);
                CreateAnAstronomicalBody.named("Ganymede").whichHasTheFollowingOrbitalProperties(planet, new Length(1070400, Length.DistanceUnit.KILOMETERS), new Angle(230)).andHasTheFollowingBodyProperties(new Length(5262f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.025f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.43f)).withAnAtmosphereOf(new Pressure(2.5f*(float)Math.pow(10,-6), PressureUnit.PASCAL), createAtmosphereOfGanymede()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.GANYMEDIAN), this);
                CreateAnAstronomicalBody.named("Callisto").whichHasTheFollowingOrbitalProperties(planet, new Length(1882700, Length.DistanceUnit.KILOMETERS), new Angle(5)).andHasTheFollowingBodyProperties(new Length(4821 / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.018f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.22f)).withAnAtmosphereOf(new Pressure(7.5f*(float)Math.pow(10, -12), PressureUnit.BAR), createAtmosphereOfCallisto()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.CALLISTOAN), this);
                CreateAnAstronomicalBody.named("Leda").whichHasTheFollowingOrbitalProperties(planet, new Length(11165000, Length.DistanceUnit.KILOMETERS), new Angle(56)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(20f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.09f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Himalia").whichHasTheFollowingOrbitalProperties(planet, new Length(11460000, Length.DistanceUnit.KILOMETERS), new Angle(67)).andHasTheFollowingBodyProperties(new Length(170f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000112f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Lysithea").whichHasTheFollowingOrbitalProperties(planet, new Length(11717000, Length.DistanceUnit.KILOMETERS), new Angle(78)).andHasTheFollowingBodyProperties(new Length(36f / 2, Length.DistanceUnit.KILOMETERS), new Mass(6.3f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Elara").whichHasTheFollowingOrbitalProperties(planet, new Length(11741000, Length.DistanceUnit.KILOMETERS), new Angle(89)).andHasTheFollowingBodyProperties(new Length(86f / 2, Length.DistanceUnit.KILOMETERS), new Mass(8.66f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Ananke").whichHasTheFollowingOrbitalProperties(planet, new Length(21276000, Length.DistanceUnit.KILOMETERS), new Angle(90)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(28f / 2, Length.DistanceUnit.KILOMETERS), new Mass(3.0f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Carme").whichHasTheFollowingOrbitalProperties(planet, new Length(23404000, Length.DistanceUnit.KILOMETERS), new Angle(-70)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(46f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.3f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Pasiphae").whichHasTheFollowingOrbitalProperties(planet, new Length(23624000, Length.DistanceUnit.KILOMETERS), new Angle(-90)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(56f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.92f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.10f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Sinope").whichHasTheFollowingOrbitalProperties(planet, new Length(23939000, Length.DistanceUnit.KILOMETERS), new Angle(-115)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(38f / 2, Length.DistanceUnit.KILOMETERS), new Mass(7.77f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            planet = CreateAnAstronomicalBody.named("Saturn").whichHasTheFollowingOrbitalProperties(star, new Length(9.53707032f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-130)).andHasTheFollowingBodyProperties(new Length(120536f / 2, Length.DistanceUnit.KILOMETERS), new Mass(95.152f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.342f)).withAGasGiantAtmosphereOf(createAtmosphereOfSaturn()).includingRings(new Mass((float)(3*Math.pow(10, 19)),Mass.MassUnit.KILOGRAM), new Length(66900, Length.DistanceUnit.KILOMETERS),  new Length(140550,Length.DistanceUnit.KILOMETERS), RingType.SATURNIAN).buildAs(new PlanetType(PlanetType.TypeOfPlanet.SATURNIAN), this);
                CreateAnAstronomicalBody.named("Pan").whichHasTheFollowingOrbitalProperties(planet, new Length(133584, Length.DistanceUnit.KILOMETERS), new Angle(0)).andHasTheFollowingBodyProperties(new Length(28.4f / 2, Length.DistanceUnit.KILOMETERS), new Mass(4.95f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.5f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Atlas").whichHasTheFollowingOrbitalProperties(planet, new Length(137670, Length.DistanceUnit.KILOMETERS), new Angle(30)).andHasTheFollowingBodyProperties(new Length(30.6f / 2, Length.DistanceUnit.KILOMETERS), new Mass(6.6f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.4f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Prometheus").whichHasTheFollowingOrbitalProperties(planet, new Length(139400, Length.DistanceUnit.KILOMETERS), new Angle(-30)).andHasTheFollowingBodyProperties(new Length(100f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000000013f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.6f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Pandora").whichHasTheFollowingOrbitalProperties(planet, new Length(141700, Length.DistanceUnit.KILOMETERS), new Angle(60)).andHasTheFollowingBodyProperties(new Length(84f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000000012f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.6f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                moon = CreateAnAstronomicalBody.named("Janus").whichHasTheFollowingOrbitalProperties(planet, new Length(151410, Length.DistanceUnit.KILOMETERS), new Angle(105)).andHasTheFollowingBodyProperties(new Length(179f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000032f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.71f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                    CreateAnAstronomicalBody.named("Epimetheus").whichIsCoorbitalWith(moon, new Angle(-60,Angle.AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(116f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000009f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.73f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);           
                CreateAnAstronomicalBody.named("Mimas").whichHasTheFollowingOrbitalProperties(planet, new Length(185520, Length.DistanceUnit.KILOMETERS), new Angle(-76)).andHasTheFollowingBodyProperties(new Length(396.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.000006f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.962f)).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.MIMANTEAN), this);
                CreateAnAstronomicalBody.named("Enceladus").whichHasTheFollowingOrbitalProperties(planet, new Length(237948, Length.DistanceUnit.KILOMETERS), new Angle(100)).andHasTheFollowingBodyProperties(new Length(504.2f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.000018f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.99f)).withAnAtmosphereOf(new Pressure((float)Math.pow(10,-12), PressureUnit.BAR), createAtmosphereOfEnceladus()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.ENCELADEAN), this);
                moon = CreateAnAstronomicalBody.named("Tethys").whichHasTheFollowingOrbitalProperties(planet, new Length(294619, Length.DistanceUnit.KILOMETERS), new Angle(90)).andHasTheFollowingBodyProperties(new Length(1066f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00132f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.80f)).buildAs(new MoonType(MoonType.TypeOfMoon.TETHYAN), this);
                    CreateAnAstronomicalBody.named("Telesto").whichIsCoorbitalWith(moon, new Angle(60,Angle.AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(24.8f/2, Length.DistanceUnit.KILOMETERS), new Mass(4.0464f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.80f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                    CreateAnAstronomicalBody.named("Calypso").whichIsCoorbitalWith(moon, new Angle(-60,Angle.AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(21.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(2.5477f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.99f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                moon = CreateAnAstronomicalBody.named("Dione").whichHasTheFollowingOrbitalProperties(planet, new Length(377396, Length.DistanceUnit.KILOMETERS), new Angle(-13)).andHasTheFollowingBodyProperties(new Length(1123.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.0003f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.55f)).buildAs(new MoonType(MoonType.TypeOfMoon.DIONEAN), this);
                    CreateAnAstronomicalBody.named("Helene").whichIsCoorbitalWith(moon, new Angle(60,Angle.AngularUnit.DEGREE)).andHasTheFollowingBodyProperties(new Length(35.2f/2, Length.DistanceUnit.KILOMETERS), new Mass(11f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.6f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Rhea").whichHasTheFollowingOrbitalProperties(planet, new Length(527108, Length.DistanceUnit.KILOMETERS), new Angle(170)).andHasTheFollowingBodyProperties(new Length(1529f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.0004f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.65f)).includingRings(new Mass((float)(1*Math.pow(10, 13)),Mass.MassUnit.KILOGRAM), new Length(1615, Length.DistanceUnit.KILOMETERS), new Length(7.7f*1529f/2, Length.DistanceUnit.KILOMETERS), RingType.NEPTUNIAN).buildAs(new MoonType(MoonType.TypeOfMoon.RHEAN), this);
                CreateAnAstronomicalBody.named("Titan").whichHasTheFollowingOrbitalProperties(planet, new Length(1221870, Length.DistanceUnit.KILOMETERS), new Angle(-130)).andHasTheFollowingBodyProperties(new Length(5150f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.023f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.22f)).withAnAtmosphereOf(new Pressure(146.7f,PressureUnit.KILOPASCAL), createAtmosphereOfTitan()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.TITANEAN), this);
                CreateAnAstronomicalBody.named("Hyperion").whichHasTheFollowingOrbitalProperties(planet, new Length(1481009, Length.DistanceUnit.KILOMETERS), new Angle(40)).andHasTheFollowingBodyProperties(new Length(266f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000094f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.25f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Iapetus").whichHasTheFollowingOrbitalProperties(planet, new Length(3560820, Length.DistanceUnit.KILOMETERS), new Angle(-160)).andHasTheFollowingBodyProperties(new Length(1436f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.0003f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.23f)).buildAs(new MoonType(MoonType.TypeOfMoon.IAPETIAN), this);
                CreateAnAstronomicalBody.named("Kiviuq").whichHasTheFollowingOrbitalProperties(planet, new Length(11110000, Length.DistanceUnit.KILOMETERS), new Angle(20)).andHasTheFollowingBodyProperties(new Length(16f/2, Length.DistanceUnit.KILOMETERS), new Mass(3.3f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Ijiraq").whichHasTheFollowingOrbitalProperties(planet, new Length(11125000, Length.DistanceUnit.KILOMETERS), new Angle(90)).andHasTheFollowingBodyProperties(new Length(12f/2, Length.DistanceUnit.KILOMETERS), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Phoebe").whichHasTheFollowingOrbitalProperties(planet, new Length(12955759, Length.DistanceUnit.KILOMETERS), new Angle(-100)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(212f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000139f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.081f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Paaliaq").whichHasTheFollowingOrbitalProperties(planet, new Length(15198000, Length.DistanceUnit.KILOMETERS), new Angle(10)).andHasTheFollowingBodyProperties(new Length(22f/2, Length.DistanceUnit.KILOMETERS), new Mass(8.2f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Albiorix").whichHasTheFollowingOrbitalProperties(planet, new Length(16182000, Length.DistanceUnit.KILOMETERS), new Angle(150)).andHasTheFollowingBodyProperties(new Length(32f/2, Length.DistanceUnit.KILOMETERS), new Mass(2.1f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Erriapus").whichHasTheFollowingOrbitalProperties(planet, new Length(17342000, Length.DistanceUnit.KILOMETERS), new Angle(170)).andHasTheFollowingBodyProperties(new Length(10f/2, Length.DistanceUnit.KILOMETERS), new Mass(7.6f*(float)(Math.pow(10,14)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Tarvos").whichHasTheFollowingOrbitalProperties(planet, new Length(17982000, Length.DistanceUnit.KILOMETERS), new Angle(-25)).andHasTheFollowingBodyProperties(new Length(15f/2, Length.DistanceUnit.KILOMETERS), new Mass(2.7f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Siarnaq").whichHasTheFollowingOrbitalProperties(planet, new Length(18180000, Length.DistanceUnit.KILOMETERS), new Angle(60)).andHasTheFollowingBodyProperties(new Length(40f/2, Length.DistanceUnit.KILOMETERS), new Mass(3.9f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Ymir").whichHasTheFollowingOrbitalProperties(planet, new Length(23130000, Length.DistanceUnit.KILOMETERS), new Angle(90)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(18f/2, Length.DistanceUnit.KILOMETERS), new Mass(4.9f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.06f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            planet = CreateAnAstronomicalBody.named("Uranus").whichHasTheFollowingOrbitalProperties(star, new Length(19.19126393f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(20)).andHasTheFollowingBodyProperties(new Length(51118f / 2, Length.DistanceUnit.KILOMETERS), new Mass(14.536f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.30f)).withAGasGiantAtmosphereOf(createAtmosphereOfUranus()).includingRings(new Mass((float) (0.9 * Math.pow(10, 16)), Mass.MassUnit.KILOGRAM), new Length(26840, Length.DistanceUnit.KILOMETERS), new Length(100000, Length.DistanceUnit.KILOMETERS), RingType.URANIAN).buildAs(new PlanetType(PlanetType.TypeOfPlanet.URANIAN), this);
                CreateAnAstronomicalBody.named("Cordelia").whichHasTheFollowingOrbitalProperties(planet, new Length(49751, Length.DistanceUnit.KILOMETERS), new Angle(90)).andHasTheFollowingBodyProperties(new Length(40.2f/2, Length.DistanceUnit.KILOMETERS), new Mass(4.4960f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Ophelia").whichHasTheFollowingOrbitalProperties(planet, new Length(53763, Length.DistanceUnit.KILOMETERS), new Angle(-145)).andHasTheFollowingBodyProperties(new Length(42.8f/2, Length.DistanceUnit.KILOMETERS), new Mass(5.3952f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Bianca").whichHasTheFollowingOrbitalProperties(planet, new Length(59165, Length.DistanceUnit.KILOMETERS), new Angle(-30)).andHasTheFollowingBodyProperties(new Length(51.4f/2, Length.DistanceUnit.KILOMETERS), new Mass(9.2917f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Cressida").whichHasTheFollowingOrbitalProperties(planet, new Length(61766, Length.DistanceUnit.KILOMETERS), new Angle(170)).andHasTheFollowingBodyProperties(new Length(79.6f/2, Length.DistanceUnit.KILOMETERS), new Mass(3.432f*(float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Desdemona").whichHasTheFollowingOrbitalProperties(planet, new Length(62658, Length.DistanceUnit.KILOMETERS), new Angle(10)).andHasTheFollowingBodyProperties(new Length(64.0f/2, Length.DistanceUnit.KILOMETERS), new Mass(1.7834f*(float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Juliet").whichHasTheFollowingOrbitalProperties(planet, new Length(64358, Length.DistanceUnit.KILOMETERS), new Angle(-25)).andHasTheFollowingBodyProperties(new Length(93f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.000000047f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Portia").whichHasTheFollowingOrbitalProperties(planet, new Length(66097, Length.DistanceUnit.KILOMETERS), new Angle(130)).andHasTheFollowingBodyProperties(new Length(134f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000028f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Rosalind").whichHasTheFollowingOrbitalProperties(planet, new Length(69926, Length.DistanceUnit.KILOMETERS), new Angle(-150)).andHasTheFollowingBodyProperties(new Length(72.0f/2, Length.DistanceUnit.KILOMETERS), new Mass(2.5477f*(float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.07f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Cupid").whichHasTheFollowingOrbitalProperties(planet, new Length(74392, Length.DistanceUnit.KILOMETERS), new Angle(45)).andHasTheFollowingBodyProperties(new Length(18f/2, Length.DistanceUnit.KILOMETERS), new Mass(1.2f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.07f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Belinda").whichHasTheFollowingOrbitalProperties(planet, new Length(75255, Length.DistanceUnit.KILOMETERS), new Angle(110)).andHasTheFollowingBodyProperties(new Length(80.6f/2, Length.DistanceUnit.KILOMETERS), new Mass(3.5668f*(float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Perdita").whichHasTheFollowingOrbitalProperties(planet, new Length(76417, Length.DistanceUnit.KILOMETERS), new Angle(-60)).andHasTheFollowingBodyProperties(new Length(30.0f/2, Length.DistanceUnit.KILOMETERS), new Mass(1.8f*(float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.08f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Puck").whichHasTheFollowingOrbitalProperties(planet, new Length(86004, Length.DistanceUnit.KILOMETERS), new Angle(-120)).andHasTheFollowingBodyProperties(new Length(162f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000049f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.11f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Mab").whichHasTheFollowingOrbitalProperties(planet, new Length(97736, Length.DistanceUnit.KILOMETERS), new Angle(0)).andHasTheFollowingBodyProperties(new Length(16f/2, Length.DistanceUnit.KILOMETERS), new Mass(4f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.103f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Miranda").whichHasTheFollowingOrbitalProperties(planet, new Length(129390, Length.DistanceUnit.KILOMETERS), new Angle(110)).andHasTheFollowingBodyProperties(new Length(472f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00001f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.32f)).buildAs(new MoonType(MoonType.TypeOfMoon.MIRANDAN), this);
                CreateAnAstronomicalBody.named("Ariel").whichHasTheFollowingOrbitalProperties(planet, new Length(190900, Length.DistanceUnit.KILOMETERS), new Angle(-175)).andHasTheFollowingBodyProperties(new Length(1158f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00022f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.39f)).buildAs(new MoonType(MoonType.TypeOfMoon.ARIELIAN), this);
                CreateAnAstronomicalBody.named("Umbriel").whichHasTheFollowingOrbitalProperties(planet, new Length(266000, Length.DistanceUnit.KILOMETERS), new Angle(-30)).andHasTheFollowingBodyProperties(new Length(1169f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.0002f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.UMBRELIAN), this);
                CreateAnAstronomicalBody.named("Titania").whichHasTheFollowingOrbitalProperties(planet, new Length(436300, Length.DistanceUnit.KILOMETERS), new Angle(-80)).andHasTheFollowingBodyProperties(new Length(1578f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.0006f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.27f)).buildAs(new MoonType(MoonType.TypeOfMoon.TITANIAN), this);
                CreateAnAstronomicalBody.named("Oberon").whichHasTheFollowingOrbitalProperties(planet, new Length(583519, Length.DistanceUnit.KILOMETERS), new Angle(95)).andHasTheFollowingBodyProperties(new Length(1523f/2, Length.DistanceUnit.KILOMETERS), new Mass(0.00046f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.24f)).buildAs(new MoonType(MoonType.TypeOfMoon.OBERONIAN), this);
                CreateAnAstronomicalBody.named("Francisco").whichHasTheFollowingOrbitalProperties(planet, new Length(4275910, Length.DistanceUnit.KILOMETERS), new Angle(15)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(22f/2, Length.DistanceUnit.KILOMETERS), new Mass(7.2f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Caliban").whichHasTheFollowingOrbitalProperties(planet, new Length(7164900, Length.DistanceUnit.KILOMETERS), new Angle(-70)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(72f/2, Length.DistanceUnit.KILOMETERS), new Mass(7.3f*(float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Stephano").whichHasTheFollowingOrbitalProperties(planet, new Length(7952320, Length.DistanceUnit.KILOMETERS), new Angle(160)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(32f/2, Length.DistanceUnit.KILOMETERS), new Mass(6.0f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Trinculo").whichHasTheFollowingOrbitalProperties(planet, new Length(8501260, Length.DistanceUnit.KILOMETERS), new Angle(140)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(18f/2, Length.DistanceUnit.KILOMETERS), new Mass(3.9f*(float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Sycorax").whichHasTheFollowingOrbitalProperties(planet, new Length(12179000, Length.DistanceUnit.KILOMETERS), new Angle(0)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(150f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000039f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Margaret").whichHasTheFollowingOrbitalProperties(planet, new Length(14420340, Length.DistanceUnit.KILOMETERS), new Angle(-90)).andHasTheFollowingBodyProperties(new Length(20f / 2, Length.DistanceUnit.KILOMETERS), new Mass(5.5f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Prospero").whichHasTheFollowingOrbitalProperties(planet, new Length(16162240, Length.DistanceUnit.KILOMETERS), new Angle(-110)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(50f / 2, Length.DistanceUnit.KILOMETERS), new Mass(8.5f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Setebos").whichHasTheFollowingOrbitalProperties(planet, new Length(17419270, Length.DistanceUnit.KILOMETERS), new Angle(120)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(47f / 2, Length.DistanceUnit.KILOMETERS), new Mass(7.5f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Ferdinand").whichHasTheFollowingOrbitalProperties(planet, new Length(20507100, Length.DistanceUnit.KILOMETERS), new Angle(90)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(21f / 2, Length.DistanceUnit.KILOMETERS), new Mass(5.4f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            planet = CreateAnAstronomicalBody.named("Neptune").whichHasTheFollowingOrbitalProperties(star, new Length(30.06896348f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-30)).andHasTheFollowingBodyProperties(new Length(49528f / 2, Length.DistanceUnit.KILOMETERS), new Mass(17.147f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.29f)).withAGasGiantAtmosphereOf(createAtmosphereOfNeptune()).includingRings(new Mass((float)(0.8*Math.pow(10, 15)),Mass.MassUnit.KILOGRAM), new Length(40900, Length.DistanceUnit.KILOMETERS), new Length(63732,Length.DistanceUnit.KILOMETERS), RingType.NEPTUNIAN).buildAs(new PlanetType(PlanetType.TypeOfPlanet.NEPTUNIAN), this);
                CreateAnAstronomicalBody.named("Naiad").whichHasTheFollowingOrbitalProperties(planet, new Length(48227, Length.DistanceUnit.KILOMETERS), new Angle(90)).andHasTheFollowingBodyProperties(new Length(66f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.9f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.072f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Thalassa").whichHasTheFollowingOrbitalProperties(planet, new Length(50075, Length.DistanceUnit.KILOMETERS), new Angle(-20)).andHasTheFollowingBodyProperties(new Length(82f / 2, Length.DistanceUnit.KILOMETERS), new Mass(3.7f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.091f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Despina").whichHasTheFollowingOrbitalProperties(planet, new Length(52526, Length.DistanceUnit.KILOMETERS), new Angle(25)).andHasTheFollowingBodyProperties(new Length(180f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000037f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.090f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Galatea").whichHasTheFollowingOrbitalProperties(planet, new Length(61953, Length.DistanceUnit.KILOMETERS), new Angle(60)).andHasTheFollowingBodyProperties(new Length(176f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000035f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.079f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Larissa").whichHasTheFollowingOrbitalProperties(planet, new Length(73548, Length.DistanceUnit.KILOMETERS), new Angle(-150)).andHasTheFollowingBodyProperties(new Length(194f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0000007f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.091f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Polyphemus").whichHasTheFollowingOrbitalProperties(planet, new Length(105283, Length.DistanceUnit.KILOMETERS), new Angle(40)).andHasTheFollowingBodyProperties(new Length(20f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.25f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM),  new Albedo(0.09f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Proteus").whichHasTheFollowingOrbitalProperties(planet, new Length(117647, Length.DistanceUnit.KILOMETERS), new Angle(130)).andHasTheFollowingBodyProperties(new Length(420f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0000074f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.096f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Triton").whichHasTheFollowingOrbitalProperties(planet, new Length(354759, Length.DistanceUnit.KILOMETERS), new Angle(-130)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(2707f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00358f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.756f)).withAnAtmosphereOf(new Pressure(14f*(float)Math.pow(10,-6), PressureUnit.BAR), createAtmosphereOfTriton()).whichHasASubsurfaceOcean().buildAs(new MoonType(MoonType.TypeOfMoon.TRITONIAN), this);
                CreateAnAstronomicalBody.named("Nereid").whichHasTheFollowingOrbitalProperties(planet, new Length(5513787, Length.DistanceUnit.KILOMETERS), new Angle(70)).andHasTheFollowingBodyProperties(new Length(340f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0000025f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.155f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Halimede").whichHasTheFollowingOrbitalProperties(planet, new Length(16589670, Length.DistanceUnit.KILOMETERS), new Angle(99)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(62f / 2, Length.DistanceUnit.KILOMETERS), new Mass(8.9920f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Sao").whichHasTheFollowingOrbitalProperties(planet, new Length(22182010, Length.DistanceUnit.KILOMETERS), new Angle(-45)).andHasTheFollowingBodyProperties(new Length(44f / 2, Length.DistanceUnit.KILOMETERS), new Mass(8.9920f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Laomedeia").whichHasTheFollowingOrbitalProperties(planet, new Length(23464130, Length.DistanceUnit.KILOMETERS), new Angle(-90)).andHasTheFollowingBodyProperties(new Length(42f / 2, Length.DistanceUnit.KILOMETERS), new Mass(8.9920f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Psamathe").whichHasTheFollowingOrbitalProperties(planet, new Length(46695000, Length.DistanceUnit.KILOMETERS), new Angle(145)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(38f / 2, Length.DistanceUnit.KILOMETERS), new Mass(1.4987f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Neso").whichHasTheFollowingOrbitalProperties(planet, new Length(49285000, Length.DistanceUnit.KILOMETERS), new Angle(0)).whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(new Length(60f / 2, Length.DistanceUnit.KILOMETERS), new Mass(2.6485f * (float)(Math.pow(10,17)), Mass.MassUnit.KILOGRAM),  new Albedo(0.16f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            planet = CreateAnAstronomicalBody.named("Pluto").whichHasTheFollowingOrbitalProperties(star, new Length(39.482f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-80)).andHasTheFollowingBodyProperties(new Length(2310f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0022f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.49f)).whichHasASubsurfaceOcean().withAnAtmosphereOf(new Pressure(0.3f, PressureUnit.PASCAL), createAtmosphereOfPluto()).buildAs(new PlanetType(PlanetType.TypeOfPlanet.DWARFPLANET), this);
                CreateAnAstronomicalBody.named("Charon").whichHasTheFollowingOrbitalProperties(planet, new Length(17536, Length.DistanceUnit.KILOMETERS), new Angle(30)).andHasTheFollowingBodyProperties(new Length(1207f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00025f, Mass.MassUnit.EARTH_MASS),  new Albedo(0.372f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Styx").whichHasTheFollowingOrbitalProperties(planet, new Length(42000, Length.DistanceUnit.KILOMETERS), new Angle(120)).andHasTheFollowingBodyProperties(new Length(25f / 2, Length.DistanceUnit.KILOMETERS), new Mass(5.25f * (float)(Math.pow(10,15)), Mass.MassUnit.KILOGRAM), new Albedo(0.372f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Nix").whichHasTheFollowingOrbitalProperties(planet, new Length(48708, Length.DistanceUnit.KILOMETERS), new Angle(-90)).andHasTheFollowingBodyProperties(new Length(90f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000000017f, Mass.MassUnit.EARTH_MASS), new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Kerberos").whichHasTheFollowingOrbitalProperties(planet, new Length(59000, Length.DistanceUnit.KILOMETERS), new Angle(-150)).andHasTheFollowingBodyProperties(new Length(40f / 2, Length.DistanceUnit.KILOMETERS), new Mass(7.8f * (float)(Math.pow(10,16)), Mass.MassUnit.KILOGRAM), new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Hydra").whichHasTheFollowingOrbitalProperties(planet, new Length(64780, Length.DistanceUnit.KILOMETERS), new Angle(20)).andHasTheFollowingBodyProperties(new Length(120f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.00000017f, Mass.MassUnit.EARTH_MASS), new Albedo(0.04f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            planet = CreateAnAstronomicalBody.named("Haumea").whichHasTheFollowingOrbitalProperties(star, new Length(43.335f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-115)).andHasTheFollowingBodyProperties(new Length(1600f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0007f, Mass.MassUnit.EARTH_MASS), new Albedo(0.7f)).buildAs(new PlanetType(PlanetType.TypeOfPlanet.DWARFPLANET), this);
                CreateAnAstronomicalBody.named("Namaka").whichHasTheFollowingOrbitalProperties(planet, new Length(25657, Length.DistanceUnit.KILOMETERS), new Angle(90)).andHasTheFollowingBodyProperties(new Length(200f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0000003f, Mass.MassUnit.EARTH_MASS), new Albedo(0.8f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
                CreateAnAstronomicalBody.named("Hi'iaka").whichHasTheFollowingOrbitalProperties(planet, new Length(49880, Length.DistanceUnit.KILOMETERS), new Angle(-20)).andHasTheFollowingBodyProperties(new Length(380f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000003f, Mass.MassUnit.EARTH_MASS), new Albedo(0.8f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
            CreateAnAstronomicalBody.named("Makemake").whichHasTheFollowingOrbitalProperties(star, new Length(45.792f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(-125)).andHasTheFollowingBodyProperties(new Length(1500f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0003f, Mass.MassUnit.EARTH_MASS), new Albedo(0.81f)).buildAs(new PlanetType(PlanetType.TypeOfPlanet.DWARFPLANET), this);
            planet = CreateAnAstronomicalBody.named("Eris").whichHasTheFollowingOrbitalProperties(star, new Length(67.668f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(80)).andHasTheFollowingBodyProperties(new Length(2326f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.0028f, Mass.MassUnit.EARTH_MASS), new Albedo(0.96f)).buildAs(new PlanetType(PlanetType.TypeOfPlanet.DWARFPLANET), this);
                CreateAnAstronomicalBody.named("Dysnomia").whichHasTheFollowingOrbitalProperties(planet, new Length(37350, Length.DistanceUnit.KILOMETERS), new Angle(0)).andHasTheFollowingBodyProperties(new Length(684f / 2, Length.DistanceUnit.KILOMETERS), new Mass(0.000006f, Mass.MassUnit.EARTH_MASS), new Albedo(0.96f)).buildAs(new MoonType(MoonType.TypeOfMoon.IRREGULAR), this);
    }
    
    private AtmosphericComposition createAtmosphereOfPluto() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.NITROGEN,0.90f));
        list.add(new AtmosphericGas(GasType.METHANE,0.09f));
        list.add(new AtmosphericGas(GasType.CARBON_MONOXIDE,0.01f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createPhotosphereOfSun() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.7346f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.2485f));
        list.add(new AtmosphericGas(GasType.OXYGEN,0.0077f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfTriton() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.NITROGEN,0.99f));
        list.add(new AtmosphericGas(GasType.METHANE,0.01f));
        return new AtmosphericComposition(list);
    }
    
    private AtmosphericComposition createAtmosphereOfGanymede() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.OXYGEN,0.99f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfEnceladus() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.WATER_VAPOR,0.91f));
        list.add(new AtmosphericGas(GasType.NITROGEN,0.04f));
        list.add(new AtmosphericGas(GasType.CARBON_DIOXIDE,0.032f));
        list.add(new AtmosphericGas(GasType.METHANE,0.0017f));
        return new AtmosphericComposition(list);
    }
    
    private AtmosphericComposition createAtmosphereOfCallisto() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.OXYGEN,0.98f));
        list.add(new AtmosphericGas(GasType.CARBON_DIOXIDE,0.02f));
        return new AtmosphericComposition(list);
    }
    
    private AtmosphericComposition createAtmosphereOfIo() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.SULFUR_DIOXIDE,0.99f));
        return new AtmosphericComposition(list);
    }
    
    private AtmosphericComposition createAtmosphereOfEuropa() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.OXYGEN,0.99f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfMercury() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.OXYGEN,0.42f));
        list.add(new AtmosphericGas(GasType.SODIUM,0.29f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.06f));
        list.add(new AtmosphericGas(GasType.POTASSIUM,0.005f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfNeptune() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.80f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.19f));
        list.add(new AtmosphericGas(GasType.METHANE,0.015f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfUranus() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.83f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.15f));
        list.add(new AtmosphericGas(GasType.METHANE,0.023f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfTitan() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.NITROGEN,0.984f));
        list.add(new AtmosphericGas(GasType.METHANE,0.014f));
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.002f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfSaturn() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.96f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.03f));
        list.add(new AtmosphericGas(GasType.METHANE,0.004f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfJupiter() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.HYDROGEN,0.898f));
        list.add(new AtmosphericGas(GasType.HELIUM,0.102f));
        list.add(new AtmosphericGas(GasType.METHANE,0.003f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfMars() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.CARBON_DIOXIDE,0.960f));
        list.add(new AtmosphericGas(GasType.ARGON,0.019f));
        list.add(new AtmosphericGas(GasType.NITROGEN,0.019f));
        list.add(new AtmosphericGas(GasType.OXYGEN,0.00145f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfEarth() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.NITROGEN,0.7808f));
        list.add(new AtmosphericGas(GasType.OXYGEN,0.2095f));
        list.add(new AtmosphericGas(GasType.ARGON,0.00930f));
        list.add(new AtmosphericGas(GasType.WATER_VAPOR,0.01f));
        list.add(new AtmosphericGas(GasType.CARBON_DIOXIDE,0.00039f));
        return new AtmosphericComposition(list);
    }

    private AtmosphericComposition createAtmosphereOfVenus() {
        List<AtmosphericGas> list = new ArrayList<AtmosphericGas>();
        list.add(new AtmosphericGas(GasType.CARBON_DIOXIDE,0.965f));
        list.add(new AtmosphericGas(GasType.NITROGEN,0.035f));
        list.add(new AtmosphericGas(GasType.SULFUR_DIOXIDE,0.00015f));
        return new AtmosphericComposition(list);
    }

    @Override
    public String getTypeName() {
        return "Solar System";
    }
}
