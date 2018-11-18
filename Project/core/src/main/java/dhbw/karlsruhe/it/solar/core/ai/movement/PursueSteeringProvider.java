package dhbw.karlsruhe.it.solar.core.ai.movement;

import mikera.vectorz.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class PursueSteeringProvider extends ArriveSteeringProvider {

    private Kinematic pursueTarget;
    // 5 days of maximum prediction
    private double maxPrediction = 5;

    public PursueSteeringProvider(double radius, double slowRadius) {
        super(radius, slowRadius);
        target = new Kinematic(new Vector2(0, 0), 0, 0);
    }

    @Override
    public Steering getSteering(final Kinematic character) {
        Vector2 direction = pursueTarget.getPosition().clone();
        direction.sub(character.getPosition());
        double distance = direction.magnitude();

        double speed = character.getSpeed();

        double prediction;
        if (speed <= distance / maxPrediction) {
            prediction = maxPrediction;
        } else {
            prediction = distance / speed;
        }

        Vector2 predictedMovement = pursueTarget.getVelocity().clone();
        predictedMovement.scale(prediction);

        Vector2 predictedPosition = pursueTarget.getPosition().clone();
        predictedPosition.add(predictedMovement);

        target.setPosition(predictedPosition);

        return super.getSteering(character);
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        pursueTarget = newTarget;
    }
}
