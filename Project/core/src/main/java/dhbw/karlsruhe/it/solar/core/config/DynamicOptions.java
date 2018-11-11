package dhbw.karlsruhe.it.solar.core.config;

import java.util.Map;

import static java.util.Map.*;

public final class DynamicOptions {

    public static final String SAVE_GAME_FILE_NEWGAME = "savegames.file.newgame";
    public static final String SAVE_GAME_FILE_CURRENT = "savegames.file.current";

    private static final Map<String, String> DEFAULTS = ofEntries(
            entry(SAVE_GAME_FILE_NEWGAME, "SaveGames/SolarSystem.xml"),
            entry(SAVE_GAME_FILE_CURRENT, "SaveGames/CurrentGame.xml")
    );

    private DynamicOptions() {}

    public static String getValue(String key) {
        return fromPropertyOrDefaults(key);
    }

    private static String fromPropertyOrDefaults(String key) {
        return System.getProperty(key, DEFAULTS.get(key));
    }

}
