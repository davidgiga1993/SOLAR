package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;

/**
 * 
 * @author Andi
 * 2015-04-19
 */
public class InfoBarDetailsTable extends Table {
    
    private InfoBarManagerSettings settings;
    private final InfoBarColonyDetails colony = new InfoBarColonyDetails();

    public InfoBarDetailsTable displayAstroBodyInformation(AstronomicalBody selectedBody) {
        if(settings.showExtraData()) {
            add(new InfoBarPhysicalCharacteristics(selectedBody)).width(ConfigurationConstants.CELL_WIDTH).top();            
        }
        if(settings.showLifeRating()) {
            add(new InfoBarLifeRatingDetails(selectedBody)).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH).top();            
        }
        if(settings.showColonyDetails()) {
            add(colony.show(selectedBody)).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH).top();           
        }
        add().padLeft(ConfigurationConstants.PADDING).expand().fill();
        return this;
    }

    public InfoBarDetailsTable displaySpaceUnitInformation(SpaceUnit selectedUnit) {
        if(settings.showExtraData()) {
            add(new InfoBarShipDetails(selectedUnit)).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH);            
        }
        add().padLeft(ConfigurationConstants.PADDING).expand().fill();
        return this;
    }

    public InfoBarDetailsTable empty() {
        add().expand().fill();
        return this;
    }

    public void initSettings(InfoBarManagerSettings settings) {
        this.settings = settings;
    }

    public void reloadAstroBodyInformation() {
        colony.reload();
    }
}
