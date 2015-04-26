package dhbw.karlsruhe.it.solar.core.astronomical_objects;

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
    
    @Override
    public boolean hasSurface() {
        return true;
    }
    
    @Override
    public boolean consistsPartiallyOfWaterIce() {
        switch(satelliteType)        {
            case LUNAR:
            case IONIAN:
                return false;
            default:
                return true;
        }
    }
    
    public MoonType(TypeOfMoon satelliteType) {
        this.satelliteType = satelliteType;
    } 
    
    public TypeOfMoon getSatelliteType() {
        return satelliteType;
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
                return "Irregular Shaped";
            default:
                return "Anomaly: Unknown Type of Moon";
        }
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

    @Override
    public boolean isRounded() {
        switch(satelliteType)        {
            case LUNAR:
            case IONIAN:
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
                return true;
            case IRREGULAR:
            default:
                return false;
        }
    }
}
