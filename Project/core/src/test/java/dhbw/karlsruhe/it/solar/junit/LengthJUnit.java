package dhbw.karlsruhe.it.solar.junit;


import dhbw.karlsruhe.it.solar.core.physics.Length;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthJUnit {

    Length km = new Length(1, Length.Unit.KILOMETERS);
    Length ld = new Length(1, Length.Unit.LUNAR_DISTANCE);
    Length au = new Length(1, Length.Unit.ASTRONOMICAL_UNITS);
    Length ly = new Length(1, Length.Unit.LIGHTYEAR);
    Length pc = new Length(1, Length.Unit.PARSEC);

    @Before
    public void setUp() {
        km = new Length(1, Length.Unit.KILOMETERS);
        ld = new Length(1, Length.Unit.LUNAR_DISTANCE);
        au = new Length(1, Length.Unit.ASTRONOMICAL_UNITS);
        ly = new Length(1, Length.Unit.LIGHTYEAR);
        pc = new Length(1, Length.Unit.PARSEC);
    }

    @After
    public void cleanUp() {

    }

    @Test
    public void testAsKilometres() {
        assertEquals(1, km.asKilometres(), 0.00001f);
        assertEquals(384400, ld.asKilometres(), 0.00001f);
        assertEquals(149597870.7f, au.asKilometres(), 0.00001f);
        assertEquals((float)(9.4605284*Math.pow(10,12)), ly.asKilometres(), 0.00001);
        assertEquals(Float.NaN, pc.asKilometres(), 0.00001);

        Length reallyBig = new Length(100000000000000000f, Length.Unit.ASTRONOMICAL_UNITS);
        assertEquals(14959787070000000000000000.0f, reallyBig.asKilometres(), 0.0001f);
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