package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon.MoonType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet.PlanetType;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing.RingType;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star.StarType;

/**
 * Builder pattern class designed to take over the creation of all actors of type astronomical body.
 * @author Andi
 *
 */
public final class CreateAstronomicalBody {
	private String name;
	private BodyProperties bodyProperties;
	private OrbitalProperties orbitalProperties;
	private StarType starType;
	private PlanetType planetType;
	private MoonType moonType;
	private boolean ringed = false;
	private Length radiusOfRings;
	private Mass massOfRings;
	private RingType typeOfRings;
	
	private CreateAstronomicalBody(String name) {
		this.name = name;
	}
		
	/**
	 * Choose a name for the astronomical body which is being created.
	 * @param name Desired name of the body.
	 * @return
	 */
	public static CreateAstronomicalBody named(String name) {
		return new CreateAstronomicalBody(name);
	}
	
	/**
	 * Adds the necessary orbital parameters for the astronomical body.
	 * The orbital parameters define this object's movement on the game map.
	 * Orbital movement is managed with polar coordinates, i.e. instead of x-/y-parameters, the game uses radius from the central body and angle away from the x-axis to calculate positions.
	 * @param orbitPrimary Object around which the new astronomical body will orbit.
	 * @param orbitalRadius Distance from the primary object around which the new astronomical body will orbit.
	 * @param startingAngle Initial angle away from the x-axis from which the new astronomical body will start its orbit.
	 * @return
	 */
	public CreatableProperties withOrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, Angle startingAngle) {
		this.orbitalProperties = new OrbitalProperties(orbitPrimary, orbitalRadius, startingAngle);
		return new CreatableProperties();
	}
	
	/**
	 * Adds the necessary orbital parameters for the astronomical body. This variant assumes that there is no orbit and that the object is sitting on top of its primary. Only to be used for stars in single-star system.
	 * @param system Map point on which the body will be placed.  
	 * @return
	 */
	public CreatableProperties withOrbitalProperties(AstronomicalBody system) {
		this.orbitalProperties = new OrbitalProperties(system, new Length(0, Length.Unit.kilometres), new Angle(0));
		return new CreatableProperties();
	}
	
	public class CreatableProperties {
		private CreatableProperties() {}
		
		/**
		 * Determines the physical properties of the celestial body such as its dimensions and mass.
		 * @param bodyRadius Size of the astronomical body.
		 * @param bodyMass Mass of the astronomical body.
		 * @return
		 */
		public CreatableType andBodyProperties(Length bodyRadius, Mass bodyMass) {
			CreateAstronomicalBody.this.bodyProperties = new BodyProperties(bodyMass, bodyRadius, null);
			return new CreatableType();
		}
		
		public class CreatableType {
			private CreatableType() {}
			
			/**
			 * Add a ring system to the astronomical object.
			 * @param massOfRings Total mass of the ring system.
			 * @param radiusOfRings Radius of the ring system.
			 * @param typeOfRings Appearance of the ring system.
			 * @return
			 */
			public CreatableType includingRings(Mass massOfRings, Length radiusOfRings, RingType typeOfRings) {
				CreateAstronomicalBody.this.ringed = true;
				CreateAstronomicalBody.this.massOfRings = massOfRings;
				CreateAstronomicalBody.this.radiusOfRings = radiusOfRings;
				CreateAstronomicalBody.this.typeOfRings = typeOfRings;
				return this;		
			}
			
			/**
			 * Causes the astronomical body to move opposite the normal (prograde) direction on its orbit
			 * Since the direction of the movement is linked to how that object formed, all major bodies of a solar system normally move prograde.
			 * Retrograde direction indicates that the astronomical body did not originate in this position and was gravitationally captured by the primary body some time in the past.
			 * @return
			 */
			public CreatableType whichMovesRetrograde() {
				CreateAstronomicalBody.this.orbitalProperties.setRetrograde();
				return this;
			}
			
			/**
			 * Indicates that this astronomical body shares its orbit with another, larger body, causing the two to gravitationally interact.
			 * The orbit display of this body will be hidden.
			 * @return
			 */
			public CreatableType whichIsCoorbital() {
				CreateAstronomicalBody.this.orbitalProperties.setCoorbital();
				return this;
			}
			
			/**
			 * Determines which type of object is to be created.
			 * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
			 * @param solarSystem The star system to which the new object will be added.
			 * @return
			 */
			public Star buildAs(StarType type, SolarSystem solarSystem) {
				CreateAstronomicalBody.this.starType = type;
				return CreateAstronomicalBody.this.buildStar(solarSystem);
			}
			
			/**
			 * Determines which type of object is to be created.
			 * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
			 * @param solarSystem The star system to which the new object will be added.
			 * @return
			 */
			public Planet buildAs(PlanetType type, SolarSystem solarSystem) {
				CreateAstronomicalBody.this.planetType = type;
				return CreateAstronomicalBody.this.buildPlanet(solarSystem);
			}
			
			/**
			 * Determines which type of object is to be created.
			 * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
			 * @param solarSystem The star system to which the new object will be added.
			 * @return
			 */
			public Moon buildAs(MoonType type, SolarSystem solarSystem) {
				CreateAstronomicalBody.this.moonType = type;
				return CreateAstronomicalBody.this.buildMoon(solarSystem);
			}
			
		}
	}
	
	private Star buildStar(SolarSystem solarSystem) {
		Star newBody = new Star(name, orbitalProperties, bodyProperties, starType);
		setUpRings(newBody);
		newBody.initializeAstronomicalBody(solarSystem);
		return newBody;
	}
	
	private Planet buildPlanet(SolarSystem solarSystem) {
		Planet newBody = new Planet(name, orbitalProperties, bodyProperties, planetType);
		setUpRings(newBody);		
		newBody.initializeAstronomicalBody(solarSystem);
		return newBody;
	}
	
	private Moon buildMoon(SolarSystem solarSystem) {
		Moon newBody = new Moon(name, orbitalProperties, bodyProperties, moonType);
		setUpRings(newBody);		
		newBody.initializeAstronomicalBody(solarSystem);
		return newBody;
	}
	
	private void setUpRings(AstronomicalBody newBody) {
		if(ringed)
		{
			newBody.setUpRings(new PlanetaryRing(newBody, massOfRings, radiusOfRings, typeOfRings));
		}
	}
}
