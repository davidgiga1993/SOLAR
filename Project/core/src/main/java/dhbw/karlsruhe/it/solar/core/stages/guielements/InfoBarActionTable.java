package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.commands.AbandonColonyCommand;
import dhbw.karlsruhe.it.solar.core.commands.ColonizeCommand;
import dhbw.karlsruhe.it.solar.core.commands.OrbitalInsertionCommand;
import dhbw.karlsruhe.it.solar.core.commands.SelfDestructCommand;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.space_units.Spaceship;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 24.02.2015.
 * Substantially revised by Andi on: 2015-04-19
 */
public class InfoBarActionTable extends Table {
    
    private final TextButton orbitalInsertion = new TextButton("Enter Orbit", Styles.TOOLTIPSKIN);
    private final TextButton colonize = new TextButton("Establish Colony", Styles.TOOLTIPSKIN);
    private final TextButton selfDestruct = new TextButton("Self Destruct", Styles.TOOLTIPSKIN);
    
    private SolarActor selectedActor; 
    
    public InfoBarActionTable(SolarActor selectedActor) {
        this.selectedActor = selectedActor;
        addButtonListeners(); 
        addButtons();
        determineVisibilityOfButtons();
    }

    private void determineVisibilityOfButtons() {
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

    private void addButtons() {
        add(orbitalInsertion).width(InformationBar.ACTION_BUTTON_WIDTH).height(InformationBar.ACTION_BUTTON_HEIGHT).pad(InformationBar.ACTION_BUTTON_PADDING);
        row();
        add(colonize).width(InformationBar.ACTION_BUTTON_WIDTH).height(InformationBar.ACTION_BUTTON_HEIGHT).pad(InformationBar.ACTION_BUTTON_PADDING);
        row();
        add(selfDestruct).width(InformationBar.ACTION_BUTTON_WIDTH).height(InformationBar.ACTION_BUTTON_HEIGHT).pad(InformationBar.ACTION_BUTTON_PADDING);
        hideAllButtons();
    }
    
    private void addButtonListeners() {
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
    }
    
    public void hideAllButtons() {
        orbitalInsertion.setVisible(false);
        colonize.setVisible(false);
        selfDestruct.setVisible(false);
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

    public void update(SolarActor selectedActor) {
    }

}
