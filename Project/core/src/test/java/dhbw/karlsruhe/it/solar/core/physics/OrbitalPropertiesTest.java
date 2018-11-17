package dhbw.karlsruhe.it.solar.core.physics;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.Vector2;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class OrbitalPropertiesTest {

    @Test
    public void calculateOrbitalPositionX() {
        var star = new Sun();
        // Eris-like
        var testee = new OrbitalProperties(star, new Length(67.668f, Length.DistanceUnit.ASTRONOMICAL_UNITS), new Angle(80, Angle.AngularUnit.DEGREE));
        // 1:1 scale
        var radiusInPixels = 506149.44f;
        Angle deltaAlpha = new Angle(1, Angle.AngularUnit.DEGREE);

        Vector2 probe = testee.getOrbitalPositionTotal(radiusInPixels, deltaAlpha);

        MathContext context = new MathContext(100);

        BigDecimal radius = BigDecimal.valueOf(radiusInPixels);
        BigDecimal angle = BigDecimal.valueOf(Math.toRadians(testee.getOrbitalAngle().inDegrees() + deltaAlpha.inDegrees()));
        BigDecimal cosine = BigDecimalMath.cos(angle, context);
        BigDecimal center = BigDecimal.valueOf(star.getX() + star.getWidth() / 2);
        BigDecimal reference = radius.multiply(cosine).add(center);

        BigDecimal delta = reference.subtract(BigDecimal.valueOf(probe.x)).abs();
        System.out.println(delta);
        assertTrue(delta.doubleValue() < 0.1);
    }

    private static class Sun implements CenterOfOrbit {
        @Override
        public float getX() {
            return 0;
        }

        @Override
        public float getWidth() {
            return 0;
        }

        @Override
        public float getY() {
            return 0;
        }

        @Override
        public float getHeight() {
            return 0;
        }

        @Override
        public Mass getMass() {
            return new Mass(1, Mass.MassUnit.SOLAR_MASS);
        }
    }
}