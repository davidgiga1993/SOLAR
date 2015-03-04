package dhbw.karlsruhe.it.solar.core.usercontrols;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
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
    
	@Override
	public void updateScale() {
		if(null != orbitalProperties)
		{
			changeOrbitScaleSpaceUnit();
		}
        float width = getWidth() / currentShapeScale * ConfigurationConstants.SCALE_FACTOR_UNITS.shapeScale;
        float height = getHeight() / currentShapeScale * ConfigurationConstants.SCALE_FACTOR_UNITS.shapeScale;
        setActorScale(ConfigurationConstants.SCALE_FACTOR_UNITS);
        setSize(width, height);
	}
	
    @Override
    public void act(float delta) {
		if(null != orbitalProperties)
		{
			changeOrbitScaleSpaceUnit();
		}
        super.act(delta);
    }
    
	@Override
    public void setDestination(AstronomicalBody destination) {
		if( isAbleToEnterOrbitAround(destination) )
		{
			//TODO: Very rough implementation. More elegant solution: Approach AI?
	        enterOrbit(destination);
	        return;
		}
        
    	//TODO: Entferne Debug-konsolenausgabe
        this.aiModule.setTarget(destination);
        this.destination = destination.getKinematic().position;
        System.out.println("Neues Ziel gesetzt f\u00fcr " + this.getName() + ": " + destination.getName() + " (" + destination.getX() + "/" + destination.getY()  + ").");
   }
    
	/**
     * Actor stops other movement actions and, starting from its current position, assumes a circular orbit around the parameter AstronomicalBody. 
     * @param orbitPrimary Object around which the actor will enter orbit.
     */
    public void enterOrbit(AstronomicalBody orbitPrimary)
    {  	
    	stopMovement();
		setNewOrbitalProperties(orbitPrimary);
    	setKinematicValues();
    	changeOrbitScaleSpaceUnit();   	
    	
    	//TODO: Entferne Debugkommentare
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println(this.getName() + " tritt in Orbit ein um: " + orbitPrimary.getName() + " (" + orbitPrimary.getX() + "/" + orbitPrimary.getY()  + "). Orbitalradius: " + df2.format(orbitalProperties.getOrbitalRadius().asAstronomicalUnit()) + " / Periodendauer: " + orbitalProperties.getOrbitalPeriodInDays());
    }

    /**
     * Ends all ongoing movement for the space unit.
     */
	private void stopMovement() {
		//TODO: Tell the AIModule to fuck off or something like that.
    	destination = null;
    	this.aiModule.setTarget(this);
	}

    /**
     * Sets the Orbital Properties of an orbit circling around the parameter AstronomicalBody, starting from the current position of the actor.
     * @param orbitPrimary Object around which the actor will enter orbit.
     */
	private void setNewOrbitalProperties(AstronomicalBody orbitPrimary)
	{
		Vector2 distance = getDistanceVector(orbitPrimary);    	
    	orbitalProperties = new OrbitalProperties(orbitPrimary, getPhysicalLength(orbitPrimary, distance), getAngleToXAxis(distance));
	}
	
	/**
	 * Determines the vector between the space unit and an astronomical body. Actor coordinates reference their lower left corner, therefore the vector calculation first determines the center of the actors.
	 * @param orbitPrimary Astronomical Body to which the space unit calculates its distance vector.
	 * @return Distance vector stating the in-game distance between the space unit and the parameter astronomical body.
	 */
	private Vector2 getDistanceVector(AstronomicalBody orbitPrimary) {
		return new Vector2( orbitPrimary.getX() + orbitPrimary.getWidth()/2, orbitPrimary.getY() + orbitPrimary.getHeight()/2 ).sub( getX() + getWidth()/2, getY() + getHeight()/2 );
	}
	
	/**
	 * Determines the actual physical length implied by an in-game vector. Takes the in-game scaling factors for the reference object into account.
	 * Essentially inverses the steps taken from the initial conversion of system creation AU distance values into the orbitalRadiusInPixel value.
	 * @param orbitPrimary Reference object to which the length is being calculated. Implies the scaling factor which needs to be used.
	 * @param distance Distance vector stating the in-game distance between the two objects.
	 * @return Physical length of the distance.
	 */
	private Length getPhysicalLength(AstronomicalBody orbitPrimary,
			Vector2 distance) {
		return new Length (inverseStagescaling(distance.len()) / new OrbitalProperties(orbitPrimary).getOrbitalSpaceUnitScaleFactor().orbitScale, Unit.kilometres);
	}

	/**
	 * Determines the angle between two positions relative to the x-axis. Used for the orbitalAngle between Astronomical Bodies.
	 * @param distance Vector containing the position
	 * @return Angle which has been calculated.
	 */
	private Angle getAngleToXAxis(Vector2 distance) {
		return new Angle(distance.angle() + 180, Angle.Unit.degree);
	}
    
	/**
	 * Sets the orbital radius relative to the parameter scaling setting.
	 * Different implementation to AstronomicalBody since space units have different orbit scaling depending on what type of object they orbit
	 * The method getOrbitalSpaceUnitScaleFactor() determines the appropriate scale setting for the object the unit is trying to orbit.
	 */
	private void changeOrbitScaleSpaceUnit() {
		setOrbitScale();
		orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometres()) * actorScale.orbitScale;
	}
	
	/**
	 * Adjusts only the Orbital Scale, not the Shapescale of the object. Allows Space Units to adjust the scale of their orbits individually.
	 * @param scale Scale value is derived from the configuration constants of the appropriate satellite to the orbital Primary body.
	 */
	private void setOrbitScale() {
		currentOrbitScale = orbitalProperties.getOrbitalSpaceUnitScaleFactor().orbitScale;
        actorScale = new SolarActorScale(currentShapeScale, currentOrbitScale);
    }
	
	/**
	 * Checks whether the space unit has approached the target Astronomical Body closely enough to be able to enter orbit around it.
	 * For this to be possible the astronomical body's gravitational field has to be dominant (i.e. noticeably stronger than that of its own primary body).
	 * @return
	 */
    private boolean  isAbleToEnterOrbitAround(AstronomicalBody destination)
    {    	  
		if( gravitationalPotentialOf(destination) > gravitationalPotentialOf(destination.getPrimary()))
		{
			return true;			
		}
		return false;
	}
    
    /**
     * Calculates the gravitational potential of the parameter astronomical body at the location of the space unit.
     * Gravitational Potential in the circular gravitational field of a point mass is: g(r) = - G * M / r² with G - Gravitational Constant, M - Mass of the point mass, r - radius to point mass
	 * Since this method compares to values relative to each other, this can be reduced to g1(r) = M1 / r², g2(r) = M2 / r²
     * @param body Point mass source of the circular gravitational field.
     * @return Value of the gravitational potential.
     */
	private float gravitationalPotentialOf(AstronomicalBody body) {
    	return (float) (body.getMass().asKilogram() / Math.pow(getPhysicalLength(body,getDistanceVector(body)).asKilometres(), 2)); 
	}
}
