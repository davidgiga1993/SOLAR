package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.commands.OrbitalInsertionCommand;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarSystem;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationActions extends Table {

    private static final int ACTION_CELL_WIDTH = 128;
    private static final int ACTION_CELL_HEIGHT = 32;
    private SolarActor selectedActor;
    private TextButton orbitalInsertion;

    public InformationActions() {
        super();
        defaults().width(ACTION_CELL_WIDTH).height(ACTION_CELL_HEIGHT);
        
        // Action Command Buttons
        orbitalInsertion = new TextButton("Enter Orbit", SolarEngine.get().styles.tooltipSkin);
        
        // Button Listeners
        orbitalInsertion.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                onOrbitalInsertionClick();
           }
        }); 
        this.selectedActor = null;
        add(orbitalInsertion).top();
    }
    
    private void onOrbitalInsertionClick() {
		//TODO: Very rough implementation. More elegant solution: Approach AI?
    	OrbitalInsertionCommand orbitalInsertion = new OrbitalInsertionCommand((SpaceUnit)selectedActor);
    	orbitalInsertion.execute();
    }

	public void changedActor(SolarActor actor) {
		this.selectedActor = actor;
		updateCommandButtons();
	}

	private void updateCommandButtons() {
		if(selectedActor instanceof SpaceUnit) {
			orbitalInsertion.setVisible(true);
			return;
		}
		orbitalInsertion.setVisible(false);
	}
}
