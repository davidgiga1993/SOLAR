package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

/**
 * Created by Arga on 04.03.2015.
 */
public class TimeLabel extends Label {

    public TimeLabel() {
        super("", SolarEngine.get().getDefaultLabelStyle());
        setAlignment(Align.right);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(GameStartStage.gameTime.toString());
    }
}
