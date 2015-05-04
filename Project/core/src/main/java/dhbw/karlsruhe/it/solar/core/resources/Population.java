package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;


/**
 * Populatio Resource: Each colony has a certain number of inhabitants. Behavior of population is governed by this class.
 * @author Andi
 * Th, 19. March 2015
 */
public class Population extends BaseResource implements ResourceInterface {

    public Population(long numberOfColonists) {
        value = numberOfColonists;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public long getMaximum() {
        return THOUSAND * TRILLION;
    }

    @Override
    public TextureRegion getIcon() {
        return TextureCacher.GAMEATLAS.findRegion("resource_placeholder");
    }

    @Override
    protected String getUnitName() {
        return "People";
    }
}
