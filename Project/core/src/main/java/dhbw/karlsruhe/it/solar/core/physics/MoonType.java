package dhbw.karlsruhe.it.solar.core.physics;

/**
 * Handles the characteristics common to all moons. Governs the type of moon.
 * @author Andi
 * Created 2015-04-05
 */
public class MoonType extends BodyType {

    private TypeOfMoon satelliteType;
    
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
}
