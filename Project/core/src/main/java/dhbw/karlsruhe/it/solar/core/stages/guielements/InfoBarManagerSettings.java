package dhbw.karlsruhe.it.solar.core.stages.guielements;

import dhbw.karlsruhe.it.solar.core.stages.GameHUDStage;

/**
 * Contains the display settings for the InfoBar as governed by the InfoBarManager.
 * @author Andi
 * created 2015-05-03
 */
public class InfoBarManagerSettings {
    
    private boolean displayExtraData;
    private boolean displayLifeRating;
    private boolean displayColonyDetails;
    private int maximumNumberOfColumns;
    private LastInfoBarButtonToggled lastButton;
    
    public InfoBarManagerSettings() {
        this.displayExtraData = true;
        this.displayLifeRating = true;
        this.displayColonyDetails = true;
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

    public boolean showExtraData() {
        return displayExtraData;
    }

    public boolean showLifeRating() {
        return displayLifeRating;
    }

    public boolean showColonyDetails() {
        return displayColonyDetails;
    }

    public void adjustMaximumNumberOfColumns() {
        maximumNumberOfColumns = calculateNumberOfInfoColumnsWhichCanBeDisplayed();
        
        while(tooManyColumnsActive()) {
            deactivateColumnWithLeastPriority();
        }
    }
    
    private void deactivateColumnWithLeastPriority() {
        if(displayExtraData && LastInfoBarButtonToggled.EXTRA_DATA != lastButton) {
            displayExtraData = !displayExtraData;
            return;
        }
        if(displayLifeRating && LastInfoBarButtonToggled.LIFERATING != lastButton) {
            displayLifeRating = !displayLifeRating;
            return;
        }
        if(displayColonyDetails && LastInfoBarButtonToggled.COLONY_DATA != lastButton) {
            displayColonyDetails = !displayColonyDetails;
            return;
        }
    }

    private boolean tooManyColumnsActive() {
        if(numberOfActiveColumns() > maximumNumberOfColumns) {
            return true;
        }
        return false;
    }

    private int numberOfActiveColumns() {
        int numberOfActiveColumns = 0;
        if(displayExtraData) {
            numberOfActiveColumns++;
        }
        if(displayLifeRating) {
            numberOfActiveColumns++;
        }
        if(displayColonyDetails) {
            numberOfActiveColumns++;
        }
        return numberOfActiveColumns;
    }

    private int calculateNumberOfInfoColumnsWhichCanBeDisplayed() {      
        return calculateCurrentFreeWidth() / (InformationBar.CELL_WIDTH + InformationBar.PADDING);
    }

    private int calculateCurrentFreeWidth() {
        return GameHUDStage.calculateInfoBarMaxWidth() - InformationBar.MINIMUM_WIDTH + InformationBar.CELL_WIDTH + InformationBar.PADDING;
    }
    
    public enum LastInfoBarButtonToggled {
        EXTRA_DATA,
        LIFERATING,
        COLONY_DATA
    }
}
