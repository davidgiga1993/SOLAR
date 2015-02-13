package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public interface SteeringProvider {
    public Steering getSteering(Kinematic position);
    public void setTarget(Kinematic newTarget);
}
