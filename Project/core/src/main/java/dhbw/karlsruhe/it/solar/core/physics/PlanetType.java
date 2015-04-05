package dhbw.karlsruhe.it.solar.core.physics;

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
    
    public TypeOfPlanet getPlanetType() {
        return planetType;
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
}
