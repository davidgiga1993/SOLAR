package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.Vector2;

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
    	//TODO: Entferne Debugkommentare
        DecimalFormat df2 = new DecimalFormat("#.##");
    	orbitalProperties = new OrbitalProperties(orbitPrimary, new Length(0.1f, Unit.astronomicalUnit), new Angle());
    	setKinematicValues();
    	changeOrbitScaleSpaceUnit();
        System.out.println(this.getName() + " tritt in Orbit ein um: " + orbitPrimary.getName() + " (" + orbitPrimary.getX() + "/" + orbitPrimary.getY()  + "). Orbitalradius: " + df2.format(orbitalProperties.getOrbitalRadius().asAstronomicalUnit()) + " / Periodendauer: " + orbitalProperties.getOrbitalPeriodInDays());
 
    }
    
    @Override
    public void setDestination(AstronomicalBody destination) {
        enterOrbit(destination);
   }
    
	/**
	 * Sets the orbital radius in pixels value relative to the parameter scaling setting.
	 * Necessary for space units which need to determine the appropriate scale setting for the object they are trying to orbit.
	 */
	public void changeOrbitScaleSpaceUnit() {
		setOrbitScale(orbitalProperties.getOrbitalSpaceUnitScaleFactor());
		orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}
	
	@Override
	public void updateScale() {
		if(null != orbitalProperties)
		{
			changeOrbitScaleSpaceUnit();
		}
	}
	
	/**
	 * Adjusts only the Orbital Scale, not the Shapescale of the object. Allows Space Units to adjust the scale of their orbits individually.
	 * @param scale Scale value is derived from the configuration constants of the appropriate satellite to the orbital Primary body.
	 */
    public void setOrbitScale(SolarActorScale scale) {
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
