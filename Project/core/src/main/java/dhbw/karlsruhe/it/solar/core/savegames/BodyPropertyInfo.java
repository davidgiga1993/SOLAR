package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.physics.BodyType;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Mass.class, BodyType.class, PlanetaryRing.class})
public class BodyPropertyInfo {
    
    @XmlElement(name = "Type")
    private BodyType type;
    @XmlElement(name = "Radius")
    private Length radius;
    @XmlElement(name = "Mass")
    private Mass mass;
    @XmlElement(name = "PlanetaryRings")
    private RingSystemInfo ring;
    
    public BodyPropertyInfo() {
        
    }
    
    public void fillBodyPropertyInfo(AstronomicalBody body) {
        this.radius = body.getRadius();
        this.mass = body.getMass();
        this.type = body.getBodyType();
        if(body.hasRingSystem()) {
            RingSystemInfo ringInfo = new RingSystemInfo();
            ringInfo.fillRingSystemInfo(body.getRings());
            this.ring = ringInfo;            
        }
    }

    public Length getRadius() {
        return radius;
    }

    public Mass getMass() {
        return mass;
    }
    
    public BodyType getType() {
        return type;
    }

    public RingSystemInfo getRingSystem() {
        return ring;
    }
}
