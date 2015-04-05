package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.physics.BodyType;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Mass.class, BodyType.class, PlanetaryRing.class})
public class BodyPropertyInfo {
    
    @XmlElement(name = "Type")
    private BodyType type;
    @XmlElement(name = "Radius")
    private Length radius;
    @XmlElement(name = "Mass")
    private Mass mass;
    @XmlElement(name = "Planetary Rings")
    private RingSystemInfo ring;
    
    public BodyPropertyInfo(AstronomicalBody body) {
        this.radius = body.getRadius();
        this.mass = body.getMass();
        this.type = body.getBodyType();
        if(body.hasRingSystem()) {
            this.ring = new RingSystemInfo(body.getRings());            
        }
    }
}
