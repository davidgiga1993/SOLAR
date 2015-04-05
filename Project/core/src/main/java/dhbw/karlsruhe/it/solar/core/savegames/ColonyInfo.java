package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Population.class})
public class ColonyInfo {
    
    @XmlElement(name="Colony Name")
    private String name;
    @XmlElement(name = "Colony Owner")
    private String ownerName;
    @XmlElement(name = "Population")
    private Population pop;

    public ColonyInfo(Colony colony) {
        this.name = colony.getName();
        this.ownerName = colony.getOwner().getName();
        this.pop = colony.getPopulation();
        
    }

}
