package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.*;

import java.util.List;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties {
    private Mass mass;
    private Length radius;
    private PlanetaryRing ring;
    private BodyType type;
    private Atmosphere atmosphere;
    private SurfaceTemperature temperature;
    private Hydrosphere hydro;
    private Biosphere bio;
    private SurfaceGravity gravity;
    private LifeRating rating;
    private Albedo albedo;
    private Time siderealRotationPeriod;
    private boolean tidallyLocked;

    public BodyProperties(Mass mass, Length radius, Albedo albedo) {
        this.mass = mass;
        this.radius = radius;
        this.gravity = new SurfaceGravity(mass, radius);
        this.albedo = albedo;
    }

    public Mass getMass() {
        return mass;
    }

    public Length getRadius() {
        return radius;
    }

    public void addMass(Mass massToBeAddedToTheBody) {
        mass.addMass(massToBeAddedToTheBody);
    }

    public void setUpRings(PlanetaryRing newRing) {
        ring = newRing;
    }

    public PlanetaryRing getRings() {
        return ring;
    }

    public void setRingPrimary(AstronomicalBody body) {
        ring.setRingPrimary(body);
    }

    public BodyType getBodyType() {
        return type;
    }

    public void setBodyType(BodyType type) {
        this.type = type;
    }

    public boolean hasRings() {
        return null != ring;
    }

    public void setUpAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public SurfaceTemperature getTemperatures() {
        return temperature;
    }

    public Hydrosphere getHydrosphere() {
        return hydro;
    }

    public void setUpHydrosphere(float liquidWaterCover, float iceCover, boolean liquidWater, boolean subsurfaceOcean) {
        this.hydro = new Hydrosphere(liquidWaterCover, iceCover, liquidWater, subsurfaceOcean);
    }

    public Biosphere getBiosphere() {
        return bio;
    }

    public void setUpBiosphere(Biosphere bio) {
        this.bio = bio;
    }

    public void calculateLifeRating() {
        this.rating = new FuzzyLogic(gravity, atmosphere, temperature, hydro, bio).calculateLifeRating();
    }

    public LifeRating getLifeRating() {
        return rating;
    }

    public SurfaceGravity getSurfaceGravity() {
        return gravity;
    }

    public Pressure getSurfacePressure() {
        if (null != atmosphere && null != atmosphere.getPressure()) {
            return atmosphere.getPressure();
        }
        return new Pressure(0f, Pressure.PressureUnit.BAR);
    }

    public boolean hasAtmosphere() {
        return null != atmosphere;
    }

    public List<AtmosphericGas> getListOfAtmosphericGases() {
        return atmosphere.getListOfAtmosphericGases();
    }

    public boolean hasSurface() {
        return type.hasSurface();
    }

    public boolean consistsPartiallyOfWaterIce() {
        return type.consistsPartiallyOfWaterIce();
    }

    /**
     * Calculates the surface temperature of that body under the assumption that it is a star conducting nuclear fusion at its core.
     * The calculation requires this body to have a valid star type, consisting of spectral type classification, numeric digit subdivision and a roman numeral luminosity type qualifier.
     */
    public void setStellarSurfaceTemperature() {
        temperature = new SurfaceTemperature(Star.getBlackBodyTemperature((StarType) type));
    }

    /**
     * Calculates the surface temperature of a planetary body.
     *
     * @param body
     */
    public void calculateSurfaceTemperature(AstronomicalBody body) {
        temperature = new SurfaceTemperature();
        temperature.calculateSurfaceTemperature(body);
    }

    public Albedo getAlbedo() {
        return albedo;
    }

    public boolean isTidallyLocked() {
        return tidallyLocked;
    }

    public void tidalLockedToPrimary(Time orbitalPeriod) {
        tidallyLocked = true;
        siderealRotationPeriod = orbitalPeriod;
    }

    public Pressure getH2OPartialPressure() {
        return atmosphere.getH2OPartialPressure();
    }

    public boolean isRounded() {
        return type.isRounded();
    }

    public Time getRotationPeriod() {
        return siderealRotationPeriod;
    }

    public void setUpSiderealRotationPeriod(Time rotationPeriod) {
        this.siderealRotationPeriod = rotationPeriod;
    }

    public Time getRotationPeriodAbsolute() {
        return siderealRotationPeriod.absolute();
    }

    public String getTexture() {
        return type.getTexture();
    }

    public boolean isColonizable() {
        return type.isColonizable();
    }
}
