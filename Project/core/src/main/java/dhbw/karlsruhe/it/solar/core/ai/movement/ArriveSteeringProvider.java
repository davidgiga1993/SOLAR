package dhbw.karlsruhe.it.solar.core.ai.movement;

import mikera.vectorz.AVector;
import mikera.vectorz.Vector2;

import static dhbw.karlsruhe.it.solar.core.utils.MathConstants.X_AXIS;

/**
 * Created by Arga on 13.02.2015.
 */
public class ArriveSteeringProvider implements SteeringProvider {

    Kinematic target;
    private Steering output = new Steering(0, 0, 0);
    private double radius;
    private double slowRadius;

    private double timeToTarget = 0.25f;

    public ArriveSteeringProvider(double radius, double slowRadius) {
        this.radius = radius;
        this.slowRadius = slowRadius;
    }


    @Override
    public Steering getSteering(final Kinematic character) {
        AVector direction = target.getPosition().subCopy(character.getPosition());
//        Vector2 direction = new Vector2(target.getPosition()).sub(character.getPosition());
        double distance = direction.magnitude();

        if (distance < radius) {
            output.setLinearZero();
            output.setAngular(character.getAngleOfPosition());
            output.setReached();
            return output;
        }

        double targetSpeed;
        if (distance > slowRadius) {
            targetSpeed = character.getMaxSpeed();
        } else {
            targetSpeed = character.getMaxSpeed() * distance / slowRadius;
        }

        AVector targetVelocity = direction.normaliseCopy();
        targetVelocity.scale(targetSpeed);
        targetVelocity.sub(character.getVelocity());

        output.setLinear(new Vector2(targetVelocity.get(0), targetVelocity.get(1)));
        output.scaleLinear(1 / timeToTarget);

        if (output.getLengthLinear() > character.getMaxAcceleration()) {
            output.normalizeLinear(character.getMaxAcceleration());
        }

        output.setAngular(direction.angle(X_AXIS));

        output.resetReached();
        return output;
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        this.target = newTarget;
    }

}
