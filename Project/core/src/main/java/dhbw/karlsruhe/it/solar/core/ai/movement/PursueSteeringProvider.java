package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class PursueSteeringProvider extends ArriveSteeringProvider {

    protected Kinematic pursueTarget;
    protected float maxPrediction = 5; // 5 days of maximum prediction

    public PursueSteeringProvider(float maxVelocity, float radius, float slowRadius) {
        super(maxVelocity, radius, slowRadius);
        target = new Kinematic(new Vector2(0,0),0,0);
    }

    @Override
    public Steering getSteering(Kinematic character) {
        Vector2 direction = new Vector2(pursueTarget.position).sub(character.position);
        float distance = direction.len();

        float speed = character.velocity.len();

        float prediction;
        if (speed <= distance / maxPrediction) {
            prediction = maxPrediction;
        } else {
            prediction = distance / speed;
        }

        Vector2 predictedMovement = new Vector2(pursueTarget.velocity).scl(prediction);
        Vector2 predictedPosition = new Vector2(pursueTarget.position).add(predictedMovement);
        target.position = predictedPosition;

        return super.getSteering(character);
    }

    @Override
    public void setTarget(Kinematic newTarget) {
        pursueTarget = newTarget;
    }
}
