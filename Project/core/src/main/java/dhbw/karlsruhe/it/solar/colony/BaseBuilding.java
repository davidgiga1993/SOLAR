package dhbw.karlsruhe.it.solar.colony;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Infrastructure.class})
public abstract class BaseBuilding implements Building {
    
    public final static Credits YEARLY_UPKEEP_INFRASTRUCTURE =  new Credits(5000000);
    
    @XmlElement(name = "Number_Built")
    protected int buildingsBuilt;
    
    @Override
    public int getNumberOfBuildingsOnline() {
        return buildingsBuilt;
    }
    
    @Override
    public int getNumberOfBuildingsBuilt() {
        return buildingsBuilt;
    }
    
    @Override
    public void build() {
        buildingsBuilt++;
    }
    
    @Override
    public Credits payUpKeep(Time deltaT) {
        return new Credits((long)(deltaT.inYears() * getYearlyUpKeep().getNumber()));
    }
    
    protected abstract Credits getYearlyUpKeep();
}
