package com.me.solar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.stages.StageManager;
import com.me.stages.StartStage;
import com.me.UserControls.*;

public class SolarEngine implements ApplicationListener, InputProcessor
{
    public OrthographicCamera camera;
    public TextureCacher Textures;
    public com.me.UserControls.Styles styles;

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

        handleInput();
        stageManager.draw();

    }

    private void handleInput() {
    	if(Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(70)) {
    		camera.zoom += 0.01f;
        }	
    	if(Gdx.input.isKeyPressed(Keys.MINUS) || Gdx.input.isKeyPressed(69)) {
    		camera.zoom -= 0.01f;
        }	
    	if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.NUM_8) || Gdx.input.isKeyPressed(152)) {
    		camera.translate(0,1,0);
        }	
    	if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.NUM_2) || Gdx.input.isKeyPressed(146)) {
    		camera.translate(0,-1,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.NUM_4) || Gdx.input.isKeyPressed(148)) {
    		camera.translate(-1,0,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.NUM_6) || Gdx.input.isKeyPressed(150)) {
    		camera.translate(1,0,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.NUM_7) || Gdx.input.isKeyPressed(151)) {
    		camera.translate(-1,1,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.NUM_9) || Gdx.input.isKeyPressed(153)) {
    		camera.translate(1,1,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.NUM_3) || Gdx.input.isKeyPressed(147)) {
    		camera.translate(1,-1,0);
        }
    	if(Gdx.input.isKeyPressed(Keys.NUM_1) || Gdx.input.isKeyPressed(145)) {
    		camera.translate(-1,-1,0);
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
//        case Keys.UP:       	
//        	camera.translate(0,10.0f,0);
//        	break;
//        case Keys.DOWN:
//        	camera.translate(0,-10.0f,0);
//        	break;
//        case Keys.LEFT:
//        	camera.translate(-10.0f,0,0);
//        	break;
//        case Keys.RIGHT:
//        	camera.translate(10.0f,0,0);
//        	break;
//        case Keys.PLUS:
//           camera.zoom += 0.05f;
//           camera.update();
//           break;
//        case Keys.MINUS:
//            camera.zoom -= 0.05f;
//            camera.update();
//            break;
        case Keys.ESCAPE:
        	stageManager.swapCurrentStage(new StartStage(this));
        }
        System.out.println(keycode);
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
    

    public boolean scrollSystemMap (int vertical, int horizontal)
    {
    	return false;
    }
}
