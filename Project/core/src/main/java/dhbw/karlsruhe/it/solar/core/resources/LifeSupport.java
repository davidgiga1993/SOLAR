package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.colony.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

public class LifeSupport extends CapacitiveResource {


    public LifeSupport() {

    }

    @Override
    public void updateConsumption(ResourceDepot consumptionPlace) {
        currentConsumption = consumptionPlace.getPopulation().getNumber();
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("life_support");
    }

    @Override
    public TextureRegion getAlertIcon() {
        return TextureCacher.GAMEATLAS.findRegion("lack_of_life_support");
    }

    @Override
    protected String noCapacityMessage() {
        return "Life Support off-line!";
    }

    @Override
    protected long getCurrentCapacity(ResourceDepot productionSite) {
        return productionSite.getCurrentLifeSupportCapacity();
    }
}
