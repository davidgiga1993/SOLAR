package dhbw.karlsruhe.it.solar.core.astronomical_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.usercontrols.SystemRoot;

/**
 * @author Andi
 *
 */
public class SolarSystem extends AstronomicalBody {
    
    public SolarSystem(String name)   {
        super(name);
        setPosition(0, 0);
        orbitalProperties.setNewOrbitPrimary(new SystemRoot(0,0));
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {

    }

    @Override
    protected boolean previewEnabled() {
        return false;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void updateScale() {
        // nothing to do
    }

    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        super.drawLines(libGDXShapeRenderer,solarShapeRenderer);
        diplaySystemCenter(libGDXShapeRenderer);
    }

    private void diplaySystemCenter(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(getX(), getY(), 10);
    }
}
