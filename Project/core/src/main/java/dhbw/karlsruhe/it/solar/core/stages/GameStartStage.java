package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.math.GridPoint2;

import dhbw.karlsruhe.it.solar.core.inputlisteners.GameInputListener;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SelectionRectangle;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarSystem;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

public class GameStartStage extends BaseStage
{
    public Selection selectedActors = new Selection();
    public SelectionRectangle selectionRectangle;
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
        placeNewShip("Nostromo", new GridPoint2(150, 100));
        placeNewShip("Destiny", new GridPoint2(75, 0));

        /*
         * Image background = SE.Service.AddBackgroundImage();
         * addActor(background);
         * background.toBack();
         */
    }

    /**
     * Initial demonstration object.
     * TODO: Remove
     */
    private void addSelectionRectangle()
    {
        selectionRectangle = new SelectionRectangle();
        addActor(selectionRectangle);
    }

    /**
     * Creates the solar system for the game.
     * Method called during startup of a game for creation of a new system map with a range of astronomical objects.
     */
    private void systemCreation()
    {
        // Creates the Solar System for the game
        solarSystem = new SolarSystem(getGameName());
        solarSystem.createSolarSystem();
        addActor(solarSystem);
        addSolarSystemActors(solarSystem);
    }

    /**
     * Adds an astronomomical object and its satellites as new actors to the game.
     * @param body Astronomical Object to be added to the game.
     */
    private void addSolarSystemActors(AstronomicalBody body)
    {
        if (body.getNumberOfSatellites() != 0)
        {
            for (int index = body.getNumberOfSatellites(); index > 0; index--)
            {
                AstronomicalBody object = (AstronomicalBody) body.getSatellites().getChildren().get(0);
                addActor(object);
                addSolarSystemActors(object);
            }
        }
    }

    /**
     * @return Name of the Save Game / System Map currently being played. Currently a stub.
     * TODO: expand appropriately
     */
    private String getGameName()
    {
        String name = "Sol";
        return name;
    }

    /**
     * Adds a new spaceship object to the game.
     * @param name Desired name of the spaceship.
     * @param startlocation Desired location at which the ship is to appear.
     */
    private void placeNewShip(String name, GridPoint2 startlocation)
    {
        Spaceship newShip = new Spaceship(name);
        newShip.setPosition(startlocation.x, startlocation.y);
        addActor(newShip);
    }
    

    /**
     * Waits for mouse input in the game and interprets it accordingly.
     * Handles selection and deselection of objects.
     * Handles movement orders.
     */
    private void gameStartStageListener()
    {
    	this.addListener(new GameInputListener(this));
    }


}
