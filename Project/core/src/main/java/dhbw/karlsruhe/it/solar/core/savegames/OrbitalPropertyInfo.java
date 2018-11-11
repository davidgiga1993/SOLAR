package dhbw.karlsruhe.it.solar.core.savegames;

import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.CoOrbital;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Angle.class, CoOrbital.class})
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
    private CoOrbital coOrbital;

    public OrbitalPropertyInfo() {

    }

    public void fillOrbitalPropertyInfo(Orbiter orbiter) {
        if (orbiter.isInOrbit()) {
            this.coOrbital = orbiter.getCoOrbitalInformation();
            if (null == coOrbital) {
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

    public CoOrbital getCoOrbital() {
        return coOrbital;
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
