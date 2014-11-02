package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, double massInEarthMasses, double orbitalRadiusInKilometers, double angleInDegree, AstronomicalBody origin)
	{
		//TODO: orbitalRadiusInKilometers was increased 20 times here for the testing of scaling purposes (moons visibly separate from planet without needing to zoom in. Needs to be redone at some point
		super(name, orbitalRadiusInKilometers*20, angleInDegree, origin);
		this.setSize(100, 100);
		this.massInKilogram = convertEarthMassesIntoKilogram(massInEarthMasses);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
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
        shapeRenderer.circle(getX() + getWidth() /2, getY() + getHeight() / 2, getHeight()/2);
        shapeRenderer.end();
	}
}
