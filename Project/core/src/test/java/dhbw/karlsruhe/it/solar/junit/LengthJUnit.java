package dhbw.karlsruhe.it.solar.junit;


import dhbw.karlsruhe.it.solar.core.physics.Length;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthJUnit {

    private Length km = new Length(1, Length.DistanceUnit.KILOMETERS);
    private Length ld = new Length(1, Length.DistanceUnit.LUNAR_DISTANCE);
    private Length au = new Length(1, Length.DistanceUnit.ASTRONOMICAL_UNITS);
    private Length ly = new Length(1, Length.DistanceUnit.LIGHTYEAR);
    private Length pc = new Length(1, Length.DistanceUnit.PARSEC);

    @Before
    public void setUp() {
        km = new Length(1, Length.DistanceUnit.KILOMETERS);
        ld = new Length(1, Length.DistanceUnit.LUNAR_DISTANCE);
        au = new Length(1, Length.DistanceUnit.ASTRONOMICAL_UNITS);
        ly = new Length(1, Length.DistanceUnit.LIGHTYEAR);
        pc = new Length(1, Length.DistanceUnit.PARSEC);
    }

    @After
    public void cleanUp() {

    }

    @Test
    public void testAsKilometres() {
        assertEquals(1, km.asKilometers(), 0.00001f);
        assertEquals(384400, ld.asKilometers(), 0.00001f);
        assertEquals(149597870.7f, au.asKilometers(), 0.00001f);
        assertEquals((float)(9.4605284*Math.pow(10,12)), ly.asKilometers(), 0.00001);
        assertEquals(Float.NaN, pc.asKilometers(), 0.00001);

        Length reallyBig = new Length(100000000000000000f, Length.DistanceUnit.ASTRONOMICAL_UNITS);
        assertEquals(14959787070000000000000000.0f, reallyBig.asKilometers(), 0.0001f);
    }


    @Test
    public void testAsLunarDistance() {
        assertEquals(2.6014568158168574401664932362123e-6, km.asLunarDistance(), 0.00001f);
        assertEquals(1, ld.asLunarDistance(), 0.00001f);
      //  assertEquals(149597870.7f, au.asLunarDistance(), 0.00001f);
      //  assertEquals(Float.NaN, ly.asLunarDistance(), 0.00001);
      //  assertEquals(Float.NaN, pc.asLunarDistance(), 0.00001);
    }

}