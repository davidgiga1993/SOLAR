package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.commands.ColonizeCommand;
import dhbw.karlsruhe.it.solar.core.commands.OrbitalInsertionCommand;
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

    public InformationActions() {
        super();
        defaults().width(ACTION_CELL_WIDTH).height(ACTION_CELL_HEIGHT);
        
        // Action Command Buttons
        orbitalInsertion = new TextButton("Enter Orbit", Styles.TOOLTIPSKIN);
        colonize = new TextButton("Establish Colony", Styles.TOOLTIPSKIN);
        
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
        
        //add Buttons to Bar
        add(orbitalInsertion).top();
        add(colonize).top();
        orbitalInsertion.setVisible(false);
        colonize.setVisible(false);
        this.selectedActor = null;
    }
    
    private void onOrbitalInsertionClick() {
        //TODO: Very rough implementation. More elegant solution: Approach AI?
        OrbitalInsertionCommand orbitalCommand = new OrbitalInsertionCommand((SpaceUnit)selectedActor);
        orbitalCommand.execute();
    }
    
    private void onColonizeClick() {
        //TODO: Adjust after implementing ColonizeCommand logic
        ColonizeCommand colonizeCommand = new ColonizeCommand((SpaceUnit)selectedActor);
        colonizeCommand.execute();
    }

    public void changedActor(SolarActor actor) {
        this.selectedActor = actor;
        updateCommandButtons();
    }

    private void updateCommandButtons() {
        orbitalInsertion.setVisible(false);
        colonize.setVisible(false);
        if(selectedActor instanceof SpaceUnit) {
            orbitalInsertion.setVisible(true);
        }
        if(selectedActor instanceof Spaceship) {
            colonize.setVisible(true);
        }
    }
}
