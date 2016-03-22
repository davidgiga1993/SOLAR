package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by argannor on 27.02.15.
 */
public class BodyGameLabel extends Label {

    private float threshold = 6f;
    private OrthographicCamera gameCamera;

    public BodyGameLabel(CharSequence text) {
        super(text, Styles.DEFAULTLABEL_STYLE);
        setAlignment(Align.center);
        SolarEngine.get().getStage("GameHUD").addActor(this);
        this.gameCamera = SolarEngine.get().getCamera();
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && gameCamera.zoom < threshold;
    }

    public void hide() {
        this.setVisible(false);
    }
}
