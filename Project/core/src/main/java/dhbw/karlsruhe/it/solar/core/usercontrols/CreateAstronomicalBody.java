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
	 * @param orbitPrimary Object around which the new astronomical body will orbit.
	 * @param orbitalRadius Distance from the primary object around which the new astronomical body will orbit.
	 * @param startingAngle Initial angle away from the y-axis from which the new astronomical body will start its orbit.
	 * @return
	 */
	public CreatableProperties withOrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, Angle startingAngle) {
		this.orbitalProperties = new OrbitalProperties(orbitPrimary, orbitalRadius, startingAngle);
		return new CreatableProperties();
	}
	
	public class CreatableProperties {
		private CreatableProperties() {}
		
		/**
		 * Determines which type of object is to be created.
		 * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
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
			 * @param typeOfRing Appearance of the ring system.
			 * @return
			 */
			public CreatableType includingRings(Mass massOfRings, Length radiusOfRings, RingType typeOfRing) {
				CreateAstronomicalBody.this.bodyProperties.setUpRings(new PlanetaryRing(null, massOfRings, radiusOfRings, typeOfRing));
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
			
		}
	}
	
	private Star buildStar(SolarSystem solarSystem) {
		Star newBody = new Star(name, orbitalProperties, bodyProperties, starType);
		newBody.initializeAstronomicalBody(solarSystem);
		return newBody;
	}
	
	private Planet buildPlanet(SolarSystem solarSystem) {
		Planet newBody = new Planet(name, orbitalProperties, bodyProperties, planetType);
		newBody.initializeAstronomicalBody(solarSystem);
		return newBody;
	}
}
