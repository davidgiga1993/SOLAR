package dhbw.karlsruhe.it.solar.core.savegames;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.BodyType;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.physics.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Mass.class, BodyType.class, PlanetaryRing.class, Atmosphere.class, Albedo.class, Hydrosphere.class, Biosphere.class, Time.class})
class BodyPropertyInfo {
    
    @XmlElement(name = "Type")
    private BodyType type;
    @XmlElement(name = "Radius")
    private Length radius;
    @XmlElement(name = "Mass")
    private Mass mass;
    @XmlElement(name = "PlanetaryRings")
    private RingSystemInfo ring;
    @XmlElement(name = "Atmosphere")
    private Atmosphere atmosphere;
    @XmlElement(name = "Albedo")
    private Albedo albedo;
    @XmlElement(name = "Tidally_Locked")
    private boolean tidallyLocked;
    @XmlElement(name = "Hydrosphere")
    private Hydrosphere hydro;
    @XmlElement(name = "Biosphere")
    private Biosphere bio;  
    @XmlElement(name = "Sidereal_Rotation_Period")
    private Time rotation;   
    
    public BodyPropertyInfo() {
        
    }
    
    public void fillBodyPropertyInfo(AstronomicalBody body) {
        this.radius = body.getRadius();
        this.mass = body.getMass();
        this.type = body.getBodyType();
        this.atmosphere = body.getAtmosphere();
        this.albedo = body.getAlbedo();
        this.hydro = body.getHydrosphere();
        this.bio = body.getBiosphere();
        this.tidallyLocked = body.isTidallyLocked();
        if(body.getRotationPeriod() != body.getOrbitalPeriod()) {
            this.rotation = body.getRotationPeriod();            
        }
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

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Albedo getAlbedo() {
        return albedo;
    }

    public Hydrosphere getHydrosphere() {
        return hydro;
    }

    public Biosphere getBiosphere() {
        return bio;
    }

    public boolean isTidallyLocked() {
        return tidallyLocked;
    }

    public Time getSiderealRotationPeriod() {
        return rotation;
    }
}
