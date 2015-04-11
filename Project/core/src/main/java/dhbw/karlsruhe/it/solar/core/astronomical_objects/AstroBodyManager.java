package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.CreateAnAstronomicalBody.CreatableProperties;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.CreateAnAstronomicalBody.CreatableProperties.CreatableType;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.AsteroidType;
import dhbw.karlsruhe.it.solar.core.physics.Atmosphere;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere;
import dhbw.karlsruhe.it.solar.core.physics.BodyType;
import dhbw.karlsruhe.it.solar.core.physics.Coorbital;
import dhbw.karlsruhe.it.solar.core.physics.Hydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.MoonType;
import dhbw.karlsruhe.it.solar.core.physics.PlanetType;
import dhbw.karlsruhe.it.solar.core.physics.StarType;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceTemperatures;
import dhbw.karlsruhe.it.solar.core.savegames.AstroBodyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.RingSystemInfo;

/**
 * Handles the creation of astronomical bodies for the game.
 * @author Andi
 * created 2015-04-06
 */
public class AstroBodyManager {
        
    private SolarSystem system;
    private AstroBodyInfo body;
    
    public AstroBodyManager() {
       
    }
    
    /**
     * Required setting for the manager. The manager needs to know the stellar system as the system root.
     * @param system 
     */
    public void initSolarSystem(SolarSystem system) {
        this.system = system;
    }

    /**
     * Creates a new Astronomical Body based on the information contained in the parameter.
     * @param body Information which will be used to construct a new celestial body.
     * @return Newly created Astronomical Body object.
     */
    public AstronomicalBody createNewBody(AstroBodyInfo body) {
        this.body = body;      
        return extractType();              
    }

    private AstronomicalBody extractType() {
        BodyType type = body.getType();
        if(type instanceof AsteroidType) {
            return extractRings().buildAs((AsteroidType)type, system);   
        }
        if(type instanceof MoonType) {
            return extractRings().buildAs((MoonType)type, system);   
        }
        if(type instanceof PlanetType) {
            return extractRings().buildAs((PlanetType)type, system);   
        }
        if(type instanceof StarType) {
            return extractRings().buildAs((StarType)type, system);   
        }
        return null;
    }
    
    private CreatableType extractRings() {
        RingSystemInfo rings = body.getRingSystem();
        if(null!=rings) {
            return extractAtmosphere().includingRings(rings.getMass(), rings.getInnerRadius(), rings.getOuterRadius(), rings.getType());            
        }
        return extractAtmosphere();
    }
    
    private CreatableType extractAtmosphere() {
        Atmosphere atmosphere = body.getAtmosphere();
        if(null!=atmosphere) {
            return extractHydrophere().withAnAtmosphereOf(atmosphere.getPressure(),atmosphere.getComposition());            
        }
        return extractHydrophere();
    }
    
    private CreatableType extractHydrophere() {
        Hydrosphere hydrosphere = body.getHydrosphere();
        if(null!=hydrosphere) {
            return extractBiosphere().withAHydrosphereOf(hydrosphere);            
        }
        return extractBiosphere();
    }
    
    private CreatableType extractBiosphere() {
        Biosphere biosphere = body.getBiosphere();
        if(null!=biosphere) {
            return extractBodyProperties().addBiosphere(biosphere.getBioType(),biosphere.getBioCover());            
        }
        return extractBodyProperties();
    }   

    private CreatableType extractBodyProperties() {
        Length radius = body.getRadius();
        Mass mass = body.getMass();
        SurfaceTemperatures temperatures = body.getTemperatures();
        if(body.isRetrograde()) {
            return extractOrbitalProperties().whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(radius, mass, temperatures);
        }
        return extractOrbitalProperties().andHasTheFollowingBodyProperties(radius, mass, temperatures);
    }

    private CreatableProperties extractOrbitalProperties() {
        Coorbital coorbital = body.getCoorbital();
        AstronomicalBody orbitPrimary = system.findAstronomicalBodyByName(body.getPrimary());
        Length orbitalRadius = body.getOrbitalRadius();
        Angle polarAngle = body.getPolarAngle();
        if(null!=coorbital) {
            return extractName().whichIsCoorbitalWith(system.findAstronomicalBodyByName(coorbital.getNameOfDominantBody()), coorbital.getAngularDeviation());
        }
        if(body.isStationary()) {
            return extractName().whichIsStationaryAt(orbitPrimary);  
        }
        return extractName().whichHasTheFollowingOrbitalProperties(orbitPrimary, orbitalRadius, polarAngle);
    }

    private CreateAnAstronomicalBody extractName() {
        return CreateAnAstronomicalBody.named(body.getName());
    }
}
