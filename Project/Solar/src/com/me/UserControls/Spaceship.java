package com.me.UserControls;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

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
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.identity();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.triangle(getX() + getWidth() / 2, getY(), getX(), getY() + getHeight(), getX() + getWidth(), getY() + getHeight());
        shapeRenderer.end();

        //Anzeige Selektions Box
        if (selected)
        {
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(getX() - 5, getY() - 5, getWidth() + 10, getHeight() + 10);
            shapeRenderer.end();
                   }
       
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
 
        //Besondere Hervorhebung des Ziels wenn selektiert
        if (selected)
        {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(destination.x - 13, destination.y -13, 26, 26);
        shapeRenderer.end();    
        }
        }

    }

    public void setDestination(GridPoint2 destination)
    {
        this.destination = destination;
        System.out.println("Neues Ziel gesetzt f�r " + this.getName() + " bei X= " + destination.x + ", Y= " + destination.y);
    }

    public GridPoint2 getDestination()
    {
        return destination;
    }

    public void moveSpaceship()
    {
    	getActions().clear();
    	
    	//MovetoAction bezieht sich auf linke untere Ecke des Spaceship-Objekts. Umrechnung auf Schiffsmittelpunkt erforderlich
    	GridPoint2 adjDestination = new GridPoint2( destination.x - (int)(getWidth() / 2), destination.y - (int)(getHeight() / 2));
    	
        MoveToAction mov = new MoveToAction();
        mov.setPosition(adjDestination.x, adjDestination.y);
		float deltaX = getX() - adjDestination.x;
		float deltaY = getY() - adjDestination.y;
		float moveTime = (float) ((Math.sqrt(Math.pow(deltaX,2)+ Math.pow(deltaY,2))) / speed);
        mov.setDuration(moveTime);
		this.addAction(mov);
	}
    
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
