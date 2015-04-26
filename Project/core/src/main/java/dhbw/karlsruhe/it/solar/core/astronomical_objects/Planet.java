package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody  {
    private AstronomicalBody outermostMoon;
    private PlanetType type;

    public Planet(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_PLANET, getTextureFromTypeOf((PlanetType)body.getBodyType()));
        this.segments = 2000;
        this.type = (PlanetType)body.getBodyType();
        preview.setColor(Color.TEAL);
    }

    private static String getTextureFromTypeOf(PlanetType planet)    {
        switch(planet.getPlanetType())        {
            case MARTIAN:
                return "Martian";
            case MERCURIAN:
                return "Mercurian";
            case DWARFPLANET:
                return "DwarfPlanet";
            case JOVIAN:
                return "Jovian";
            case VENUSIAN:
                return "Venusian";
            case NEPTUNIAN:
                return "Neptunian";
            case SATURNIAN:
                return "Saturn";
            case URANIAN:
                return "Uranian";
            case TERRAN:
                return "Terran";
            default:
                return "DwarfPlanet";
        }
    }

    @Override
    public void addSatellite(AstronomicalBody newSatellite) {
        super.addSatellite(newSatellite);
        if(outermostMoon == null || outermostMoon.getOrbitalRadius().asKilometers() < newSatellite.getOrbitalRadius().asKilometers()) {
            outermostMoon = newSatellite;
        }
    }

    @Override
    protected boolean canBeSeen() {
        float size = getWidth();
        if(outermostMoon != null) {
            size = outermostMoon.getOrbitalRadiusInPixels() * 2;
        }
        return (size / SolarEngine.get().getSolarCameraZoom()) > 1f;
    }
    
    @Override
    public String getTypeName() {
        return type.resolveTypeName();
    }
}
