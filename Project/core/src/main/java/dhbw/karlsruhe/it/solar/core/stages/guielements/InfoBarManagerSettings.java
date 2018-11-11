package dhbw.karlsruhe.it.solar.core.stages.guielements;

import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.stages.GameHUDStage;

import javax.xml.bind.annotation.XmlElement;

/**
 * Contains the display settings for the InfoBar as governed by the InfoBarManager.
 *
 * @author Andi
 * created 2015-05-03
 */
public class InfoBarManagerSettings {

    @XmlElement(name = "Display_Extra_Setting")
    private boolean displayExtraData = true;
    @XmlElement(name = "Display_Liferating_Setting")
    private boolean displayLifeRating = true;
    @XmlElement(name = "Display_Colonydetails_Setting")
    private boolean displayColonyDetails = true;
    @XmlElement(name = "Display_Colony_Buildings_Setting")
    private boolean displayColonyBuildings = true;
    private int maximumNumberOfColumns;
    private LastInfoBarButtonToggled lastButton;

    public InfoBarManagerSettings() {

    }

    public void toggleDisplayExtraData() {
        displayExtraData = !displayExtraData;
        lastButton = LastInfoBarButtonToggled.EXTRA_DATA;
    }

    public void toggleDisplayLifeRating() {
        displayLifeRating = !displayLifeRating;
        lastButton = LastInfoBarButtonToggled.LIFERATING;
    }

    public void toggleDisplayColonyDetails() {
        displayColonyDetails = !displayColonyDetails;
        lastButton = LastInfoBarButtonToggled.COLONY_DATA;
    }

    public void toggleDisplayColonyBuildings() {
        displayColonyBuildings = !displayColonyBuildings;
        lastButton = LastInfoBarButtonToggled.COLONY_BUILDINGS;
    }

    public boolean showExtraData() {
        return displayExtraData;
    }

    public boolean showLifeRating() {
        return displayLifeRating;
    }

    public boolean showColonyDetails() {
        return displayColonyDetails;
    }

    public boolean showBuildingDetails() {
        return displayColonyBuildings;
    }

    public void adjustMaximumNumberOfColumns() {
        maximumNumberOfColumns = calculateNumberOfInfoColumnsWhichCanBeDisplayed();

        while (tooManyColumnsActive()) {
            deactivateColumnWithLeastPriority();
        }
    }

    private void deactivateColumnWithLeastPriority() {
        if (displayExtraData && LastInfoBarButtonToggled.EXTRA_DATA != lastButton) {
            displayExtraData = false;
            return;
        }
        if (displayLifeRating && LastInfoBarButtonToggled.LIFERATING != lastButton) {
            displayLifeRating = false;
            return;
        }
        if (displayColonyBuildings && LastInfoBarButtonToggled.COLONY_BUILDINGS != lastButton) {
            displayColonyBuildings = false;
            return;
        }
        if (displayColonyDetails && LastInfoBarButtonToggled.COLONY_DATA != lastButton) {
            displayColonyDetails = false;
            return;
        }
    }

    private boolean tooManyColumnsActive() {
        return numberOfActiveColumns() > maximumNumberOfColumns;
    }

    private int numberOfActiveColumns() {
        int numberOfActiveColumns = 0;
        if (displayExtraData) {
            numberOfActiveColumns++;
        }
        if (displayLifeRating) {
            numberOfActiveColumns++;
        }
        if (displayColonyBuildings) {
            numberOfActiveColumns++;
        }
        if (displayColonyDetails) {
            numberOfActiveColumns++;
        }
        return numberOfActiveColumns;
    }

    private int calculateNumberOfInfoColumnsWhichCanBeDisplayed() {
        return calculateCurrentFreeWidth() / (ConfigurationConstants.CELL_WIDTH + ConfigurationConstants.PADDING);
    }

    private int calculateCurrentFreeWidth() {
        return GameHUDStage.calculateInfoBarMaxWidth() - InfoBar.MINIMUM_WIDTH + ConfigurationConstants.CELL_WIDTH + ConfigurationConstants.PADDING;
    }

    public enum LastInfoBarButtonToggled {
        EXTRA_DATA,
        LIFERATING,
        COLONY_DATA,
        COLONY_BUILDINGS
    }
}
