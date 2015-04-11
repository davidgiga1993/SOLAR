package dhbw.karlsruhe.it.solar.core.physics;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

public class AtmosphericComposition {
    
    @XmlElement
    private List<AtmosphericGas> gases;
    
    public AtmosphericComposition() {
        
    }
    
    public AtmosphericComposition(List<AtmosphericGas> gases) {
        this.gases = gases;        
    }
}
