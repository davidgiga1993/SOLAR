package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

public class OrbitalPropertyInfo {
    
    @XmlElement(name = "Primary")
    private String orbitPrimary;
    @XmlElement(name = "Orbital Radius")
    private Length orbitalRadius;
    @XmlElement(name = "Polar Angle")
    private Angle orbitalAngle;

    public OrbitalPropertyInfo(Orbiter orbiter) {
        if(orbiter.isInOrbit()) {
            this.orbitPrimary = orbiter.getPrimary().getName();
            this.orbitalRadius = orbiter.getOrbitalRadius();
            this.orbitalAngle = orbiter.getOrbitalAngle();            
        }
    }

}
