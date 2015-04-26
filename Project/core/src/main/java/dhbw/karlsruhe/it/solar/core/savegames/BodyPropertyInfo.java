package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.BodyType;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.physics.Albedo;
import dhbw.karlsruhe.it.solar.core.physics.Atmosphere;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere;
import dhbw.karlsruhe.it.solar.core.physics.Hydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Mass.class, BodyType.class, PlanetaryRing.class, Atmosphere.class, Albedo.class, Hydrosphere.class, Biosphere.class})
public class BodyPropertyInfo {
    
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
    @XmlElement(name = "Tidally_Locked_To_Star")
    private boolean tidallyLockedToStar;
    @XmlElement(name = "Hydrosphere")
    private Hydrosphere hydro;
    @XmlElement(name = "Biosphere")
    private Biosphere bio;   
    
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
        this.tidallyLockedToStar = body.isTidallyLockedToStar();
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

    public boolean isTidallyLockedToStar() {
        return tidallyLockedToStar;
    }
}
