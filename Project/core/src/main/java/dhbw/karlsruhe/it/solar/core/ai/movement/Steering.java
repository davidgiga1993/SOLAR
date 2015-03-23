package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class Steering {
    private Vector2 linear;
    private float angular;
    private boolean reached = false;

    public Steering(Vector2 linear, float angular) {
        this.linear = linear;
        this.angular = angular;
    }

    public Steering(float linearX, float linearY, float angular) {
        this(new Vector2(linearX, linearY), angular);
    }
    
    public boolean isReached() {
        return reached;
    }
    
    public void setLinearZero() {
        linear.setZero();
    }
    
    public void setAngular(float newAngle) {
        angular = newAngle;
    }
    
    public void setReached() {
        reached = true;
    }
    
    public void resetReached() {
        reached = false;
    }
    
    public void setLinear(Vector2 newVector) {
        linear = newVector;
    }
    
    public float getLengthLinear() {
        return linear.len();
    }
    
    public void normalizeLinear(float maxAcceleration) {
        linear.nor().scl(maxAcceleration);
    }
    
    public Vector2 scaleLinear(float newScalar) {
        return linear.scl(newScalar);    
    }

}
