package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.colony.ResourceDepot;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

public class LifeSupport extends CapacitiveResource {
    
    private static final long SUPPORTED_POPULATION_PER_INFRASTRUCTURE = 10 * THOUSAND;
    
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
    protected long capacityPerUnit() {
        return SUPPORTED_POPULATION_PER_INFRASTRUCTURE;
    }

    @Override
    protected long numberOfUnits(ResourceDepot productionSite) {
        return productionSite.getNumberOfWorkingLifeSupportUnits();
    }

    @Override
    protected String noCapacityMessage() {
        return "Life Support off-line!";
    }
}
