package dhbw.karlsruhe.it.solar.core.ai;

import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedListener;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import mikera.vectorz.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public interface AIModule {
    /**
     * Lets the AI calculate the Actor's new position, velocity and rotation
     *
     * @param time passed since the last calculation
     */
    AIOutput act(float time);

    /**
     * Sets the Actor's position
     *
     * @param position
     */
    void setPosition(Vector2 position);

    /**
     * Sets the Actor's target position
     *
     * @param tarPosition Vector2
     */
    void setTarget(Vector2 tarPosition);

    /**
     * Sets the Actor's target object
     *
     * @param target Kinematic
     */
    void setTarget(Kinematic target);

    /**
     * Set's the Actor's target object
     *
     * @param target AstronomicalBody
     */
    void setTarget(Orbiter target);

    boolean isMoving();

    void addEventListener(TargetReachedListener newListener);

    void removeEventListener(TargetReachedListener newListener);
}
