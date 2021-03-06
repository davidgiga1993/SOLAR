package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

import java.util.HashMap;
import java.util.Map;


public class BackgroundStage extends BaseBackgroundStage {
    private static final String DEFAULT_BACKGROUND = "Hintergrund01.png";
    private Image backgroundImage;
    private Map<String, Texture> backgroundTextures;

    public BackgroundStage(SolarEngine se) {
        super(se, "Background");

        loadTextures();
        changeBackground(DEFAULT_BACKGROUND);

        addActor(backgroundImage);
        backgroundImage.setScaling(Scaling.fill);
    }

    void changeBackground(String name) {
        clear();
        backgroundImage = new Image(backgroundTextures.get(name));
        backgroundImage.setScaling(Scaling.fill);
        backgroundImage.setPosition(0, 0);
        backgroundImage.setColor(1, 1, 1, .6f);
        addActor(backgroundImage);
    }

    private void loadTextures() {
        backgroundTextures = new HashMap<>(2);

        Texture hintergrund1 = new Texture(Gdx.files.internal("data/Hintergrund01.png"));
        hintergrund1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Texture hintergrund2 = new Texture(Gdx.files.internal("data/Hintergrund02.png"));
        hintergrund1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        backgroundTextures.put("Hintergrund01.png", hintergrund1);
        backgroundTextures.put("Hintergrund02.png", hintergrund2);
    }

    @Override
    public void dispose() {
        for (Texture t : backgroundTextures.values()) {
            t.dispose();
        }
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }
}
