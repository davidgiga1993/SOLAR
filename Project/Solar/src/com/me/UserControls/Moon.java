package com.me.UserControls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Moon extends AstronomicalBody
{
	public Moon(String name, int orbitalRadius, int angleInDegree, AstronomicalBody origin)
	{
		super(name, orbitalRadius, angleInDegree, origin);
		this.setSize(25, 25);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
   
        displayOrbit();
        displayMoon();       
	}

	private void displayMoon() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle(getX(), getY(), getHeight()/2);
        shapeRenderer.end();
	}
}