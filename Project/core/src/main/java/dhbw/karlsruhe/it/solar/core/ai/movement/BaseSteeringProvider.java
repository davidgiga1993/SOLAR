package dhbw.karlsruhe.it.solar.core.ai.movement;

/**
 * Created by Arga on 14.02.2015.
 */
public abstract class BaseSteeringProvider implements SteeringProvider{

    Steering output = new Steering(0,0,0);
    Kinematic target;

    float maxVelocity;

    float radius;
    float slowRadius;

    float timeToTarget = 0.25f;

    public BaseSteeringProvider(float maxVelocity, float radius, float slowRadius) {
        this.maxVelocity = maxVelocity;
        this.radius = radius;
        this.slowRadius = slowRadius;
    }
}
