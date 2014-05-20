package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Asteroid extends AstronomicalBody
{
	public Asteroid(String name, int orbitalRadius, int angleInDegree)
	{
		super(name, orbitalRadius, angleInDegree);
		this.setSize(25, 10);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        displayAsteroid();   
	}

	private void displayAsteroid() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.ellipse(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();
	}
}