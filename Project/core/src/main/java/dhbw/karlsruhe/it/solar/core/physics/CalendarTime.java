package dhbw.karlsruhe.it.solar.core.physics;

import java.util.GregorianCalendar;

/**
 * Created by Arga on 04.03.2015.
 */
public class CalendarTime {

    private double currentDay = 1;
    private GregorianCalendar cal = new GregorianCalendar(2500,GregorianCalendar.JANUARY,1);

    public CalendarTime() {
        cal.setLenient(true);
    }

    public void addDays(float daysToAdd) {
        int temp = (int) (currentDay + daysToAdd) - (int) currentDay;
        cal.add(GregorianCalendar.DATE, temp);
        currentDay += daysToAdd;
    }

    @Override
    public String toString() {
        return cal.get(GregorianCalendar.DATE) + "." + (cal.get(GregorianCalendar.MONTH)+1) + "." + cal.get(GregorianCalendar.YEAR);
        //return String.valueOf((int) currentDay);
    }
}
