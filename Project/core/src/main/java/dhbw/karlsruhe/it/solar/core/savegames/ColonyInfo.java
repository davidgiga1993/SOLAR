package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.player.Colony;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Population.class})
public class ColonyInfo {
    
    @XmlElement(name="ColonyName")
    private String name;
    @XmlElement(name = "ColonyOwner")
    private String ownerName;
    @XmlElement(name = "Population")
    private Population pop;

    public ColonyInfo() {
        
    }
    
    public void fillColonyInfo(Colony colony) {
        this.name = colony.getName();
        this.ownerName = colony.getOwner().getName();
        this.pop = colony.getPopulation();
        
    }

    public String getColonyName() {
        return name;
    }

    public String getNameOfOwner() {
        return ownerName;
    }

    public Population getPopulation() {
        return pop;
    }

}
