package com.me.solar;

import stages.StageManager;
import UserControls.Styles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SolarEngine implements ApplicationListener, InputProcessor
{
    public OrthographicCamera camera;
    public TextureCacher Textures;
    public Styles styles;

    // Stage manager
    public StageManager stageManager;

    public static final float Width = 900;
    public static final float Height = 600;
    public static final float WidthHalf = Width / 2;
    public static final float HeightHalf = Height / 2;

    private SpriteBatch mainBatch;



    @Override
    public void create()
    {
        // Eingaben durch diese Klasse verarbeiten
        Gdx.input.setInputProcessor(this);

        // Kamera erstellen
        camera = new OrthographicCamera(Width, Height);

        mainBatch = new SpriteBatch();

        Textures = new TextureCacher();
        Textures.LoadTextures();
        
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
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // Projektionsmatrix auf Batch anwenden
        mainBatch.setProjectionMatrix(camera.combined);

        stageManager.draw();

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
        case Keys.DOWN:
            camera.zoom += 0.05f;
            camera.update();
            break;
        case Keys.UP:
            camera.zoom -= 0.05f;
            camera.update();
            break;
        }
        stageManager.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
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
}
