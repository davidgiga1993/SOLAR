package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.BodyType;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.physics.Atmosphere;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere;
import dhbw.karlsruhe.it.solar.core.physics.Hydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceTemperature;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Length.class, Mass.class, BodyType.class, PlanetaryRing.class, Atmosphere.class, SurfaceTemperature.class, Hydrosphere.class, Biosphere.class})
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
    @XmlElement(name = "Surface_Temperatures")
    private SurfaceTemperature temperatures;
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
        this.temperatures = body.getTemperatures();
        this.hydro = body.getHydrosphere();
        this.bio = body.getBiosphere();
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

    public SurfaceTemperature getTemperatures() {
        return temperatures;
    }

    public Hydrosphere getHydrosphere() {
        return hydro;
    }

    public Biosphere getBiosphere() {
        return bio;
    }
}
