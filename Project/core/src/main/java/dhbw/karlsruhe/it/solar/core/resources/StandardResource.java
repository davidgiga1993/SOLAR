package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.physics.Time;

/**
 * Superclass for the standard resources of the game which are local and not capacitive (deplete normally).
 * Contrast with GlobalResource and CapacitiveResource.
 * @author Andi
 * created 2015-05-16
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Population.class})
public abstract class StandardResource extends BaseResource implements StandardResourceInterface {
    
    @XmlElement(name = "Remaining_Fraction_Of_Value_Currently_Produced")
    protected float valueRemainingFraction;
    
    protected abstract void updateProductionStatistic();
    protected abstract void updateProduction(Time deltaT, ResourceDepot productionPlace);
  
    @Override
    public void updateResource(Time deltaT, ResourceDepot productionPlace) {
        updateProductionStatistic();
        updateProduction(deltaT, productionPlace);
    }
    
    @Override
    public long getNumber() {
        return value;
    }
    
    @Override
    public String toString() {
        if(inTrillions(value) > 0.1f) {
            return constructResourceStatement("tr", inTrillions(value));
        }
        if(inBillions(value) > 0.1f) {
            return constructResourceStatement("bi", inBillions(value));
        }
        if(inMillions(value) > 0.1f) {
            return constructResourceStatement("mi", inMillions(value));
        }
        if(inThousands(value) > 0.1f) {
            return constructResourceStatement("k", inThousands(value));
        }
        return constructResourceStatement("", value);   
    }
    
    protected abstract String constructResourceStatement(String unit, float value);
}
