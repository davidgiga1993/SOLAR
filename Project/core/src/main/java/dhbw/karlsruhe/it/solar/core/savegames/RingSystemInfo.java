package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing.RingType;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;

public class RingSystemInfo {

    @XmlElement(name = "MassOfRingSystem")
    private Mass mass;
    @XmlElement(name = "InnermostRing")
    protected Length innerRadius;
    @XmlElement(name = "OutermostRing")
    private Length outerRadius;
    @XmlElement(name = "TypeOfRingSystem")
    private RingType type;
 
    public RingSystemInfo() {
        
    }
    
    public void fillRingSystemInfo(PlanetaryRing rings) {
        this.mass = rings.getMass();
        this.innerRadius = rings.getInnerRadius();
        this.outerRadius = rings.getRadius();
        this.type = rings.getType();
    }

    public Mass getMass() {
        return mass;
    }

    public Length getInnerRadius() {
        return innerRadius;
    }
    
    public Length getOuterRadius() {
        return outerRadius;
    }
    
    public RingType getType() {
        return type;
    }
}
