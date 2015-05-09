package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

/**
 * 
 * @author Andi
 * 2015-04-19
 */
public class InfoBarDetailsTable extends Table {
    
    private InfoBarManagerSettings settings;

    public InfoBarDetailsTable displayAstroBodyInformation(AstronomicalBody selectedBody) {
        if(settings.showExtraData()) {
            add(new InfoBarPhysicalCharacteristics(selectedBody)).width(InfoBar.CELL_WIDTH).top();            
        }
        if(settings.showLifeRating()) {
            add(new InfoBarLifeRatingDetails(selectedBody)).padLeft(InfoBar.PADDING).width(InfoBar.CELL_WIDTH).top();            
        }
        if(settings.showColonyDetails()) {
            add(new InfoBarColonyDetails((selectedBody).getColony())).padLeft(InfoBar.PADDING).width(InfoBar.CELL_WIDTH).top();           
        }
        add().padLeft(InfoBar.PADDING).expand().fill();
        return this;
    }

    public InfoBarDetailsTable displaySpaceUnitInformation(SpaceUnit selectedUnit) {
        if(settings.showExtraData()) {
            add(new InfoBarShipDetails(selectedUnit)).padLeft(InfoBar.PADDING).width(InfoBar.CELL_WIDTH);            
        }
        add().padLeft(InfoBar.PADDING).expand().fill();
        return this;
    }

    public InfoBarDetailsTable empty() {
        add().expand().fill();
        return this;
    }

    public void initSettings(InfoBarManagerSettings settings) {
        this.settings = settings;
    }
}
