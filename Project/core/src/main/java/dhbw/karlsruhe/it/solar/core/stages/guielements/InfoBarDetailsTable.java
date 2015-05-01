package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

/**
 * 
 * @author Andi
 * 2015-04-19
 */
public class InfoBarDetailsTable extends Table {
    
    public InfoBarDetailsTable(SolarActor selectedActor) {
        clear();
        add(new InfoBarOverviewTable(selectedActor)).width(InformationBar.CELL_SIZE);
        
        if(selectedActor instanceof AstronomicalBody && !(selectedActor instanceof SolarSystem)) {
            add(new InfoBarPhysicalCharacteristics((AstronomicalBody)selectedActor)).padLeft(InformationBar.PADDING).width(InformationBar.CELL_SIZE);
            //TODO: Remove debug-expansion of liferating bar
            add(new InfoBarLifeRatingDetails((AstronomicalBody)selectedActor)).padLeft(InformationBar.PADDING).width(1.6f*InformationBar.CELL_SIZE);
            add().padLeft(InformationBar.PADDING).expand().fill();
        }
        if(selectedActor instanceof SpaceUnit) {
            add().padLeft(InformationBar.PADDING).expand().fill();
        }     
    }
}
