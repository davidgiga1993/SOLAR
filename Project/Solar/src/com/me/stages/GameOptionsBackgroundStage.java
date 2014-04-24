package com.me.stages;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.me.solar.SolarEngine;

public class GameOptionsBackgroundStage extends BaseStage
{

	 private SelectBox dropdown;
	
    public GameOptionsBackgroundStage(final SolarEngine SE)
    {
    	 super(SE, "GameOptionsBackground");
    	 
    	 //SelectBox dropdown = new SelectBox(new String[] {"item1", "item2"}, null);
         
         
         addActor(dropdown);
     }

}
