package dhbw.karlsruhe.it.solar.config;

import java.util.HashMap;
import java.util.Map;

public final class DynamicOptions {

    public static final String SAVEGAME_FILE_NEWGAME = "savegames.file.newgame";
    public static final String SAVEGAME_FILE_CURRENT = "savegames.file.current";

    private static final Map<String, String> DEFAULTS = new HashMap<>();

    static {
        DEFAULTS.put(SAVEGAME_FILE_NEWGAME, "SaveGames/SolarSystem.xml");
        DEFAULTS.put(SAVEGAME_FILE_CURRENT, "SaveGames/CurrentGame.xml");
    }

    private DynamicOptions() {
    }

    public static String getValue(String key) {
        return fromPropertyOrDefaults(key);
    }

    private static String fromPropertyOrDefaults(String key) {
        return System.getProperty(key, DEFAULTS.get(key));
    }

}
