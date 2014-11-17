package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 16.11.2014.
 */
public class Tooltip extends Window {

    public Tooltip(String title, Skin tooltipSkin) {
        super(title, tooltipSkin);
        setMovable(false);
        setResizable(false);
    }


    public void updatePosition() {
        super.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    }
}
