package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import dhbw.karlsruhe.it.solar.core.colony.BuildingManager;
import dhbw.karlsruhe.it.solar.core.colony.Colony;
import dhbw.karlsruhe.it.solar.core.colony.ColonyBuildings;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstroBodyManager;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GameInputListener;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.physics.CalendarTime;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.physics.Time.TimeUnit;
import dhbw.karlsruhe.it.solar.core.resources.BaseResource;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.savegames.*;
import dhbw.karlsruhe.it.solar.core.solar.SolarCamera;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnitManager;
import dhbw.karlsruhe.it.solar.core.space_units.Spaceship;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceStation;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBarManagerSettings;
import dhbw.karlsruhe.it.solar.core.usercontrols.DoubleActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SelectionRectangle;
import dhbw.karlsruhe.it.solar.core.usercontrols.ShapeRenderable;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.player.Player;
import dhbw.karlsruhe.it.solar.core.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class GameStartStage extends BaseStage implements Telegraph {
    public static final CalendarTime GAME_TIME = new CalendarTime();
    private static GameInputListener inputListener;
    private static float gameSpeed = 0f;
    private static float oldGameSpeed = 1f;
    private final SolarCamera camera;
    private Selection selectedActors = new Selection();
    private SelectionRectangle selectionRectangle;
    private SolarSystem solarSystem;
    private PlayerManager playerManager = new PlayerManager();
    private SolarShapeRenderer solarShapeRenderer = new SolarShapeRenderer();
    private ShapeRenderer libGDXShapeRenderer = new ShapeRenderer();
    private List<PlanetaryRing> ringList = new ArrayList<>();

    private List<DoubleActor> doubleActors = new ArrayList<>(200);

    private GameStartStage(SolarEngine se, SolarCamera camera) {
        super(se, "GameStartStage");
        se.setZoomSolarCameraTo(25);

        gameStartStageListener();
        addSelectionRectangle();
        this.camera = camera;
    }

    /**
     * Initialize a new game creating a new system.
     */
    public static void startNewGame() {
        GAME_TIME.reset();
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();

        GameStartStage gameStage = initGameStartStage(engine);
        GameHUDStage gameHUDStage = initGameHUDStage(engine);

        gameStage.initNewGame();

        initMultiplexer(gameStage, gameHUDStage);
    }

    /**
     * Initialize a game using the previously saved game state.
     */
    public static void startCurrentGame() {
        GAME_TIME.reset();
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();

        GameStartStage gameStage = initGameStartStage(engine);
        GameHUDStage gameHUDStage = initGameHUDStage(engine);

        gameStage.initCurrentGame();

        initMultiplexer(gameStage, gameHUDStage);
    }

    private static GameStartStage initGameStartStage(SolarEngine engine) {
        GameStartStage gameStage = new GameStartStage(engine, engine.getCamera());
        engine.addStage(gameStage);

        if (SolarEngine.DEBUG) {
            HUDStage hudStage = new HUDStage(engine, "HUD");
            engine.addStage(new HUDStage(engine, "HUD"));
        }
        return gameStage;
    }

    private static void initMultiplexer(GameStartStage gameStage,
                                        GameHUDStage gameHUDStage) {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gameHUDStage);
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private static GameHUDStage initGameHUDStage(SolarEngine engine) {
        GameHUDStage gameHUDStage = new GameHUDStage(engine);
        engine.addStage(gameHUDStage);

        gameHUDStage.init();
        return gameHUDStage;
    }

    public static void endGame() {
        SolarEngine engine = SolarEngine.get();

        engine.disposeOfStage("GameHUD");

        if (SolarEngine.DEBUG) {
            engine.disposeOfStage("HUD");
        }

        engine.disposeOfStage("GameStartStage");

        Gdx.input.setInputProcessor(engine);
        engine.startGame();
    }

    public static void changeTimeSpeed(float increase) {
        float newSpeed = GameStartStage.gameSpeed + increase;
        if (newSpeed < 0) {
            newSpeed = 0;
        }
        GameStartStage.gameSpeed = (float) Math.round(newSpeed * 100) / 100;
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, GameStartStage.gameSpeed);
    }

    public static void setTimeSpeed(float newSpeed) {
        GameStartStage.gameSpeed = (float) Math.round(newSpeed * 10) / 10;
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, GameStartStage.gameSpeed);
    }

    public static void togglePause() {
        if (GameStartStage.gameSpeed == 0) {
            GameStartStage.gameSpeed = GameStartStage.oldGameSpeed;
        } else {
            GameStartStage.oldGameSpeed = GameStartStage.gameSpeed;
            GameStartStage.gameSpeed = 0;
        }
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, GameStartStage.gameSpeed);
    }

    public static void inputListenerInteract(InputEvent event) {
        inputListener.interact(event, 0, 0);
    }

    public static void inputListenerNavigate(InputEvent event) {
        inputListener.navigate(event, 0, 0);
    }

    public static float getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Old method / Scenario : Creates the solar system with code lines, doesn't use save game mechanic.
     */
    public static void startSolarScenario() {
        GAME_TIME.reset();
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();

        GameStartStage gameStage = initGameStartStage(engine);
        GameHUDStage gameHUDStage = initGameHUDStage(engine);

        gameStage.initExampleSystem();
        gameHUDStage.update();

        initMultiplexer(gameStage, gameHUDStage);
    }

    public static void inputListenerMoveCamera(SolarActor target) {
        inputListener.moveCamera(target);
    }

    public static void stopTime() {
        setTimeSpeed(0);
    }

    private void initCurrentGame() {
        SaveGameManager loadedGame = new SaveGameManager(this);
        loadedGame.loadCurrentGame();
    }

    private void initNewGame() {
        SaveGameManager loadedGame = new SaveGameManager(this);
        loadedGame.loadNewGame();
    }

    private void placeNewColony(String nameOfAstronomicalBody, String colonyName, Player foundingPlayer, Population colonists, ColonyBuildings buildings) {
        AstronomicalBody primary = solarSystem.findAstronomicalBodyByName(nameOfAstronomicalBody);
        Colony newColony = primary.establishColony(colonyName, foundingPlayer, colonists);
        newColony.initBuildings(buildings);
    }

    @Override
    public void draw() {
        // render line shapes
        libGDXShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        libGDXShapeRenderer.setProjectionMatrix(getCamera().combined);
        solarShapeRenderer.setProjectionMatrix(getCamera().combined);
        ringList.clear();
        for (Actor child : getRoot().getChildren()) {
            if (child instanceof ShapeRenderable) {
                ((ShapeRenderable) child).drawLines(libGDXShapeRenderer, solarShapeRenderer);
            }
            if (child instanceof PlanetaryRing) {
                ringList.add((PlanetaryRing) child);
            }
        }
        libGDXShapeRenderer.end();

        drawSprites();

        for (PlanetaryRing ring : ringList) {
            ring.draw();
        }
    }

    /**
     * Will draw the sprites using set batch with the following steps to reduce floating point error:
     * <ol>
     *     <li>Set camera's position to zero</li>
     *     <li>subtract the camera's actual position from every DoubleActor</li>
     *     <li>draw everything</li>
     *     <li>restore actors' actual positions</li>
     *     <li>reset camera's position back to it's actual position</li>
     * </ol>
     */
    private void drawSprites() {
        Vector3 oldPosition = new Vector3(camera.position);
        camera.position.setZero();
        doubleActors.forEach(item -> item.translateAndBackup(-camera.positionDouble.x, -camera.positionDouble.y));

        camera.update();

        if (!getRoot().isVisible()) return;

        Batch batch = this.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // FIXME: Ich denke es ist sinnvoll hier in Java die Positionen alle um die Kameraposition zu reduzieren.
        // Au�erdem muss die Kamera noch auf Double umgestellt werden \o/
        getRoot().draw(batch, 1);
        batch.end();

        doubleActors.forEach(DoubleActor::restore);
        camera.position.set(oldPosition);
    }

    @Override
    public void dispose() {
        super.dispose();
        solarShapeRenderer.dispose();
        libGDXShapeRenderer.dispose();
    }

    @Override
    public void act(float delta) {
        inputListener.handleContinuousInput(delta);
        Time newDelta = new Time(delta * GameStartStage.gameSpeed, TimeUnit.DAYS);
        GAME_TIME.addTime(newDelta);
        se.update(newDelta);
        super.act(newDelta.inDays());
    }

    private void addSelectionRectangle() {
        selectionRectangle = new SelectionRectangle();
        addActor(selectionRectangle);
    }

    /**
     * Waits for mouse input in the game and interprets it accordingly.
     * Handles selection and deselection of objects.
     * Handles movement orders.
     */
    private void gameStartStageListener() {
        inputListener = new GameInputListener(this);
        this.addListener(inputListener);
    }

    public Player getPlayerOnThisPlatform() {
        return playerManager.getPlayerOnThisPlatform();
    }

    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(this, SolarMessageType.NEW_ACTOR_ADDED, actor);
        if(actor instanceof DoubleActor) {
            doubleActors.add((DoubleActor) actor);
        }
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        return false;
    }

    public AstronomicalBody calculateDominantGravitationSourceAt(SpaceUnit unit) {
        return solarSystem.calculateDominantGravitationSourceAt(unit);
    }

    public List<SpaceUnit> getSelectedSpaceUnits() {
        return selectedActors.getSpaceUnits();
    }

    public void addToSelectedActors(Actor newlySelected) {
        selectedActors.add(newlySelected);
    }

    public void removeFromSelectedActors(Actor deselectedActor) {
        selectedActors.remove(deselectedActor);
    }

    public void clearSelectedActors() {
        selectedActors.clear();
    }

    public SolarActor getRepresentativeOfSelectedActors() {
        return selectedActors.getRepresentative();
    }

    public void startOfSelectionRectangle(float x, float y) {
        selectionRectangle.setStart(x, y);
    }

    public void updateEndOfSelectionRectangle(float x, float y) {
        selectionRectangle.updateEnd(x, y);
    }

    public void hideSelectionRectangle() {
        selectionRectangle.hide();
    }

    public Rectangle getSelectionRectangle() {
        return selectionRectangle.getRectangle();
    }

    public boolean isThisPlayerOnThisPlatform(Player player) {
        return playerManager.isThisPlayerOnThisPlatform(player);
    }

    public void removeShip(SpaceUnit spaceUnit) {
        spaceUnit.remove();
        selectedActors.remove(spaceUnit);
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(this, SolarMessageType.ACTOR_REMOVED, spaceUnit);
    }

    public void refreshSelection(SolarActor actor) {
        selectedActors.remove(actor);
        selectedActors.add(actor);
    }

    public List<Player> getPlayers() {
        return playerManager.getPlayersInGame();
    }

    /**
     * Converts player information in the form of PlayerInfo into players on the game stage. Part of the loading process.
     *
     * @param players
     */
    public void initPlayers(List<PlayerInfo> players) {
        for (PlayerInfo playerInfo : players) {
            playerManager.createNewPlayer(playerInfo);
        }
        playerManager.initPlayerOnThisPlatform(0);
    }

    /**
     * Converts astronomical body information in the form of AstroBodyInfo into celestial objects on the game stage. Part of the loading process.
     *
     * @param astroBodies
     */
    public void initAstroBodies(List<AstroBodyInfo> astroBodies) {
        AstroBodyManager manager = new AstroBodyManager();
        AstroBodyInfo system = astroBodies.remove(0);
        solarSystem = new SolarSystem(system.getName());
        manager.initSolarSystem(solarSystem);
        addActor(solarSystem);
        for (AstroBodyInfo body : astroBodies) {
            addActor(manager.createNewBody(body));
            initColonies(body);
        }

    }

    private void initColonies(AstroBodyInfo body) {
        if (body.isColonized()) {
            ColonyInfo colony = body.getColonyInfo();
            placeNewColony(body.getName(), colony.getColonyName(), playerManager.getPlayerFromName(colony.getNameOfOwner()), colony.getPopulation(), colony.getBuildings());
        }
    }

    /**
     * Converts unit information in the form of SpaceUnitInfo into space unit objects on the game stage. Part of the loading process.
     *
     * @param spaceUnits
     */
    public void initUnits(List<SpaceUnitInfo> spaceUnits) {
        SpaceUnitManager manager = new SpaceUnitManager(playerManager, solarSystem);
        for (SpaceUnitInfo unit : spaceUnits) {
            addActor(manager.createNewUnit(unit));
        }
    }

    /**
     * Converts mission information in the form of MissionInfoExtended into orders to space units. Part of the loading process.
     *
     * @param listOfMissions
     */
    public void assignMission(List<MissionInfoExtended> listOfMissions) {
        for (MissionInfoExtended mission : listOfMissions) {
            assignDestination(mission);
        }
    }

    private void assignDestination(MissionInfoExtended mission) {
        SpaceUnit unit = (SpaceUnit) findActorByName(mission.getUnitName());

        if (mission.isMissionTargetAnObject()) {
            assignObjectTarget(mission, unit);
            return;
        }
        assert unit != null;
        Vector2 destination = mission.getDestinationCoordinates();
        unit.setDestination(new mikera.vectorz.Vector2(destination.x, destination.y));
    }

    private void assignObjectTarget(MissionInfoExtended mission, SpaceUnit unit) {
        SolarActor target = (SolarActor) findActorByName(mission.getDestinationName());
        if (target instanceof AstronomicalBody) {
            unit.setDestination((AstronomicalBody) target);
            return;
        }
        if (target instanceof KinematicObject) {
            unit.setDestination((KinematicObject) target);
        }
    }

    private Actor findActorByName(String unitName) {
        for (Actor gameActor : getActors()) {
            if (unitName.equals(gameActor.getName())) {
                return gameActor;
            }
        }
        return null;
    }

    /**
     * Old method retained for code maintenance purposes. Manually initializes an example system without the use of a save file.
     */
    private void initExampleSystem() {
        playerManager.initializePlayers();
        initSettings(new InfoBarManagerSettings());

        systemCreation();
        placeNewShip("Event Horizon", new Vector2(1200, 500), playerManager.getPlayerNumber(0));
        placeNewShip("Nostromo", new Vector2(1500, 1000), playerManager.getPlayerNumber(0));
        placeNewShip("Destiny", new Vector2(1550, 1050), playerManager.getPlayerNumber(1));
        placeNewStation("Deep Space Nine", new Vector2(1500, 0), playerManager.getPlayerNumber(0));

        // Create an example space station orbiting Earth
        SpaceStation babylon = placeNewStation("Babylon 5", new Vector2(-3755.3f, -6477.7f), playerManager.getPlayerNumber(1));
        AstronomicalBody primary = solarSystem.findAstronomicalBodyByName(ConfigurationConstants.HOME_WORLD);
        if (null != primary) {
            babylon.enterOrbit(primary);
        }
        //place some example colonies
        placeNewColony(ConfigurationConstants.HOME_WORLD, ConfigurationConstants.HOME_WORLD, playerManager.getPlayerNumber(0), new Population(7125 * BaseResource.MILLION), new BuildingManager().createInfrastructure(1000000).generateColonyBuildings());
        placeNewColony("Moon", "Tranquility Base", playerManager.getPlayerNumber(1), new Population(3141 * BaseResource.THOUSAND), new BuildingManager().createInfrastructure(2500).generateColonyBuildings());
        placeNewColony("Mars", "Utopia Planitia", playerManager.getPlayerNumber(0), new Population(11235), new BuildingManager().createInfrastructure(2).generateColonyBuildings());
    }

    /**
     * Creates the solar system for the game.
     * Method called during startup of a game for creation of a new system map with a range of astronomical objects.
     */
    private void systemCreation() {
        // TODO: Remove - Old method, Creates the Solar System for the game
        solarSystem = new SolarSystem("Solar System");
        solarSystem.createSolarSystem();
        addActor(solarSystem);
        addSolarSystemActors(solarSystem);
    }

    /**
     * Adds an astronomical object and its satellites as new actors to the game.
     *
     * @param body Astronomical Object to be added to the game.
     */
    private void addSolarSystemActors(AstronomicalBody body) {
        // TODO: Remove - Old method
        if (body.getNumberOfSatellites() != 0) {
            for (AstronomicalBody astronomicalBody : body.getSatellites()) {
                addActor(astronomicalBody);
                addSolarSystemActors(astronomicalBody);
            }
        }
    }

    /**
     * Adds a new spaceship object to the game.
     *
     * @param name          Desired name of the spaceship.
     * @param startLocation Desired location at which the ship is to appear.
     */
    private void placeNewShip(String name, Vector2 startLocation, Player owner) {
        Spaceship newShip = Spaceship.placeNewShip(name, new mikera.vectorz.Vector2(startLocation.x, startLocation.y), owner);
        addActor(newShip);
    }

    /**
     * Adds a new space station object to the game.
     *
     * @param name          Desired name of the station.
     * @param startLocation Desired location at which the station is to appear.
     */
    private SpaceStation placeNewStation(String name, Vector2 startLocation, Player owner) {
        SpaceStation newStation = SpaceStation.placeNewStation(name, new mikera.vectorz.Vector2(startLocation.x, startLocation.y), owner);
        addActor(newStation);
        return newStation;
    }

    public InfoBarManagerSettings getSettings() {
        return se.getSettings();
    }

    public void initSettings(InfoBarManagerSettings settings) {
        se.initSettings(settings);
    }

    public void initGameTime(Time gameTimeElapsed) {
        GAME_TIME.initGameTime(gameTimeElapsed);
    }

    public void updateProduction(Time deltaT) {
        for (Player player : getPlayers()) {
            player.updateProduction(deltaT);
            player.updateTotalPopulation();
            player.updateTreasury(deltaT);
        }
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
}
