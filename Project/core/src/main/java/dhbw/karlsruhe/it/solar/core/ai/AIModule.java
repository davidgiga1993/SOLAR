package dhbw.karlsruhe.it.solar.core.ai;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;

/**
 * Created by Arga on 13.02.2015.
 */
public interface AIModule {
    /**
     * Called once every frame to update the GameObject
     * @param time
     */
    public AIOutput act(float time);

    public void setPosition(Vector2 position);

    public void setTarget(Vector2 tarPosition);
    public void setTarget(Kinematic target);

    public boolean isMoving();
}
