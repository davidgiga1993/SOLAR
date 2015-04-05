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
}
