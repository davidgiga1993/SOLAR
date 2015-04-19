package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * 
 * @author Andi
 * 2015-04-19
 */
public class InfoBarDetailsTable extends Table {
    
    private SolarActor selectedActor;

    public InfoBarDetailsTable() {
        
    }

    public void update(SolarActor selectedActor) {
        this.selectedActor = selectedActor;
        clear();
        row();
        if(selectedActor instanceof AstronomicalBody) {
            generateBodyDetails();   
        }
        if(selectedActor instanceof SpaceUnit) {
            ;
        }
    }

    private void generateBodyDetails() {
        add(new Label("Radius: ", Styles.DEFAULTLABEL_STYLE)).expand().left();
        add(new Label(((AstronomicalBody)selectedActor).getRadius().toString(), Styles.DEFAULTLABEL_STYLE)).right();   
        row();
        add(new Label("Mass: ", Styles.DEFAULTLABEL_STYLE)).left();
        add(new Label(((AstronomicalBody)selectedActor).getMass().toString(), Styles.DEFAULTLABEL_STYLE)).right();   
        row();
        add(new Label("Life Rating: ", Styles.DEFAULTLABEL_STYLE)).left();
        add(new Label(((AstronomicalBody)selectedActor).getLifeRating().inPercent(), Styles.DEFAULTLABEL_STYLE)).right();   
        row();
        add(new Label("Surface Gravity: ", Styles.DEFAULTLABEL_STYLE)).left();
        add(new Label(((AstronomicalBody)selectedActor).getSurfaceGravity().toString(), Styles.DEFAULTLABEL_STYLE)).right();
        row();
        add(new Label("Atmosphere: ", Styles.DEFAULTLABEL_STYLE)).left();
        add(new Label(((AstronomicalBody)selectedActor).getLifeRating().atmosphereFuzzy(), Styles.DEFAULTLABEL_STYLE)).right();
    }
}
