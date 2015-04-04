package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.commands.AbandonColonyCommand;
import dhbw.karlsruhe.it.solar.core.commands.ColonizeCommand;
import dhbw.karlsruhe.it.solar.core.commands.OrbitalInsertionCommand;
import dhbw.karlsruhe.it.solar.core.commands.SelfDestructCommand;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationActions extends Table {

    private static final int ACTION_CELL_WIDTH = 128;
    private static final int ACTION_CELL_HEIGHT = 32;
    private SolarActor selectedActor;
    private TextButton orbitalInsertion;
    private TextButton colonize;
    private TextButton selfDestruct;

    public InformationActions() {
        super();
        defaults().width(ACTION_CELL_WIDTH).height(ACTION_CELL_HEIGHT);
        
        // Action Command Buttons
        orbitalInsertion = new TextButton("Enter Orbit", Styles.TOOLTIPSKIN);
        colonize = new TextButton("Establish Colony", Styles.TOOLTIPSKIN);
        selfDestruct = new TextButton("Self Destruct", Styles.TOOLTIPSKIN);
        
        // Button Listeners
        orbitalInsertion.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                onOrbitalInsertionClick();
           }
        });        
        colonize.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onColonizeClick();
            }
         }); 
        selfDestruct.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onSelfDestructClick();
            }
         }); 
        
        //add Buttons to Bar
        add(orbitalInsertion).top();
        add(colonize).top();
        add(selfDestruct).bottom();
        hideAllButtons();
        this.selectedActor = null;
    }
    
    private void onOrbitalInsertionClick() {
        //TODO: Very rough implementation. More elegant solution: Approach AI?
        OrbitalInsertionCommand orbitalCommand = new OrbitalInsertionCommand((SpaceUnit)selectedActor);
        orbitalCommand.execute();
    }
    
    private void onColonizeClick() {
        ColonizeCommand colonizeCommand = new ColonizeCommand((SpaceUnit)selectedActor);
        colonizeCommand.execute();
    }
    
    private void onSelfDestructClick() {
        if (selectedActor instanceof SpaceUnit) {
            SelfDestructCommand selfDestruct = new SelfDestructCommand((SpaceUnit)selectedActor);
            selfDestruct.execute(); 
        }
        if (selectedActor instanceof AstronomicalBody && ((AstronomicalBody) selectedActor).isColonized()) {
            AbandonColonyCommand selfDestruct = new AbandonColonyCommand((AstronomicalBody)selectedActor);
            selfDestruct.execute();
        }       
    }

    public void changedActor(SolarActor actor) {
        this.selectedActor = actor;
        updateCommandButtons();
    }

    private void updateCommandButtons() {
        hideAllButtons();
        if(selectedActor instanceof SpaceUnit) {
            orbitalInsertion.setVisible(true);
            selfDestruct.setText("Self Destruct");
            selfDestruct.setVisible(true);
        }
        if(selectedActor instanceof Spaceship) {
            colonize.setVisible(true);
        }
        if(selectedActor instanceof AstronomicalBody && ((AstronomicalBody)selectedActor).isColonized()) {
            selfDestruct.setText("Abandon Colony");
            selfDestruct.setVisible(true);
        }
    }

    private void hideAllButtons() {
        orbitalInsertion.setVisible(false);
        colonize.setVisible(false);
        selfDestruct.setVisible(false);
    }
}
