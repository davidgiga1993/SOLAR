package dhbw.karlsruhe.it.solar.core.ai.movement;

/**
 * Created by Arga on 13.02.2015.
 */
public interface SteeringProvider {
    /**
     * Calculates steering to previous set target
     *
     * @param position steering object's current position
     * @return calculated steering
     */
    Steering getSteering(final Kinematic position);

    /**
     * Sets steering target
     *
     * @param newTarget target to steer at
     */
    void setTarget(Kinematic newTarget);
}
