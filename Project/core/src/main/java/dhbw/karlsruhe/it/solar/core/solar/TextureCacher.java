package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.Map;

public class TextureCacher {
    public static final Map<String, NinePatch> ninePatchCache = new HashMap<String, NinePatch>();
    public static final TextureAtlas gameAtlas = new TextureAtlas(Gdx.files.internal("packed/texturePack.atlas"));

    private TextureCacher() {
    	
    }

    public static void cleanUp() {
        gameAtlas.dispose();
    }

}
