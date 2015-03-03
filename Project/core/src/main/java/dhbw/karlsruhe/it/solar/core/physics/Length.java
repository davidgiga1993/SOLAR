package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

/**
 * Created by Arga on 21.11.2014.
 */
public class Length {

    public static final int LUNAR_DISTANCE_IN_KILOMETRES = 384400;
    public static final float ASTRONOMICAL_UNIT_IN_KILOMETRES = 149597870.7f;
    public static final float ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES = 389.17240036f;
    public static final float LIGHT_YEAR_IN_LUNAR_DISTANCES = 24611681.77050156f;
    public static final float PARSEC_IN_LUNAR_DISTANCES = 80272569.66167685f;
    public static final float LIGHT_YEARS_IN_ASTRONOMICAL_UNITS = 63241.0770842f;
    public static final float PARSEC_IN_ASTRONOMICAL_UNITS = 206264.80599999f;
    public static final float PARSEC_IN_LIGHT_YEARS = 3.2615637732f;

    protected Unit unit = Unit.astronomicalUnit;
    protected float value = 0f;
    
    public Length()
    {
    	this.value = 0;
    	this.unit = Unit.kilometres;
    }

    public Length(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public void set(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public float asKilometres() {
        switch(unit) {
            case kilometres:
                return value;
            case lunarDistance:
                return value * LUNAR_DISTANCE_IN_KILOMETRES;
            case astronomicalUnit:
                return value * ASTRONOMICAL_UNIT_IN_KILOMETRES;
            default:
                return Float.NaN;
        }
    }

    public float asLunarDistance() {
        switch(unit) {
            case kilometres:
                return value / LUNAR_DISTANCE_IN_KILOMETRES;
            case lunarDistance:
                return value;
            case astronomicalUnit:
                return value * ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES;
            case lightYear:
                return value * LIGHT_YEAR_IN_LUNAR_DISTANCES;
            case parsec:
                return value * PARSEC_IN_LUNAR_DISTANCES;
            default:
                return Float.NaN;
        }
    }

    public float asAstronomicalUnit() {
        switch(unit) {
            case kilometres:
                return value / ASTRONOMICAL_UNIT_IN_KILOMETRES;
            case lunarDistance:
                return value / ASTRONOMICAL_UNIT_IN_LUNAR_DISTANCES;
            case astronomicalUnit:
                return value;
            case lightYear:
                return value * LIGHT_YEARS_IN_ASTRONOMICAL_UNITS;
            case parsec:
                return value * PARSEC_IN_ASTRONOMICAL_UNITS;
            default:
                return Float.NaN;
        }
    }

    public float asLightYear() {
        switch (unit) {
            case lunarDistance:
                return value / LIGHT_YEAR_IN_LUNAR_DISTANCES;
            case astronomicalUnit:
                return value / LIGHT_YEARS_IN_ASTRONOMICAL_UNITS;
            case lightYear:
                return value;
            case parsec:
                return value * PARSEC_IN_LIGHT_YEARS;
            default:
                return Float.NaN;
        }
    }

    public enum Unit {
        kilometres,
        lunarDistance,
        astronomicalUnit,
        lightYear,
        parsec
    }

	public static Length calculateDistance(SolarActor actorOne, SolarActor actorTwo)
	{
		double directDistance = Math.sqrt( Math.pow(actorTwo.getX() - actorOne.getX(),2) + Math.pow(actorTwo.getY() - actorOne.getY(), 2));
		
		return new Length( (float)directDistance, Unit.kilometres);
	}
}
