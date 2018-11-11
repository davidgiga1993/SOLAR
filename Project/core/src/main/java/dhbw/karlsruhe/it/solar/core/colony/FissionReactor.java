package dhbw.karlsruhe.it.solar.core.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.core.physics.Power;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.solar.TextureCache;

/**
 * @author Andi
 * created 2015-05-30
 */
public class FissionReactor extends PowerPlant {

    private static final Power FISSION_REACTOR_POWER_OUTPUT = new Power(10f, Power.PowerUnit.MEGAWATT);
    private final static Credits YEARLY_UPKEEP_FISSION_REACTOR = new Credits(30000000);

    public FissionReactor() {

    }

    public FissionReactor(int initialReactors) {
        this.buildingsBuilt = initialReactors;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCache.GAME_ATLAS.findRegion("reactor_fission");
    }

    @Override
    public Power getElectricPowerConsumption() {
        return new Power(0, Power.PowerUnit.WATT);
    }

    @Override
    public long getCapacityPerBuilding() {
        return (long) FISSION_REACTOR_POWER_OUTPUT.inWatt();
    }

    @Override
    protected Credits getYearlyUpKeep() {
        return YEARLY_UPKEEP_FISSION_REACTOR;
    }

}
