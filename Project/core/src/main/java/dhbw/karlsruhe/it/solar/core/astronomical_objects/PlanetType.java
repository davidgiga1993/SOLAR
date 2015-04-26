package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all planets. Governs the type of planet.
 * @author Andi
 * Created 2015-04-05
 */
public class PlanetType extends BodyType{

    @XmlElement(name = "Type")
    private TypeOfPlanet planetType;
    
    public PlanetType() {
        
    }
    
    public PlanetType(TypeOfPlanet planetType) {
        this.planetType = planetType;
    } 
    
    @Override
    public boolean consistsPartiallyOfWaterIce() {
        switch(planetType)        {
            case DWARFPLANET:
                return true;
            default:
                return false;
            }
    }
    
    @Override
    public boolean hasSurface() {
        switch(planetType)        {
            case MARTIAN:
            case MERCURIAN:
            case VENUSIAN:
            case TERRAN:
            case DWARFPLANET:
                return true;
            case JOVIAN:
            case SATURNIAN:
            case NEPTUNIAN:
            case URANIAN:
            default:
                return false;
        }
    }
    
    public TypeOfPlanet getPlanetType() {
        return planetType;
    }
    
    public String resolveTypeName() {
        switch(planetType)        {
        case MARTIAN:
        case MERCURIAN:
        case VENUSIAN:
        case TERRAN:
            return "Terrestrial Planet";
        case JOVIAN:
        case SATURNIAN:
            return "Gas Giant";
        case NEPTUNIAN:
        case URANIAN:
            return "Ice Giant";
        case DWARFPLANET:
            return "Dwarf Planet";
        default:
            return "Anomaly: Unknown Type of Planet";
        }
    }
    
    public enum TypeOfPlanet {
            MERCURIAN,
            VENUSIAN,
            TERRAN,
            MARTIAN,
            DWARFPLANET,
            JOVIAN,
            SATURNIAN,
            URANIAN,
            NEPTUNIAN
        }

    @Override
    public boolean isRounded() {
        return true;
    }
}
