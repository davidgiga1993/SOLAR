package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
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
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private LabelStyle boldedStyle = Styles.BOLDLABEL_STYLE;
    
    public InfoBarOverviewTable() {

    }
    
    private void generateMissionInfo() {
        if(selectedActor instanceof AstronomicalBody) {
            add(new Label("Life Rating: ", style)).left();
            add(new Label(((AstronomicalBody)selectedActor).getLifeRating().inPercent(), ((AstronomicalBody)selectedActor).getLifeRating().getLRStyle())).right();               
            row();
        }
        if(((Orbiter)selectedActor).isInOrbit()) {
            add(new Label("In Orbit of: ",style)).left();
            add(new Label(((Orbiter)selectedActor).getNameOfPrimary(),style)).right();
            row();
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

    public void update(SolarActor selectedActor) {
        this.selectedActor = selectedActor; 
        clear();
        add(new BaseNavigationLabel(selectedActor.getName(), selectedActor, boldedStyle)).expand().left();
        row();
        add(new Label("Type: ",style)).left();
        add(new Label(selectedActor.getTypeName(),style)).right();
        row();
        generateMissionInfo();
        row();
        add(new InfoBarNavigationLabel("Show On Map", selectedActor, boldedStyle)).left();
    }

}
