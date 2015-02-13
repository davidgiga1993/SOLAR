package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class Kinematic {
    public Vector2 position;

    /**
     * This float holds the orientation angle in radians
     */
    public float rotation;
    public Vector2 velocity;

    public float maxSpeed;
    public float maxAcceleration = 100000f;

    protected float slowDownDistance;
    protected float accelerationModifier = 1f;
    protected float slowDownTime = 0.25f;

    public boolean isMoving = false;


    /**
     * Creates a new Kinematic representation of the Game Object
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param speed the object's current speed
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(Vector2 position, float rotation, float speed, float maxSpeed) {
        this.position = position;
        this.velocity = new Vector2(1,0).setAngle(rotation).scl(speed);
        this.rotation = rotation;
        this.maxSpeed = maxSpeed;
        this.slowDownDistance = maxSpeed * slowDownTime;
    }

    /**
     * Creates a new Kinematic representation of the Game Object
     * Note: This constructor assumes the object's current speed to be 0.
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(Vector2 position, float rotation, float maxSpeed) {
        this(position, rotation, 0, maxSpeed);
    }

    /**
     * This method calculates the orientation (Vector2) based on the given rotation (float)
     * @param rotation angle in radians
     * @return vectorial represantation of the angle
     */
    public static Vector2 rotationToOrientation(float rotation) {
        Vector2 orientation = new Vector2(1,0);
        orientation.setAngle(rotation);
        return orientation;
    }

    public void update(Steering steering, float time) {
        float steeringLen = steering.linear.len();
        if (steeringLen == 0) {
            velocity.setZero();
            isMoving = false;
            return;
        }
        isMoving = true;
        rotation = velocity.angle();

        Vector2 distance = new Vector2(velocity).scl(time);
        position.add(distance);

        velocity.add(steering.linear.scl(time));

        if (velocity.len() > maxSpeed) {
            velocity.nor().scl(maxSpeed);
        }
    }


}
