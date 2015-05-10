package dhbw.karlsruhe.it.solar.core.space_units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.AIModule;
import dhbw.karlsruhe.it.solar.core.ai.AIOutput;
import dhbw.karlsruhe.it.solar.core.ai.AISpaceshipModule;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedEvent;
import dhbw.karlsruhe.it.solar.core.ai.events.TargetReachedListener;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.commands.OrbitalInsertionCommand;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Length.DistanceUnit;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.BaseResource;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.ShapeRenderable;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;
import dhbw.karlsruhe.it.solar.player.Ownable;
import dhbw.karlsruhe.it.solar.player.Player;

/**
 * 
 * @author Andi
 * SpaceUnit is supposed to define all shared properties of player unit objects such as ships or stations into one superclass
 * derived from SolarActor from which the individual unit subclasses can inherit.
 */
public abstract class SpaceUnit extends Orbiter implements ShapeRenderable, Ownable  {
    
    public static final Color SPACEUNIT_ORBIT_COLOR = new Color(0, 0.5f, 0, 1);
    public static final Credits SPACESHIP_YEARLY_RUNNING_COST = new Credits(10000000);
    public static final Credits SPACESTATION_YEARLY_RUNNING_COST = new Credits(50000000);

    protected Player owner;
    protected Vector2 destinationVector;
    protected Orbiter destination;
    protected float speed;
    AIModule aiModule;
    AIOutput aiOutput;
    
    
     public SpaceUnit(String name, Player owner, float speed)       {
          super(name);
        setActorScale(ConfigurationConstants.SCALE_FACTOR_UNITS);
         this.selected = false;
        this.owner = owner;
        this.speed = speed;
        selectionColor = owner.getPlayerColor();
        preview.setColor(selectionColor);
          orbitColor = SPACEUNIT_ORBIT_COLOR;
     }

     /**
      * Expenses of running this space unit over a given amount of time.
      * @param deltaT
      * @return
      */
     public Credits payUpKeep(Time deltaT) {
         return new Credits((long)(getRunningCosts().getNumber() * deltaT.inYears()));
     }
     
     protected abstract Credits getRunningCosts();

     /**
      * Shared constructor tasks for space units which have to come after their texture is added.
      * @param width
      * @param length
      */
     protected void initSpaceUnit(Length width, Length length) {
          this.setSize(width, length);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        kinematic.setMaxSpeed(speed);
        kinematic.setRotation(getRotation());        
        initializeAIModule();
     }

     private void initializeAIModule() {
        this.aiModule = new AISpaceshipModule(this);
        aiModule.setTarget(destinationVector);
        aiModule.addEventListener(new TargetReachedListener() {
            @Override
            public void handle(TargetReachedEvent event) {
                System.out.println("Target reached");
            }
        });
     }
     
    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        libGDXShapeRenderer.identity();

