package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstroBodyManager;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.PlanetaryRing;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GameInputListener;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.savegames.AstroBodyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.ColonyInfo;
import dhbw.karlsruhe.it.solar.core.savegames.PlayerInfo;
import dhbw.karlsruhe.it.solar.core.savegames.SaveGameManager;
import dhbw.karlsruhe.it.solar.core.savegames.SpaceUnitInfo;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnitManager;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;
import dhbw.karlsruhe.it.solar.player.Player;
import dhbw.karlsruhe.it.solar.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class GameStartStage extends BaseStage implements Telegraph {
    private Selection selectedActors = new Selection();
    private SelectionRectangle selectionRectangle;
    private SolarSystem solarSystem;
    private static GameInputListener inputListener;
    private PlayerManager playerManager = new PlayerManager();

    private SolarShapeRenderer solarShapeRenderer = new SolarShapeRenderer();
    private ShapeRenderer libGDXShapeRenderer = new ShapeRenderer();

    private static float gameSpeed = 0f;
    private static float oldGameSpeed = 1f;

    public static final Time GAMETIME = new Time();

    private List<PlanetaryRing> ringList = new ArrayList<PlanetaryRing>();

    public GameStartStage(SolarEngine se)   {
        super(se, "GameStartStage");
        se.setZoomSolarCameraTo(25);

        gameStartStageListener();
        addSelectionRectangle();
    }
    
    /**
     * Initialize a new game creating a new system.
     */
    public static void startNewGame() {
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
        SolarEngine engine = (SolarEngine) Gdx.app.getApplicationListener();

        GameStartStage gameStage = initGameStartStage(engine);
        GameHUDStage gameHUDStage = initGameHUDStage(engine);
        
        gameStage.initCurrentGame();
        
        initMultiplexer(gameStage, gameHUDStage);
    }

    private static GameStartStage initGameStartStage(SolarEngine engine) {
        GameStartStage gameStage = new GameStartStage(engine);
        engine.addStage(gameStage);

        if (SolarEngine.DEBUG) {
            HUDStage hudStage = new HUDStage(engine, "HUD");
            engine.addStage(hudStage);
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

        if(SolarEngine.DEBUG) {
            engine.disposeOfStage("HUD");
        }

        engine.disposeOfStage("GameStartStage");

        Gdx.input.setInputProcessor(engine);
        engine.startGame();
    }

    public void initCurrentGame() {
        SaveGameManager loadedGame = new SaveGameManager(this);
        loadedGame.loadCurrentGame();
    }
    
    public void initNewGame() {
        SaveGameManager loadedGame = new SaveGameManager(this);
        loadedGame.loadNewGame();        
    }

    private void placeNewColony(String nameOfAstronomicalBody, String colonyName, Player foundingPlayer, Population colonists) {
        AstronomicalBody primary = solarSystem.findAstronomicalBodyByName(nameOfAstronomicalBody);
        primary.establishColony(colonyName, foundingPlayer, colonists);
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
        float newDelta = delta * GameStartStage.gameSpeed;
        GAMETIME.addDays(newDelta);
        super.act(newDelta);
    }

    private void addSelectionRectangle()    {
        selectionRectangle = new SelectionRectangle();
        addActor(selectionRectangle);
    }    

    /**
     * Waits for mouse input in the game and interprets it accordingly.
     * Handles selection and deselection of objects.
     * Handles movement orders.
     */
    private void gameStartStageListener()    {
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
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, new Float(GameStartStage.gameSpeed));
    }

    public static void setTimeSpeed(float newSpeed) {
        GameStartStage.gameSpeed = (float) Math.round(newSpeed * 10)/10;
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED,new Float(GameStartStage.gameSpeed));
    }

    public static void togglePause() {
        if(GameStartStage.gameSpeed == 0) {
            GameStartStage.gameSpeed = GameStartStage.oldGameSpeed;
        } else {
            GameStartStage.oldGameSpeed = GameStartStage.gameSpeed;
            GameStartStage.gameSpeed = 0;
        }
        SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(null, SolarMessageType.GAME_SPEED_CHANGED, new Float(GameStartStage.gameSpeed));
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
        selectionRectangle.setStart(x,y);
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
    
    public static void inputListenerInteract(InputEvent event) {
        inputListener.interact(event, 0, 0);
    }
    
    public static void inputListenerNavigate(InputEvent event) {
        inputListener.navigate(event, 0, 0);
    }
    
    public static float getGameSpeed() {
        return gameSpeed;
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
        if(body.isColonized()) {
            ColonyInfo colony = body.getColonyInfo();
            placeNewColony(body.getName(), colony.getColonyName(), playerManager.getPlayerFromName(colony.getNameOfOwner()), colony.getPopulation());;
           }
    }

    /**
     * Converts unit information in the form of SpaceUnitInfo into space unit objects on the game stage. Part of the loading process.
     * @param spaceUnits
     */
    public void initUnits(List<SpaceUnitInfo> spaceUnits) {
        SpaceUnitManager manager = new SpaceUnitManager(playerManager,solarSystem);
        for (SpaceUnitInfo unit : spaceUnits) {
           addActor(manager.createNewUnit(unit));
        }        
    }
}
