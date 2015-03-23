package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arga on 16.11.2014.
 */
public class FontCacher {
    public static final Map<String, BitmapFont> CACHE = new HashMap<String, BitmapFont>();

    private FontCacher() {
        
    }

    /**
     * Disposes all Fonts and removes them from the cache
     */
    public static void cleanUp() {
        Collection<BitmapFont> fonts = CACHE.values();
        for(BitmapFont font : fonts) {
            font.dispose();
        }
        CACHE.clear();
    }

    /**
     * Add the font to the cache
     * @param font to be cached
     * @param tag give it a name :)
     */
    public static void addFont(BitmapFont font, String tag) {
        CACHE.put(tag, font);
    }

    /**
     * Returns the corresponding font. If none is found null gets returned.
     * @param tag to search for
     * @return BitmapFont or NULL
     */
    public static BitmapFont getFont(String tag) {
        return CACHE.get(tag);
    }


}
