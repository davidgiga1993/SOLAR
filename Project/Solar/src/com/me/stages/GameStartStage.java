package com.me.stages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.UserControls.Rectangle;
import com.me.UserControls.Spaceship;
import com.me.UserControls.Star;
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
        
    	gameStartStageListener();
                      
        placeNewShip("Event Horizon", new GridPoint2(100, 100));
        placeNewShip("Nostromo", new GridPoint2(143, 75));
        placeNewShip("Destiny", new GridPoint2(121, 144));
        
        placeNewStar("Sol", new GridPoint2(-300,-300));

        exampleRectangle();
    }

    
	private void exampleRectangle() {
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
	}

	private void placeNewShip(String name, GridPoint2 startlocation) {
		Spaceship newShip = new Spaceship(name);
        newShip.setPosition(startlocation.x, startlocation.y);
        addActor(newShip);
	}
	
	private void placeNewStar(String name, GridPoint2 startlocation) {
		Star newStar = new Star(name);
		//setPosition setzt die Position des linken unteren Randes des Objekts. Daher sind die Koordinaten angepasst, um den Kreismottelpunkt als Koordinate zu erhalten
        newStar.setPosition(startlocation.x-newStar.getWidth()/2, startlocation.y-newStar.getHeight()/2);
        addActor(newStar);
	}

	/**
	 * Wartet auf Mausinputs im Spielfeld und wertet diese aus.
	 */
	private void gameStartStageListener() {
		this.addListener(new ClickListener()
    	{            
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	//Deselektion: linker Mausklick in den leeren Raum deselektiert Auswahl
            	if (event.getTarget() instanceof Spaceship == false && button == 0)
            		discardAllSelections();   
            	
            	//Selektion von Raumschiffen
            	if ((event.getTarget() instanceof Spaceship || event.getTarget() instanceof Star ) && button == 0)
            		addSelection(event.getTarget());   
            	
            	//Ziel vorgeben: rechter mausklick bei selektiertem Raumschiff soll ein Ziel angeben
            	if (button == 1 && ( selectedActors.isEmpty() == false ))
            	{
            		setNewDestination( new GridPoint2((int)x, (int)y));
            		moveSelectedSpaceship();
            	}
          	    
            	//TODO: Remove Diagnoseausgabe wenn nicht mehr benötigt
       	     System.out.println("Selected Actors: " + selectedActors.toString());
      	     return true;
            }
            
    	});
	}
	
	public void moveSelectedSpaceship()
	{
		for (int index = selectedActors.size(); index > 0; index--)
		{
			Actor actor = selectedActors.get(index - 1);
		     if (actor instanceof Spaceship)
		    	 ((Spaceship)actor).moveSpaceship();
		}		
	}
	
	public void setNewDestination( GridPoint2 target)
	{
		for (int index = selectedActors.size(); index > 0; index--)
		{
			Actor actor = selectedActors.get(index - 1);
		     if (actor instanceof Spaceship)
		    	 ((Spaceship) actor).setDestination(target);
		}		
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
			discardAllSelections();
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
	
	public void discardAllSelections()
	{
		for (int index = selectedActors.size(); index > 0; index--)
		{
			Actor actor = selectedActors.get(index - 1);
		    removeActor(actor);
		}
	}

}
