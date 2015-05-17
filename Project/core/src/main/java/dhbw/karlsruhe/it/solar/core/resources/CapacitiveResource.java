package dhbw.karlsruhe.it.solar.core.resources;

/**
 * Superclass for resources with capacitive logic which behave differently from normally depleting resources.
 * @author Andi
 * created 2015-05-16
 */
public abstract class CapacitiveResource extends BaseResource implements CapacitiveResourceInterface {
    
    protected long currentConsumption;
    
    @Override
    public String toString() {
        value = 10000;
        if(0 == value) {
            return noCapacityMessage();
        }
        return consumptionRatio() + " % consumption";  
    }
    
    protected abstract String noCapacityMessage();

    @Override
    public long getCapacity() {
        return value;
    }
    
    @Override
    public long getCurrentConsumption(ResourceDepot consumptionPlace) {
        return currentConsumption;
    }
    
    @Override
    public boolean demandExceedsSupply(ResourceDepot depot) {
        return getCurrentConsumption(depot) >= getCapacity();
    }
    
    @Override
    public void updateCapacity(ResourceDepot productionSite) {
        value = capacityPerUnit() * numberOfUnits(productionSite);
    }
    
    protected abstract long capacityPerUnit();
    protected abstract long numberOfUnits(ResourceDepot productionSite);
    
    private String consumptionRatio() {
        return String.format("%.00f", (float)currentConsumption / (float)value * 100);
    }  
}
