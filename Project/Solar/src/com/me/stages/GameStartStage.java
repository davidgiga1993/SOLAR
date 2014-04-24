package com.me.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.me.UserControls.Rectangle;
import com.me.solar.SolarEngine;

public class GameStartStage extends BaseStage
{
	private Group background;
	private Group gamestage;
	private Group foreground;
	
	private String defaultBackground = "hintergrund01.png";
	
    public GameStartStage(SolarEngine SE)
    {
        super(SE);
        
        InitializeGroups();
        
        addActor(background);
        addActor(gamestage);
        addActor(foreground);
        
        ChangeBackgroundImage(defaultBackground);
        
        
        Rectangle rect = new Rectangle();
        rect.setPosition(0, 0);
        rect.setSize(100, 100);        

        rect.addListener(new InputListener()
        {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                System.out.println("enter");
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                System.out.println("exit");
            }

            // Hier können weitere Events stehen
        });

        // Animationen
        RotateToAction ac = new RotateToAction();
        ac.setRotation(900);
        ac.setDuration(20);
        ac.setInterpolation(Interpolation.exp5);

        rect.addAction(ac);

        // Zum Zeichen / Logik loop hinzufügen
        gamestage.addActor(rect);
    }

	public void ChangeBackgroundImage(String backgroundImage) {
		Texture texture = new Texture(Gdx.files.internal("data/" + backgroundImage));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int height = texture.getHeight();
        int width = texture.getWidth();
        TextureRegion region = new TextureRegion(texture, 0, 0, width, height);          
        
        Image actor = new Image(region);
        actor.setScaling(Scaling.fill);
        actor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.addActor(actor);
	}

	private void InitializeGroups() {
		int x = -Gdx.graphics.getWidth()/2;
		int y = -Gdx.graphics.getHeight()/2;
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		background = new Group();
        background.setBounds(x , y, width, height);
        gamestage = new Group();
        gamestage.setBounds(x , y, width, height);
        foreground = new Group();
        foreground.setBounds(x , y, width, height);
	}
    
    
    
    

}
