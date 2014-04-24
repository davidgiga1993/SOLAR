package com.me.stages;

import Actions.LabelFontScalerAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.me.solar.SolarEngine;

public class GameOptionsBackgroundStage extends BaseStage
{

	 private SelectBox dropdown;
	
    public GameOptionsBackgroundStage(final SolarEngine SE)
    {
    	 super(SE);
    	 
    	 //SelectBox dropdown = new SelectBox(new String[] {"item1", "item2"}, null);
         
         
         addActor(dropdown);
     }

}
