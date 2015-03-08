package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.BodyProperties;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.stages.guielements.BodyGameLabel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi
 *
 */
public abstract class AstronomicalBody extends Orbiter
{
	protected BodyProperties physicalProperties;
	protected List<AstronomicalBody> satellites = new ArrayList<AstronomicalBody>();
    protected BodyGameLabel label;

	public AstronomicalBody(String name)
	{
		super(name, new OrbitalProperties(null, new Length(0, Length.Unit.kilometres), new Angle()), ConfigurationConstants.SCALE_FACTOR_STAR);
		this.physicalProperties = new BodyProperties(new Mass(1, Mass.Unit.KILOGRAM), new Length(1, Length.Unit.kilometres), null);
	}

	public AstronomicalBody(String name, OrbitalProperties orbit, BodyProperties body, SolarActorScale scaleFactor, String textureName)
	{
		super(name, orbit, scaleFactor);
		setupSolarActorSprite(textureName);
		this.physicalProperties = body;
		this.label = new BodyGameLabel(name);	
		changeBodyScale();
	}

	private void changeBodyScale() {
		float tSize = scaleDistanceToStage(physicalProperties.getRadius().asKilometres()) * actorScale.shapeScale * 2;
		this.setSize(tSize, tSize);
	}

    /**
     * Adds a new Asteroid with the specified parameters as a satellite orbiting the astronomical body.
     * @param name Desired name of the Asteroid.
	 * @param radius Desired radius of the Asteroid
     * @param mass Desired mass of the Asteroid in kilogram
     * @param orbitalRadius Desired orbital radius around the parent body in kilometers
     * @param orbitalAngle Desired angle of the Asteroid's position on the map of the system relative to its parent body
     * @return created Asteroid object
     */
    public Asteroid placeNewAsteroid(String name, Length radius, Mass mass, Length orbitalRadius, Angle orbitalAngle)
    {
        Asteroid newObject = new Asteroid(name, radius, mass, orbitalRadius, orbitalAngle, this);
        newObject.setOrbitalPositionTotal();
        satellites.add(newObject);
        return newObject;
    }

	/**
	 * Searches the satellites of a parent astronomical object for a satellite with a matching name.
	 * @param name Searched for key word
	 * @return Satellite object with matching name or null if no satellite was found
	 */
	public AstronomicalBody findSatelliteByName(String name)
	{
		AstronomicalBody searchedBody;
		for (AstronomicalBody satellite : satellites) {
			if (satellite.getName().equals(name)) {
				return satellite;
			}
			searchedBody = satellite.findSatelliteByName(name);
			if (null != searchedBody && searchedBody.getName().equals(name)) {
				return searchedBody;
			}
		}
		return null;
	}
    
    @Override
    protected void adjustLabelPosition() {
		if(label.isVisible()) {
            Vector2 labelPos = new Vector2(getX()+getWidth(), getY() + getHeight());
            getStage().getViewport().project(labelPos);
            label.setPosition(labelPos.x, labelPos.y);
        }
	}
    
    protected void setUpRings(PlanetaryRing ring)
    {
    	physicalProperties.setUpRings(ring);
    	satellites.add(physicalProperties.getRings());
    }
    
    public void setRingPrimary(AstronomicalBody body)
    {
    	physicalProperties.setRingPrimary(body);
    }
    
	public void initializeAstronomicalBody(SolarSystem solarSystem) {
		setOrbitalPositionTotal();
		addAsSatellite();
        solarSystem.addMass(getMass());
        if(null != physicalProperties.getRings())
        {
        	setRingPrimary(this);
        }
	}

	private void addAsSatellite() {
        orbitalProperties.addAsSatellite(this);		
	}

	public List<AstronomicalBody> getSatellites()
	{
		return satellites;
	}

	public int getNumberOfSatellites()
	{
		return satellites.size();
	}
	
	public Length getRadius() {
		return physicalProperties.getRadius();
	}

	public Mass getMass() {
		return physicalProperties.getMass();
	}

	public Length calculateMaxOrbitalRadius() {
			return orbitalProperties.calculateMaxOrbitalRadius(physicalProperties.getMass());
	}
	
	protected void addMass(Mass massToBeAddedToTheBody) {
		physicalProperties.addMass(massToBeAddedToTheBody);
	}

	public void addSatellite(AstronomicalBody newSatellite) {
       satellites.add(newSatellite);		
	}
}
