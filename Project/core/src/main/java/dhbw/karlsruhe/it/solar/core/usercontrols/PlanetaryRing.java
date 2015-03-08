package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.graphics.AnnulusShader;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * Defines the behavior of planetary ring systems for giant planets.
 * @author Andi
 *
 */
public class PlanetaryRing extends AstronomicalBody {

	private static final AnnulusShader RING_SHADER = new AnnulusShader();

	protected Length innerRadius;

	protected Mesh ringMesh;
	protected float polygonWidth;
	protected float polygonHeight;

	public PlanetaryRing(AstronomicalBody orbitPrimary, Mass mass, Length innerRadius, Length outerRadius, RingType type) {
		super(nameOfRings(orbitPrimary), orbitOfRings(orbitPrimary, outerRadius), bodyOfRings(mass, outerRadius), scaleOfRings(), textureOfRings(type));
		this.innerRadius = innerRadius;
		label.hide();
		this.setTouchable(Touchable.disabled);
		updateScale();
		ringMesh = createPolygonRegion(1, getWidth()/2,segments);
	}

	private Mesh createPolygonRegion(float innerRadius, float outerRadius, int segments) {
		float phi = 360f/segments;

		float r = innerRadius;
		float R = outerRadius;

		float vertices[] = new float[segments*8];
		short triangles[] = new short[segments*2];
		short count = 0;

		for(int i = 0; i < vertices.length;) {
			float sin = (float) Math.sin(phi*i);
			float cos = (float) Math.cos(phi*i);

			// position
			vertices[i++] = cos * r;
			vertices[i++] = sin * r;
			// texture
			vertices[i++] = 0;
			vertices[i++] = 0;


			vertices[i++] = cos * R;
			vertices[i++] = sin * R;

			vertices[i++] = 1;
			vertices[i++] = 0;

			triangles[count] = count++;
			triangles[count] = count++;
		}

		polygonWidth = vertices[6];
		polygonHeight = outerRadius;

		Mesh ringMesh = new Mesh(true, segments*2, segments*2, new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"), new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, "a_texCoord"));
		ringMesh.setVertices(vertices);
		ringMesh.setIndices(triangles);

		return ringMesh;
	}

	@Override
	protected void displayOrbit(SolarShapeRenderer shapeRenderer) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		AstronomicalBody primary = getPrimary();
		float x = primary.getX() + primary.getOriginX();
		float y = primary.getY() + primary.getOriginY();

		ringMesh.bind(RING_SHADER);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		int vertexCount = segments*2;

		RING_SHADER.begin();

		RING_SHADER.setUniformMatrix("u_projTrans", SolarEngine.get().camera.combined);
		RING_SHADER.setUniformf("u_center", x, y);

		ringMesh.render(RING_SHADER, GL20.GL_TRIANGLE_STRIP, 0, vertexCount);

		RING_SHADER.end();
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
		// planetary rings must be shifted by an offset when the primary is scaled
		// however they'll need to get scaled when the moon orbit changes
		// so the size should be radius = radius * moonOrbitScale + delta_planetsize
		float radius = SolarActor.scaleDistanceToStage(physicalProperties.getRadius().asKilometres());
		radius *= ConfigurationConstants.SCALE_FACTOR_MOON.orbitScale;
		radius += calculateOrbitOffset();
		float width = radius * 2;
		setSize(width, width);

		// Note: this actually does work. however scaling the texture is not the same as scaling an annulus (2d-ring)
	}
	
	@Override
    public void setRingPrimary(AstronomicalBody body)
    {
    	orbitalProperties.setNewOrbitPrimary(body);
    }

	private static SolarActorScale scaleOfRings() {
		return ConfigurationConstants.SCALE_FACTOR_PLANET;
	}
	
	private static OrbitalProperties orbitOfRings(AstronomicalBody orbitPrimary, Length radius) {
		return new OrbitalProperties(orbitPrimary, radius, new Angle());
	}

	private static BodyProperties bodyOfRings(Mass mass, Length radius) {
		return new BodyProperties(mass, radius, null);
	}

	private static String nameOfRings(AstronomicalBody orbitPrimary) {
		if ( null == orbitPrimary)
		{
			return "Ring system";
		}
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

	@Override
	protected boolean previewEnabled() {
		return false;
	}

	@Override
	public boolean handleMessage(Telegram telegram) {
		if (telegram.message == SolarMessageType.GAME_SCALE_CHANGED && telegram.sender.equals(ConfigurationConstants.SCALE_FACTOR_PLANET) || telegram.sender.equals(ConfigurationConstants.SCALE_FACTOR_MOON)) {
			updateScale();
		}
		return false;
	}
}
