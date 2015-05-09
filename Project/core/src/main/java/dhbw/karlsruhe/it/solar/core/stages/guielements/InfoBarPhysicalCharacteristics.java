package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Star;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarPhysicalCharacteristics extends Table {
    
    private AstronomicalBody selectedActor;
    private final static int MAX_NUMBER_OF_GASES_SHOWN = 3;
    
    public InfoBarPhysicalCharacteristics(AstronomicalBody selectedActor) {
        this.selectedActor = selectedActor;
        generatePhysicalDetails();
    }
    
    private void generatePhysicalDetails() {
        add(new Label("Mean Radius: ", Styles.MENUELABEL_STYLE)).expand().left();
        add(new Label(selectedActor.getRadius().toString(), Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right();   
        row();
        add(new Label("Rotation: ", Styles.MENUELABEL_STYLE)).left();
        rotationInfo();   
        row();
        add(new Label("Mass: ", Styles.MENUELABEL_STYLE)).left();
        add(new Label(selectedActor.getMass().toString(), Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right();   
        row();
        add(new Label("Albedo: ", Styles.MENUELABEL_STYLE)).left();
        add(new Label(selectedActor.getAlbedo().toString(), Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right();   
        row();
        if(selectedActor.hasAtmosphere()) {
            addAtmosphereLabel();
            listAtmosphericComposition();
        }
    }

    private void rotationInfo() {
        if(selectedActor.isTidallyLocked()) {
            add(new Label(selectedActor.getRotationPeriod().toString() + " (synchronous)", Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right();            
            return;
        }
        add(new Label(selectedActor.getRotationPeriod().toString(), Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right();
    }

    private void addAtmosphereLabel() {
        if(selectedActor instanceof Star) {
            add(new Label("Photosphere:", Styles.MENUELABEL_STYLE)).left();
            return;
        }
        if(selectedActor.hasSurface()) {
            add(new Label("Atmo: Surface Pressure: ", Styles.MENUELABEL_STYLE)).left();
            add(new Label(selectedActor.getSurfacePressure().toString(), Styles.MENUELABEL_STYLE)).padLeft(InfoBar.PADDING).right(); 
            return;
        }
        add(new Label("Atmosphere:", Styles.MENUELABEL_STYLE)).left();
    }

    private void listAtmosphericComposition() {
        int i = 0;
        for (AtmosphericGas gas : selectedActor.getListOfAtmosphericGases()) {
            row();
            add(new Label(InfoBar.TAB + gas.getName(), Styles.MENUELABEL_STYLE)).left();  
            add(new Label(gas.getCompositionPercentage(), Styles.MENUELABEL_STYLE)).right();
            i++;
            if(i>=MAX_NUMBER_OF_GASES_SHOWN) {
                break;
            }
        }
    }
}
