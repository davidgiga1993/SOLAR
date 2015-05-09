package dhbw.karlsruhe.it.solar.core.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

/**
 * 
 * @author Andi
 * created 2015-05-04
 */
public abstract class BaseResource implements ResourceInterface {
    
    public final static long THOUSAND = 1000;
    public final static long MILLION = THOUSAND * THOUSAND;
    public final static long BILLION = THOUSAND * MILLION;
    public final static long TRILLION = THOUSAND * BILLION;
    
    @XmlElement(name = "Value")
    protected long value;
    @XmlElement(name = "Values_Of_Last_Month")
    protected List<Long> valuesOfLastMonth = new ArrayList<Long>();
    @XmlElement(name = "Time_Of_Last_Resource_Update")
    protected Time oldGameTime;
    protected float changeLastMonth;
    
    protected abstract String getUnitName();
    
    protected abstract void updateProductionStatistic();
    
    public long getNumber() {
        return value;
    }
    
    public void updateResource() {
        updateProductionStatistic();
    }
    
    public void addToValue(BaseResource resource) {
        value += resource.getNumber();
    }

    @Override
    public String toString() {
        if(inTrillions() > 0.1f) {
            return constructResourceStatement("Trillion", inTrillions());
        }
        if(inBillions() > 0.1f) {
            return constructResourceStatement("Billion", inBillions());
        }
        if(inMillions() > 0.1f) {
            return constructResourceStatement("Million", inMillions());
        }
        if(inThousands() > 0.1f) {
            return constructResourceStatement("k", inThousands());
        }
        return constructResourceStatement("", value);   
    }
    
    protected boolean isANewDay() {        
        if( oldGameTime == null || (int)GameStartStage.GAMETIME.getGameTimeElapsed().inDays() != (int)oldGameTime.inDays()) {
            oldGameTime = new Time(GameStartStage.GAMETIME.getGameTimeElapsed().inDays(),TimeUnit.DAYS);
            return true;
        }
        return false;
    }

    private String constructResourceStatement(String unit, float value) {
        return formatValue(value) + " " + unit + " " + getUnitName() + " ( " + changeLastMonth() + " %)";
    }
    
    private String changeLastMonth() {
        if(changeLastMonth >= 0) {
            return "+" + formatValue(changeLastMonth * 100);            
        }
        return formatValue(changeLastMonth * 100);  
    }

    private String formatValue(float number) {
        return String.format("%.02f", number);
    }
    
    private float inThousands() {
        return (float)value / (float)THOUSAND;
    }
    
    private float inMillions() {
        return (float)value / (float)MILLION;
    }
    
    private float inBillions() {
        return (float)value / (float)BILLION;
    }
    
    private float inTrillions() {
        return (float)value / (float)TRILLION;
    }
}
