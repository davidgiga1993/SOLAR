package dhbw.karlsruhe.it.solar.core.usercontrols;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceStation;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;

/**
 * @author Andi
 * <p>
 * Orbiter contains all relevant information necessary for an actor to be able to orbit another Orbiter actor in the game.
 * Therefore it handles all methods and information relating to the game's orbital mechanics.
 */
public abstract class Orbiter extends SolarActor implements ShapeRenderable, KinematicObject {
    private static final float PREVIEW_PIXEL_WIDTH = 5.5f;
    protected final Kinematic kinematic;
    protected OrbitalProperties orbitalProperties;
    protected PreviewActor preview;
    protected double orbitalRadiusInPixels;
    protected int segments = 250;
    protected Color orbitColor = Color.TEAL;

    private Matrix4 orbitTransform = new Matrix4();

    protected Orbiter(String name) {
        this(name, null, ConfigurationConstants.SCALE_FACTOR_STAR);
    }

    protected Orbiter(String name, OrbitalProperties orbit, SolarActorScale scaleFactor) {
        super(name);
        setActorScale(scaleFactor);
        this.kinematic = new Kinematic(new Vector2(getX(), getY()), 0, 0);
        this.orbitalProperties = orbit;
        if (null != orbitalProperties && null != orbitalProperties.getPrimary()) {
            setKinematicValues();
            changeOrbitScale();
        }
        preview = new PreviewActor(this, getWidth(), PREVIEW_PIXEL_WIDTH, SpaceStation.SPACE_UNIT_ORBIT_COLOR);
    }

    protected void setKinematicValues() {
        float orbitalSpeed = calculateOrbitalSpeed();
        kinematic.setPosition(getAdjustedPosition());
        kinematic.setRotation(0f);
        kinematic.setVelocity(new mikera.vectorz.Vector2(orbitalSpeed, 0));
        kinematic.setMaxSpeed(orbitalSpeed);
    }

    /**
     * Calculates the center of an object texture based on its x/y position coordinates which correspond to the lower left corner of the texture.
     *
     * @return Position coordinates of the center of the object's texture.
     */
    private mikera.vectorz.Vector2 getAdjustedPosition() {
        return new mikera.vectorz.Vector2(getXDouble() + getWidth() / 2, getYDouble() + getHeight() / 2);
    }

    /**
     * Calculates the x/y game coordinates for an object based on its kinematic position values which correspond to the center of its texture.
     *
     * @return Position coordinates for the game logic which correspond to the lower left corner of an object.
     */
    protected mikera.vectorz.Vector2 getReAdjustedPosition() {
        return new mikera.vectorz.Vector2(kinematic.getXPosition() - getWidth() / 2, kinematic.getYPosition() - getHeight() / 2);
    }

