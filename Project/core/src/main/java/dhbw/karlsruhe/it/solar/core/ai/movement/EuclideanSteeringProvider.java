package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * The EuclideanSteeringProvider outputs the euclidean distance vector
 * Created by Arga on 13.02.2015.
 */
public class EuclideanSteeringProvider implements SteeringProvider {

    Steering output = new Steering(0,0,0);
    Vector2 target = new Vector2(0,0);

    float maxVelocity;

    float radius;
    float slowRadius;

    public EuclideanSteeringProvider(float maxVelocity, float radius, float slowRadius) {
        this.maxVelocity = maxVelocity;
        this.radius = radius;
        this.slowRadius = slowRadius;
    }

    @Override
    public Steering getSteering(Kinematic character) {
        Vector2 direction = new Vector2(target).sub(character.position);
        float distance = direction.len();

        if (distance < radius) {
            output.linear.setZero();
            output.angular = character.position.angle();
            return output;
        }

        float desiredVelocity;
        if (distance > slowRadius) {
            desiredVelocity = maxVelocity;
        } else {
            desiredVelocity = maxVelocity * distance / slowRadius;
        }

        output.linear = direction.nor().scl(desiredVelocity);
        output.angular = direction.angle();

        return output;
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        this.target = newTarget.position;
    }
}
