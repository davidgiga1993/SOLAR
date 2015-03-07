package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by argannor on 27.02.15.
 */
public class BodyGameLabel extends Label {

    protected float threshold = 6f;
    protected OrthographicCamera gameCamera;

    public BodyGameLabel(CharSequence text) {
        super(text, SolarEngine.get().styles.defaultLabelStyle);
        setAlignment(Align.center);
        SolarEngine.get().stageManager.getStage("GameHUD").addActor(this);
        this.gameCamera = SolarEngine.get().camera;
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
