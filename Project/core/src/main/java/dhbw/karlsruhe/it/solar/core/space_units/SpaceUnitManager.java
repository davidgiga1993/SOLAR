package dhbw.karlsruhe.it.solar.core.space_units;

import com.badlogic.gdx.math.Vector2;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.savegames.MissionInfo;
import dhbw.karlsruhe.it.solar.core.savegames.OrbitalPropertyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.SpaceUnitInfo;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.player.Player;
import dhbw.karlsruhe.it.solar.player.PlayerManager;

/**
 * Handles the creation of space units for the game.
 * @author Andi
 * created 2015-04-06
 */
public class SpaceUnitManager {
    
    private PlayerManager playerManager;
    private SolarSystem solarSystem;
    private String name;
    private Player owner;
    private OrbitalPropertyInfo orbitInfo;
    private Vector2 startlocation;
    private AstronomicalBody primary;
    private MissionInfo mission;
    
    
    public SpaceUnitManager(PlayerManager playerManager, SolarSystem solarSystem) {
        this.playerManager = playerManager;
        this.solarSystem = solarSystem;
    }

    /**
     * Creates a new Space Unit based on the information contained in the parameter.
     * @param unit Information which will be used to construct a new unit.
     * @return Newly created Spaceship or Station.
     */
    public SpaceUnit createNewUnit(SpaceUnitInfo unit) {
        String type = unit.getType();
        this.name = unit.getName();
        this.owner = playerManager.getPlayerFromName(unit.getOwnerName());
        this.orbitInfo = unit.getOrbitInfo();
        this.startlocation = unit.getLocation();
        this.mission = unit.getMissionInfo();
        
        return createUnitBasedOnType(unit, type);         
    }

    private SpaceUnit createUnitBasedOnType(SpaceUnitInfo unit, String type) {
        if(type.equals("Ship")) {
            return createShip(unit);
        }
        if(type.equals("Station")) {
            return createStation(unit);
        }
        return null;
    }

    private Spacestation createStation(SpaceUnitInfo unit) {
        Spacestation station;
        
        if(null!=startlocation) {
            station = Spacestation.placeNewStation(name, startlocation, owner);  
            return station;
        }
        
        if(null!=orbitInfo) {            
            extractOrbitalLocation();
            station = Spacestation.placeNewStation(name, startlocation, owner);
            station.enterOrbit(primary);
            return station;
        }
        
        return null;
    }

    private void extractOrbitalLocation() {
        primary = solarSystem.findAstronomicalBodyByName(orbitInfo.getPrimary());
        OrbitalProperties prop = new OrbitalProperties(primary,orbitInfo.getOrbitalRadius(),orbitInfo.getPolarAngle());
        float orbitalRadiusInPixels = SolarActor.scaleDistanceToStage(prop.getOrbitalRadius().asKilometers()) * prop.getOrbitalSpaceUnitScaleFactor().getOrbitScale();
        startlocation = prop.getOrbitalPositionTotal(orbitalRadiusInPixels, new Angle());
    }

    private Spaceship createShip(SpaceUnitInfo unit) {
        Spaceship ship;
        
        if(null!=startlocation) {
            ship = Spaceship.placeNewShip(name, startlocation, owner);
            return ship;
        }
        
        if(null!=orbitInfo) {            
            extractOrbitalLocation();
            ship = Spaceship.placeNewShip(name, startlocation, owner);
            ship.enterOrbit(primary);
            return ship;
        }
        
        return null;
    }
}
