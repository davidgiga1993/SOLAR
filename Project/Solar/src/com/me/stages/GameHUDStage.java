package com.me.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.me.solar.SolarEngine;

public class GameHUDStage extends HUDStage{
	

    private Table navigationBar;

	public GameHUDStage(final SolarEngine SE) {
		super(SE, "GameHUD");
		

        BitmapFont font = new BitmapFont();
        
        
		navigationBar = new Table();
        navigationBar.setSize(30, Gdx.graphics.getHeight());
        navigationBar.setPosition(-SolarEngine.WidthHalf, SolarEngine.HeightHalf - navigationBar.getHeight());
        navigationBar.align(Align.left);
        navigationBar.setColor(Color.CYAN);
        navigationBar.add(new Label("Results", new LabelStyle(font, Color.WHITE))).left().top().expandX();
        navigationBar.row();
        navigationBar.add(new Label("Option1", new LabelStyle(font, Color.WHITE)));
        navigationBar.row();
        navigationBar.add(new Label("Option2", new LabelStyle(font, Color.WHITE)));
        
        addActor(navigationBar);
	}
	
	

}