    /**
     * Calculates the actual movement speed on the game map relative to the scaling setting for the stage.
     * Value is calculated according to the orbital circumference (derived from the scaled Orbital Radius value) divided by the Orbital Period in Days.
     *
     * @return Total Movement speed of the orbital object on the game map. Unit: Scale-Adjusted Kilometres per Day.
     */
    private float calculateOrbitalSpeed() {
        return (float) ((2 * Math.PI * scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometers())) / orbitalProperties.getOrbitalPeriod().inDays());
    }

    /**
     * Sets the orbital radius in pixels value relative to the scaling setting for the stage.
     */
    private void changeOrbitScale() {
        if (orbitalProperties == null) {
            return;
        }
        orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometers()) * actorScale.getOrbitScale();
        orbitalRadiusInPixels += calculateOrbitOffset();
    }

    @Override
    public void updateScale() {
        changeOrbitScale();
        super.updateScale();
    }

    protected float calculateOrbitOffset() {
        AstronomicalBody primary = getPrimary();
        if (primary == null) {
            return 0;
        }
        float radius = primary.getWidth() / 2;
        float normRadius = (float) (primary.getRadius().asKilometers() / STAGE_SCALING_FACTOR);
        return radius - normRadius;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (null != orbitalProperties) {
            actOrbitalMovement(delta);
        }
    }

    protected void actOrbitalMovement(float delta) {
        orbitalProperties.updateOrbitalAngle(delta);
        setOrbitalPositionTotal();

        adjustLabelPosition();

        kinematic.setRotation(orbitalProperties.getOrbitalAngle().inDegrees() + 90.);
        kinematic.setVelocityAngle(kinematic.getRotation());
    }

    /**
     * Method implements change of position of labels for actors using the Orbiter superclass.
     */
    protected void adjustLabelPosition() {
        // TODO: should this be empty? If so then document why.
    }

    /**
     * Calculates the current position of the astronomical body on the system map based on its Orbital Radius and Angle attributes.
     */
    protected void setOrbitalPositionTotal() {
        kinematic.setPosition(orbitalProperties.getOrbitalPositionTotal(orbitalRadiusInPixels, new Angle()));
        this.setPosition(getReAdjustedPosition().x, getReAdjustedPosition().y);
    }

    public mikera.vectorz.Vector2 calculateFuturePosition(double delta) {
        return orbitalProperties.calculateFuturePosition(orbitalRadiusInPixels, delta);
    }

    /**
     * Returns a Vector2 containing the position of this objects center of orbit.
     *
     * @return Vector2
     */
    public mikera.vectorz.Vector2 getCenterOfOrbit() {
        return orbitalProperties.getCenterOfOrbit();
    }

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        if (previewEnabled() && !canBeSeen()) {
            preview.drawLines(libGDXShapeRenderer, solarShapeRenderer);
        }
        if (orbitalProperties != null) {
            displayOrbit(solarShapeRenderer);
        }
    }

    /**
     * If selected, the object is highlighted by a selection box.
     *
     * @param shapeRenderer
     */
    protected void displaySelectionBox(ShapeRenderer shapeRenderer) {
        if (selected) {
            shapeRenderer.setColor(selectionColor);
            shapeRenderer.rect(getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
        }
    }

    protected boolean canBeSeen() {
        return (getWidth() / SolarEngine.get().getSolarCameraZoom()) > 1f;
    }

    protected boolean previewEnabled() {
        return true;
    }

    protected void displayOrbit(SolarShapeRenderer shapeRenderer) {
        if (!orbitalProperties.isCoOrbital()) {
            shapeRenderer.setTransformMatrix(orbitTransform.setToTranslation(
                    (float) orbitalProperties.calculateCenterOfOrbitX(),
                    (float) orbitalProperties.calculateCenterOfOrbitY(),
                    0));
            shapeRenderer.setColor(orbitColor);
            shapeRenderer.orbit(orbitalRadiusInPixels, segments);
        }
    }

    @Override
    public Kinematic getKinematic() {
        return this.kinematic;
    }

    public Length getOrbitalRadius() {
        return orbitalProperties.getOrbitalRadius();
    }

    public Time getOrbitalPeriod() {
        return orbitalProperties.getOrbitalPeriod();
    }

    public AstronomicalBody getPrimary() {
        return orbitalProperties.getPrimary().asAstronomicalBody();
    }

    protected void setKinematicPosition(mikera.vectorz.Vector2 position) {
        kinematic.setPosition(position);
    }

    public Angle getOrbitalAngle() {
        return orbitalProperties.getOrbitalAngle();
    }

    public boolean isInOrbit() {
        return (null != orbitalProperties);
    }

    public boolean isInRetrogradeOrbit() {
        return isInOrbit() && orbitalProperties.isRetrograde();
    }

    public boolean isInCoOrbitalOrbit() {
        return isInOrbit() && orbitalProperties.isCoOrbital();
    }

    public CoOrbital getCoOrbitalInformation() {
        return orbitalProperties.getCoOrbitalInformation();
    }

    public double getOrbitalRadiusInPixels() {
        return orbitalRadiusInPixels;
    }

    public String getNameOfPrimary() {
        return orbitalProperties.getPrimary().asAstronomicalBody().getName();
    }
}
