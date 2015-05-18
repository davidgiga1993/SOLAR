package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dhbw.karlsruhe.it.solar.colony.ResourceDepot;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Superclass for resources with capacitive logic which behave differently from normally depleting resources.
 * @author Andi
 * created 2015-05-16
 */
public abstract class CapacitiveResource extends BaseResource implements CapacitiveResourceInterface {
    
    protected long currentConsumption;
    
    @Override
    public String toString() {
        if(0 == value) {
            return noCapacityMessage();
        }
        return consumptionRatio() + " % consumption";  
    }
    
    protected abstract String noCapacityMessage();

    @Override
    public long getCapacity() {
        return value;
    }
    
    @Override
    public long getCurrentConsumption(ResourceDepot consumptionPlace) {
        return currentConsumption;
    }
    
    @Override
    public boolean demandExceedsSupply() {
        return currentConsumption >= value;
    }
    
    @Override
    public void updateCapacity(ResourceDepot productionSite) {
        value = capacityPerUnit() * numberOfUnits(productionSite);
        if(demandExceedsSupply()) {
            productionSite.alertCapacityExceeded(this);
            return;
        }
        productionSite.capacitySufficient(this);
    }
    
    @Override
    public LabelStyle getDisplayStyle() {
        if(currentConsumption < 0.80 * value) {
            return Styles.MENUELABEL_GREEN;
        }
        if(currentConsumption < 0.95 * value) {
            return Styles.MENUELABEL_YELLOW;
        }
        if(currentConsumption <= value) {
            return Styles.MENUELABEL_ORANGE;
        }
        return Styles.MENUELABEL_RED;
    }
    
    @Override
    public Table loadAlertIcon() {
        Table imageTable = new Table();
        Image selectedImage = new Image();
        selectedImage.setDrawable(new TextureRegionDrawable(getAlertIcon()));
        imageTable.add(selectedImage).width(ConfigurationConstants.ICON_SIZE).height(ConfigurationConstants.ICON_SIZE);
        return imageTable;  
    }
    
    protected abstract TextureRegion getAlertIcon();
    
    protected abstract long capacityPerUnit();
    protected abstract long numberOfUnits(ResourceDepot productionSite);
    
    private String consumptionRatio() {
        return String.format("%.00f", (float)currentConsumption / (float)value * 100);
    }  
}
