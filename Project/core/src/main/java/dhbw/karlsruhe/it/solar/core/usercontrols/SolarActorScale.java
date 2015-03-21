package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;

/**
 * Created by Arga on 11.02.2015.
 */
public class SolarActorScale implements Telegraph {
    public float shapeScale;
    public float orbitScale;

    public SolarActorScale(float shapeScale, float orbitScale) {
        this.shapeScale = shapeScale;
        this.orbitScale = orbitScale;
    }

    public void set(float shapeScale, float orbitScale) {
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(this, SolarMessageType.GAME_SCALE_CHANGED);
        this.shapeScale = shapeScale;
        this.orbitScale = orbitScale;
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        return true;
    }
}
