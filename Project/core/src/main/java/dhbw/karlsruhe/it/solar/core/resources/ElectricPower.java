package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.colony.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * 
 * @author Andi
 * created 2015-05-29
 */
public class ElectricPower extends CapacitiveResource {

    @Override
    public void updateConsumption(ResourceDepot consumptionPlace) {
        currentConsumption = (long)consumptionPlace.getElectricPowerConsumption().inWatt();
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("electricity");
    }

    @Override
    protected String noCapacityMessage() {
        return "Power plants off-line!";
    }

    @Override
    protected TextureRegion getAlertIcon() {
        return TextureCacher.GAMEATLAS.findRegion("lack_of_electricity");
    }
}
