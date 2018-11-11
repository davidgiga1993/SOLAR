package dhbw.karlsruhe.it.solar.core.stages.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import dhbw.karlsruhe.it.solar.core.stages.guielements.ScrollFocusable;

/**
 * @author Arga
 * created on 20.03.2016.
 */
public class ScrollFocusOnMouseOverListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if (event instanceof InputEvent) {
            InputEvent.Type eventType = ((InputEvent) event).getType();
            if (eventType == InputEvent.Type.enter) {
                event.getStage().setScrollFocus(getFocusTarget(event));
            }
            if (eventType == InputEvent.Type.exit) {
                event.getStage().unfocus(getFocusTarget(event));
            }
        }
        return false;
    }

    private Actor getFocusTarget(Event event) {
        Actor listenerActor = event.getListenerActor();
        if (listenerActor instanceof ScrollFocusable) {
            return ((ScrollFocusable) listenerActor).getScrollTarget();
        }
        return listenerActor;
    }
}
