package dhbw.karlsruhe.it.solar.core.ai.movement;


import mikera.vectorz.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class Steering {
    private Vector2 linear;
    private double angular;
    private boolean reached = false;

    private Steering(Vector2 linear, double angular) {
        this.linear = linear;
        this.angular = angular;
    }

    public Steering(double linearX, double linearY, double angular) {
        this(new Vector2(linearX, linearY), angular);
    }

    public boolean isReached() {
        return reached;
    }

    public void setLinearZero() {
        linear.setValues(0, 0);
    }

    public void setAngular(double newAngle) {
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

    public double getLengthLinear() {
        return linear.magnitude();
    }

    public void normalizeLinear(double maxAcceleration) {
        linear.normalise();
        linear.scale(maxAcceleration);
    }

    public Vector2 scaleLinear(double newScalar) {
        linear.scale(newScalar);
        return linear;
    }

}
