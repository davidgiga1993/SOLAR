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
        // Average Distance is the distance to the center of orbit.
        // Max-Distance = distanceToCenter + radius
        // Min-Distance = distanceToCenter - radius
        Vector2 centerOfOrbit = target.getCenterOfOrbit();
        Vector2 directionToCenter = new Vector2(centerOfOrbit).sub(character.position);

        float radius = target.getOrbitalRadiusInPixels();
        float timeMargin = radius / character.maxSpeed;

        float distanceToCenter = directionToCenter.len();
        float timeToTravel = distanceToCenter / character.maxSpeed;
        Vector2 futurePosition = target.calculateFuturePosition(timeToTravel);

        Vector2 directionToFuturePosition = new Vector2(futurePosition).sub(character.position);
        float newDistance = directionToFuturePosition.len();

        if(newDistance < distanceToCenter) {
            // Target will be closer to the character
            futurePosition = target.calculateFuturePosition(timeToTravel - timeMargin);
        }
        if (newDistance > distanceToCenter) {
            futurePosition = target.calculateFuturePosition(timeToTravel + timeMargin);
        }

        super.target = new Kinematic(futurePosition, 0,0,0);

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
