package dhbw.karlsruhe.it.solar.colony;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

public abstract class BaseBuilding implements Building {
    
    public final static Credits YEARLY_UPKEEP_INFRASTRUCTURE =  new Credits(5000000);
    
    protected int buildingsBuilt;
    
    @Override
    public long getNumberOfBuildingsOnline() {
        return buildingsBuilt;
    }
    
    @Override
    public String getNumberOfBuildingsBuilt() {
        return String.valueOf(buildingsBuilt);
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
