package dhbw.karlsruhe.it.solar.core.utils;

import mikera.vectorz.Vector2;

public class VectorUtils {

    public static Vector2 convert(com.badlogic.gdx.math.Vector2 lowPrecisionVector2) {
        if(lowPrecisionVector2 == null) {
            return new Vector2(0, 0);
        }
        return new Vector2(lowPrecisionVector2.x, lowPrecisionVector2.y);
    }

}
