package dhbw.karlsruhe.it.solar.core.savegames;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Coorbital;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Angle.class, Coorbital.class})
public class OrbitalPropertyInfo {

    @XmlElement(name = "Primary")
    private String orbitPrimaryName;
    @XmlElement(name = "OrbitalRadius")
    private Length orbitalRadius;
    @XmlElement(name = "PolarAngle")
    private Angle polarAngle;
    @XmlElement(name = "Retrograde")
    private boolean retrograde;
    @XmlElement(name = "CoorbitalInfo")
    private Coorbital coorbital;

    public OrbitalPropertyInfo() {

    }

    public void fillOrbitalPropertyInfo(Orbiter orbiter) {
        if (orbiter.isInOrbit()) {
            this.coorbital = orbiter.getCoorbitalInformation();
            if (null == coorbital) {
                this.orbitPrimaryName = orbiter.getPrimary().getName();
                this.orbitalRadius = orbiter.getOrbitalRadius();
                this.polarAngle = orbiter.getOrbitalAngle();
            }
            this.retrograde = orbiter.isInRetrogradeOrbit();
        }
    }

    public boolean isStationary() {
        return orbitalRadius.asKilometers() == 0;
    }

    public String getPrimary() {
        return orbitPrimaryName;
    }

    public Coorbital getCoorbital() {
        return coorbital;
    }

    public boolean isRetrograde() {
        return retrograde;
    }

    public Length getOrbitalRadius() {
        return orbitalRadius;
    }

    public Angle getPolarAngle() {
        return polarAngle;
    }

}
