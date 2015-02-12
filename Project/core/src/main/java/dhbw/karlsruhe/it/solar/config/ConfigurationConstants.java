package dhbw.karlsruhe.it.solar.config;


import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

public final class ConfigurationConstants {
	
	private ConfigurationConstants() {
		// utility classes should hide the implicit default constructor to avoid non static access.
	}
	
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;

	public static final SolarActorScale SCALE_FACTOR_PLANET = new SolarActorScale(400, 1);
	public static final SolarActorScale SCALE_FACTOR_STAR = new SolarActorScale(50, 1);
	public static final SolarActorScale SCALE_FACTOR_MOON = new SolarActorScale(500, 1f);
	public static final SolarActorScale SCALE_FACTOR_ASTEROID = new SolarActorScale(500, 1);
	public static final SolarActorScale SCALE_FACTOR_UNITS = new SolarActorScale(40000,1);
	public static final boolean SCALE_DIALOG_ENABLED = true;
}
