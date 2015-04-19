package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 24.02.2015.
 * Substantially revised by Andi on: 2015-04-19
 */
public class InfoBarOverviewTable extends Table {
    
    private SolarActor selectedActor;
    
    public InfoBarOverviewTable() {

    }
    
    private void generateMissionInfo() {
        if(((Orbiter)selectedActor).isInOrbit()) {
            add(new Label("In Orbit of: ",Styles.DEFAULTLABEL_STYLE)).left();
            add(new Label(((Orbiter)selectedActor).getNameOfPrimary(),Styles.DEFAULTLABEL_STYLE)).right();
            row();
            add(new Label("Orbital Period: ",Styles.DEFAULTLABEL_STYLE)).left();
            add(new Label(((Orbiter)selectedActor).getOrbitalPeriod().toString(),Styles.DEFAULTLABEL_STYLE)).right();
            row();
            add(new Label("Semi-major Axis: ",Styles.DEFAULTLABEL_STYLE)).left();
            add(new Label(((Orbiter)selectedActor).getOrbitalRadius().toString(),Styles.DEFAULTLABEL_STYLE)).right();
            return;
        }
        if(selectedActor instanceof SpaceUnit) {
            add(new Label("En route to: ",Styles.DEFAULTLABEL_STYLE)).left();
            add(new Label(((SpaceUnit)selectedActor).getMission(),Styles.DEFAULTLABEL_STYLE)).right();
            row();
            add(new Label("ETA: ",Styles.DEFAULTLABEL_STYLE)).left();
            //TODO: Implementiere ETA-Funktionalität aus der AI-Berechnung raus (Schätzung okay)
            add(new Label("Unknown",Styles.DEFAULTLABEL_STYLE)).right();            
        }
    }

    public void update(SolarActor selectedActor) {
        this.selectedActor = selectedActor; 
        clear();
        add(new BaseNavigationLabel(selectedActor.getName(), "", selectedActor)).expand().left();
        row();
        add(new Label("Type: ",Styles.DEFAULTLABEL_STYLE)).left();
        add(new Label(selectedActor.getTypeName(),Styles.DEFAULTLABEL_STYLE)).right();
        row();
        generateMissionInfo();
        row();
        add(new InfoBarNavigationLabel(">>Show On Map<<", "", selectedActor)).left();
    }

}
