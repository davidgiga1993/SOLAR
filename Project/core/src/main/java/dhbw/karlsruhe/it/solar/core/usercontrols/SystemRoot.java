package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.g2d.Batch;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.physics.Length.DistanceUnit;
import dhbw.karlsruhe.it.solar.core.physics.Mass;
import dhbw.karlsruhe.it.solar.core.physics.Mass.MassUnit;

/**
 * Created by Arga on 23.11.2014.
 */
public class SystemRoot extends AstronomicalBody {

    public SystemRoot(float x, float y) {
        super("SystemRoot", null, null,  null, null);
        this.setPosition(x,y);
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
    
    @Override
    protected void setupSolarActorSprite(String textureName) {
        return;
    }
    
    @Override
    public void setActorScale(SolarActorScale scale) {
        return;
    }
    
    @Override
    protected void changeBodyScale() {
        return;
    }
    
    @Override
    public Mass getMass() {
        return new Mass(0f,MassUnit.KILOGRAM);
    }
    
    @Override
    public Length getRadius() {
        return new Length(0f,DistanceUnit.KILOMETERS);
    }
}
