package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Handles the characteristics common to all planets. Governs the type of planet.
 * @author Andi
 * Created 2015-04-05
 */
public class PlanetType extends BodyType {

    private TypeOfPlanet planetType;
    
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
