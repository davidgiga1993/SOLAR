package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import dhbw.karlsruhe.it.solar.core.exceptions.DeprecatedException;
import dhbw.karlsruhe.it.solar.core.exceptions.NotImplementedException;

public class DoubleActor extends Actor {

    double x,y;
    double oldX, oldY;

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
    }

    public double getXDouble() {
        return x;
    }

    public double getYDouble() {
        return y;
    }

    public float getX() {
        return (float) x;
    }

    public float getY() {
        return (float) y;
    }

    @Deprecated
    public void setX(float x) {
        DeprecatedException.printWarning("Please use setX(double x) instead");
        this.x = (double) x;
        super.setX(x);
    }

    public void setX(double x) {
        this.x = x;
        super.setX((float) x);
    }

    @Deprecated
    public void setY(float y) {
        DeprecatedException.printWarning("Please use setY(double y) instead");
        this.y = (double) y;
        super.setY(y);
    }

    public void setY(double y) {
        this.y = y;
        super.setY((float) y);
    }

    public void translateAndBackup(double x, double y) {
        this.oldX = this.x;
        this.oldY = this.y;
        this.x += x;
        this.y += y;
    }

    public void restore() {
        this.x = this.oldX;
        this.y = this.oldY;
    }

    @Deprecated
    public void setPosition(float x, float y) {
        DeprecatedException.printWarning("Please use setPosition(double x, double y) instead");
        this.x = (double) x;
        this.y = (double) y;
        super.setPosition(x, y);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        super.setPosition((float) x, (float) y);
    }

    @Deprecated
    public void moveBy(float x, float y) {
        DeprecatedException.printWarning("Please use moveBy(double x, double y) instead");
        if(x != 0 || y != 0) {
            this.x += x;
            this.y += y;
            super.moveBy(x, y);
        }
    }

    public void moveBy(double x, double y) {
        if(x != 0 || y != 0) {
            this.x += x;
            this.y += y;
            super.moveBy((float) x, (float) y);
        }
    }

    @Deprecated
    @Override
    public float getTop() {
        DeprecatedException.printWarning("Please use getTopDouble() instead");
        return (float) (y + getHeight());
    }

    @Deprecated
    @Override
    public float getRight() {
        DeprecatedException.printWarning("Please use getRightDouble() instead");
        return (float) (x + getWidth());
    }

    public double getTopDouble() {
        return y + getHeight();
    }

    public double getRightDouble() {
        return x + getWidth();
    }

    @Deprecated
    public void setBounds (float x, float y, float width, float height) {
        if (this.x != x || this.y != y) {
            DeprecatedException.printWarning("Please use setBounds(double, double, float, float) instead");
            setPosition(x, y);
        }
        this.setSize(width, height);
    }

    public void setBounds(double x, double y, float width, float height) {
        if (this.x != x || this.y != y) {
            setPosition(x, y);
        }
        this.setSize(width, height);
    }

    @Override
    public Vector2 screenToLocalCoordinates(Vector2 screenCoords) {
        System.out.println("screenToLocalCoordinates");
        return super.screenToLocalCoordinates(screenCoords);
    }

    @Override
    public Vector2 stageToLocalCoordinates(Vector2 stageCoords) {
        System.out.println("screenToLocalCoordinates");
        return super.stageToLocalCoordinates(stageCoords);
    }

    @Override
    public Vector2 localToStageCoordinates(Vector2 localCoords) {
        System.out.println("localToStageCoordinates");
        return super.localToStageCoordinates(localCoords);
    }

    @Override
    public Vector2 localToParentCoordinates(Vector2 localCoords) {
        System.out.println("localToParentCoordinates");
        return super.localToParentCoordinates(localCoords);
    }

    @Override
    public Vector2 localToAscendantCoordinates(Actor ascendant, Vector2 localCoords) {
        System.out.println("localToAscendantCoordinates");
        return super.localToAscendantCoordinates(ascendant, localCoords);
    }

    @Override
    public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
//        System.out.println("parentToLocalCoordinates");
        // TODO: gets called frequently
        return super.parentToLocalCoordinates(parentCoords);
    }

    @Override
    public boolean clipBegin() {
        throw new NotImplementedException("Clipping is not implemented for DoubleActor");
    }

    @Override
    public boolean clipBegin(float x, float y, float width, float height) {
        throw new NotImplementedException("Clipping is not implemented for DoubleActor");
    }

    @Override
    public void clipEnd() {
        throw new NotImplementedException("Clipping is not implemented for DoubleActor");
    }

    @Override
    public float getX(int alignment) {
        throw new NotImplementedException("Alignment is not implemented for DoubleActor");
    }

    @Override
    public void setX(float x, int alignment) {
        throw new NotImplementedException("Alignment is not implemented for DoubleActor");
    }

    @Override
    public void setY(float y, int alignment) {
        throw new NotImplementedException("Alignment is not implemented for DoubleActor");
    }

    @Override
    public float getY(int alignment) {
        throw new NotImplementedException("Alignment is not implemented for DoubleActor");
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        throw new NotImplementedException("Alignment is not implemented for DoubleActor");
    }
}
