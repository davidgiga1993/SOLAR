package dhbw.karlsruhe.it.solar.core.ai;


import mikera.vectorz.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class AIOutput {
    private Vector2 position;
    private double rotation;

    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }

    public void setRotation(double newRotation) {
        rotation = newRotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getRotation() {
        return rotation;
    }
}
