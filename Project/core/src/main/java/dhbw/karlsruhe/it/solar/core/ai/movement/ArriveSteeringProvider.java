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
    public Steering getSteering(Kinematic character) {
        Vector2 direction = new Vector2(target.position).sub(character.position);
        float distance = direction.len();

        if (distance < radius) {
            output.linear.setZero();
            output.angular = character.position.angle();
            output.reached = true;
            return output;
        }

        float targetSpeed;
        if (distance > slowRadius) {
            targetSpeed = character.maxSpeed;
        } else {
           targetSpeed = character.maxSpeed * distance / slowRadius;
        }

        Vector2 targetVelocity = direction.nor().scl(targetSpeed);

        output.linear = targetVelocity.sub(character.velocity);
        output.linear.scl(1/timeToTarget);

        if (output.linear.len() > character.maxAcceleration) {
            output.linear.nor().scl(character.maxAcceleration);
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
