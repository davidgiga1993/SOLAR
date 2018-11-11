package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class ArriveSteeringProvider implements SteeringProvider {

    Kinematic target;
    private Steering output = new Steering(0, 0, 0);
    private float radius;
    private float slowRadius;

    private float timeToTarget = 0.25f;

    public ArriveSteeringProvider(float radius, float slowRadius) {
        this.radius = radius;
        this.slowRadius = slowRadius;
    }


    @Override
    public Steering getSteering(final Kinematic character) {
        Vector2 direction = new Vector2(target.getPosition()).sub(character.getPosition());
        float distance = direction.len();

        if (distance < radius) {
            output.setLinearZero();
            output.setAngular(character.getAngleOfPosition());
            output.setReached();
            return output;
        }

        float targetSpeed;
        if (distance > slowRadius) {
            targetSpeed = character.getMaxSpeed();
        } else {
            targetSpeed = character.getMaxSpeed() * distance / slowRadius;
        }

        Vector2 targetVelocity = direction.nor().scl(targetSpeed);

        output.setLinear(targetVelocity.sub(character.getVelocity()));
        output.scaleLinear(1 / timeToTarget);

        if (output.getLengthLinear() > character.getMaxAcceleration()) {
            output.normalizeLinear(character.getMaxAcceleration());
        }

        output.setAngular(direction.angle());

        output.resetReached();
        return output;
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        this.target = newTarget;
    }

}
