package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing.RingType;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.physics.Angle.AngularUnit;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere.BiosphereType;

/**
 * Builder pattern class designed to take over the creation of all actors of type astronomical body.
 * @author Andi
 *
 */
public final class CreateAnAstronomicalBody {
    private String name;
    private BodyProperties bodyProperties;
    private OrbitalProperties orbitalProperties;
    private BodyType type;
    private boolean ringed = false;
    private boolean atmosphere = false;
    private Length outerRadiusOfRings;
    private Length innerRadiusOfRings;
    private Mass massOfRings;
    private RingType typeOfRings;
    private Pressure surfacePressure;
    private AtmosphericComposition atmosphericComposition;
    private float liquidWaterCover;
    private float iceCover;
    private boolean liquidWater = false;
    private boolean subsurfaceOcean = false;
    private Biosphere bio;
    private boolean tidallyLocked;
    private Time rotationPeriod;
    
    private CreateAnAstronomicalBody(String name) {
        this.name = name;
    }
        
    /**
     * Choose a name for the astronomical body which is being created.
     * @param name Desired name of the body.
     * @return
     */
    public static CreateAnAstronomicalBody named(String name) {
        return new CreateAnAstronomicalBody(name);
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
    public CreatableProperties whichHasTheFollowingOrbitalProperties(AstronomicalBody orbitPrimary, Length orbitalRadius, Angle startingAngle) {
        this.orbitalProperties = new OrbitalProperties(orbitPrimary, orbitalRadius, startingAngle);
        return new CreatableProperties();
    }
    
    /**
     * Adds the necessary orbital parameters for the astronomical body. This variant assumes that there is no orbit and that the object is sitting on top of its primary.
     * Will move together with its primary body. Only to be used for stars in single-star system.
     * @param system Object on which the body will be placed.  
     * @return
     */
    public CreatableProperties whichIsStationaryAt(AstronomicalBody system) {
        this.orbitalProperties = new OrbitalProperties(system, new Length(0, Length.DistanceUnit.KILOMETERS), new Angle(0));
        return new CreatableProperties();
    }
    
    /**
     * Indicates that this astronomical body shares its orbit with another, larger body, causing the two to gravitationally interact.
     * The orbit display of this body will be hidden.
     * @param largerBody Object with which this new astronomical body is sharing its orbital properties.  
     * @return
     */
    public CreatableProperties whichIsCoorbitalWith(AstronomicalBody dominantBody, Angle angularDeviation) {
        this.orbitalProperties = new OrbitalProperties(dominantBody.getPrimary(), new Length(dominantBody.getOrbitalRadius().asKilometers(),dhbw.karlsruhe.it.solar.core.physics.Length.DistanceUnit.KILOMETERS), new Angle(dominantBody.getOrbitalAngle().inDegrees(), AngularUnit.DEGREE));
        this.orbitalProperties.setCoorbital(dominantBody, angularDeviation);
        return new CreatableProperties();
    }
    
    public class CreatableProperties {
        private CreatableProperties() {
            
        }
        
        /**
         * Causes the astronomical body to move opposite the normal (prograde) direction on its orbit
         * Since the direction of the movement is linked to how that object formed, all major bodies of a solar system normally move prograde.
         * Retrograde direction indicates that the astronomical body did not originate in this position and was gravitationally captured by the primary body some time in the past.
         * @return
         */
        public CreatableProperties whileMovingInRetrogradeDirection() {
            CreateAnAstronomicalBody.this.orbitalProperties.setRetrograde();
            return this;
        }
        
        /**
         * Determines the physical properties of the celestial body such as its dimensions and mass.
         * @param bodyRadius Size of the astronomical body.
         * @param bodyMass Mass of the astronomical body.
         * @param tidallyLockedToStar 
         * @return
         */
        public CreatableType andHasTheFollowingBodyProperties(Length bodyRadius, Mass bodyMass, Albedo albedo) {
            CreateAnAstronomicalBody.this.bodyProperties = new BodyProperties(bodyMass, bodyRadius, albedo);
            return new CreatableType();
        }
        
        public class CreatableType {
            private CreatableType() {
                
            }
            
            /**
             * Add a ring system to the astronomical object.
             * @param massOfRings Total mass of the ring system.
             * @param radiusOfRings Radius of the ring system.
             * @param typeOfRings Appearance of the ring system.
             * @return
             */
            public CreatableType includingRings(Mass massOfRings, Length innerRadiusOfRings, Length outerRadiusOfRings, RingType typeOfRings) {
                CreateAnAstronomicalBody.this.ringed = true;
                CreateAnAstronomicalBody.this.massOfRings = massOfRings;
                CreateAnAstronomicalBody.this.innerRadiusOfRings = innerRadiusOfRings;
                CreateAnAstronomicalBody.this.outerRadiusOfRings = outerRadiusOfRings;
                CreateAnAstronomicalBody.this.typeOfRings = typeOfRings;
                return this;        
            }
            
            /**
             * Body will complete one rotation around itself per complete orbit around its primary object.
             * This is the final effect of tidal forces caused by the gravitational interaction between primary and satellite.
             * @return
             */
            public CreatableType whichIsTidallyLockedToItsPrimary() {
                CreateAnAstronomicalBody.this.tidallyLocked = true;
                return this;        
            }
            
            public CreatableType whichRotatesEvery(Time siderealRotationPeriod) {
                CreateAnAstronomicalBody.this.rotationPeriod = siderealRotationPeriod;
                return this;
            }
                        
            /**
             * Add an atmosphere to the astronomical object.
             * @param surfacePressure
             * @param atmosphericComposition
             * @return
             */
            public CreatableType withAnAtmosphereOf(Pressure surfacePressure, AtmosphericComposition atmosphericComposition) {
                CreateAnAstronomicalBody.this.atmosphere = true;
                CreateAnAstronomicalBody.this.surfacePressure = surfacePressure;
                CreateAnAstronomicalBody.this.atmosphericComposition = atmosphericComposition;
                return this;        
            }

            /**
             * Creates an atmosphere for gas giants without surface pressure.
             * @param createAtmosphereOfNeptune
             * @return
             */
            public CreatableType withAGasGiantAtmosphereOf(AtmosphericComposition atmosphericComposition) {
                CreateAnAstronomicalBody.this.atmosphere = true;
                CreateAnAstronomicalBody.this.surfacePressure = null;
                CreateAnAstronomicalBody.this.atmosphericComposition = atmosphericComposition;
                return this;  
            }
            
            /**
             * Add a biosphere of natural lifeforms to the planet
             * @param bioType
             * @param biosphereCoverage
             * @return
             */
            public CreatableType addBiosphere(BiosphereType bioType, float biosphereCoverage) {
                CreateAnAstronomicalBody.this.bio = new Biosphere(bioType,biosphereCoverage);             
                return this;        
            }    
            
            /**
             * Adds liquid water oceans and ice caps to the astronomical object. Used to add water to terrestrial planets or moons.
             * @param liquidWaterCover
             * @param iceCover
             * @return
             */
            public CreatableType withOceanCoverAndIceCapOf(float liquidWaterCover, float iceCover) {
                CreateAnAstronomicalBody.this.liquidWaterCover = liquidWaterCover;
                CreateAnAstronomicalBody.this.iceCover = iceCover;
                CreateAnAstronomicalBody.this.liquidWater = true;
                return this;        
            }
            
            /**
             * Adds a subsurface ocean to an astronomical object.
             * @param liquidWaterCover
             * @param iceCover
             * @return
             */
            public CreatableType whichHasASubsurfaceOcean() {
                CreateAnAstronomicalBody.this.subsurfaceOcean = true;
                return this;        
            }
            
            /**
             * Determines which type of object is to be created.
             * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
             * @param solarSystem The star system to which the new object will be added.
             * @return
             */
            public Star buildAs(StarType star, SolarSystem solarSystem) {
                CreateAnAstronomicalBody.this.type = star;
                return CreateAnAstronomicalBody.this.buildStar(solarSystem);
            }
            
            /**
             * Determines which type of object is to be created.
             * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
             * @param solarSystem The star system to which the new object will be added.
             * @return
             */
            public Planet buildAs(PlanetType planet, SolarSystem solarSystem) {
                CreateAnAstronomicalBody.this.type = planet;
                return CreateAnAstronomicalBody.this.buildPlanet(solarSystem);
            }
            
            /**
             * Determines which type of object is to be created.
             * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
             * @param solarSystem The star system to which the new object will be added.
             * @return
             */
            public Moon buildAs(MoonType moon, SolarSystem solarSystem) {
                CreateAnAstronomicalBody.this.type = moon;
                CreateAnAstronomicalBody.this.tidallyLocked = true;
                return CreateAnAstronomicalBody.this.buildMoon(solarSystem);
            }
            
            /**
             * Determines which type of object is to be created.
             * @param type Type can be used for any of the astronomical object classes such as StarType, PlanetType, MoonType, ...
             * @param solarSystem The star system to which the new object will be added.
             * @return
             */
            public Asteroid buildAs(AsteroidType asteroid, SolarSystem solarSystem) {
                CreateAnAstronomicalBody.this.type = asteroid;
                return CreateAnAstronomicalBody.this.buildAsteroid(solarSystem);
            }            
        }
    }
    
    private Star buildStar(SolarSystem solarSystem) {
        bodyProperties.setBodyType(type);
        Star newBody = new Star(name, orbitalProperties, bodyProperties);
        setupBody(solarSystem, newBody);
        return newBody;
    }
    
    private Planet buildPlanet(SolarSystem solarSystem) {
        bodyProperties.setBodyType(type);
        Planet newBody = new Planet(name, orbitalProperties, bodyProperties);
        setupBody(solarSystem, newBody);
        return newBody;
    }
    
    private Moon buildMoon(SolarSystem solarSystem) {
        bodyProperties.setBodyType(type);
        Moon newBody = new Moon(name, orbitalProperties, bodyProperties);
        setupBody(solarSystem, newBody);
        return newBody;
    }
    
    private Asteroid buildAsteroid(SolarSystem solarSystem) {
        bodyProperties.setBodyType(type);
        Asteroid newBody = new Asteroid(name, orbitalProperties, bodyProperties);
        setupBody(solarSystem, newBody);
        return newBody;
    }
    
    private void setupBody(SolarSystem solarSystem, AstronomicalBody newBody) {
        setUpRings(newBody);
        setUpAtmosphere(newBody);
        setUpHydrosphere(newBody);
        setUpBiosphere(newBody);
        setUpBodyRotation(newBody);
        newBody.calculateSurfaceTemperature();
        newBody.calculateLifeRating();
        newBody.initializeAstronomicalBody(solarSystem);
    }

    private void setUpBodyRotation(AstronomicalBody newBody) {
        if(tidallyLocked) {
            newBody.tidalLockToPrimary();
            return;
        }
        newBody.setSiderealRotationPeriod(rotationPeriod);
    }
    
    private void setUpBiosphere(AstronomicalBody newBody) {
        if(null != bio) {
            newBody.setUpBiosphere(bio);
        }
        
    }
    
    private void setUpHydrosphere(AstronomicalBody newBody) {
        newBody.determineHydrosphere(liquidWaterCover, iceCover, liquidWater, subsurfaceOcean);
    }

    private void setUpAtmosphere(AstronomicalBody newBody) {
        if(atmosphere) {
            newBody.setUpAtmosphere(new Atmosphere(surfacePressure,atmosphericComposition));
        }     
    }

    private void setUpRings(AstronomicalBody newBody) {
        if(ringed)        {
            newBody.setUpRings(new PlanetaryRing(newBody, massOfRings, innerRadiusOfRings, outerRadiusOfRings, typeOfRings));
        }
    }
}
