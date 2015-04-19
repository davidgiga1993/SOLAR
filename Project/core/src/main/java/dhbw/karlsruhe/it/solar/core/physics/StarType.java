package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all stars. Governs the type of star.
 * @author Andi
 * Created 2015-04-05
 */
public class StarType extends BodyType {

    @XmlElement(name = "Type")
    private TypeOfStar starType;
    
    public StarType() {
        
    }
    
    public StarType(TypeOfStar starType) {
        this.starType = starType;
    } 
    
    public TypeOfStar getStarType() {
        return starType;
    }
    
    public enum TypeOfStar {
            OTYPE,
            BTYPE,
            ATYPE,
            FTYPE,
            GTYPE,
            KTYPE,
            MTYPE,
        }

    public String resolveTypeName() {
        switch(starType)        {
            case GTYPE:
                return "G-Type Main-Sequence Star";
            default:
                return "Anomaly: Unknown Type of Star";
        }
    }
}
