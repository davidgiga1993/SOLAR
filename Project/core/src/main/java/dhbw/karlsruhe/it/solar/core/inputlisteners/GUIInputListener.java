package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUIActor;

/**
 * Created by Arga on 16.11.2014.
 */
public class GUIInputListener extends InputListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return event.getTarget() instanceof GUIActor;
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        if (event.getTarget() instanceof GUIActor) {
            return false;
        }
        return false;
    }
}
