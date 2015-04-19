package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.g2d.Batch;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;

/**
 * Created by Arga on 23.11.2014.
 */
public class SystemRoot extends AstronomicalBody {

    public SystemRoot(float x, float y) {
        super("SystemRoot");
        this.setPosition(x,y);
       // this.massInKilogram = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        return;
    }

    @Override
    public void updateScale() {
        // nothing to do
    }

    @Override
    public String getTypeName() {
        return null;
    }
}
