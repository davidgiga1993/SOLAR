package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.usercontrols.PlanetaryRing.RingType;

public class RingSystemInfo {

    @XmlElement(name = "Mass of Ring System")
    private Mass mass;
    @XmlElement(name = "Innermost Ring")
    protected Length innerRadius;
    @XmlElement(name = "Outermost Ring")
    private Length outerRadius;
    @XmlElement(name = "Type of Ring System")
    private RingType type;
    
    public RingSystemInfo(PlanetaryRing rings) {
        this.mass = rings.getMass();
        this.innerRadius = rings.getInnerRadius();
        this.outerRadius = rings.getRadius();
        this.type = rings.getType();
    }
}
