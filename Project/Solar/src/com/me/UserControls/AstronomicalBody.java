package com.me.UserControls;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class AstronomicalBody extends SolarActor {
	
	protected Group satellites;
	
    public Group getSatellites()
    {
    	return satellites;
    }
    
    public int getNumberOfSatellites()
    {
    	if (satellites == null )
    		return 0;
    	else
    		return satellites.getChildren().size;
    }

}
