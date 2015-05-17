package dhbw.karlsruhe.it.solar.colony;

import dhbw.karlsruhe.it.solar.core.resources.Credits;

public abstract class BaseBuilding implements Building {
    
    public final static Credits YEARLY_UPKEEP_INFRASTRUCTURE =  new Credits(5000000);
    
    private int buildingsBuilt;
    
    @Override
    public long getNumberOfBuildingsOnline() {
        return buildingsBuilt;
    }
}
