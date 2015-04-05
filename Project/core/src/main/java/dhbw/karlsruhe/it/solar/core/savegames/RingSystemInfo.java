package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing.RingType;

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
}
