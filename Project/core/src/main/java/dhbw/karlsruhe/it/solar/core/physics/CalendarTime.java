package dhbw.karlsruhe.it.solar.core.physics;

import java.util.GregorianCalendar;

import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;

/**
 * Created by Arga on 04.03.2015.
 */
public class CalendarTime {

    private Time currentDay = new Time(0,TimeUnit.DAYS);
    private GregorianCalendar cal = new GregorianCalendar(2500,GregorianCalendar.JANUARY,1);

    public CalendarTime() {
        cal.setLenient(true);
    }

    public void addTime(Time daysToAdd) {
        int temp = (int) (currentDay.inDays() + daysToAdd.inDays()) - (int) currentDay.inDays();
        cal.add(GregorianCalendar.DATE, temp);
        currentDay.add(daysToAdd);
    }

    @Override
    public String toString() {
        return cal.get(GregorianCalendar.DATE) + "." + (cal.get(GregorianCalendar.MONTH)+1) + "." + cal.get(GregorianCalendar.YEAR);
        //return String.valueOf((int) currentDay);
    }

    public void initGameTime(Time gameTimeElapsed) {
        this.currentDay = gameTimeElapsed;
        cal.add(GregorianCalendar.DATE, (int)currentDay.inDays());
    }

    public Time getGameTimeElapsed() {
        return currentDay;
    }

    public void reset() {
        currentDay = new Time(0,TimeUnit.DAYS);
        cal = new GregorianCalendar(2500,GregorianCalendar.JANUARY,1);
    }
}
