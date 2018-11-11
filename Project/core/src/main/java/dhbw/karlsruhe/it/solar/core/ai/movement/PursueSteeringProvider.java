package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class PursueSteeringProvider extends ArriveSteeringProvider {

    private Kinematic pursueTarget;
    // 5 days of maximum prediction
    private float maxPrediction = 5;

    public PursueSteeringProvider(float radius, float slowRadius) {
        super(radius, slowRadius);
        target = new Kinematic(new Vector2(0, 0), 0, 0);
    }

    @Override
    public Steering getSteering(final Kinematic character) {
        Vector2 direction = new Vector2(pursueTarget.getPosition()).sub(character.getPosition());
        float distance = direction.len();

        float speed = character.getSpeed();

        float prediction;
        if (speed <= distance / maxPrediction) {
            prediction = maxPrediction;
        } else {
            prediction = distance / speed;
        }

        Vector2 predictedMovement = new Vector2(pursueTarget.getVelocity()).scl(prediction);
        Vector2 predictedPosition = new Vector2(pursueTarget.getPosition()).add(predictedMovement);
        target.setPosition(predictedPosition);

        return super.getSteering(character);
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        pursueTarget = newTarget;
    }
}
