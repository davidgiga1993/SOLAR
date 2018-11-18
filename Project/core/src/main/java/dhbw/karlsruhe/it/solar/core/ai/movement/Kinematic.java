package dhbw.karlsruhe.it.solar.core.ai.movement;


import mikera.vectorz.Vector2;

import static dhbw.karlsruhe.it.solar.core.utils.MathConstants.X_AXIS;

/**
 * Created by Arga on 13.02.2015.
 */
public class Kinematic {
    private Vector2 position;

    /**
     * This float holds the orientation angle in radians
     */
    private double rotation;
    private Vector2 velocity;
    private double maxSpeed;
    private double maxAcceleration = 1000.;
    private boolean isMoving = false;


    /**
     * Creates a new Kinematic representation of the Game Object
     *
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param speed    the object's current speed
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(com.badlogic.gdx.math.Vector2 position, double rotation, double speed, double maxSpeed) {
        this(new Vector2(position.x, position.y), rotation, speed, maxSpeed);
    }

    /**
     * Creates a new Kinematic representation of the Game Object
     *
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param speed    the object's current speed
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(Vector2 position, double rotation, double speed, double maxSpeed) {
        this.position = position;
        this.velocity = new Vector2(1, 0);
        this.velocity.rotate2D(rotation);
        this.velocity.scale(speed);
        this.rotation = rotation;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Creates a new Kinematic representation of the Game Object
     * Note: This constructor assumes the object's current speed to be 0.
     *
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(com.badlogic.gdx.math.Vector2 position, double rotation, double maxSpeed) {
        this(position, rotation, 0, maxSpeed);
    }

    /**
     * Creates a new Kinematic representation of the Game Object
     * Note: This constructor assumes the object's current speed to be 0.
     *
     * @param position the object's current position
     * @param rotation the object's current rotation in radians
     * @param maxSpeed the object's maximal speed
     */
    public Kinematic(Vector2 position, double rotation, double maxSpeed) {
        this(position, rotation, 0, maxSpeed);
    }

    public void update(Steering steering, float time) {
        double steeringLen = steering.getLengthLinear();
        if (steeringLen == 0) {
            velocity.setValues(0, 0);
            isMoving = false;
            return;
        }
        isMoving = true;
        rotation = position.angle(X_AXIS);

        Vector2 distance = new Vector2(velocity.x, velocity.y);
        distance.scale(time);
        position.add(distance);

        Vector2 scaledSteering = steering.scaleLinear(time);
        velocity.add(scaledSteering);

        if (velocity.magnitude() > maxSpeed) {
            velocity.normalise();
            velocity.scale(maxSpeed);
        }
    }

    public Vector2 getPosition() {
        return new Vector2((float) position.x, (float) position.y);
    }

    public void setPosition(Vector2 newValue) {
        position.x = newValue.x;
        position.y = newValue.y;
    }

    public double getAngleOfPosition() {
        return position.angle(X_AXIS);
    }

    public double getXPosition() {
        return position.x;
    }

    public double getYPosition() {
        return position.y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(float value) {
        this.rotation = value;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public double getSpeed() {
        return velocity.magnitude();
    }

    public void setVelocity(Vector2 newVelocity) {
        this.velocity.x = newVelocity.x;
        this.velocity.y = newVelocity.y;
    }

    public void setVelocityAngle(double degrees) {
        double currentAngle = velocity.angle(X_AXIS);
        double delta = degrees - currentAngle;
        velocity.rotate2D(delta);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double newMaximum) {
        this.maxSpeed = newMaximum;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public boolean isMoving() {
        return isMoving;
    }


}
