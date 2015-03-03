package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.AIModule;
import dhbw.karlsruhe.it.solar.core.ai.AIOutput;
import dhbw.karlsruhe.it.solar.core.ai.movement.Kinematic;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Length.Unit;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * Code for the space station unit object
 * @author Andi
 */
public class Spacestation extends SpaceUnit
{
	private static float stationSpeed = 500f;

	public Spacestation(String name, Length width, Length length, Player owner)
	{
		super(name, owner,stationSpeed);
	    setupSolarActorSprite("space_station");  
        initSpaceUnit(width, length);
	}
	
    /**
     * Adds a new space station object to the game.
     * @param name Desired name of the station.
     * @param startlocation Desired location at which the station is to appear.
     */
    public static Spacestation placeNewStation(String name, Vector2 startlocation, Player owner)
    {
        Spacestation newStation = new Spacestation(name, new Length(1, Length.Unit.kilometres), new Length(1, Length.Unit.kilometres), owner);
        newStation.setPosition(startlocation.x, startlocation.y);
        newStation.kinematic.position = startlocation;
        newStation.setDestination(startlocation);
        return newStation;
    }
    
    public void enterOrbit(AstronomicalBody orbitPrimary)
    {  	
    	//TODO: Tell the AIModule to fuck off or something like that.
    	destination = null;
		Vector2 distance = new Vector2( orbitPrimary.getX() + orbitPrimary.getWidth()/2, orbitPrimary.getY() + orbitPrimary.getHeight()/2 ).sub( getX() + getWidth()/2, getY() + getHeight()/2 );
    	Length orbitalRadius = calculateDistanceInKilometer(distance, new OrbitalProperties(orbitPrimary));     
    	Angle angle = new Angle(distance.angle() + 180, Angle.Unit.degree);
    	
    	orbitalProperties = new OrbitalProperties(orbitPrimary, orbitalRadius, angle);
    	setKinematicValues();
    	changeOrbitScaleSpaceUnit();   	
    	
    	//TODO: Entferne Debugkommentare
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println(this.getName() + " tritt in Orbit ein um: " + orbitPrimary.getName() + " (" + orbitPrimary.getX() + "/" + orbitPrimary.getY()  + "). Orbitalradius: " + df2.format(orbitalProperties.getOrbitalRadius().asAstronomicalUnit()) + " / Periodendauer: " + orbitalProperties.getOrbitalPeriodInDays());
    }

    /**
     * Turns a in-game distance vector into the corresponding physical value.
     * Essentially inverses the steps taken from the initial conversion of system creation AU distance values into the orbitalRadiusInPixel value.
     * @param distance Vector distance to be converted back into a physical value.
     * @return Result is the corresponding physical distance in kilometer.
     */
	private Length calculateDistanceInKilometer(Vector2 distance, OrbitalProperties newOrbit) {	
		return new Length (inverseStagescaling(distance.len()) / newOrbit.getOrbitalSpaceUnitScaleFactor().orbitScale, Unit.kilometres);
	}

	@Override
    public void setDestination(AstronomicalBody destination) {
        enterOrbit(destination);
   }
    
	/**
	 * Sets the orbital radius in pixels value relative to the parameter scaling setting.
	 * Necessary for space units which need to determine the appropriate scale setting for the object they are trying to orbit.
	 */
	private void changeOrbitScaleSpaceUnit() {
		setOrbitScale(orbitalProperties.getOrbitalSpaceUnitScaleFactor());
		orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}
	
	@Override
	public void updateScale() {
		if(null != orbitalProperties)
		{
			changeOrbitScaleSpaceUnit();
		}
        float width = getWidth() / currentShapeScale * actorScale.shapeScale;
        float height = getHeight() / currentShapeScale * actorScale.shapeScale;
        setSize(width, height);
        setActorScale(actorScale);
	}
	
	/**
	 * Adjusts only the Orbital Scale, not the Shapescale of the object. Allows Space Units to adjust the scale of their orbits individually.
	 * @param scale Scale value is derived from the configuration constants of the appropriate satellite to the orbital Primary body.
	 */
	private void setOrbitScale(SolarActorScale scale) {
        actorScale = new SolarActorScale(currentShapeScale, scale.orbitScale);
        currentOrbitScale = scale.orbitScale;
    }
    
    @Override
    public void act(float delta) {
		if(null != orbitalProperties)
		{
			changeOrbitScaleSpaceUnit();
		}
        super.act(delta);
    }
}
