package dhbw.karlsruhe.it.solar.config;


import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

public final class ConfigurationConstants {

    public static final int SCREENWIDTH = 800;
    public static final int SCREENHEIGHT = 600 ;

    public static final SolarActorScale SCALE_FACTOR_PLANET = new SolarActorScale(1, 0.05f);
    public static final SolarActorScale SCALE_FACTOR_STAR = new SolarActorScale(1, 1);
    public static final SolarActorScale SCALE_FACTOR_MOON = new SolarActorScale(10, 1f);
    public static final SolarActorScale SCALE_FACTOR_ASTEROID = new SolarActorScale(1, 0.05f);
    public static final SolarActorScale SCALE_FACTOR_UNITS = new SolarActorScale(4000000,1);
    public static final boolean SCALE_DIALOG_ENABLED = true;
    // GUI Settings
    public static final int INFOBAR_HEIGHT = 160;
    public static final int RESSOURCEBAR_HEIGHT = 50;
    public static final int GUI_NAVIGATION_WIDTH = 175;
    public final static int CELL_WIDTH = 215;
    public final static int PADDING = 14;
    public static final String HOMEWORLD = "Earth";

    private ConfigurationConstants() {
        
    }
}
