package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class Steering {
    public Vector2 linear;
    public float angular;

    public Steering(Vector2 linear, float angular) {
        this.linear = linear;
        this.angular = angular;
    }

    public Steering(float linearX, float linearY, float angular) {
        this(new Vector2(linearX, linearY), angular);
    }

}
