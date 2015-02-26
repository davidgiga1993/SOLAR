package dhbw.karlsruhe.it.solar.core.usercontrols;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;

/**
 * @author Andi
 *
 */
public class Asteroid extends AstronomicalBody
{
	public Asteroid(String name, Length radius, double massInKilogram, double orbitalRadiusInKilometers, float angleInDegree, AstronomicalBody origin)
	{
		super(name, radius, orbitalRadiusInKilometers, massInKilogram, angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_ASTEROID,"IrregularSatellite");
	}
	
//	@Override
//	protected void drawBody(ShapeRenderer shapeRenderer) {
//		shapeRenderer.end();
//		shapeRenderer.begin(ShapeType.Filled);             
//        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
//        shapeRenderer.setColor(Color.GRAY);
//        shapeRenderer.ellipse(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
//        shapeRenderer.end();
//		shapeRenderer.begin(ShapeType.Line);
//	}
}