package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.SolarSystem;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

/**
 * Determines which InfoBar tables are displayed to the player and which stay hidden.
 * Works in combination with the buttons of the InfoBarMenueButtons class.
 * @author Andi
 * created 2015-05-03
 */
public class InfoBarManager extends Table {
    
    private SolarActor selectedActor;
    private final InfoBarManagerSettings settings = new InfoBarManagerSettings();
    private final InfoBarDetailsTable table = new InfoBarDetailsTable(settings);
    private final InfoBarMenueButtons buttons = new InfoBarMenueButtons(this);
    
    
    public InfoBarManager displayContent(SolarActor selectedActor) {
        this.selectedActor = selectedActor;
        clear();
        createInfoBarMenueButtons();
        createInfoBarDetailsTable();
        return this;
    }

    public void createInfoBarDetailsTable() {
        table.clear();
        settings.adjustMaximumNumberOfColumns();
        if(selectedActor instanceof AstronomicalBody && !(selectedActor instanceof SolarSystem)) {
            displayAstroBodyInformation();
            return;
        }
        if(selectedActor instanceof SpaceUnit) {
            displaySpaceUnitInformation();
            return;
        }
        add(table.empty()).padLeft(InformationBar.PADDING).expandX().fillX();
    }

    private void displaySpaceUnitInformation() {
        add(table.displaySpaceUnitInformation((SpaceUnit)selectedActor)).padLeft(InformationBar.PADDING).expandX().fillX();
    }

    private void displayAstroBodyInformation() {
        add(table.displayAstroBodyInformation((AstronomicalBody)selectedActor)).padLeft(InformationBar.PADDING).expandX().fillX();
    }

    public void createInfoBarMenueButtons() {
        buttons.clear();
        if(selectedActor instanceof AstronomicalBody && !(selectedActor instanceof SolarSystem)) {
            add(buttons.displayAstroBodyButtons()).width(InformationBar.MENUE_CELL_WIDTH).left();
            return;
        }
        if(selectedActor instanceof SpaceUnit) {
            add(buttons.displaySpaceUnitButtons()).width(InformationBar.MENUE_CELL_WIDTH).left();
            return;
        }
        add(buttons.empty()).left();
    }
    
    public void onDisplayExtraDataClick() {
        settings.toggleDisplayExtraData();
        reload();
    }

    public void onDisplayLifeRatingClick() {
        settings.toggleDisplayLifeRating();
        reload();
    }

    public void onDisplayColonyDetailsClick() {
        settings.toggleDisplayColonyDetails();
        reload(); 
    }
    
    private void reload() {
        clear();
        createInfoBarMenueButtons();
        createInfoBarDetailsTable();
    }

    public void update() {
        reload();
    }
}
