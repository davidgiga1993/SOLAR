package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Andi
 *
 */
public class Planet extends AstronomicalBody
{
	public Planet(String name, double massInEarthMasses, double orbitalRadiusInAU, double angleInDegree, AstronomicalBody origin)
	{
		super(name, convertAUIntoKilometer(orbitalRadiusInAU), angleInDegree, origin);
		this.setSize(250, 250);
		this.massInKilogram = convertEarthMassesIntoKilogram(massInEarthMasses);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());        
        shapeRenderer.identity();
        
        displayOrbit();
        displayPlanet();  
	}

	private void displayPlanet() {
		shapeRenderer.begin(ShapeType.Filled);             
        shapeRenderer.rotate(0.f, 0.f, 1.f, getRotation());
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(getX() + getWidth() /2, getY() + getHeight() / 2, getHeight()/2);
        shapeRenderer.end();
	}
	
    /**
     * Adds a new moon with the specified parameters as a satellite orbiting the planet.
     * @param name Desired name of the Moon
     * @param massInEarthMasses Desired mass of the planet in multiples of Earth Masses
     * @param orbitalRadiusInKilometers Desired orbital radius around the star in kilometers
     * @param angleInDegree Desired angle of the moon's position on the map of the system relative to its planet
     * @return created Moon object
     */
    public Moon placeNewMoon(String name, double massInEarthMasses, double orbitalRadiusInKilometers, double angleInDegree)
    {
        Moon newObject = new Moon(name, massInEarthMasses, orbitalRadiusInKilometers, angleInDegree, this);
        newObject.calculateOrbitalPositionTotal();
        satellites.addActor(newObject);
        return newObject;
    }	
}
