package com.me.stages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.UserControls.Asteroid;
import com.me.UserControls.SolarSystem;
import com.me.UserControls.Moon;
import com.me.UserControls.SelectionRectangle;
import com.me.UserControls.SolarActor;
import com.me.UserControls.Spaceship;
import com.me.UserControls.Star;
import com.me.UserControls.Planet;
import com.me.solar.SolarEngine;

public class GameStartStage extends BaseStage
{
    protected List<Actor> selectedActors = new ArrayList<Actor>();
	private SelectionRectangle SelRec;
	private SolarSystem solarSystem;

    public GameStartStage(SolarEngine SE)
    {
        super(SE, "GameStartStage");
        SE.Service.StartGame();
        
        SE.stageManager.insertStageToBack(new BackgroundStage(SE));
        SE.stageManager.addStage(new HUDStage(SE, "HUD"));
        SE.stageManager.addStage(new GameHUDStage(SE));

        gameStartStageListener();     
        addSelectionRectangle(); 
    	systemCreation();
    	    	
        placeNewShip("Event Horizon", new GridPoint2(0, 120));
//        placeNewShip("Nostromo", new GridPoint2(150, 100));
//        placeNewShip("Destiny", new GridPoint2(75, 0));

        
        /*Image background = SE.Service.AddBackgroundImage();
        addActor(background);
        background.toBack();*/
    }

	private void addSelectionRectangle() {
		SelRec = new SelectionRectangle();
    	addActor(SelRec);
	}

	private void systemCreation() {
		//Creates the Solar System for the game
    	solarSystem = new SolarSystem(getGameName());
    	solarSystem.createSolarSystem(); 	        
    	addSolarSystemActors();
	}

	private void addSolarSystemActors() {
		for (int index = solarSystem.getNumberOfMainBodies(); index > 0; index-- )
    	{
    		addActor(solarSystem.getSolarSystem().getChildren().get(0));
    	}		
	}

	private String getGameName() {
		String name = "Sol";
		return name;
	}

    private void placeNewShip(String name, GridPoint2 startlocation)
    {
        Spaceship newShip = new Spaceship(name);
        newShip.setPosition(startlocation.x, startlocation.y);
        addActor(newShip);
    }



    /**
     * Wartet auf Mausinputs im Spielfeld und wertet diese aus.
     */
    private void gameStartStageListener()
    {
		this.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	//Selection Box Functionality
            	SelRec.resetSelRec(x,y);
            	
            	//Deselektion: linker Mausklick in den leeren Raum deselektiert Auswahl wenn Shift/Control nicht gedrückt
            	if (event.getTarget() instanceof SolarActor == false && button == 0 && !SE.isControlPressed() && !SE.isShiftPressed())
                    discardAllSelections();

                // Selektion von Raumschiffen
                if (event.getTarget() instanceof SolarActor && button == 0)
                    addSelection(event.getTarget());

                // Ziel vorgeben: rechter mausklick bei selektiertem Raumschiff soll ein Ziel angeben
                if (button == 1 && (selectedActors.isEmpty() == false))
                {
                    setNewDestination(new GridPoint2((int) x, (int) y));
                    moveSelectedSpaceship();
                }
          	    return true;
            }
                        
            @Override
            public void touchUp(InputEvent event, float x, float y,
            		int pointer, int button) {
            	SelRec.hide();
             	getSelectionBoxSelectedActors();
            }           
            
			@Override
            public void touchDragged(InputEvent event, float x, float y,
            		int pointer) {
          	     SelRec.updatePositionAndSize(x,y);
            }
        });
    }

    private void getSelectionBoxSelectedActors()
    {
//    	//Geht alle Actors in der Stage durch und überprüft, ob sie in der Box liegen. Falls ja, werden sie zur Selektion geaddet
    	for ( int index = 0; index < getActors().size; index++ )
    	{
    		float x = getActors().get(index).getX();
    		float y = getActors().get(index).getY();
    		if ( isXWithinSelRec(x))
    			if(isYWithinSelRec(y))
    				if(getActors().get(index) instanceof SolarActor && !selectedActors.contains(getActors().get(index)))
          				selectActor(getActors().get(index));
    	}
	}

	private boolean isYWithinSelRec(float y) {
		return y > SelRec.getY() &&  y < (SelRec.getY() + SelRec.getHeight());
	}

	private boolean isXWithinSelRec(float x) {
		return x > SelRec.getX() &&  x < (SelRec.getX() + SelRec.getWidth());
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
		 
     	//TODO: Remove Diagnoseausgabe wenn nicht mehr benötigt
	     System.out.println("Selected Actors: " + selectedActors.toString());
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
