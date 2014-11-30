package dhbw.karlsruhe.it.solar.junit;


import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthJUnit {

    Length km = new Length(1, Length.Unit.kilometres);
    Length ld = new Length(1, Length.Unit.lunarDistance);
    Length au = new Length(1, Length.Unit.astronomicalUnit);
    Length ly = new Length(1, Length.Unit.lightYear);
    Length pc = new Length(1, Length.Unit.parsec);

    @Before
    public void setUp() {
        km = new Length(1, Length.Unit.kilometres);
        ld = new Length(1, Length.Unit.lunarDistance);
        au = new Length(1, Length.Unit.astronomicalUnit);
        ly = new Length(1, Length.Unit.lightYear);
        pc = new Length(1, Length.Unit.parsec);
    }

    @After
    public void cleanUp() {

    }

    @Test
    public void testAsKilometres() {
        assertEquals(1, km.asKilometres(), 0.00001f);
        assertEquals(384400, ld.asKilometres(), 0.00001f);
        assertEquals(149597870.7f, au.asKilometres(), 0.00001f);
        assertEquals(Float.NaN, ly.asKilometres(), 0.00001);
        assertEquals(Float.NaN, pc.asKilometres(), 0.00001);

        Length reallyBig = new Length(100000000000000000f, Length.Unit.astronomicalUnit);
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