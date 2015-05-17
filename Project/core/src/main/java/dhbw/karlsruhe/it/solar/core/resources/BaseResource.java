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
public abstract class BaseResource implements BaseResourceInterface {
    
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
    @XmlElement(name = "Change_Last_Month")
    protected float changeLastMonth;
    
    protected abstract String constructResourceStatement(String unit, float value);

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
    
    protected boolean isANewDay() {        
        if( oldGameTime == null || (int)GameStartStage.GAMETIME.getGameTimeElapsed().inDays() != (int)oldGameTime.inDays()) {
            oldGameTime = new Time(GameStartStage.GAMETIME.getGameTimeElapsed().inDays(),TimeUnit.DAYS);
            return true;
        }
        return false;
    }

    protected void updateValuesOfLastMonthList() {
        valuesOfLastMonth.add(value);
        if(valuesOfLastMonth.size()>31) {
            valuesOfLastMonth.remove(0);
        }
    }

    protected String formatValue(float number) {
        return String.format("%.02f", number);
    }
    
    protected float inThousands(float value) {
        return value / (float)THOUSAND;
    }
    
    protected float inMillions(float value) {
        return value / (float)MILLION;
    }
    
    protected float inBillions(float value) {
        return value / (float)BILLION;
    }
    
    protected float inTrillions(float value) {
        return value / (float)TRILLION;
    }
}
