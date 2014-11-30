package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, double massInEarthMasses, double orbitalRadiusInKilometers, double angleInDegree, AstronomicalBody origin)
	{
		//TODO: orbitalRadiusInKilometers was increased 20 times here for the testing of scaling purposes (moons visibly separate from planet without needing to zoom in. Needs to be redone at some point
		super(name, orbitalRadiusInKilometers, convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin);
		this.orbitalRadiusInKilometers = orbitalRadiusInKilometers * 20;
		this.setSize(100, 100);
		this.color = Color.GRAY;
	}


}
