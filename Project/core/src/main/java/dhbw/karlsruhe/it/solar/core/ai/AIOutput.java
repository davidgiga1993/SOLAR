package dhbw.karlsruhe.it.solar.core.ai;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class AIOutput {
    private Vector2 position;
    private float rotation;
    
    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }
    
    public void setRotation(float newRotation) {
        rotation = newRotation;
    }
    
    public Vector2 getPosition() {
        return position;
    }
    
    public float getRotation() {
        return rotation;
    }
}
