package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.*;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.stages.guielements.BodyGameLabel;
import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;
import dhbw.karlsruhe.it.solar.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi
 *
 */
public abstract class AstronomicalBody extends Orbiter  {
    protected BodyProperties physicalProperties;
    protected List<AstronomicalBody> satellites = new ArrayList<AstronomicalBody>();
    protected BodyGameLabel label;
    private Colony colony;

    public AstronomicalBody(String name)    {
        super(name, new OrbitalProperties(null, new Length(0, Length.DistanceUnit.KILOMETERS), new Angle()), ConfigurationConstants.SCALE_FACTOR_STAR);
        this.physicalProperties = new BodyProperties(new Mass(1, Mass.MassUnit.KILOGRAM), new Length(1, Length.DistanceUnit.KILOMETERS), null);
    }

    public AstronomicalBody(String name, OrbitalProperties orbit, BodyProperties body, SolarActorScale scaleFactor, String textureName)    {
        super(name, orbit, scaleFactor);
        setupSolarActorSprite(textureName);
        this.physicalProperties = body;
        this.colony = null;
        this.label = new BodyGameLabel(name);    
        changeBodyScale();
    }
    
    private void changeBodyScale() {
        float tSize = scaleDistanceToStage(physicalProperties.getRadius().asKilometres()) * actorScale.getShapeScale() * 2;
        this.setSize(tSize, tSize);
    }

    /**
     * Searches the satellites of a parent astronomical object for a satellite with a matching name.
     * @param name Searched for key word
     * @return Satellite object with matching name or null if no satellite was found
     */
    public AstronomicalBody findAstronomicalBodyByName(String name)    {
        
        if(this.getName().equals(name)) {
            return this;
        }
        
        AstronomicalBody searchedBody;
        for (AstronomicalBody satellite : satellites) {
            if (satellite.getName().equals(name)) {
                return satellite;
            }
            searchedBody = satellite.findAstronomicalBodyByName(name);
            if (null != searchedBody && searchedBody.getName().equals(name)) {
                return searchedBody;
            }
        }
        return null;
    }
    
    public AstronomicalBody calculateDominantGravitationSourceAt(SpaceUnit unit) {
        AstronomicalBody dominantSource = null;
        for (AstronomicalBody satellite : satellites) {
            if(unit.isAbleToEnterOrbitAround(satellite)) {
                dominantSource = satellite;
            }
            AstronomicalBody dominantSatellite = null;
            dominantSatellite = satellite.calculateDominantGravitationSourceAt(unit);
            if(null != dominantSatellite) {
                dominantSource = dominantSatellite;
            }
        }
        return dominantSource;
    }
    
    @Override
    protected void adjustLabelPosition() {
        if(label.isVisible()) {
            Vector2 labelPos = new Vector2(getX()+getWidth(), getY() + getHeight());
            getStage().getViewport().project(labelPos);
            label.setPosition(labelPos.x, labelPos.y);
        }
    }
    
    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        if(null != colony) {
            displaySelectionBox(libGDXShapeRenderer);
        }
        super.drawLines(libGDXShapeRenderer, solarShapeRenderer);
    }
    
    protected void setUpRings(PlanetaryRing ring)    {
        physicalProperties.setUpRings(ring);
        satellites.add(physicalProperties.getRings());
    }
    
    public void setRingPrimary(AstronomicalBody body)    {
        physicalProperties.setRingPrimary(body);
    }
    
    public void initializeAstronomicalBody(SolarSystem solarSystem) {
        setOrbitalPositionTotal();
        addAsSatellite();
        solarSystem.addMass(getMass());
        if(null != physicalProperties.getRings())      {
            setRingPrimary(this);
        }
    }
    
    public Colony establishColony(String colonyName, Player player, Population colonists) {
        colony = new Colony(colonyName, this, player, colonists);
        selectionColor = player.getPlayerColor();
        preview.setColor(selectionColor);
        return colony;
    }

    protected void addAsSatellite() {
        orbitalProperties.addAsSatellite(this);        
    }

    public List<AstronomicalBody> getSatellites()    {
        return satellites;
    }

    public int getNumberOfSatellites()    {
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

    public boolean isColonized() {
        return null != colony;
    }
    
    public String getPopulationNumbers() {
        return colony.getPopulationNumbers();
    }

    public String getColonyName() {
        return colony.getName();
     }

     public boolean isColonyOwnedBy(Player humanPlayer) {
        return colony.isOwnedBy(humanPlayer);
    }

    public Colony getColony() {
        return colony;
    }

    public void abandonColony() {
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(this, SolarMessageType.ACTOR_REMOVED, this);  
        colony = null;
        ((GameStartStage)getStage()).refreshSelection(this);
    }

    public boolean isPlayerAlsoColonyOwner() {
        return ((GameStartStage)getStage()).isThisPlayerOnThisPlatform(colony.getOwner());
    }
    
    public BodyType getBodyType() {
        return physicalProperties.getBodyType();
    }

    public PlanetaryRing getRings() {
        return physicalProperties.getRings();
    }
    
    public boolean hasRingSystem() {
        return physicalProperties.hasRings();
    }
}