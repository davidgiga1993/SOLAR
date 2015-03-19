package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.inputlisteners.GameInputListener;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.resources.Population.Unit;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;
import dhbw.karlsruhe.it.solar.player.Player;
import dhbw.karlsruhe.it.solar.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class GameStartStage extends BaseStage implements Telegraph
{
    public Selection selectedActors = new Selection();
    public SelectionRectangle selectionRectangle;
    private SolarSystem solarSystem;
    public static GameInputListener inputListener;
    private PlayerManager playerManager = new PlayerManager();

    private SolarShapeRenderer solarShapeRenderer = new SolarShapeRenderer();
    private ShapeRenderer libGDXShapeRenderer = new ShapeRenderer();

    public static float gameSpeed = 0f;
    public static float oldGameSpeed = 1f;

    public static Time gameTime = new Time();

    protected Player humanPlayer;
    protected Player aiPlayer;

    private List<PlanetaryRing> ringList = new ArrayList<PlanetaryRing>();

    /**
     * Call this to initialize a new game.
     */
    public static void startGame(){
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();

        GameStartStage gameStage = new GameStartStage(engine);
        engine.stageManager.addStage(gameStage);

        if (SolarEngine.DEBUG) {
            HUDStage hudStage = new HUDStage(engine, "HUD");
            engine.stageManager.addStage(hudStage);
        }

        GameHUDStage gameHUDStage = new GameHUDStage(engine);
        engine.stageManager.addStage(gameHUDStage);

        gameHUDStage.init();
        gameStage.init();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gameHUDStage);
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    public static void endGame() {
        SolarEngine engine = SolarEngine.get();
        StageManager manager = engine.stageManager;

        manager.removeStage("GameHUD").dispose();

        if(SolarEngine.DEBUG) {
            manager.removeStage("HUD").dispose();
        }

        manager.removeStage("GameStartStage").dispose();

        Gdx.input.setInputProcessor(engine);
        engine.stageManager.StartGame();
    }

    public GameStartStage(SolarEngine SE)
    {
        super(SE, "GameStartStage");
        SE.Service.StartGame();
        SE.camera.zoom = 25;

        gameStartStageListener();
        addSelectionRectangle();


        humanPlayer = playerManager.createPlayer("Human Player");
        aiPlayer = playerManager.createPlayer("CPU Player");
    }

    public void init() {
        systemCreation();
        placeNewShip("Event Horizon", new Vector2(1200, 500), humanPlayer);
        placeNewShip("Nostromo", new Vector2(1500, 1000), humanPlayer);
        placeNewShip("Destiny", new Vector2(1550, 1050), aiPlayer);
        placeNewStation("Deep Space Nine", new Vector2(1500, 0), humanPlayer);
        
        // Create an example space station orbiting Earth and found a player colony on Earth
        Spacestation babylon = placeNewStation("Babylon 5", new Vector2(-3755.3f,-6477.7f), aiPlayer);
        AstronomicalBody primary = solarSystem.findSatelliteByName("Earth");
        if (null != primary) {
            babylon.enterOrbit(primary);
            primary.establishColony(humanPlayer, new Population(7.125f, Unit.BILLION));
        }
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
            if(child instanceof  PlanetaryRing) {
                ringList.add((PlanetaryRing) child);
            }
        }
        libGDXShapeRenderer.end();
        // draw sprite batch and polygon batch stuff
        super.draw();

        for (PlanetaryRing ring : ringList) {
            ring.draw();
        }
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
        delta *= GameStartStage.gameSpeed;
        gameTime.addDays(delta);
        super.act(delta);
    }

    /**
     * Initial demonstration object.
     * TODO: Remove
     */
    private void addSelectionRectangle()
    {
        selectionRectangle = new SelectionRectangle();
        addActor(selectionRectangle);
    }

    /**
     * Creates the solar system for the game.
     * Method called during startup of a game for creation of a new system map with a range of astronomical objects.
     */
    private void systemCreation()
    {
        // Creates the Solar System for the game
        solarSystem = new SolarSystem(getGameName());
        solarSystem.createSolarSystem();
        addActor(solarSystem);
        addSolarSystemActors(solarSystem);
    }

    /**
     * Adds an astronomomical object and its satellites as new actors to the game.
     * @param body Astronomical Object to be added to the game.
     */
    private void addSolarSystemActors(AstronomicalBody body)
    {
        if (body.getNumberOfSatellites() != 0)
        {
            for (AstronomicalBody astronomicalBody : body.getSatellites()) {
                addActor(astronomicalBody);
                addSolarSystemActors(astronomicalBody);
            }
        }
    }

    /**
     * @return Name of the Save Game / System Map currently being played. Currently a stub.
     * TODO: expand appropriately
     */
    private String getGameName()
    {
        String name = "Sol";
        return name;
    }

    /**
     * Adds a new spaceship object to the game.
     * @param name Desired name of the spaceship.
     * @param startlocation Desired location at which the ship is to appear.
     */
    private void placeNewShip(String name, Vector2 startlocation, Player owner)
    {
        Spaceship newShip = Spaceship.placeNewShip(name, startlocation, owner);
        addActor(newShip);
    }
    
    /**
     * Adds a new space station object to the game.
     * @param name Desired name of the station.
     * @param startlocation Desired location at which the station is to appear.
     */
    private Spacestation placeNewStation(String name, Vector2 startlocation, Player owner)
    {
        Spacestation newStation = Spacestation.placeNewStation(name, startlocation, owner);
        addActor(newStation);
        return newStation;
    }
    

    /**
     * Waits for mouse input in the game and interprets it accordingly.
     * Handles selection and deselection of objects.
     * Handles movement orders.
     */
    private void gameStartStageListener()
    {
    	inputListener = new GameInputListener(this);
    	this.addListener(inputListener);
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }


    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
        SolarEngine.messageDispatcher.dispatchMessage(this, SolarMessageType.NEW_ACTOR_ADDED, actor);
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        return false;
    }

    public static void changeTimeSpeed(float increase) {
        float newSpeed = GameStartStage.gameSpeed + increase;
        if (newSpeed < 0) {
            newSpeed = 0;
        }
        GameStartStage.gameSpeed = (float) Math.round(newSpeed * 10)/10;
        SolarEngine.messageDispatcher.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, new Float(GameStartStage.gameSpeed));
    }

    public static void setTimeSpeed(float newSpeed) {
        GameStartStage.gameSpeed = (float) Math.round(newSpeed * 10)/10;
        SolarEngine.messageDispatcher.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED,new Float(GameStartStage.gameSpeed));
    }

    public static void togglePause() {
        if(GameStartStage.gameSpeed == 0) {
            GameStartStage.gameSpeed = GameStartStage.oldGameSpeed;
        } else {
            GameStartStage.oldGameSpeed = GameStartStage.gameSpeed;
            GameStartStage.gameSpeed = 0;
        }
        SolarEngine.messageDispatcher.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, new Float(GameStartStage.gameSpeed));
    }
}
