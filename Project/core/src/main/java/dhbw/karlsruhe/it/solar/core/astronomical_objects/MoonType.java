package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all moons. Governs the type of moon.
 * @author Andi
 * Created 2015-04-05
 */
public class MoonType extends BodyType {

    @XmlElement(name = "Type")
    private TypeOfMoon typeOfMoon;
    @XmlElement(name = "Texture")
    private TextureTypeOfMoon textureOfMoon;
    
    public MoonType() {
        
    }
    
    public MoonType(TypeOfMoon typeOfMoon, TextureTypeOfMoon textureOfMoon) {
        this.typeOfMoon = typeOfMoon;
        this.textureOfMoon = textureOfMoon;
    } 
    
    @Override
    public boolean hasSurface() {
        return true;
    }
    
    @Override
    public boolean consistsPartiallyOfWaterIce() {
        switch(typeOfMoon)        {
            case TERRESTRIAL_MOON:
                return false;
            case ICE_MOON:
            case IRREGULAR_SHAPED:
            default:
                return true;
        }
    }
    
    public TypeOfMoon getTypeOfMoon() {
        return typeOfMoon;
    }
    
    public String resolveTypeName() {
        switch(typeOfMoon)        {
            case TERRESTRIAL_MOON:
                return "Terrestrial Moon";
            case ICE_MOON:
                return "Ice Moon";
            case IRREGULAR_SHAPED:
                return "Irregular Shaped";
            default:
                return "Anomaly: Unknown Type of Moon";
        }
    }

    @Override
    public boolean isRounded() {
        switch(typeOfMoon)        {
            case TERRESTRIAL_MOON:
            case ICE_MOON:
                return true;
            case IRREGULAR_SHAPED:
            default:
                return false;
        }
    }

    @Override
    public String getTexture() {
        switch(textureOfMoon)        {
            case LUNAR:
                return "Lunar";
            case IONIAN:
                return "Ionian";
            case EUROPAN:
                return "Europan";
            case GANYMEDIAN:
                return "Ganymedian";
            case CALLISTOAN:
                return "Callistoan";
            case MIMANTEAN:
                return "Mimantean";
            case ENCELADEAN:
                return "Enceladean";
            case TETHYAN:
                return "Tethyan";
            case DIONEAN:
                return "Dionean";
            case RHEAN:
                return "Rhean";
            case TITANEAN:
                return "Titanean";
            case IAPETIAN:
                return "Iapetian";
            case MIRANDAN:
                return "Mirandan";
            case ARIELIAN:
                return "Arielian";
            case UMBRELIAN:
                return "Umbrelian";
            case TITANIAN:
                return "Titanian";
            case OBERONIAN:
                return "Oberonian";
            case TRITONIAN:
                return "Tritonian";
            case PHOEBE_DEFAULT_IRREGULAR:
                return "IrregularSatellite";               
            default:
                return "IrregularSatellite";
        }
    }
    
    public enum TextureTypeOfMoon {
        PHOEBE_DEFAULT_IRREGULAR,
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
    
    public enum TypeOfMoon {
        TERRESTRIAL_MOON,
        ICE_MOON,
        IRREGULAR_SHAPED
    }
}
