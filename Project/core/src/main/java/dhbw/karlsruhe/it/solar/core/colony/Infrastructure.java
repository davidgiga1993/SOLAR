package dhbw.karlsruhe.it.solar.core.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.core.physics.Power;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.solar.TextureCache;

public class Infrastructure extends BaseBuilding {

    private static final long SUPPORTED_POPULATION_PER_INFRASTRUCTURE = 10 * THOUSAND;
    private static final Power INFRASTRUCTURE_POWER_CONSUMPTION = new Power(1f, Power.PowerUnit.KILOWATT);
    private final static Credits YEARLY_UPKEEP_INFRASTRUCTURE = new Credits(5000000);

    public Infrastructure() {

    }

    public Infrastructure(int initialInfrastructure) {
        this.buildingsBuilt = initialInfrastructure;
    }

    @Override
    protected Credits getYearlyUpKeep() {
        return YEARLY_UPKEEP_INFRASTRUCTURE;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCache.GAME_ATLAS.findRegion("infrastructure");
    }

    @Override
    public Power getElectricPowerConsumption() {
        return new Power(INFRASTRUCTURE_POWER_CONSUMPTION.inMegawatt() * buildingsBuilt, Power.PowerUnit.MEGAWATT);
    }

    @Override
    public long getCapacityPerBuilding() {
        return SUPPORTED_POPULATION_PER_INFRASTRUCTURE;
    }
}
