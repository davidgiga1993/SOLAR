package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties {
    private Mass mass;
    private Length radius;
    private PlanetaryRing ring;
    private BodyType type;
    private Atmosphere atmosphere;
    private SurfaceTemperatures temperatures;
    private Hydrosphere hydro;
    private Biosphere bio;
    private SurfaceGravity gravity;
    private LifeRating rating;

    public BodyProperties(Mass mass, Length radius, PlanetaryRing ring, SurfaceTemperatures temperatures) {
        this.mass = mass;
        this.radius = radius;
        this.ring = ring;
        this.temperatures = temperatures;
        this.gravity = new SurfaceGravity(mass, radius);
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

    public SurfaceTemperatures getTemperatures() {
        return temperatures;
    }

    public Hydrosphere getHydrosphere() {
        return hydro;
    }

    public void setUpHydrosphere(Hydrosphere hydro) {
        this.hydro = hydro;
    }

    public Biosphere getBiosphere() {
        return bio;
    }

    public void setUpBiosphere(Biosphere bio) {
        this.bio = bio;
    }

    public void calculateLifeRating() {
        this.rating = new FuzzyLogic(gravity, atmosphere, temperatures, hydro, bio).calculateLifeRating();
    }

    public LifeRating getLifeRating() {
        return rating;
    }
}
