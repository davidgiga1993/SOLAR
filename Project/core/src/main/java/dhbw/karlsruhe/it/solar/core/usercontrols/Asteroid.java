package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody
{
	public Asteroid(String name, double massInKilogram, double orbitalRadiusInKilometers, double angleInDegree, AstronomicalBody origin)
	{
		super(name, orbitalRadiusInKilometers, angleInDegree, origin);
		this.setSize(100, 50);
		this.massInKilogram = massInKilogram;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        displayOrbit();
        displayAsteroid();   
	}

	private void displayAsteroid() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.ellipse(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();
	}
}