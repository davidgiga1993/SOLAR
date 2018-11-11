package dhbw.karlsruhe.it.solar.core.config;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

public final class ConfigurationConstants {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static final SolarActorScale SCALE_FACTOR_PLANET = new SolarActorScale(1, 0.05f);
    public static final SolarActorScale SCALE_FACTOR_STAR = new SolarActorScale(1, 1);
    public static final SolarActorScale SCALE_FACTOR_MOON = new SolarActorScale(10, 1f);
    public static final SolarActorScale SCALE_FACTOR_ASTEROID = new SolarActorScale(1, 0.05f);
    public static final SolarActorScale SCALE_FACTOR_UNITS = new SolarActorScale(4000000, 1);
    public static final boolean SCALE_DIALOG_ENABLED = true;
    // GUI Settings
    public static final int INFO_BAR_HEIGHT = 160;
    public static final int RESOURCE_BAR_HEIGHT = 50;
    public static final int GUI_NAVIGATION_WIDTH = 175;
    public final static int CELL_WIDTH = 215;
    public final static int PADDING = 14;
    public final static int INNER_CELL_PADDING = 6;
    public static final int ICON_SIZE = 16;
    public static final String HOME_WORLD = "Earth";

    private ConfigurationConstants() {

    }
}
