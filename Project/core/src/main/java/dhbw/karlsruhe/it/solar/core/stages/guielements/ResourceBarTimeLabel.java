package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 04.03.2015.
 */
class ResourceBarTimeLabel extends Label {

    public ResourceBarTimeLabel() {
        super("", Styles.DEFAULT_LABEL_STYLE);
        setAlignment(Align.right);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(GameStartStage.GAME_TIME.toString());
    }
}
