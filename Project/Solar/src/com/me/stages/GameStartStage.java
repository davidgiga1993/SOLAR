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
        
    	this.addListener(new ClickListener()
    	{
            public void clicked(InputEvent event, float x, float y)
            {
            	//Deselektion: Klick in den leeren Raum deselektiert Auswahl
            	if (event.getTarget() instanceof Spaceship == false)
            		removeSelections();
            }  
    	});
                      
        Spaceship ship1 = new Spaceship("Event Horizon");
        ship1.setPosition(100, 100);
    	ship1.addListener(new ClickListener()
    	{
            public void clicked(InputEvent event, float x, float y)
            {
                addSelection(event.getListenerActor());
       	     System.out.println("Selected Actors: " + selectedActors.toString());
            }  
    	});
        Spaceship ship2 = new Spaceship("Nostromo");
        ship2.setPosition(100, -100);
    	ship2.addListener(new ClickListener()
    	{
            public void clicked(InputEvent event, float x, float y)
            {
                addSelection(event.getListenerActor());
       	     System.out.println("Selected Actors: " + selectedActors.toString());
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
		{
			selectActor(actor);
			return;
		}
		// Variant 2: no Shift or Control-button is pressed: discard all other selections
		if (SE.isShiftPressed() == false && SE.isControlPressed() == false )
		{
			removeSelections();
			selectActor(actor);
			return;
		}	
		// Variant 3: object already in list 'added' with Click+SHIFT or Click+CONTROL: remove that object from selection
		if (selectedActors.contains(actor) && ( SE.isShiftPressed() == true || SE.isControlPressed() == true ) )
		{
			removeActor(actor);
			return;
		}
		// Variant 4: new additional object added to list with Click+SHIFT or Click+CONTROL
		else
		{
			selectActor(actor);
		}
	}

	private void selectActor(Actor actor)
	{
		if (selectedActors.contains(actor))
			return;
		selectedActors.add(actor);
		 if (actor instanceof Spaceship)
			 ((Spaceship) actor).select();
	}
	
	private void removeActor(Actor actor)
	{
		 if (actor instanceof Spaceship)
			 ((Spaceship) actor).deselect();
		 selectedActors.remove(actor);
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
