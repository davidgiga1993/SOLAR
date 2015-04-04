package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Arga on 03.03.2015.
 */
public class SolarCamera extends OrthographicCamera {
    protected boolean isLocked = false;
    protected Actor lockTarget;

    protected Vector2 translationTarget = new Vector2(0,0);
    protected Vector2 movementTarget = null;
    protected float zoomTarget;
    protected boolean zoomTargetActive = false;
    protected static final float TIME_TO_TRANSLATE = 0.1f;
    protected static final float TIME_TO_ZOOM = 0.25f;

    public SolarCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        zoomTarget = zoom;
    }

    /**
     * This method is responsible to update the camera's position according to it's current targets.
     * Should be called once every frame.
     * @param delta time between this and last frame
     */
    public void update(float delta) {
        if(isLocked) {
            lockOnTarget();
        }
        smoothUpdate(delta);
        super.update();
    }

    private void lockOnTarget() {
        float x = lockTarget.getX() + lockTarget.getOriginX();
        float y = lockTarget.getY() + lockTarget.getOriginY();
        movementTarget = new Vector2(x, y);
    }

    @Override
    public void translate(float x, float y) {
        if(x == 0 && y == 0) {
            return;
        }
        isLocked = false;
        movementTarget = null;
        zoomTargetActive = false;
        super.translate(x,y);
    }

    @Override
    public void translate(Vector2 vec) {
        translate(vec.x, vec.y);
    }

    private void smoothUpdate(float delta) {
        if(movementTarget != null && !movementTarget.epsilonEquals(position.x, position.y, 0.001f)) {
            smoothTranslation(delta);
        }
        if(zoomTargetActive) {
            smoothZoom(delta);
        }
    }

    private void smoothZoom(float delta) {
        float zoomDelta = (zoomTarget - zoom) / TIME_TO_ZOOM;
        zoom += zoomDelta * delta;
    }

    private void smoothTranslation(float delta) {
        translationTarget = new Vector2(movementTarget).sub(position.x, position.y);
        float scl = translationTarget.len() / TIME_TO_TRANSLATE;
        Vector2 translation = new Vector2(translationTarget).nor().scl(scl * delta);
        super.translate(translation.x, translation.y);
    }

    /**
     * Moves the camera smoothly to the target actor
     * @param actor to move to
     */
    public void moveTo(Actor actor) {
        lockTarget = actor;
        isLocked = actor != null;
    }

    /**
     * Moves the camera smoothly to the target location
     * @param x
     * @param y
     */
    public void moveTo(float x, float y) {
        movementTarget = new Vector2(x, y);
        translationTarget = new Vector2(x, y).sub(position.x, position.y);
    }

    /**
     * Zooms smoothly to the targeted zoom level
     * @param zoomTarget
     */
    public void zoomTo(float zoomTarget) {
        this.zoomTarget = zoomTarget;
        zoomTargetActive = true;
    }

    /**
     * Sets the zoom directly without any transition.
     * @param newZoom
     */
    public void setZoom(float newZoom) {
        zoomTargetActive = false;
        zoom = newZoom;
    }
}
