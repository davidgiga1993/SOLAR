package com.me.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.solar.SolarEngine;

public class GameHUDStage extends HUDStage{
	

    private Table navigationBar;
    private Table RessourceOverview;

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
        
        RessourceOverview = new Table();
        //RessourceOverview.setBackground();
        
        RessourceOverview.setSize(Gdx.graphics.getWidth(), 100);
        RessourceOverview.setPosition(-SolarEngine.WidthHalf, SolarEngine.HeightHalf - RessourceOverview.getHeight());
        RessourceOverview.align(Align.right);
        
        //Label CreditsLabel = new Label("Credits", new LabelStyle(font, new Color(125, 125, 125, 255)));
        RessourceOverview.add(new Label("", new LabelStyle(font, Color.WHITE))).uniform();
        RessourceOverview.add(new Label("Value", new LabelStyle(font, Color.WHITE))).uniform();
        RessourceOverview.add(new Label("Raise Rate", new LabelStyle(font, Color.WHITE))).uniform();
        RessourceOverview.row();
        RessourceOverview.add(new Label("Credits", new LabelStyle(font, Color.WHITE))).uniform();
        RessourceOverview.add(new Label(String.valueOf(SE.Service.credits.getValue()), new LabelStyle(font, Color.WHITE)));
        RessourceOverview.add(new Label(String.valueOf(SE.Service.credits.getRaiseRate()), new LabelStyle(font, Color.WHITE)));
        
        //Label CreditsValue = new Label(String.valueOf(SE.Service.credits.getValue()), new LabelStyle(font, new Color(125, 125, 125, 255)));
        //Label CreditsRaiseRate = new Label(String.valueOf(SE.Service.credits.getRaiseRate()), new LabelStyle(font, new Color(125, 125, 125, 255)));
        
        //RessourceOverview.addActor(CreditsLabel);
        //RessourceOverview.addActor(CreditsValue);
        //RessourceOverview.addActor(CreditsRaiseRate);     
        
        addActor(navigationBar);
        addActor(RessourceOverview);
	}
	
	

}