        displaySelectionBox(libGDXShapeRenderer);
        displayCourseAndDestination(libGDXShapeRenderer);
        super.drawLines(libGDXShapeRenderer, solarShapeRenderer);
    }
    
    @Override
    public boolean isOwnedBy(Player player) {
        return player.equals(owner);
    }

    @Override
    public void act(float delta) {
        if(isInOrbit())        {
               changeOrbitScaleSpaceUnit();
             super.act(delta);
             return;
          }
        aiOutput = aiModule.act(delta);
        setPosition(aiOutput.getPosition().x-getOriginX(), aiOutput.getPosition().y-getOriginY());
        // TODO: fix rotation offset of space unit... +90Â° necessary atm.
        setRotation(aiOutput.getRotation() + 90);
        super.act(delta);
    }
    
     @Override
     public void updateScale() {
          if(isInOrbit())          {
               changeOrbitScaleSpaceUnit();
          }
        float width = getWidth() / currentShapeScale * ConfigurationConstants.SCALE_FACTOR_UNITS.getShapeScale();
        float height = getHeight() / currentShapeScale * ConfigurationConstants.SCALE_FACTOR_UNITS.getShapeScale();
        setActorScale(ConfigurationConstants.SCALE_FACTOR_UNITS);
        setSize(width, height);
     }
    
     /**
      * Sets the orbital radius relative to the parameter scaling setting.
      * Different implementation to AstronomicalBody since space units have different orbit scaling depending on what type of object they orbit. 
      * The method getOrbitalSpaceUnitScaleFactor() determines the appropriate scale setting for the object the unit is trying to orbit.
      */
     private void changeOrbitScaleSpaceUnit() {
          setOrbitScale();
          orbitalRadiusInPixels = scaleDistanceToStage(orbitalProperties.getOrbitalRadius().asKilometers()) * actorScale.getOrbitScale();
     }
     
     /**
      * Adjusts only the Orbital Scale, not the Shapescale of the object. Allows Space Units to adjust the scale of their orbits individually.
      * @param scale Scale value is derived from the configuration constants of the appropriate satellite to the orbital Primary body.
      */
     private void setOrbitScale() {
         currentOrbitScale = OrbitalProperties.getOrbitalSpaceUnitScaleFactor(orbitalProperties.getPrimary()).getOrbitScale();
         actorScale = new SolarActorScale(currentShapeScale, currentOrbitScale);
    }
     
    /**
     * The Space Unit object receives a new destination for its course. The old destination is discarded.
     * @param destination Desired destination coordinates
     */
    public void setDestination(Vector2 destination)    {
         if(isInOrbit())        {
              leaveOrbit();
         }
        this.aiModule.setTarget(destination);
        this.destinationVector = destination;
        this.destination = null;
    }
    
    public void setDestination(KinematicObject destination)    {
         if(isInOrbit())         {
              leaveOrbit();     
         }
        this.aiModule.setTarget(destination.getKinematic());
        this.destinationVector = destination.getKinematic().getPosition();
        this.destination = (Orbiter) destination;
    }
    
    public void setDestination(AstronomicalBody destination) {
          if( isAlreadyInOrbitOfBodyOtherThan(destination) )          {
               leaveOrbit();
          }
          if( isAbleToEnterOrbitAround(destination) )          {
               //TODO: Very rough implementation. More elegant solution: Approach AI?
              OrbitalInsertionCommand orbitalInsertion = new OrbitalInsertionCommand(this);
              orbitalInsertion.execute();
             return;
          }
        this.aiModule.setTarget(destination);
        this.destinationVector = destination.getKinematic().getPosition();
        this.destination = destination;
     }

     public void establishColony(AstronomicalBody destination, Population colonists) {
          destination.establishColony("Testkolonie", owner, colonists);
     }
     
     public void establishColony() {
          orbitalProperties.getPrimary().establishColony("Testkolonie", owner, new Population(10 * BaseResource.THOUSAND));
     }
    
     /**
     * Actor stops other movement actions and, starting from its current position, assumes a circular orbit around the parameter AstronomicalBody. 
     * @param orbitPrimary Object around which the actor will enter orbit.
     */
     public void enterOrbit(AstronomicalBody orbitPrimary)    {       
         stopMovement();
         setNewOrbitalProperties(orbitPrimary);
         setKinematicValues();
         changeOrbitScaleSpaceUnit();        
    }
    
    public void enterOrbit() {
         AstronomicalBody orbitPrimary = calculateDominantGravitationSource();         
         stopMovement();
         setNewOrbitalProperties(orbitPrimary);
         setKinematicValues();
         changeOrbitScaleSpaceUnit();             
    }
    
    /**
     * Ends all ongoing movement for the space unit.
     */
     private void stopMovement() {
          //TODO: Tell the AIModule to fuck off or something like that.
         destinationVector = null;
         destination = null;
         this.aiModule.setTarget(this);
     }
     
        /**
     * Sets the Orbital Properties of an orbit circling around the parameter AstronomicalBody, starting from the current position of the actor.
     * @param orbitPrimary Object around which the actor will enter orbit.
     */
     private void setNewOrbitalProperties(AstronomicalBody orbitPrimary)     {
          Vector2 distance = getDistanceVector(orbitPrimary);         
         orbitalProperties = new OrbitalProperties(orbitPrimary, getPhysicalLength(orbitPrimary, distance), getAngleToXAxis(distance));
     }
     
     /**
      * Determines the vector between the space unit and another solar actor. Actor coordinates reference their lower left corner, therefore the vector calculation first determines the center of the actors.
      * @param target Target solar actor to which the space unit calculates its distance vector.
      * @return Distance vector stating the in-game distance between the space unit and the parameter astronomical body.
      */
     private Vector2 getDistanceVector(SolarActor target) {
          return new Vector2( target.getX() + target.getWidth()/2, target.getY() + target.getHeight()/2 ).sub( getX() + getWidth()/2, getY() + getHeight()/2 );
     }
     
     private Vector2 getDistanceVector(Vector2 target) {
         return new Vector2( target.x, target.y ).sub( getX() + getWidth()/2, getY() + getHeight()/2 );
    }
     
     /**
      * Determines the actual physical length implied by an in-game vector. Takes the in-game scaling factors for the reference object into account.
      * Essentially inverses the steps taken from the initial conversion of system creation AU distance values into the orbitalRadiusInPixel value.
      * @param orbitPrimary Reference object to which the length is being calculated. Implies the scaling factor which needs to be used.
      * @param distance Distance vector stating the in-game distance between the two objects.
      * @return Physical length of the distance.
      */
     private Length getPhysicalLength(Orbiter orbitPrimary, Vector2 distance) {
          return new Length (inverseStagescaling(distance.len()) / OrbitalProperties.getOrbitalSpaceUnitScaleFactor(orbitPrimary).getOrbitScale(), DistanceUnit.KILOMETERS);
     }

     /**
      * Determines the angle between two positions relative to the x-axis. Used for the orbitalAngle between Astronomical Bodies.
      * @param distance Vector containing the position
      * @return Angle which has been calculated.
      */
     private Angle getAngleToXAxis(Vector2 distance) {
          return new Angle(distance.angle() + 180, Angle.AngularUnit.DEGREE);
     }

     public Length physicalDistanceTo(Orbiter destination) {
          return getPhysicalLength(destination, getDistanceVector(destination));
     }
     
     public Length physicalDistanceTo(Vector2 destination) {
         return getPhysicalLength(((GameStartStage)getStage()).getSolarSystem(), getDistanceVector(destination));
    }

     private Length maxOrbitalRadiusFor(AstronomicalBody destination) {
          return destination.calculateMaxOrbitalRadius();
     }
     
    /**
     * Resizes the Space Unit
     * @param width
     * @param height
     */
    public void setSize(Length width, Length height) {
        // convert to pixels and scale with scale setting
        float pWidth = scaleDistanceToStage(width.asKilometers()) * ConfigurationConstants.SCALE_FACTOR_UNITS.getShapeScale();
        float pHeight = scaleDistanceToStage(height.asKilometers()) * ConfigurationConstants.SCALE_FACTOR_UNITS.getShapeScale();
        // call super
        super.setSize(pWidth , pHeight);
    }

     /**
     * Methods regulates how the course of the unit is displayed 
     * and also how the destination of the movement order is to be marked.
     * @param shapeRenderer
     */
    private void displayCourseAndDestination(ShapeRenderer shapeRenderer)    {
        if (destinationVector != null && this.aiModule.isMoving())        {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.line(getX() + getWidth() / 2, getY() + getHeight() / 2, destinationVector.x, destinationVector.y);
            shapeRenderer.circle(destinationVector.x, destinationVector.y, 10);

            displaySelectedDestination(shapeRenderer);
        }
    }

    /**
     * The destination of the movement order is highlighted differently if selected.
     * @param shapeRenderer
     */
    private void displaySelectedDestination(ShapeRenderer shapeRenderer)    {
        if (selected)        {
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(destinationVector.x - 13, destinationVector.y - 13, 26, 26);
        }
    }

    /**
     * @return Destination coordinates the spaceship object is heading to
     */
    public Vector2 getDestinationVector()    {
        return destinationVector;
    }
    
    /**
     * @return Destination object the spaceship object is heading to
     */
    public Orbiter getDestination()    {
        return destination;
    }
    
    public void leaveOrbit() {
        orbitalProperties = null;
        System.out.println(this.getName() + " verlässt Orbit. Gegenwärtige Position ( " + getX() + " / " + getY() + " ).");
        kinematic.setMaxSpeed(speed);
        aiModule.setPosition(kinematic.getPosition());
    }
    
     /**
      * Checks whether the space unit is already in orbit around another body and therefore needs to leave that orbit to correctly carry out the movement order.
      * @param destination New destination which is being compared with the current orbital primary.
      * @return
      */
     private boolean isAlreadyInOrbitOfBodyOtherThan(AstronomicalBody destination) {
         return isInOrbit() && destination != orbitalProperties.getPrimary();
     }
     
     /**
      * Checks whether the space unit has approached the target Astronomical Body closely enough to be able to enter orbit around it.
      * For this to be possible the astronomical body's gravitational field has to be dominant (i.e. noticeably stronger than that of its own primary body).
      * @return
      */
    public boolean  isAbleToEnterOrbitAround(AstronomicalBody destination)    {
        return maxOrbitalRadiusFor(destination).asAstronomicalUnit() > physicalDistanceTo(destination).asAstronomicalUnit();
    }

     public AstronomicalBody calculateDominantGravitationSource() {
          return ((GameStartStage)getStage()).calculateDominantGravitationSourceAt(this);
     }

    public boolean isPlayerAlsoShipOwner() {
        return ((GameStartStage)getStage()).isThisPlayerOnThisPlatform(owner);
    }

    public void removeShip() {
        ((GameStartStage)getStage()).removeShip(this);
        owner.removeShip(this);
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOnMission() {
        return null != destinationVector;
    }

    public String getNameOfDestination() {
        return destination.getName();
    }

    public String getMissionDescription() {
        if(null!=destination) {
            return destination.getName();
        }
        if(null!=destinationVector) {
            return destinationVector.x + "/" + destinationVector.y;
        }
        return "Idle";
    }

    public boolean isInOrbitAroundColonizableWorld() {
        return orbitalProperties.primaryIsColonizable();
    }

    public boolean isInOrbitAroundClaimableWorld() {
        return orbitalProperties.primaryIsClaimable();
    }

    public SolarActor getMissionTargetActor() {
        if(null!=destination) {
            return destination;
        }
        return null;
    }

    public String getMissionDistanceValue() {
        if(null != destination) {
            return physicalDistanceTo(getDestination()).toString();            
        }
        if(null != destinationVector) {
            return physicalDistanceTo(destinationVector).toString();
        }
        return new Length(0, DistanceUnit.KILOMETERS).toString();
    }
}
