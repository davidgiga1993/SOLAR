package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.ai.msg.Telegram;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;

/**
 * Created by Arga on 07.03.2015.
 */
public class LinkedSolarActorScale extends SolarActorScale {

    private SolarActorScale linkedShapeScale;
    private SolarActorScale linkedOrbitScale;

    public LinkedSolarActorScale(float shapeScale, float orbitScale, SolarActorScale linkedShapeScale, SolarActorScale linkedOrbitScale) {
        super(shapeScale, orbitScale);
        this.linkedShapeScale = linkedShapeScale;
        this.linkedOrbitScale = linkedOrbitScale;
        set(shapeScale, orbitScale);

        SolarEngine.messageDispatcher.addListener(this, SolarMessageType.GAME_SCALE_CHANGED);
    }

    @Override
    public void set(float shapeScale, float orbitScale) {
        if(linkedShapeScale == null) {
            this.shapeScale = shapeScale;
        } else {
            this.shapeScale = linkedShapeScale.shapeScale;
        }
        if(linkedOrbitScale == null) {
            this.orbitScale = orbitScale;
        } else {
            this.orbitScale = linkedOrbitScale.shapeScale;
        }
        SolarEngine.messageDispatcher.dispatchMessage(this, SolarMessageType.GAME_SCALE_CHANGED);
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.sender.equals(linkedOrbitScale) || telegram.sender.equals(linkedShapeScale)) {
            set(shapeScale, orbitScale);
            return true;
        }
        return false;
    }
}
