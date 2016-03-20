package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;

/**
 * Created by argannor on 19.02.15.
 */
public class OrbitalArriveSteeringProvider extends ArriveSteeringProvider {


    private Orbiter target;
    private float reachedRadius = 75f;

    public OrbitalArriveSteeringProvider(float radius, float slowRadius) {
        super(radius, slowRadius);
    }

    @Override
    public Steering getSteering(final Kinematic character) {
        // Estimate traveling time:
        // Average Distance is the distance to the center of orbit.
        // Max-Distance = distanceToCenter + radius
        // Min-Distance = distanceToCenter - radius
        // so the time to travel there at max speed gives us a very rough estimation
        Vector2 centerOfOrbit = target.getCenterOfOrbit();
        Vector2 directionToCenter = new Vector2(centerOfOrbit).sub(character.getPosition());
        float distanceToCenter = directionToCenter.len();
        float timeToTravel = distanceToCenter / character.getMaxSpeed();

        // Calculate target's position at that moment
        Vector2 futurePosition = target.calculateFuturePosition(timeToTravel);

        // Calculate the distance between the character and the target's future position
        Vector2 directionToFuturePosition = new Vector2(futurePosition).sub(character.getPosition());
        float newDistance = directionToFuturePosition.len();

        // Calculate the time error (e.g. waiting or missing) in this estimation
        float timeError = newDistance - distanceToCenter;
        timeError /= character.getMaxSpeed();

        // Add the time error to our initial estimation and calculate the corrected future position
        futurePosition = target.calculateFuturePosition(timeError + timeToTravel);

        super.target = new Kinematic(futurePosition, 0, target.getKinematic().getMaxSpeed());

        Steering steering = super.getSteering(character);
        if (futurePosition.sub(character.getPosition()).len() < reachedRadius) {
            steering.setReached();
        }

        return steering;
    }

    public void setOrbitingTarget(Orbiter target) {
        this.target = target;
    }

}
