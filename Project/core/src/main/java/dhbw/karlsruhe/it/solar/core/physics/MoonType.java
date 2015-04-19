package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all moons. Governs the type of moon.
 * @author Andi
 * Created 2015-04-05
 */
public class MoonType extends BodyType {

    @XmlElement(name = "Type")
    private TypeOfMoon satelliteType;
    
    public MoonType() {
        
    }
    
    public MoonType(TypeOfMoon satelliteType) {
        this.satelliteType = satelliteType;
    } 
    
    public TypeOfMoon getSatelliteType() {
        return satelliteType;
    }
    
    public enum TypeOfMoon {
        IRREGULAR,
        LUNAR,
        IONIAN,
        EUROPAN,
        GANYMEDIAN,
        CALLISTOAN,
        MIMANTEAN,
        ENCELADEAN,
        TETHYAN,
        DIONEAN,
        RHEAN,
        TITANEAN,
        IAPETIAN,
        MIRANDAN,
        ARIELIAN,
        UMBRELIAN,
        TITANIAN,
        OBERONIAN,
        TRITONIAN
    }

    public String resolveTypeName() {
        switch(satelliteType)        {
            case LUNAR:
            case IONIAN:
                return "Terrestrial Moon";
            case EUROPAN:
            case GANYMEDIAN:
            case CALLISTOAN:
            case MIMANTEAN:
            case ENCELADEAN:
            case TETHYAN:
            case DIONEAN:
            case RHEAN:
            case TITANEAN:
            case IAPETIAN:
            case MIRANDAN:
            case ARIELIAN:
            case UMBRELIAN:
            case TITANIAN:
            case OBERONIAN:
            case TRITONIAN:
                return "Ice Moon";
            case IRREGULAR:
                return "Captured Asteroid";
            default:
                return "Anomaly: Unknown Type of Moon";
        }
    }
}
