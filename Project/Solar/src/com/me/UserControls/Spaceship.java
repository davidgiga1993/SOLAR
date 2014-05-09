package com.me.UserControls;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Spaceship extends SolarActor
{

    private GridPoint2 destination;
	private float speed = 100f;
	
    public Spaceship(String name)
    {
        this.setSize(33, 33);
        this.shapeRenderer = new ShapeRenderer();
        this.selected = false;
        this.destination = null;
        this.setName(name);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {  	
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.identity();
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());     
        
        displaySpaceship();
        displaySelectionBox();       
        displayCourseAndDestination();

    }
    

	private void displaySpaceship() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.triangle(getX() + getWidth() / 2, getY(), getX(), getY() + getHeight(), getX() + getWidth(), getY() + getHeight());
		shapeRenderer.end();
	}

	private void displayCourseAndDestination() {
		//Anzeige des Kurses und Markierung des Ziels
        if ( destination != null && this.getActions().size != 0 )
        {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.line(getX() + getWidth() / 2, getY() + getHeight() / 2, destination.x, destination.y);
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.circle(destination.x, destination.y, 10);
        shapeRenderer.end();        
 
        displaySelectedDestination();
        }
	}

	private void displaySelectedDestination() {
		//Besondere Hervorhebung des Ziels wenn selektiert
        if (selected)
        {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(destination.x - 13, destination.y -13, 26, 26);
        shapeRenderer.end();    
        }
	}

	private void displaySelectionBox() {
		//Anzeige Selektions Box
        if (selected)
        {
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(getX() - 5, getY() - 5, getWidth() + 10, getHeight() + 10);
            shapeRenderer.end();
                   }
	}

    public void setDestination(GridPoint2 destination)
    {
        this.destination = destination;
        System.out.println("Neues Ziel gesetzt für " + this.getName() + " bei X= " + destination.x + ", Y= " + destination.y);
    }

    public GridPoint2 getDestination()
    {
        return destination;
    }

    public void moveSpaceship()
    {
    	getActions().clear();  	
 //       setPosition(-getWidth() /2 , -getHeight()/2);
    	SequenceAction sequence = new SequenceAction();
 //   	sequence.addAction(shipRotation());
    	sequence.addAction(shipMovement());
    	addAction(sequence);
	}

	private MoveToAction shipMovement() {
		//MovetoAction bezieht sich auf linke untere Ecke des Spaceship-Objekts. Umrechnung auf Schiffsmittelpunkt erforderlich
    	GridPoint2 adjDestination = new GridPoint2( destination.x - (int)(getWidth() / 2), destination.y - (int)(getHeight() / 2));
        MoveToAction mov = new MoveToAction();
        mov.setPosition(adjDestination.x, adjDestination.y);
		float deltaX = getX() - adjDestination.x;
		float deltaY = getY() - adjDestination.y;
		float moveTime = (float) ((Math.sqrt(Math.pow(deltaX,2)+ Math.pow(deltaY,2))) / speed);
        mov.setDuration(moveTime);
		return mov;
	}
    
	private RotateToAction shipRotation()
	{
		float angle = (float)calculateRotationAngle();
        return Actions.rotateTo(angle, calculateRotationDuration(angle));        
	}

	private float calculateRotationDuration(float angle) {
		float DurationMofifier = 90f;
		if ( angle < 0 )
			return -angle/DurationMofifier;
		else
			return angle/DurationMofifier;
	}

	private double calculateRotationAngle()
	{
		return -Math.toDegrees(Math.atan2( getX() + getWidth()/2 - destination.x , getY() + getHeight()/2 - destination.y ));
	}

	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
