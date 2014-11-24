package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.g2d.Batch;

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
}
