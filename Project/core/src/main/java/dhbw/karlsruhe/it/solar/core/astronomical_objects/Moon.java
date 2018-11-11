package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.PreviewActor;

/**
 * @author Andi
 */
public class Moon extends AstronomicalBody {

    private MoonType type;

    public Moon(String name, OrbitalProperties orbit, BodyProperties body) {
        super(name, orbit, body, ConfigurationConstants.SCALE_FACTOR_MOON, body.getTexture());
        this.type = (MoonType) body.getBodyType();
        label.setThreshold(0.4f);
        this.orbitColor = Color.GRAY;
        preview = new PreviewActor(this, getWidth(), 10, Color.GRAY);
    }

    @Override
    protected boolean previewEnabled() {
        // this needs to be refined in the future
        return SolarEngine.get().getSolarCameraZoom() < .3f;
    }

    @Override
    public String getTypeName() {
        return type.resolveTypeName();
    }
}
