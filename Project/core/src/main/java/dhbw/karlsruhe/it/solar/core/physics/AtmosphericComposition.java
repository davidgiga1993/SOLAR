package dhbw.karlsruhe.it.solar.core.physics;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Pressure.PressureUnit;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

public class AtmosphericComposition {
    
    @XmlElement
    private List<AtmosphericGas> gases;
    
    public AtmosphericComposition() {
        
    }
    
    public AtmosphericComposition(List<AtmosphericGas> gases) {
        this.gases = gases;        
    }
    
    public List<AtmosphericGas> getListOfAtmosphericGases() {
        return gases;
    }

    public Pressure getOxygenPartialPressure(Pressure atmosphericPressure) {
        for (AtmosphericGas gas : gases) {
            if( gas.isOxygen() ) {
                return gas.partialPressure(atmosphericPressure);
            }
        }
        return new Pressure(0f,PressureUnit.PASCAL);
    }

    public String listAtmosphericGases() {
        String listOfGases = "";
        for (AtmosphericGas gas : gases) {
            listOfGases += gas.toString();
        }
        return listOfGases;
    }
}
