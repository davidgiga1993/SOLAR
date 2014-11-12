package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dhbw.karlsruhe.it.solar.core.solar.logic.GameLogicService;
import dhbw.karlsruhe.it.solar.core.stages.StageManager;
import dhbw.karlsruhe.it.solar.core.stages.StartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;


public class SolarEngine implements ApplicationListener, InputProcessor
{
    public GameLogicService Service = new GameLogicService();
    
    public OrthographicCamera camera;
    public OrthographicCamera HUDcamera;
    public OrthographicCamera Backgroundcamera;

    public TextureCacher Textures;
    public Styles styles;

    // Stage manager
    public StageManager stageManager;

    public static final float Width = 900;
    public static final float Height = 600;
    public static final float WidthHalf = Width / 2;
    public static final float HeightHalf = Height / 2;

    private SpriteBatch mainBatch;

    public enum myKeys
    {
        NONE, UP, DOWN, LEFT, RIGHT, PLUS, MINUS, SHIFT, CONTROL, ESC
    };

    private myKeys pressedKey = myKeys.NONE;

    @Override
    public void create()
    {
        // Eingaben durch diese Klasse verarbeiten
        Gdx.input.setInputProcessor(this);

        // Kamera erstellen
        camera = new OrthographicCamera(Width, Height);
        HUDcamera = new OrthographicCamera(Width, Height);
        Backgroundcamera = new OrthographicCamera(Width, Height);

        mainBatch = new SpriteBatch();

        Textures = new TextureCacher();
        Textures.LoadTextures();
        // Texture.setEnforcePotImages(false);

        styles = new Styles(Textures);

        // Einstiegspunkt ins Spiel
        stageManager = new StageManager(this);
        stageManager.StartGame();
    }

    // Anwendung wird beendet
    @Override
    public void dispose()
    {
        mainBatch.dispose();
    }

    @Override
    public void render()
    {
        // Reset farbe
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Projektionsmatrix auf Batch anwenden
        mainBatch.setProjectionMatrix(camera.combined);

        handleInput();
        camera.update();
        stageManager.draw();

    }

    /**
     * Camera movement controls.
     * Handle zoom and lateral camera movement based on pressed keys.
     */
    private void handleInput()
    {
        if (pressedKey == myKeys.PLUS)
        {
            camera.zoom -= 0.10f;
        }
        if (pressedKey == myKeys.MINUS)
        {
            camera.zoom += 0.10f;
        }
        if (pressedKey == myKeys.UP)
        {
            camera.translate(0, 10, 0);
        }
        if (pressedKey == myKeys.DOWN)
        {
            camera.translate(0, -10, 0);
        }
        if (pressedKey == myKeys.LEFT)
        {
            camera.translate(-10, 0, 0);
        }
        if (pressedKey == myKeys.RIGHT)
        {
            camera.translate(10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.NUM_7) || Gdx.input.isKeyPressed(151))
        {
            camera.translate(-10, 10, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.NUM_9) || Gdx.input.isKeyPressed(153))
        {
            camera.translate(10, 10, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.NUM_3) || Gdx.input.isKeyPressed(147))
        {
            camera.translate(10, -10, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.NUM_1) || Gdx.input.isKeyPressed(145))
        {
            camera.translate(-10, -10, 0);
        }
        if (pressedKey == myKeys.ESC)
        {
            if (stageManager.getStage("GameStartStage") != null)
            {
                stageManager.removeStages();
                stageManager.addStage(new StartStage(this));
            }
        }
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public boolean keyDown(int keycode)
    {        
        switch (keycode)
        {
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
        }
        stageManager.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
        case Keys.NUM_8:
        case 152:
        case Keys.UP:
        case Keys.NUM_2:
        case 146:
        case Keys.DOWN:
        case Keys.NUM_4:
        case 148:
        case Keys.LEFT:
        case Keys.NUM_6:
        case 150:
        case Keys.RIGHT:
        case 70:
        case Keys.PLUS:
        case Keys.MINUS:
        case Keys.SHIFT_LEFT:
        case Keys.SHIFT_RIGHT:
        case Keys.CONTROL_LEFT:
        case Keys.CONTROL_RIGHT:
        case Keys.ESCAPE:
            pressedKey = myKeys.NONE;
            break;
        }
        stageManager.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        stageManager.keyTyped(character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        stageManager.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        stageManager.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        stageManager.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        stageManager.mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        stageManager.scrolled(amount);
        return false;
    }

    public boolean scrollSystemMap(int vertical, int horizontal)
    {
        return false;
    }

    public boolean isShiftPressed()
    {
        if (pressedKey == myKeys.SHIFT)
            return true;
        return false;
    }

    public boolean isControlPressed()
    {
        if (pressedKey == myKeys.CONTROL)
            return true;
        return false;
    }
   
}