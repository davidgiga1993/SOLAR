package dhbw.karlsruhe.it.solar.core.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by argannor on 25.03.15.
 */
public class DoubleVector2 {
    private double x;
    private double y;

    public DoubleVector2() {
        x = 0;
        y = 0;
    }

    public DoubleVector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DoubleVector2(DoubleVector2 vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public DoubleVector2(Vector2 vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public Vector2 getVector2() {
        return new Vector2((float) x, (float) y);
    }

    private void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vec) {
        set(vec.x, vec.y);
    }

    public void set(DoubleVector2 vec) {
        set(vec.x, vec.y);
    }

    public DoubleVector2 add(DoubleVector2 vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    public DoubleVector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public DoubleVector2 add(Vector2 vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    public float angle() {
        float angle = (float) Math.atan2(y, x) * MathUtils.radiansToDegrees;
        if (angle < 0) angle += 360;
        return angle;
    }

}
