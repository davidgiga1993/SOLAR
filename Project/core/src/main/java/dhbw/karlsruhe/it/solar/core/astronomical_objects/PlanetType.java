package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all planets. Governs the type of planet.
 * @author Andi
 * Created 2015-04-05
 */
public class PlanetType extends BodyType{

    @XmlElement(name = "Type")
    private TypeOfPlanet typeOfPlanet;
    @XmlElement(name = "Texture")
    private TextureTypeOfPlanet textureOfPlanet;
    
    public PlanetType() {
        
    }
    
    public PlanetType(TypeOfPlanet planetMainType, TextureTypeOfPlanet planetTexture) {
        this.typeOfPlanet = planetMainType;
        this.textureOfPlanet = planetTexture;
    } 
    
    @Override
    public boolean consistsPartiallyOfWaterIce() {
        if(typeOfPlanet == TypeOfPlanet.DWARFPLANET) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean hasSurface() {
        switch(typeOfPlanet)        {
            case TERRESTRIAL:
            case DWARFPLANET:
                return true;
            case GASGIANT:
            case ICEGIANT:
            default:
                return false;
        }
    }
    
    @Override
    public String getTexture() {
        switch(textureOfPlanet)        {
            case MERCURIAN:
                return "Mercurian";
            case VENUSIAN:
                return "Venusian";
            case TERRAN:
                return "Terran";
            case MARTIAN:
                return "Martian";
            case JOVIAN:
                return "Jovian";
            case SATURNIAN:
                return "Saturn";
            case URANIAN:
                return "Uranian";
            case NEPTUNIAN:
                return "Neptunian";
            case DWARFPLANET:
                return "DwarfPlanet";
            default:
                return "DwarfPlanet";
        }
    }
    
    public String resolveTypeName() {
        switch(typeOfPlanet)        {
        case TERRESTRIAL:
            return "Terrestrial Planet";
        case GASGIANT:
            return "Gas Giant";
        case ICEGIANT:
            return "Ice Giant";
        case DWARFPLANET:
            return "Dwarf Planet";
        default:
            return "Anomaly: Unknown Type of Planet";
        }
    }

    @Override
    public boolean isColonizable() {
        switch(typeOfPlanet)        {
        case TERRESTRIAL:
        case DWARFPLANET:
            return true;
        case GASGIANT:
        case ICEGIANT:
            return false;
        default:
            return false;
        }
    }
    
    @Override
    public boolean isRounded() {
        return true;
    }
    
    public enum TypeOfPlanet {
        TERRESTRIAL,
        GASGIANT,
        ICEGIANT,
        DWARFPLANET
    }
    
    public enum TextureTypeOfPlanet {
        MERCURIAN,
        VENUSIAN,
        TERRAN,
        MARTIAN,
        JOVIAN,
        SATURNIAN,
        URANIAN,
        NEPTUNIAN,
        DWARFPLANET
    }
}
