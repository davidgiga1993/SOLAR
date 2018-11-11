package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.savegames.AstroBodyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.RingSystemInfo;

/**
 * Handles the creation of astronomical bodies for the game.
 *
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
     *
     * @param system
     */
    public void initSolarSystem(SolarSystem system) {
        this.system = system;
    }

    /**
     * Creates a new Astronomical Body based on the information contained in the parameter.
     *
     * @param body Information which will be used to construct a new celestial body.
     * @return Newly created Astronomical Body object.
     */
    public AstronomicalBody createNewBody(AstroBodyInfo body) {
        this.body = body;
        return extractType();
    }

    private AstronomicalBody extractType() {
        BodyType type = body.getType();
        if (type instanceof AsteroidType) {
            return extractRings().buildAs((AsteroidType) type, system);
        }
        if (type instanceof MoonType) {
            return extractRings().buildAs((MoonType) type, system);
        }
        if (type instanceof PlanetType) {
            return extractRings().buildAs((PlanetType) type, system);
        }
        if (type instanceof StarType) {
            return extractRings().buildAs((StarType) type, system);
        }
        return null;
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractRings() {
        RingSystemInfo rings = body.getRingSystem();
        if (null != rings) {
            return extractAtmosphere().includingRings(rings.getMass(), rings.getInnerRadius(), rings.getOuterRadius(), rings.getType());
        }
        return extractAtmosphere();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractAtmosphere() {
        Atmosphere atmosphere = body.getAtmosphere();
        if (null != atmosphere && null != atmosphere.getPressure()) {
            return extractSubsurfaceOcean().withAnAtmosphereOf(atmosphere.getPressure(), atmosphere.getComposition());
        }
        if (null != atmosphere) {
            return extractSubsurfaceOcean().withAGasGiantAtmosphereOf(atmosphere.getComposition());
        }
        return extractSubsurfaceOcean();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractSubsurfaceOcean() {
        Hydrosphere hydrosphere = body.getHydrosphere();
        if (null != hydrosphere && hydrosphere.getSubsurfaceOcean()) {
            return extractOceansAndIceCaps().whichHasASubsurfaceOcean();
        }
        return extractOceansAndIceCaps();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractOceansAndIceCaps() {
        Hydrosphere hydrosphere = body.getHydrosphere();
        if (null != hydrosphere && (hydrosphere.getWaterCover() > 0 || hydrosphere.getIceCover() > 0)) {
            return extractBiosphere().withOceanCoverAndIceCapOf(hydrosphere.getWaterCover(), hydrosphere.getIceCover());
        }
        return extractBiosphere();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractBiosphere() {
        Biosphere biosphere = body.getBiosphere();
        if (null != biosphere) {
            return extractTidalLock().addBiosphere(biosphere.getBioType(), biosphere.getBioCover());
        }
        return extractTidalLock();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractTidalLock() {
        if (body.isTidallyLocked()) {
            return extractSiderealRotationPeriod().whichIsTidallyLockedToItsPrimary();
        }
        return extractSiderealRotationPeriod();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractSiderealRotationPeriod() {
        Time rotationPeriod = body.getSiderealRotationPeriod();
        if (null != rotationPeriod) {
            return extractBodyProperties().whichRotatesEvery(rotationPeriod);
        }
        return extractBodyProperties();
    }

    private CreateAnAstronomicalBody.CreatableProperties.CreatableType extractBodyProperties() {
        Length radius = body.getRadius();
        Mass mass = body.getMass();
        Albedo albedo = body.getAlbedo();
        if (body.isRetrograde()) {
            return extractOrbitalProperties().whileMovingInRetrogradeDirection().andHasTheFollowingBodyProperties(radius, mass, albedo);
        }
        return extractOrbitalProperties().andHasTheFollowingBodyProperties(radius, mass, albedo);
    }

    private CreateAnAstronomicalBody.CreatableProperties extractOrbitalProperties() {
        CoOrbital coorbital = body.getCoOrbital();
        AstronomicalBody orbitPrimary = system.findAstronomicalBodyByName(body.getPrimary());
        Length orbitalRadius = body.getOrbitalRadius();
        Angle polarAngle = body.getPolarAngle();
        if (null != coorbital) {
            return extractName().whichIsCoOrbitalWith(system.findAstronomicalBodyByName(coorbital.getNameOfDominantBody()), coorbital.getAngularDeviation());
        }
        if (body.isStationary()) {
            return extractName().whichIsStationaryAt(orbitPrimary);
        }
        return extractName().whichHasTheFollowingOrbitalProperties(orbitPrimary, orbitalRadius, polarAngle);
    }

    private CreateAnAstronomicalBody extractName() {
        return CreateAnAstronomicalBody.named(body.getName());
    }
}
