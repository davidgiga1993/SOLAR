package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Coorbital;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Angle.class, Coorbital.class})
public class OrbitalPropertyInfo {
    
    @XmlElement(name = "Primary")
    private String orbitPrimary;
    @XmlElement(name = "Orbital Radius")
    private Length orbitalRadius;
    @XmlElement(name = "Polar Angle")
    private Angle orbitalAngle;
    @XmlElement(name = "Retrograde")
    private boolean retrograde;
    @XmlElement(name = "Coorbital Info")
    private Coorbital coorbital;

    public OrbitalPropertyInfo(Orbiter orbiter) {
        if(orbiter.isInOrbit()) {
            this.coorbital = orbiter.getCoorbitalInformation();
            if(null==coorbital) {
                this.orbitPrimary = orbiter.getPrimary().getName();
                this.orbitalRadius = orbiter.getOrbitalRadius();
                this.orbitalAngle = orbiter.getOrbitalAngle();
            }
            this.retrograde = orbiter.isInRetrogradeOrbit();               
        }
    }

}
