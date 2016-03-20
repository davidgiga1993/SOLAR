package dhbw.karlsruhe.it.solar.core.stages.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author Arga
 *         created on 20.03.2016.
 */
public class ChangeLambdaListener extends ChangeListener {

    private final Runnable action;

    public ChangeLambdaListener(Runnable action) {
        this.action = action;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        action.run();
    }
}
