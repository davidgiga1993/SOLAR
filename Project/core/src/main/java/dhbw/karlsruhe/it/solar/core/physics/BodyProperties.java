package dhbw.karlsruhe.it.solar.core.physics;

import java.util.List;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.BodyType;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Star;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.StarType;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

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
    private boolean tidallyLocked;

    public BodyProperties(Mass mass, Length radius, Albedo albedo) {
        this.mass = mass;
        this.radius = radius;
        this.gravity = new SurfaceGravity(mass, radius);
        this.albedo = albedo;
    }
    
    public Mass getMass()    {
        return mass;
    }
    
    public Length getRadius()    {
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
        if(null!= ring) {
            return true;
        }
        return false;
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
        return atmosphere.getPressure();
    }

    public boolean hasAtmosphere() {
        if(null != atmosphere) {
            return true;
        }
        return false;
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
        temperature = new SurfaceTemperature(Star.getBlackBodyTemperature((StarType)type));     
    }

    /**
     * Calculates the surface temperature of a planetary body.
     * @param starsInSystem
     */
    public void calculateSurfaceTemperature(AstronomicalBody body) {
        temperature = new SurfaceTemperature();
        temperature.calculateSurfaceTemperature(body, albedo);
    }

    public Albedo getAlbedo() {
        return albedo;
    }

    public boolean isTidallyLocked() {
        return tidallyLocked;
    }

    public void tidalLockedToPrimary() {
        tidallyLocked = true;
    }

    public Pressure getH2OPartialPressure() {
        return atmosphere.getH2OPartialPressure();
    }

    public boolean isRounded() {
        return type.isRounded();
    }
}
