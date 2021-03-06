package dhbw.karlsruhe.it.solar.core.space_units;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.physics.Angle;
import dhbw.karlsruhe.it.solar.core.physics.OrbitalProperties;
import dhbw.karlsruhe.it.solar.core.savegames.MissionInfo;
import dhbw.karlsruhe.it.solar.core.savegames.OrbitalPropertyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.SpaceUnitInfo;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.player.Player;
import dhbw.karlsruhe.it.solar.core.player.PlayerManager;
import dhbw.karlsruhe.it.solar.core.utils.VectorUtils;
import mikera.vectorz.Vector2;

/**
 * Handles the creation of space units for the game.
 *
 * @author Andi
 * created 2015-04-06
 */
public class SpaceUnitManager {

    private PlayerManager playerManager;
    private SolarSystem solarSystem;
    private String name;
    private Player owner;
    private OrbitalPropertyInfo orbitInfo;
    private Vector2 startLocation;
    private AstronomicalBody primary;
    private MissionInfo mission;


    public SpaceUnitManager(PlayerManager playerManager, SolarSystem solarSystem) {
        this.playerManager = playerManager;
        this.solarSystem = solarSystem;
    }

    /**
     * Creates a new Space Unit based on the information contained in the parameter.
     *
     * @param unit Information which will be used to construct a new unit.
     * @return Newly created Spaceship or Station.
     */
    public SpaceUnit createNewUnit(SpaceUnitInfo unit) {
        String type = unit.getType();
        this.name = unit.getName();
        this.owner = playerManager.getPlayerFromName(unit.getOwnerName());
        this.orbitInfo = unit.getOrbitInfo();
        this.startLocation = VectorUtils.convert(unit.getLocation());
        this.mission = unit.getMissionInfo();

        return createUnitBasedOnType(unit, type);
    }

    private SpaceUnit createUnitBasedOnType(SpaceUnitInfo unit, String type) {
        if (type.equals("Ship")) {
            return createShip(unit);
        }
        if (type.equals("Station")) {
            return createStation(unit);
        }
        return null;
    }

    private SpaceStation createStation(SpaceUnitInfo unit) {
        SpaceStation station;

        if (null != startLocation) {
            station = SpaceStation.placeNewStation(name, startLocation, owner);
            return station;
        }

        if (null != orbitInfo) {
            extractOrbitalLocation();
            station = SpaceStation.placeNewStation(name, startLocation, owner);
            station.enterOrbit(primary);
            return station;
        }

        return null;
    }

    private void extractOrbitalLocation() {
        primary = solarSystem.findAstronomicalBodyByName(orbitInfo.getPrimary());
        OrbitalProperties prop = new OrbitalProperties(primary, orbitInfo.getOrbitalRadius(), orbitInfo.getPolarAngle());
        double orbitalRadiusInPixels = SolarActor.scaleDistanceToStage(prop.getOrbitalRadius().asKilometers()) * OrbitalProperties.getOrbitalSpaceUnitScaleFactor(prop.getPrimary().asAstronomicalBody()).getOrbitScale();
        startLocation = prop.getOrbitalPositionTotal(orbitalRadiusInPixels, new Angle());
    }

    private Spaceship createShip(SpaceUnitInfo unit) {
        Spaceship ship;

        if (null != startLocation) {
            ship = Spaceship.placeNewShip(name, startLocation, owner);
            return ship;
        }

        if (null != orbitInfo) {
            extractOrbitalLocation();
            ship = Spaceship.placeNewShip(name, startLocation, owner);
            ship.enterOrbit(primary);
            return ship;
        }

        return null;
    }
}
