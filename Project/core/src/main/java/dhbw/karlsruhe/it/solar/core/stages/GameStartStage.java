package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GameInputListener;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.logic.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;
import dhbw.karlsruhe.it.solar.player.Player;
import dhbw.karlsruhe.it.solar.player.PlayerManager;

public class GameStartStage extends BaseStage
{
    public Selection selectedActors = new Selection();
    public SelectionRectangle selectionRectangle;
    private SolarSystem solarSystem;
    private GameInputListener inputListener;
    private PlayerManager playerManager = new PlayerManager();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    protected Player humanPlayer;
    protected Player aiPlayer;

    /**
     * Call this to initialize a new game.
     */
    public static void startGame(){
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();


        BackgroundStage backgroundStage = new BackgroundStage(engine);
        engine.stageManager.insertStageToBack(backgroundStage);

        GameStartStage gameStage = new GameStartStage(engine);
        engine.stageManager.addStage(gameStage);

        if (SolarEngine.DEBUG) {
            HUDStage hudStage = new HUDStage(engine, "HUD");
            engine.stageManager.addStage(hudStage);
        }

        GameHUDStage gameHUDStage = new GameHUDStage(engine);
        engine.stageManager.addStage(gameHUDStage);



        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gameHUDStage);
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    public static void endGame() {
        SolarEngine engine = SolarEngine.get();
        StageManager manager = engine.stageManager;

        manager.removeStage("Background").dispose();
        manager.removeStage("GameHUD").dispose();

        if(SolarEngine.DEBUG) {
            manager.removeStage("HUD").dispose();
        }

        manager.removeStage("GameStartStage").dispose();

        Gdx.input.setInputProcessor(engine);
        engine.stageManager.StartGame();
    }

    @Override
    public void draw() {
        // render line shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(getCamera().combined);
        for (Actor child : getRoot().getChildren()) {
            if (child instanceof ShapeRenderable) {
                ((ShapeRenderable) child).drawLines(shapeRenderer);
            }
        }
        shapeRenderer.end();
        // draw sprite batch stuff
        super.draw();
    }

    public GameStartStage(SolarEngine SE)
    {
        super(SE, "GameStartStage");
        SE.Service.StartGame();
        SE.camera.zoom = 1;

        gameStartStageListener();
        addSelectionRectangle();
        systemCreation();

        humanPlayer = playerManager.createPlayer("Human Player");
        aiPlayer = playerManager.createPlayer("CPU Player");

        placeNewShip("Event Horizon", new Vector2(0, 0), humanPlayer);
        placeNewShip("Nostromo", new Vector2(1500, 1000), humanPlayer);
        //placeNewShip("Destiny", new GridPoint2(75, 0), aiPlayer);
    }
    
    @Override
    public void act(float delta) {
    	inputListener.handleContinousInput(delta);
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
            for (int index = body.getNumberOfSatellites(); index > 0; index--)
            {
                AstronomicalBody object = (AstronomicalBody) body.getSatellites().getChildren().get(0);
                addActor(object);
                addSolarSystemActors(object);
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


}
