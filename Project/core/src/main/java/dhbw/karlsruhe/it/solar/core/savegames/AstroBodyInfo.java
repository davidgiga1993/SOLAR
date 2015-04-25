package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.BodyType;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Atmosphere;
import dhbw.karlsruhe.it.solar.core.physics.Biosphere;
import dhbw.karlsruhe.it.solar.core.physics.Coorbital;
import dhbw.karlsruhe.it.solar.core.physics.Hydrosphere;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.SurfaceTemperature;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({OrbitalPropertyInfo.class, BodyPropertyInfo.class, ColonyInfo.class})
public class AstroBodyInfo {
    
    @XmlElement(name = "Name")
    private String name;
    @XmlElement (name ="BodyProperties")
    private BodyPropertyInfo body;
    @XmlElement (name ="OrbitalProperties")
    private OrbitalPropertyInfo orbit;
    @XmlElement(name = "PlayerColony")
    private ColonyInfo colony;

    public AstroBodyInfo() {
        
    }
    
    public void fillAstroBodyInfo(AstronomicalBody body) {
        this.name = body.getName();
        OrbitalPropertyInfo orbitalInfo = new OrbitalPropertyInfo();
        orbitalInfo.fillOrbitalPropertyInfo(body);
        this.orbit = orbitalInfo;
        BodyPropertyInfo bodyInfo = new BodyPropertyInfo();
        bodyInfo.fillBodyPropertyInfo(body);
        this.body = bodyInfo;
        if(body.isColonized()) {
            ColonyInfo colonyInfo = new ColonyInfo();
            colonyInfo.fillColonyInfo(body.getColony());
            this.colony = colonyInfo;            
        }
    }

    public String getName() {
        return name;
    }

    public boolean isStationary() {
       return orbit.isStationary();
    }

    public String getPrimary() {
        return orbit.getPrimary();
    }

    public Coorbital getCoorbital() {
        return orbit.getCoorbital();
    }

    public boolean isRetrograde() {
        return orbit.isRetrograde();
    }

    public Length getOrbitalRadius() {
        return orbit.getOrbitalRadius();
    }

    public Angle getPolarAngle() {
        return orbit.getPolarAngle();
    }

    public Length getRadius() {
        return body.getRadius();
    }

    public Mass getMass() {
        return body.getMass();
    }

    public BodyType getType() {
        return body.getType();
    }

    public RingSystemInfo getRingSystem() {
        return body.getRingSystem();
    }

    public boolean isColonized() {
        return (null!=colony);
    }
    
    public ColonyInfo getColonyInfo() {
        return colony;
    }

    public Atmosphere getAtmosphere() {
        return body.getAtmosphere();
    }

    public SurfaceTemperature getTemperatures() {
        return body.getTemperatures();
    }

    public Hydrosphere getHydrosphere() {
        return body.getHydrosphere();
    }

    public Biosphere getBiosphere() {
        return body.getBiosphere();
    }

}
