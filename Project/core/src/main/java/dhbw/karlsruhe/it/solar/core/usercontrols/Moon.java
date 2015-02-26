package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;

/**
 * @author Andi
 *
 */
public class Moon extends AstronomicalBody
{
	public Moon(String name, Length radius, double massInEarthMasses, double orbitalRadiusInKilometers, float angleInDegree, AstronomicalBody origin)
	{
		//TODO: orbitalRadiusInKilometers was increased 20 times here for the testing of scaling purposes (moons visibly separate from planet without needing to zoom in. Needs to be redone at some point
		super(name, radius, orbitalRadiusInKilometers, convertEarthMassesIntoKilogram(massInEarthMasses), angleInDegree, origin, ConfigurationConstants.SCALE_FACTOR_MOON);
		//this.orbitalRadiusInKilometers = orbitalRadiusInKilometers * 20;
		//this.setSize(100, 100);
		this.setColor(Color.GRAY);
	    setupSolarActorSprite("TerrestrialMoon");
	}
	
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        solarActorSprite.setPosition(getX(), getY());
        solarActorSprite.draw(batch);
    }


}
