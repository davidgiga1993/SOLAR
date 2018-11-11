package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.core.colony.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.solar.TextureCache;

/**
 * @author Andi
 * created 2015-05-29
 */
public class ElectricPower extends CapacitiveResource {

    public ElectricPower() {

    }

    @Override
    public void updateConsumption(ResourceDepot consumptionPlace) {
        currentConsumption = (long) consumptionPlace.getElectricPowerConsumption().inWatt();
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCache.GAME_ATLAS.findRegion("electricity");
    }

    @Override
    protected String noCapacityMessage() {
        return "Power-plants off-line!";
    }

    @Override
    protected TextureRegion getAlertIcon() {
        return TextureCache.GAME_ATLAS.findRegion("lack_of_electricity");
    }

    @Override
    protected long getCurrentCapacity(ResourceDepot productionSite) {
        return productionSite.getCurrentPowerCapacity();
    }
}
