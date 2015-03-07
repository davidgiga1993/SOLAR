package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.scenes.scene2d.Touchable;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * Defines the behavior of planetary ring systems for giant planets.
 * @author Andi
 *
 */
public class PlanetaryRing extends AstronomicalBody {
	
	public PlanetaryRing(AstronomicalBody orbitPrimary, Mass mass, Length radius, RingType type) {
		super(nameOfRings(orbitPrimary), orbitOfRings(orbitPrimary, radius), bodyOfRings(mass, radius), scaleOfRings(), textureOfRings(type));
		label.hide();
		this.setTouchable(Touchable.disabled);
		updateScale();
	}

	@Override
	protected void displayOrbit(SolarShapeRenderer shapeRenderer) {

	}
	
	@Override
	protected void actOrbitalMovement(float delta) {
		orbitalProperties.updateOrbitalAngle(delta);
		
		kinematic.position.x = orbitalProperties.getPrimaryX();
		kinematic.position.y = orbitalProperties.getPrimaryY();
    	this.setPosition(kinematic.position.x  - getWidth() / 2, kinematic.position.y  - getHeight() / 2);

        adjustLabelPosition();

		kinematic.rotation = orbitalProperties.getOrbitalAngle().inDegrees() + 90f;
		kinematic.velocity.setAngle(kinematic.rotation);
	}
	
	@Override
	public void updateScale() {
		SolarActorScale scale = new SolarActorScale(ConfigurationConstants.SCALE_FACTOR_MOON.orbitScale, ConfigurationConstants.SCALE_FACTOR_MOON.orbitScale);
        float width = getWidth() / currentShapeScale * scale.shapeScale;
        float height = getHeight() / currentShapeScale * scale.shapeScale;
        setActorScale(scale);
        setSize(width, height);
	}

	private static SolarActorScale scaleOfRings() {
		return ConfigurationConstants.SCALE_FACTOR_PLANET;
	}
	
	private static OrbitalProperties orbitOfRings(
			AstronomicalBody orbitPrimary, Length radius) {
		return new OrbitalProperties(orbitPrimary, radius, new Angle());
	}

	private static BodyProperties bodyOfRings(Mass mass, Length radius) {
		return new BodyProperties(mass, radius, null);
	}

	private static String nameOfRings(AstronomicalBody orbitPrimary) {
		return "Rings of " + orbitPrimary.getName();
	}
	
    public enum RingType {
		JOVIAN,
		SATURNIAN,
		URANIAN,
		NEPTUNIAN
	}
    
	private static String textureOfRings(RingType type)
	{
		switch(type)
		{
			case JOVIAN:
				return "Rings_Jupiter";
			case SATURNIAN:
				return "Rings_Saturn";
			case URANIAN:
				return "Rings_Uranus";
			case NEPTUNIAN:
				return "Rings_Neptune";
			default:
				return "Rings_Jupiter";
		}
	}

}
