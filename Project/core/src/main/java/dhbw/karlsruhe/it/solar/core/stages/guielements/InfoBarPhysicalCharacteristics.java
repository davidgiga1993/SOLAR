package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Star;
import dhbw.karlsruhe.it.solar.core.physics.AtmosphericGas;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarPhysicalCharacteristics extends Table {
    
    private AstronomicalBody selectedActor;
    private final static int MAX_NUMBER_OF_GASES_SHOWN = 3;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    
    public InfoBarPhysicalCharacteristics(AstronomicalBody selectedActor) {
        this.selectedActor = selectedActor;
        generatePhysicalDetails();
    }
    
    private void generatePhysicalDetails() {
        add(new Label("Mean Radius: ", style)).left();
        add(new Label(selectedActor.getRadius().toString(), style)).padLeft(ConfigurationConstants.PADDING).right();   
        row();
        add(new Label("Rotation: ", style)).left();
        rotationInfo();   
        row();
        add(new Label("Mass: ", style)).left();
        add(new Label(selectedActor.getMass().toString(), style)).padLeft(ConfigurationConstants.PADDING).right();   
        row();
        add(new Label("Albedo: ", style)).left();
        add(new Label(selectedActor.getAlbedo().toString(), style)).padLeft(ConfigurationConstants.PADDING).right();   
        row();
        if(selectedActor.hasAtmosphere()) {
            addAtmosphereLabel();
            listAtmosphericComposition();
        }
    }

    private void rotationInfo() {
        if(selectedActor.isTidallyLocked()) {
            add(new Label(selectedActor.getRotationPeriod().toString() + " (synchronous)", style)).padLeft(ConfigurationConstants.PADDING).right();            
            return;
        }
        add(new Label(selectedActor.getRotationPeriod().toString(), style)).padLeft(ConfigurationConstants.PADDING).right();
    }

    private void addAtmosphereLabel() {
        if(selectedActor instanceof Star) {
            add(new Label("Photosphere:", style)).left();
            return;
        }
        if(selectedActor.hasSurface()) {
            add(new Label("Atmo: Pressure: ", style)).left();
            add(new Label(selectedActor.getSurfacePressure().toString(), style)).padLeft(ConfigurationConstants.PADDING).right(); 
            return;
        }
        add(new Label("Atmosphere:", style)).left();
    }

    private void listAtmosphericComposition() {
        int i = 0;
        for (AtmosphericGas gas : selectedActor.getListOfAtmosphericGases()) {
            row();
            add(new Label(InfoBar.TAB + gas.getName(), style)).left();  
            add(new Label(gas.getCompositionPercentage(), style)).right();
            i++;
            if(i>=MAX_NUMBER_OF_GASES_SHOWN) {
                break;
            }
        }
    }
}
