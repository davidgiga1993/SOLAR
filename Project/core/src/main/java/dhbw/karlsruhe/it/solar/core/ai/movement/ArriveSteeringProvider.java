package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class ArriveSteeringProvider implements SteeringProvider {

    Steering output = new Steering(0,0,0);
    Kinematic target;

    float radius;
    float slowRadius;

    float timeToTarget = 0.25f;

    public ArriveSteeringProvider(float radius, float slowRadius) {
        this.radius = radius;
        this.slowRadius = slowRadius;
    }


    @Override
    public Steering getSteering(final Kinematic character) {
        Vector2 direction = new Vector2(target.getPosition()).sub(character.getPosition());
        float distance = direction.len();

        if (distance < radius) {
            output.linear.setZero();
            output.angular = character.getAngleOfPosition();
            output.reached = true;
            return output;
        }

        float targetSpeed;
        if (distance > slowRadius) {
            targetSpeed = character.getMaxSpeed();
        } else {
           targetSpeed = character.getMaxSpeed() * distance / slowRadius;
        }

        Vector2 targetVelocity = direction.nor().scl(targetSpeed);

        output.linear = targetVelocity.sub(character.getVelocity());
        output.linear.scl(1/timeToTarget);

        if (output.linear.len() > character.getMaxAcceleration()) {
            output.linear.nor().scl(character.getMaxAcceleration());
        }

        output.angular = direction.angle();

        output.reached = false;
        return output;
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        this.target = newTarget;
    }

}
