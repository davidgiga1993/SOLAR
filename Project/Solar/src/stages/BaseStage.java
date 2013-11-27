package stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.solar.SolarEngine;

public abstract class BaseStage extends Stage
{
    protected final SolarEngine SE;

    public BaseStage(SolarEngine SE)
    {
        super(SolarEngine.Width, SolarEngine.Height, false);       
        this.SE = SE;
        setCamera(SE.camera);
    }
}
