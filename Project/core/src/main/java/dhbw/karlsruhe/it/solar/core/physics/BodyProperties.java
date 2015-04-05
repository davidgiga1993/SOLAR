package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing;

/**
 * Created by Arga on 25.11.2014.
 */
public class BodyProperties {
    private Mass mass;
    private Length radius;
    private PlanetaryRing ring;
    private BodyType type;

    public BodyProperties(Mass mass, Length radius, PlanetaryRing ring) {
        this.mass = mass;
        this.radius = radius;
        this.ring = ring;
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
}
