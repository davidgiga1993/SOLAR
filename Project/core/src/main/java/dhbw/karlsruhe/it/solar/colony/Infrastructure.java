package dhbw.karlsruhe.it.solar.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

public class Infrastructure extends BaseBuilding {

    @Override
    public Credits payUpKeep() {
        return YEARLY_UPKEEP_INFRASTRUCTURE;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("infrastructure");
    }
}
