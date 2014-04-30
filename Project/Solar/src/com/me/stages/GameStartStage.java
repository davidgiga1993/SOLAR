package com.me.stages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.UserControls.Rectangle;
import com.me.UserControls.Spaceship;
import com.me.solar.SolarEngine;

public class GameStartStage extends BaseStage
{	
	protected List<Actor> selectedActors = new ArrayList<Actor>();
	
    public GameStartStage(SolarEngine SE)
    {
        super(SE, "GameStartStage");
        
        SE.stageManager.insertStageToBack(new BackgroundStage(SE));
        SE.stageManager.addStage(new HUDStage(SE, "HUD"));
        SE.stageManager.addStage(new GameHUDStage(SE));
                      
        Spaceship ship1 = new Spaceship("Event Horizon");
        ship1.setPosition(100, 100);
    	ship1.addListener(new ClickListener()
    	{
            public void clicked(InputEvent event, float x, float y)
            {
                addSelection(event.getListenerActor());
            }  
    	});
        Spaceship ship2 = new Spaceship("Nostromo");
        ship2.setPosition(100, -100);
    	ship2.addListener(new ClickListener()
    	{
            public void clicked(InputEvent event, float x, float y)
            {
                addSelection(event.getListenerActor());
            }  
    	});
       

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
      
        addActor(rect);
        addActor(ship1);
        addActor(ship2);
    }
    
	public void addSelection(Actor actor)
	{
		// variant 1: no other object are in selectedActors-List: simple left click selects object
		if (selectedActors.isEmpty())
			selectActor(actor);
		// Variant 2: no Shift or Control-button is pressed: discard all other selections
		if (SE.isShiftPressed() == false && SE.isControlPressed() == false )
		{
			removeSelections();
			selectActor(actor);
		}		
		// Variant 3: additional object added to list with Click+SHIFT or Click+CONTROL
		else
		{
			selectActor(actor);
		}
     System.out.println("Selected Actors: " + selectedActors.toString());
	}

	private void selectActor(Actor actor)
	{
		if (selectedActors.contains(actor))
			return;
		selectedActors.add(actor);
		 if (actor instanceof Spaceship)
			 ((Spaceship) actor).select();
	}
	
	public void removeSelections()
	{
		for (int index = selectedActors.size(); index > 0; index--)
		{
			Actor actor = selectedActors.get(index - 1);
		     if (actor instanceof Spaceship)
		    	 ((Spaceship) actor).deselect();
			selectedActors.remove(index - 1);
		}
	}
}
