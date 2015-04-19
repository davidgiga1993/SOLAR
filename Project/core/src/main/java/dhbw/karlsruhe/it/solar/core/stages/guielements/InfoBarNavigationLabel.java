package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

public class InfoBarNavigationLabel extends BaseNavigationLabel {
    
    public InfoBarNavigationLabel(CharSequence name, String tab, SolarActor actor) {
        super(name, tab, actor);
    }
    
    @Override
    protected void onLeftClick(InputEvent event) {
        GameStartStage.inputListenerMoveCamera(actor);
    }

}
