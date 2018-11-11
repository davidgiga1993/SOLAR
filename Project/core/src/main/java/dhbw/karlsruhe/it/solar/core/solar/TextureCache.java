package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.Map;

public class TextureCache {
    public static final Map<String, NinePatch> NINE_PATCH_CACHE = new HashMap<>();
    public static final TextureAtlas GAME_ATLAS = new TextureAtlas(Gdx.files.internal("packed/texturePack.atlas"));

    private TextureCache() {

    }

    public static void cleanUp() {
        GAME_ATLAS.dispose();
    }

}
