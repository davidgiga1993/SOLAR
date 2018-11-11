package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AtmosphericComposition {

    @XmlElement
    private List<AtmosphericGas> gases;

    public AtmosphericComposition() {

    }

    public AtmosphericComposition(List<AtmosphericGas> gases) {
        if (gases.size() > 0) {
            this.gases = gases;
            return;
        }
        throw new IllegalArgumentException("There must be at least one gas in this atmospheric composition");
    }

    public List<AtmosphericGas> getListOfAtmosphericGases() {
        return gases;
    }

    public Pressure getOxygenPartialPressure(Pressure atmosphericPressure) {
        for (AtmosphericGas gas : gases) {
            if (gas.isOxygen()) {
                return gas.partialPressure(atmosphericPressure);
            }
        }
        return new Pressure(0f, PressureUnit.PASCAL);
    }

    public Pressure getH2OPartialPressure(Pressure atmosphericPressure) {
        for (AtmosphericGas gas : gases) {
            if (gas.isWaterVapor()) {
                return gas.partialPressure(atmosphericPressure);
            }
        }
        return new Pressure(0f, PressureUnit.PASCAL);
    }
}
