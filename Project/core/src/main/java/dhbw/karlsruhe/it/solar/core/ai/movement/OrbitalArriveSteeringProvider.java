package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

/**
 * Created by argannor on 19.02.15.
 */
public class OrbitalArriveSteeringProvider extends ArriveSteeringProvider {


    protected AstronomicalBody target;
    protected float reachedRadius = 75f;

    public OrbitalArriveSteeringProvider(float radius, float slowRadius) {
        super(radius, slowRadius);
    }

    @Override
    public Steering getSteering(Kinematic character) {
        // Estimate traveling time:
        // Average Distance is the distance to the center of orbit.
        // Max-Distance = distanceToCenter + radius
        // Min-Distance = distanceToCenter - radius
        // so the time to travel there at max speed gives us a very rough estimation
        Vector2 centerOfOrbit = target.getCenterOfOrbit();
        Vector2 directionToCenter = new Vector2(centerOfOrbit).sub(character.position);
        float distanceToCenter = directionToCenter.len();
        float timeToTravel = distanceToCenter / character.maxSpeed;

        // Calculate target's position at that moment
        Vector2 futurePosition = target.calculateFuturePosition(timeToTravel);

        // Calculate the distance between the character and the target's future position
        Vector2 directionToFuturePosition = new Vector2(futurePosition).sub(character.position);
        float newDistance = directionToFuturePosition.len();

        // Calculate the time error (e.g. waiting or missing) in this estimation
        float timeError = newDistance - distanceToCenter;
        timeError /= character.maxSpeed;

        // Add the time error to our initial estimation and calculate the corrected future position
        futurePosition = target.calculateFuturePosition(timeError + timeToTravel);

        super.target = new Kinematic(futurePosition, 0, target.getKinematic().maxSpeed);

        Steering steering = super.getSteering(character);
        if (futurePosition.sub(character.position).len() < reachedRadius) {
            steering.reached = true;
        }

        return steering;
    }

    public void setOrbitingTarget(AstronomicalBody target) {
        this.target = target;
    }

}
