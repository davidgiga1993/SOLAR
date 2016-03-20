package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

    private static final float CONSUMPTION_APPROACHES_CAPACITY_RATIO = 0.80f;
    private static final float CONSUMPTION_CLOSE_TO_CAPACITY_RATIO = 0.95f;
    private static final float NO_IMPACT_THRESHOLD = 1f;
    private static final float STRONG_IMPACT_THRESHOLD = 0.5f;
    long currentConsumption;
    
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
    public boolean consumptionOverCapacity() {
        return currentConsumption > value;
    }
    
    @Override
    public boolean consumptionCloseToCapacity() {
        return currentConsumption > CONSUMPTION_CLOSE_TO_CAPACITY_RATIO * value;
    }
    
    @Override
    public boolean consumptionApproachesCapacity() {
        return currentConsumption > CONSUMPTION_APPROACHES_CAPACITY_RATIO * value;       
    }
  
    @Override
    public float capacityRatio() {
        return ((float)currentConsumption)/((float)value);
    }
    
    @Override
    public void updateCapacity(ResourceDepot productionSite) {
        value = getCurrentCapacity(productionSite);
        if(consumptionOverCapacity()) {
            productionSite.alertCapacityExceeded(this);
            return;
        }
        productionSite.capacitySufficient(this);
    }
    
    @Override
    public LabelStyle getDisplayStyle() {
        if(consumptionOverCapacity()) {
            return Styles.MENUELABEL_RED;
        }
        if(consumptionCloseToCapacity()) {
            return Styles.MENUELABEL_ORANGE;
        }
        if(consumptionApproachesCapacity()) {
            return Styles.MENUELABEL_YELLOW;
        }
        return Styles.MENUELABEL_GREEN;
    }
    
    @Override
    public Table loadAlertIcon() {
        Table imageTable = new Table();
        Image selectedImage = new Image();
        selectedImage.setDrawable(new TextureRegionDrawable(getAlertIcon()));
        imageTable.add(selectedImage).width(ConfigurationConstants.ICON_SIZE).height(ConfigurationConstants.ICON_SIZE);
        return imageTable;  
    }
    
    @Override    
    public float populationGrowthImpactOfResource() {       
            if(consumptionCloseToCapacity()) {
                return STRONG_IMPACT_THRESHOLD * ( 1 - capacityRatio()) / ( 1 - CONSUMPTION_CLOSE_TO_CAPACITY_RATIO);            
            }
            if(consumptionApproachesCapacity()) {
                return 1 + gradientFormula() * (CONSUMPTION_APPROACHES_CAPACITY_RATIO - capacityRatio());
                        
            }
            return 1;
    }

    private float gradientFormula() {
        return (STRONG_IMPACT_THRESHOLD - NO_IMPACT_THRESHOLD) / (CONSUMPTION_APPROACHES_CAPACITY_RATIO - CONSUMPTION_CLOSE_TO_CAPACITY_RATIO );
    }
    
    protected abstract TextureRegion getAlertIcon();
    protected abstract long getCurrentCapacity(ResourceDepot productionSite);
    
    private String consumptionRatio() {
        return String.format("%.00f", capacityRatio() * 100);
    }  
}
