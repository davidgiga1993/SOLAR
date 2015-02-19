package dhbw.karlsruhe.it.solar.core.ai;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

/**
 * Created by Arga on 13.02.2015.
 */
public interface AIModule {
    /**
     * Lets the AI calculate the Actor's new position, velocity and rotation
     * @param time passed since the last calculation
     */
    public AIOutput act(float time);

    /**
     * Sets the Actor's position
     * @param position
     */
    public void setPosition(Vector2 position);

    /**
     * Sets the Actor's target position
     * @param tarPosition Vector2
     */
    public void setTarget(Vector2 tarPosition);

    /**
     * Sets the Actor's target object
     * @param target Kinematic
     */
    public void setTarget(Kinematic target);

    /**
     * Set's the Actor's target object
     * @param target AstronomicalBody
     */
    public void setTarget(AstronomicalBody target);

    public boolean isMoving();
}
