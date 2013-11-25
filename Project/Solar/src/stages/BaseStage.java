package stages;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.solar.SolarEngine;

public abstract class BaseStage extends Stage implements InputProcessor
{
    protected SolarEngine SE;

    public BaseStage(SolarEngine SE)
    {
        super(SolarEngine.Width, SolarEngine.Height, false);
        setCamera(SE.camera);
        this.SE = SE;

    }

    @Override
    public abstract boolean keyDown(int keycode);

    @Override
    public abstract boolean keyUp(int keycode);

    @Override
    public abstract boolean keyTyped(char character);

    @Override
    public abstract boolean touchDown(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchUp(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchDragged(int screenX, int screenY, int pointer);

    @Override
    public abstract boolean mouseMoved(int screenX, int screenY);

    @Override
    public abstract boolean scrolled(int amount);
}
