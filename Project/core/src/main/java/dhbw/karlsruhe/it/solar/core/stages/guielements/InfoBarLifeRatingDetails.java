package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.FuzzyInformation;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarLifeRatingDetails extends Table {
    
    private AstronomicalBody selectedActor;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    
    private FuzzyInformation rating;
    private FuzzyInformation gravity;
    private FuzzyInformation temperatures;
    private FuzzyInformation atmosphere;
    private FuzzyInformation hydrosphere;
    private FuzzyInformation biosphere;

    public InfoBarLifeRatingDetails(AstronomicalBody selectedActor) {
        this.selectedActor = selectedActor;
        
        if(selectedActor.isColonizable()) {
            fetchFuzzyInformation();
            generateBodyDetails();      
            return;
        }
        
        add(new Label("No Surface", Styles.BOLDLABEL_STYLE));
    }
    
    private void fetchFuzzyInformation() {
        this.rating = selectedActor.ratingFuzzy();
        this.gravity = selectedActor.gravityFuzzy();
        this.temperatures = selectedActor.temperatureFuzzy();
        this.atmosphere = selectedActor.atmosphereFuzzy();
        this.hydrosphere = selectedActor.hydrosphereFuzzy();
        this.biosphere = selectedActor.biosphereFuzzy();
        
    }

    private void generateBodyDetails() {
        add(new Label("Life Rating: ", style)).left();
        add().expand();
        add(new Label(rating.getFuzzyInfo(),rating.getStyle())).right(); 
        row();
        add(new Label("Gravity: ", style)).left();
        add(new Label(gravity.getPhyicalValue(), style)).padLeft(ConfigurationConstants.PADDING);
        add(new Label(gravity.getFuzzyInfo(), gravity.getStyle())).padLeft(ConfigurationConstants.PADDING).right();
        row();
        add(new Label("Temperature: ", style)).left();
        add(new Label(temperatures.getPhyicalValue(), style)).padLeft(ConfigurationConstants.PADDING);
        add(new Label(temperatures.getFuzzyInfo(), temperatures.getStyle())).padLeft(ConfigurationConstants.PADDING).right();
        row();
        add(new Label("Atmosphere: ", style)).left();
        add(new Label(atmosphere.getPhyicalValue(), style)).padLeft(ConfigurationConstants.PADDING);
        add(new Label(atmosphere.getFuzzyInfo(), atmosphere.getStyle())).padLeft(ConfigurationConstants.PADDING).right();
        row();
        add(new Label("Hydrosphere: ", style)).left();
        add(new Label(hydrosphere.getPhyicalValue(), style)).padLeft(ConfigurationConstants.PADDING);
        add(new Label(hydrosphere.getFuzzyInfo(), hydrosphere.getStyle())).padLeft(ConfigurationConstants.PADDING).right();
        row();
        add(new Label("Biosphere: ", style)).left();
        add(new Label(biosphere.getPhyicalValue(), style)).padLeft(ConfigurationConstants.PADDING);
        add(new Label(biosphere.getFuzzyInfo(), biosphere.getStyle())).padLeft(ConfigurationConstants.PADDING).right();
    }

}
