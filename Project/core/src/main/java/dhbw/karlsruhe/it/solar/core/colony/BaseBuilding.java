package dhbw.karlsruhe.it.solar.core.colony;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Infrastructure.class})
public abstract class BaseBuilding implements Building {

    final static int THOUSAND = 1000;

    @XmlElement(name = "Number_Built")
    int buildingsBuilt;

    @Override
    public long getNumberOfBuildingsOnline() {
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
        return new Credits((long) (deltaT.inYears() * getYearlyUpKeep().getNumber()));
    }

    protected abstract Credits getYearlyUpKeep();
}
