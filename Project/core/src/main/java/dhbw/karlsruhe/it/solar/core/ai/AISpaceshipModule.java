package dhbw.karlsruhe.it.solar.core.ai;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.ai.movement.*;

/**
 * Created by Arga on 13.02.2015.
 */
public class AISpaceshipModule implements AIModule {

    Kinematic kinematic;
    AIOutput output;
    SteeringProvider currentSteeringProvider;

    SteeringProvider pursueSteeringProvider;
    SteeringProvider arriveSteeringProvider;

    public AISpaceshipModule(KinematicObject object) {
        kinematic = object.getKinematic();
        output = new AIOutput();
        pursueSteeringProvider = new PursueSteeringProvider(kinematic.maxSpeed, 10, kinematic.maxSpeed * 0.25f);
        arriveSteeringProvider = new ArriveSteeringProvider(kinematic.maxSpeed, 10, kinematic.maxSpeed * 0.25f);
    }

    @Override
    public AIOutput act(float time) {
        // update Kinematic
        Steering steering = currentSteeringProvider.getSteering(kinematic);
        kinematic.update(steering, time);
        
        // output
        output.position = kinematic.position;
        output.rotation = kinematic.rotation;
        return output;
    }

    @Override
    public void setTarget(Vector2 tarPosition) {
        Kinematic newTarget = new Kinematic(tarPosition, 0,0,0);
        setTarget(newTarget, arriveSteeringProvider);
    }

    @Override
    public void setTarget(Kinematic target) {
        setTarget(target, pursueSteeringProvider);
    }

    public void setTarget(Kinematic target, SteeringProvider provider) {
        currentSteeringProvider = provider;
        provider.setTarget(target);
    }

    @Override
    public void setPosition(Vector2 position) {
        kinematic.position = position;
    }

    @Override
    public boolean isMoving() {
        return true;
    }
}
