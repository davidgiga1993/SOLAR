package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public class Kinematic {
    private Vector2 position;

    /**
     * This float holds the orientation angle in radians
     */
    private float rotation;
    private Vector2 velocity;
    private float maxSpeed;
    private float maxAcceleration = 1000f;
    private boolean isMoving = false;


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
    
    public Vector2 getPosition() {
    	return position;
    }
    
    public void setPosition(Vector2 newValue) {
    	position.x = newValue.x;
    	position.y = newValue.y;
    }
    
    public float getAngleOfPosition() {
    	return position.angle();
    }
    
    public float getXPosition() {
    	return position.x;
    }
    
    public float getYPosition() {
    	return position.y;
    }
    
    public float getRotation() {
    	return rotation;
    }
    
    public void setRotation(float value) {
    	this.rotation = value;
    }
    
    public Vector2 getVelocity() {
    	return velocity;
    }
    
    public float getSpeed() {
    	return velocity.len();
    }
    
    public void setVelocity(Vector2 newVelocity) {
    	this.velocity.x = newVelocity.x;
    	this.velocity.y = newVelocity.y; 
    }
    
    public void setVelocityAngle(float degrees) {
    	velocity.setAngle(degrees);
    }
    
    public float getMaxSpeed() {
    	return maxSpeed;
    }
    
    public void setMaxSpeed(float newMaximum) {
    	this.maxSpeed = newMaximum;
    }
    
    public float getMaxAcceleration() {
    	return maxAcceleration;
    }
    
    public boolean isMoving() {
    	return isMoving;
    }


}
