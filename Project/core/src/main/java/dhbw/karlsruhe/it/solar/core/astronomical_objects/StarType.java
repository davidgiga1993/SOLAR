package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Handles the characteristics common to all stars. Governs the type of star.
 *
 * @author Andi
 * Created 2015-04-05
 */
public class StarType extends BodyType {

    @XmlElement(name = "Morgan_Keenan_Spectral_Type")
    private MorganKeenanSpectralType sprectralType;
    @XmlElement(name = "Spectral_Type_Subdivision")
    private SpectralTypeSubdivision spectral_subdivision;
    @XmlElement(name = "Luminosity_Class")
    private LuminosityClass luminosityClass;

    public StarType() {

    }

    public StarType(MorganKeenanSpectralType sprectralType, SpectralTypeSubdivision spectral_subdivision, LuminosityClass luminosityClass) {
        this.sprectralType = sprectralType;
        this.spectral_subdivision = spectral_subdivision;
        this.luminosityClass = luminosityClass;
    }

    @Override
    public boolean hasSurface() {
        return false;
    }

    @Override
    public boolean consistsPartiallyOfWaterIce() {
        return false;
    }

    public MorganKeenanSpectralType getSpectralType() {
        return sprectralType;
    }

    public String resolveTypeName() {
        return getSpectralTypeName() + getSpectralTypeSubdivisionName() + "-Type " + getLuminosityClassName() + " Star";
    }

    private String getSpectralTypeName() {
        switch (sprectralType) {
            case O:
                return "O";
            case B:
                return "B";
            case A:
                return "A";
            case F:
                return "F";
            case G:
                return "G";
            case K:
                return "K";
            case M:
                return "M";
            default:
                return "Unknown";
        }
    }

    private String getSpectralTypeSubdivisionName() {
        switch (spectral_subdivision) {
            case ZERO:
                return "0";
            case ONE:
                return "1";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            default:
                return "Unknown";
        }
    }

    private String getLuminosityClassName() {
        switch (luminosityClass) {
            case HYPERGIANT:
                return "Hypergiant";
            case SUPERGIANT:
                return "Supergiant";
            case BRIGHT_GIANT:
                return "Bright Giant";
            case REGULAR_GIANT:
                return "Giant";
            case SUB_GIANT:
                return "Subgiant";
            case MAIN_SEQUENCE_STAR:
                return "Dwarf";
            case SUB_DWARF:
                return "Sub Dwarf";
            case WHITE_DWARF:
                return "White Dwarf";
            default:
                return "Unknown";
        }
    }

    public enum MorganKeenanSpectralType {
        O,
        B,
        A,
        F,
        G,
        K,
        M,
    }

    public enum SpectralTypeSubdivision {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE
    }

    public enum LuminosityClass {
        HYPERGIANT,
        SUPERGIANT,
        BRIGHT_GIANT,
        REGULAR_GIANT,
        SUB_GIANT,
        MAIN_SEQUENCE_STAR,
        SUB_DWARF,
        WHITE_DWARF
    }

    public SpectralTypeSubdivision getSpectralSubdivision() {
        return spectral_subdivision;
    }

    @Override
    public boolean isRounded() {
        return true;
    }

    @Override
    public String getTexture() {
        switch (sprectralType) {
            case G:
                return "GTypeMainSequence";
            default:
                return "GTypeMainSequence";
        }
    }

    @Override
    public boolean isColonizable() {
        return false;
    }
}
