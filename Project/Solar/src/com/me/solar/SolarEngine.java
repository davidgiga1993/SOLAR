package com.me.solar;

import stages.StageManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SolarEngine implements ApplicationListener, InputProcessor
{
    public OrthographicCamera camera;
    public TextureLoader Textures;

    public static final float Width = 900;
    public static final float Height = 600;
    public static final float WidthHalf = Width / 2;
    public static final float HeightHalf = Height / 2;

    private SpriteBatch mainBatch;

    private Texture texture;
    private Sprite sprite;

    // Stage manager
    private StageManager mStage;

    @Override
    public void create()
    {
        // Eingaben durch diese Klasse verarbeiten
        Gdx.input.setInputProcessor(this);

        // Kamera erstellen
        camera = new OrthographicCamera(Width, Height);

        mainBatch = new SpriteBatch();

        texture = TextureLoader.Load("data/libgdx.png");
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        Textures = new TextureLoader();
        Textures.LoadTextures();

        TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

        sprite = new Sprite(region);
        sprite.setSize(Width, Height);
        sprite.setPosition(-WidthHalf, -HeightHalf);

        // Einstiegspunkt ins Spiel
        mStage = new StageManager(this);
        mStage.StartGame();

    }

    // Anwendung wird beendet
    @Override
    public void dispose()
    {
        mainBatch.dispose();
        texture.dispose();
    }

    @Override
    public void render()
    {
        // Reset farbe
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // Projektionsmatrix auf Batch anwenden
        mainBatch.setProjectionMatrix(camera.combined);

        mStage.draw();

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
        mStage.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        mStage.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        mStage.keyTyped(character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        mStage.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        mStage.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        mStage.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        mStage.mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        mStage.scrolled(amount);
        return false;
    }
}
