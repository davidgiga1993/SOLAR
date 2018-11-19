package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.exceptions.DeprecatedException;
import dhbw.karlsruhe.it.solar.core.exceptions.NotImplementedException;
import dhbw.karlsruhe.it.solar.core.usercontrols.DoubleActor;
import mikera.vectorz.Vector2;
import mikera.vectorz.Vector3;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Arga on 03.03.2015.
 */
public class SolarCamera extends OrthographicCamera {
    private static final double TIME_TO_TRANSLATE = 0.1;
    private static final float TIME_TO_ZOOM = 0.25f;
    private boolean isLocked = false;
    private Actor lockTarget;
    private Vector3 translationTarget = new Vector3();
    private Vector3 movementTarget = null;
    public Vector3 positionDouble = new Vector3();
    private float zoomTarget;
    private boolean zoomTargetActive = false;

    public SolarCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        zoomTarget = zoom;
    }

    /**
     * This method is responsible to update the camera's positionDouble according to it's current targets.
     * Should be called once every frame.
     *
     * @param delta time between this and last frame
     */
    public void update(float delta) {
        if (isLocked) {
            lockOnTarget();
        }
        smoothUpdate(delta);
        super.update();
    }

    // TODO: Improve Camera Lock on by scaling the agressiveness with the time the camera is locked on to this object
    private void lockOnTarget() {
        double x, y;
        if (lockTarget instanceof DoubleActor) {
            x = ((DoubleActor) lockTarget).getXDouble();
            y = ((DoubleActor) lockTarget).getYDouble();
        } else {
            x = lockTarget.getX();
            y = lockTarget.getY();
        }
        x += lockTarget.getOriginX();
        y += lockTarget.getOriginY();
        movementTarget = new Vector3(x, y, 0);
    }

    @Override
    public void translate(float x, float y) {
        DeprecatedException.printWarning("Please use translateDouble instead");
        this.translateDouble(x, y);
    }

    public void translateDouble(double x, double y) {
        if (x == 0 && y == 0) {
            return;
        }
        isLocked = false;
        movementTarget = null;
        zoomTargetActive = false;
        this.positionDouble.add(x, y, 0);
        updateSuperPosition();
    }

    private void updateSuperPosition() {
        super.position.set((float) this.positionDouble.x, (float) this.positionDouble.y, (float) this.positionDouble.z);
    }

    public void translateDouble(Vector2 vector) {
        translateDouble(vector.x, vector.y);
    }

    @Override
    public void translate(com.badlogic.gdx.math.Vector2 vec) {
        DeprecatedException.printWarning("Please use translateDouble instead");
        translate(vec.x, vec.y);
    }

    private void smoothUpdate(float delta) {
        if (movementTarget != null && !movementTarget.epsilonEquals(positionDouble, 0.001)) {
            smoothTranslation(delta);
        }
        if (zoomTargetActive) {
            smoothZoom(delta);
        }
    }

    private void smoothZoom(float delta) {
        float zoomDelta = (zoomTarget - zoom) / TIME_TO_ZOOM;
        zoom += zoomDelta * delta;
    }

    private void smoothTranslation(double delta) {
        translationTarget = movementTarget.clone();
        translationTarget.sub(positionDouble);
        Vector3 translation = translationTarget.clone();
        translation.scale(delta / TIME_TO_TRANSLATE);
        this.positionDouble.add(translation);
        updateSuperPosition();
    }

    /**
     * Moves the camera smoothly to the target actor
     *
     * @param actor to move to
     */
    public void moveTo(Actor actor) {
        lockTarget = actor;
        isLocked = actor != null;
    }

    /**
     * Moves the camera smoothly to the target location
     *
     * @param x
     * @param y
     */
    public void moveTo(double x, double y) {
        movementTarget = new Vector3(x, y, 0);
        translationTarget = new Vector3(x, y, 0);
        translationTarget.sub(positionDouble);
    }

    /**
     * Zooms smoothly to the targeted zoom level
     *
     * @param zoomTarget
     */
    public void zoomTo(float zoomTarget) {
        this.zoomTarget = zoomTarget;
        zoomTargetActive = true;
    }

    /**
     * Sets the zoom directly without any transition.
     *
     * @param newZoom
     */
    public void setZoom(float newZoom) {
        zoomTargetActive = false;
        zoom = newZoom;
    }

    @Override
    public void rotate(float angle) {
        throw new NotImplementedException("camera rotation is not implemented");
    }


}
