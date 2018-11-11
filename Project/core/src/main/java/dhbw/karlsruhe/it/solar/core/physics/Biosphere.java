package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlElement;


public class Biosphere {

    @XmlElement(name = "Biosphere_Coverage")
    private float bioCover;
    @XmlElement(name = "Type_of_Biosphere")
    private BiosphereType type;

    public Biosphere() {

    }

    public Biosphere(BiosphereType bioType, float biosphereCoverage) {
        if (0 <= biosphereCoverage && 1 >= biosphereCoverage) {
            this.bioCover = biosphereCoverage;
            this.type = bioType;
            return;
        }
        throw new IllegalArgumentException("Biosphere Coverage must lie between 0 and 1!");
    }

    public BiosphereType getBioType() {
        return type;
    }

    public float getBioCover() {
        return bioCover;
    }

    public enum BiosphereType {
        TERRAN,
        ALIEN
    }

    private float compatibilityWithTerranBiomes() {
        switch (type) {
            case ALIEN:
                return 0.5f;
            case TERRAN:
                return 1f;
            default:
                return 0;
        }
    }

    public float getUseableBioCover() {
        return bioCover * compatibilityWithTerranBiomes();
    }

    @Override
    public String toString() {
        return "covers " + formatValue() + " %";
    }

    private String formatValue() {
        return String.format("%.00f", bioCover * 100);
    }
}
