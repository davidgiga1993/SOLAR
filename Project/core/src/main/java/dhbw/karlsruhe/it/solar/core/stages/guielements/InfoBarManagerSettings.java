package dhbw.karlsruhe.it.solar.core.stages.guielements;

/**
 * Contains the display settings for the InfoBar as governed by the InfoBarManager.
 * @author Andi
 * created 2015-05-03
 */
public class InfoBarManagerSettings {
    
    private boolean displayExtraData;
    private boolean displayLifeRating;
    private boolean displayColonyDetails;
    
    public InfoBarManagerSettings() {
        this.displayExtraData = true;
        this.displayLifeRating = true;
        this.displayColonyDetails = true;
    }
    
    public void toggleDisplayPhysicalData() {
        displayExtraData = !displayExtraData;
    }

    public void toggleDisplayLifeRating() {
        displayLifeRating = !displayLifeRating;
    }

    public void toggleDisplayColonyDetails() {
        displayColonyDetails = !displayColonyDetails;
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
}
