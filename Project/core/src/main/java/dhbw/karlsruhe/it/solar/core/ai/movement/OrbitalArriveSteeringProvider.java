package dhbw.karlsruhe.it.solar.core.ai.movement;

import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import mikera.vectorz.Vector2;

/**
 * Created by argannor on 19.02.15.
 */
public class OrbitalArriveSteeringProvider extends ArriveSteeringProvider {


    private Orbiter target;
    private double reachedRadius = 75.;

    public OrbitalArriveSteeringProvider(double radius, double slowRadius) {
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
        Vector2 directionToCenter = centerOfOrbit.clone();
        directionToCenter.sub(character.getPosition());
        double distanceToCenter = directionToCenter.magnitude();
        double timeToTravel = distanceToCenter / character.getMaxSpeed();

        // Calculate target's position at that moment
        Vector2 futurePosition = target.calculateFuturePosition(timeToTravel);

        // Calculate the distance between the character and the target's future position
        Vector2 directionToFuturePosition = futurePosition.clone();
        directionToFuturePosition.sub(character.getPosition());
        double newDistance = directionToFuturePosition.magnitude();

        // Calculate the time error (e.g. waiting or missing) in this estimation
        double timeError = newDistance - distanceToCenter;
        timeError /= character.getMaxSpeed();

        // Add the time error to our initial estimation and calculate the corrected future position
        futurePosition = target.calculateFuturePosition(timeError + timeToTravel);

        super.target = new Kinematic(futurePosition, 0., target.getKinematic().getMaxSpeed());

        Steering steering = super.getSteering(character);
        futurePosition.sub(character.getPosition());
        if (futurePosition.magnitude() < reachedRadius) {
            steering.setReached();
        }

        return steering;
    }

    public void setOrbitingTarget(Orbiter target) {
        this.target = target;
    }

}
