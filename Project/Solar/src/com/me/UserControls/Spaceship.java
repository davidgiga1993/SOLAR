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
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
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

        if (selected)
        {
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.rect(getX() - 5, getY() - 5, getWidth() + 10, getHeight() + 10);
        }

        shapeRenderer.end();
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
        MoveToAction mov = new MoveToAction();
        mov.setPosition(this.destination.x, this.destination.y);
		float deltaX = getX() - destination.x;
		float deltaY = getY() - destination.y;
		float moveTime = (float) ((Math.sqrt(Math.pow(deltaX,2)+ Math.pow(deltaY,2))) / speed);
        mov.setDuration(moveTime);
		this.addAction(mov);
	}

}
