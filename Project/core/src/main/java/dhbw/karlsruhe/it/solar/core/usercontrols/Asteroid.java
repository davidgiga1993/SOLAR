package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody
{
	public Asteroid(String name, Length radius, double massInKilogram, double orbitalRadiusInKilometers, double angleInDegree, AstronomicalBody origin)
	{
		super(name, radius, orbitalRadiusInKilometers, massInKilogram, angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_ASTEROID);
		//this.setSize(100, 50);
		this.setColor(Color.LIGHT_GRAY);
	}

	@Override
	protected void drawBody(ShapeRenderer shapeRenderer) {
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.ellipse(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
	}
}