package dhbw.karlsruhe.it.solar.core.savegames;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.colony.BaseBuilding;
import dhbw.karlsruhe.it.solar.colony.BuildingManager;
import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.colony.ColonyBuildings;
import dhbw.karlsruhe.it.solar.core.resources.Population;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Population.class, BaseBuilding.class})
public class ColonyInfo {
    
    @XmlElement(name="ColonyName")
    private String name;
    @XmlElement(name = "ColonyOwner")
    private String ownerName;
    @XmlElement(name = "Population")
    private Population pop;
    @XmlElement(name = "Colony_Buildings")
    private List<BaseBuilding> buildings;

    public ColonyInfo() {
        
    }
    
    public void fillColonyInfo(Colony colony) {
        this.name = colony.getName();
        this.ownerName = colony.getOwner().getName();
        this.pop = colony.getPopulation();
        this.buildings = colony.getListOfColonyBuildings();      
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

    public ColonyBuildings getBuildings() {
        return new BuildingManager().createFromBuildingList(buildings).generateColonyBuildings();
    }

}
