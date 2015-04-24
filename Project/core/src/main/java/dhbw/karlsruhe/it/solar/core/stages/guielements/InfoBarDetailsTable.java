package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyString;
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
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    
    private FuzzyString gravity;
    private FuzzyString temperatures;
    private FuzzyString atmosphere;
    private FuzzyString hydrosphere;
    private FuzzyString biosphere;
    
    public InfoBarDetailsTable() {
    }

    public void update(SolarActor selectedActor) {
        this.selectedActor = selectedActor;
        clear();
        row();
        if(selectedActor instanceof AstronomicalBody) {
            fetchFuzzyInformation();
            generateBodyDetails();   
        }
        if(selectedActor instanceof SpaceUnit) {
            ;
        }
    }

    private void fetchFuzzyInformation() {
        this.gravity = ((AstronomicalBody)selectedActor).getLifeRating().gravityFuzzy();
        this.temperatures = ((AstronomicalBody)selectedActor).getLifeRating().temperatureFuzzy();
        this.atmosphere = ((AstronomicalBody)selectedActor).getLifeRating().atmosphereFuzzy();
        this.hydrosphere = ((AstronomicalBody)selectedActor).getLifeRating().hydrosphereFuzzy();
        this.biosphere = ((AstronomicalBody)selectedActor).getLifeRating().biosphereFuzzy();
        
    }

    private void generateBodyDetails() {
        add(new Label("Radius: ", style)).expand().left();
        add(new Label(((AstronomicalBody)selectedActor).getRadius().toString(), style)).right();   
        row();
        add(new Label("Mass: ", style)).left();
        add(new Label(((AstronomicalBody)selectedActor).getMass().toString(), style)).right();   
        row();
        add(new Label("Surface Gravity: ", style)).left();
        add(new Label(((AstronomicalBody)selectedActor).getSurfaceGravity().toString(), style)).expand();
        add(new Label(gravity.getInfo(), gravity.getStyle())).right();
        row();
        add(new Label("Temperatures: ", style)).left();
        add(new Label(temperatures.getInfo(), temperatures.getStyle())).right();
        row();
        add(new Label("Atmosphere: ", style)).left();
        add(new Label(atmosphere.getInfo(), atmosphere.getStyle())).right();
        row();
        add(new Label("Hydrosphere: ", style)).left();
        add(new Label(hydrosphere.getInfo(), hydrosphere.getStyle())).right();
        row();
        add(new Label("Biosphere: ", style)).left();
        add(new Label(biosphere.getInfo(), biosphere.getStyle())).right();
    }
}
