package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.stages.*;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBarManagerSettings;

public class SolarEngine extends Game implements InputProcessor {
    public static final boolean DEBUG = false;

    public static final MessageDispatcher MESSAGE_DISPATCHER = new MessageDispatcher();

    private SolarCamera camera;
    private OrthographicCamera guiCamera;
    private OrthographicCamera backgroundCamera;

    // Stage manager
    private StageManager stageManager;

    private static float Width = 900;
    private static float Height = 600;
    public static final float HALF_WIDTH = Width / 2;
    public static final float HALF_HEIGHT = Height / 2;

    private SpriteBatch mainBatch;

    public enum myKeys {
        NONE, UP, DOWN, LEFT, RIGHT, PLUS, MINUS, SHIFT, CONTROL, ESC
    }

    private myKeys pressedKey = myKeys.NONE;

    @Override
    public void create() {
        // Inputs should be processed by this class
        Gdx.input.setInputProcessor(this);

        // Create camera
        camera = new SolarCamera(Width, Height);
        guiCamera = new OrthographicCamera(Width, Height);
        backgroundCamera = new OrthographicCamera(Width, Height);

        mainBatch = new SpriteBatch();

        // Game's entry point
        stageManager = new StageManager(this);
        stageManager.addStage(new BackgroundStage(this));
        stageManager.startGame();
    }

    // Exiting application
    @Override
    public void dispose() {
        mainBatch.dispose();
        FontCache.cleanUp();
        TextureCache.cleanUp();
    }

    @Override
    public void render() {
        // Reset color
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Apply projection matrix to batch
        mainBatch.setProjectionMatrix(camera.combined);

        handleInput();
        camera.update(Gdx.graphics.getDeltaTime());

        stageManager.draw();
    }

    /**
     * Camera movement controls.
     * Handle zoom and lateral camera movement based on pressed keys.
     */
    private void handleInput() {
        if (pressedKey == myKeys.ESC && stageManager.getStage("GameStartStage") != null) {
            stageManager.removeStages();
            stageManager.addStage(new StartStage(this));
        }
    }

    @Override
    public void resize(int width, int height) {
        Width = width;
        Height = height;
        float ratio = (float) (Gdx.graphics.getHeight()) / (float) (Gdx.graphics.getWidth());
        camera.viewportWidth = width;
        camera.viewportHeight = width * ratio;
        camera.update();
        guiCamera.viewportWidth = width;
        guiCamera.viewportHeight = width * ratio;
        guiCamera.update();

        stageManager.resize(width, (int) (width * ratio));

        super.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Keys.NUM_8:
            case 152:
            case Keys.UP:
                pressedKey = myKeys.UP;
                break;
            case Keys.NUM_2:
            case 146:
            case Keys.DOWN:
                pressedKey = myKeys.DOWN;
                break;
            case Keys.NUM_4:
            case 148:
            case Keys.LEFT:
                pressedKey = myKeys.LEFT;
                break;
            case Keys.NUM_6:
            case 150:
            case Keys.RIGHT:
                pressedKey = myKeys.RIGHT;
                break;
            case 70:
            case Keys.PLUS:
                pressedKey = myKeys.PLUS;
                break;
            case Keys.MINUS:
                pressedKey = myKeys.MINUS;
                break;
            case Keys.SHIFT_LEFT:
            case Keys.SHIFT_RIGHT:
                pressedKey = myKeys.SHIFT;
                break;
            case Keys.CONTROL_LEFT:
            case Keys.CONTROL_RIGHT:
                pressedKey = myKeys.CONTROL;
                break;
            case Keys.ESCAPE:
                pressedKey = myKeys.ESC;
                break;
            default:
                break;
        }
        stageManager.keyDown(keyCode);
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        switch (keyCode) {
            default:
                pressedKey = myKeys.NONE;
                break;
        }
        stageManager.keyUp(keyCode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        stageManager.keyTyped(character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stageManager.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        stageManager.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        stageManager.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        stageManager.mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        stageManager.scrolled(amount);
        return false;
    }

    public static SolarEngine get() {
        return (SolarEngine) Gdx.app.getApplicationListener();
    }

    public void moveSolarCamera(float x, float y) {
        camera.moveTo(x, y);
    }

    public void moveSolarCamera(Actor target) {
        camera.moveTo(target);
    }

    public void zoomSolarCameraTo(float zoomTarget) {
        camera.zoomTo(zoomTarget);
    }

    public void setZoomSolarCameraTo(float newZoom) {
        camera.setZoom(newZoom);
    }

    public float getSolarCameraZoom() {
        return camera.zoom;
    }

    public void translateSolarCamera(Vector2 vec) {
        camera.translate(vec);
    }

    public SolarCamera getCamera() {
        return camera;
    }

    public Matrix4 getCameraCombined() {
        return camera.combined;
    }

    public OrthographicCamera getGUICamera() {
        return guiCamera;
    }

    public OrthographicCamera getBackgroundCamera() {
        return backgroundCamera;
    }

    public void swapCurrentStage(BaseStage newStage) {
        stageManager.swapCurrentStage(newStage);
    }

    public void removeStage(String name) {
        stageManager.removeStage(name);
    }

    public void disposeOfStage(String name) {
        stageManager.removeStage(name).dispose();
    }

    public void addStage(BaseStage newStage) {
        stageManager.addStage(newStage);
    }

    public BaseStage getStage(String tag) {
        return stageManager.getStage(tag);
    }

    public void startGame() {
        stageManager.startGame();
    }

    public InfoBarManagerSettings getSettings() {
        return ((GameHUDStage) getStage("GameHUD")).getSettings();
    }

    public void initSettings(InfoBarManagerSettings settings) {
        ((GameHUDStage) getStage("GameHUD")).initSettings(settings);
    }

    public GameStartStage getGameStage() {
        return (GameStartStage) getStage("GameStartStage");
    }

    public void update(Time deltaT) {
        getGameHUDStage().update();
        getGameStage().updateProduction(deltaT);
    }

    private GameHUDStage getGameHUDStage() {
        return (GameHUDStage) getStage("GameHUD");
    }
}
