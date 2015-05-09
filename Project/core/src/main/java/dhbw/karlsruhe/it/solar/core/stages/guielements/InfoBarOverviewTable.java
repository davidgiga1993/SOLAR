package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;
import dhbw.karlsruhe.it.solar.core.usercontrols.SystemRoot;

/**
 * Created by Arga on 24.02.2015.
 * Substantially revised by Andi on: 2015-04-19
 */
public class InfoBarOverviewTable extends Table {
    
    private SolarActor selectedActor;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private final TextButton showOnMap = new TextButton("Show On Map", Styles.TOOLTIPSKIN);
    
    public InfoBarOverviewTable(SolarActor selectedActor) {
        this.selectedActor = selectedActor; 
        clear();
        add(new NavBarBaseLabel(selectedActor.getName(), selectedActor, Styles.BOLDLABEL_STYLE)).left();
        row();
        add(new Label("Type: ",style)).left();
        add(new Label(selectedActor.getTypeName(),style)).right();
        row();
        generateMissionInfo();
        row();
        add(showOnMap).align(Align.center).colspan(2).width(InfoBar.ACTION_BUTTON_WIDTH).height(InfoBar.ACTION_BUTTON_HEIGHT).pad(InfoBar.ACTION_BUTTON_PADDING);
        showOnMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onShowOnMapClick();
            }
         });
    }
    
    private void generateMissionInfo() {
        if(((Orbiter)selectedActor).isInOrbit()) {
            add(new Label("In Orbit of: ",style)).left();
            addNavigationLabelForPrimary();
            add(new Label("Orbital Period: ",style)).left();
            add(new Label(((Orbiter)selectedActor).getOrbitalPeriod().toString(),style)).right();
            row();
            add(new Label("Semi-major Axis: ",style)).left();
            add(new Label(((Orbiter)selectedActor).getOrbitalRadius().toString(),style)).right();
            return;
        }
        if(selectedActor instanceof SpaceUnit) {
            add(new Label("En route to: ",style)).left();
            add(new Label(((SpaceUnit)selectedActor).getMission(),style)).right();
            row();
            add(new Label("ETA: ",style)).left();
            //TODO: Implementiere ETA-Funktionalität aus der AI-Berechnung raus (Schätzung okay)
            add(new Label("Unknown",style)).right();            
        }
    }

    private void addNavigationLabelForPrimary() {
        if(((Orbiter)selectedActor).getPrimary() instanceof SystemRoot) {
            add(new Label(((Orbiter)selectedActor).getNameOfPrimary(), style)).right().expand();
            row();
            return;
        }
        add(new NavBarBaseLabel(((Orbiter)selectedActor).getNameOfPrimary(),((Orbiter)selectedActor).getPrimary(), style)).right().expand();
        row();
    }

    private void onShowOnMapClick() {
        GameStartStage.inputListenerMoveCamera(selectedActor);            
    }
}
