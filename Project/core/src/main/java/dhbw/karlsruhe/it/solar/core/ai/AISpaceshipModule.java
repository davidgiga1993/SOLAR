package dhbw.karlsruhe.it.solar.core.ai;

import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedEvent;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedListener;
import dhbw.karlsruhe.it.solar.core.ai.movement.*;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 13.02.2015.
 */
public class AISpaceshipModule implements AIModule {

    Kinematic kinematic;
    AIOutput output;
    SteeringProvider currentSteeringProvider;

    SteeringProvider pursueSteeringProvider;
    SteeringProvider arriveSteeringProvider;
    OrbitalArriveSteeringProvider orbitalArriveSteeringProvider;

    List<TargetReachedListener> targetReachedListeners = new ArrayList<TargetReachedListener>();

    boolean targetReached = true;

    public AISpaceshipModule(KinematicObject object) {
        kinematic = object.getKinematic();
        output = new AIOutput();

        pursueSteeringProvider = new PursueSteeringProvider(10, kinematic.maxSpeed * .5f);
        arriveSteeringProvider = new ArriveSteeringProvider(10, kinematic.maxSpeed * .5f);
        orbitalArriveSteeringProvider = new OrbitalArriveSteeringProvider(10, kinematic.maxSpeed * .5f);
    }

    @Override
    public AIOutput act(float time) {
        // update Kinematic
        Steering steering = currentSteeringProvider.getSteering(kinematic);
        kinematic.update(steering, time);
        
        // output
        output.position = kinematic.position;
        output.rotation = kinematic.rotation;

        if(steering.reached && !targetReached) {
            fireTargetReached();
            targetReached = true;
        }

        return output;
    }

    private void fireTargetReached() {
        TargetReachedEvent event = new TargetReachedEvent(null);
        for (TargetReachedListener listener : targetReachedListeners) {
            listener.handle(event);
        }
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

    public void setTarget(AstronomicalBody target) {
        orbitalArriveSteeringProvider.setOrbitingTarget(target);
        currentSteeringProvider = orbitalArriveSteeringProvider;
        targetReached = false;
    }

    protected void setTarget(Kinematic target, SteeringProvider provider) {
        currentSteeringProvider = provider;
        provider.setTarget(target);
        targetReached = false;
    }

    @Override
    public void setPosition(Vector2 position) {
        kinematic.position = position;
    }

    @Override
    public boolean isMoving() {
        return kinematic.isMoving;
    }

    @Override
    public void addEventListener(TargetReachedListener newListener) {
        targetReachedListeners.add(newListener);
    }

    @Override
    public void removeEventListener(TargetReachedListener listener) {
        targetReachedListeners.remove(listener);
    }
}
