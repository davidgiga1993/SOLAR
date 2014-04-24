package com.me.stages;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.solar.SolarEngine;

public class StageManager extends BaseStage
{
    private List<BaseStage> Stages = new ArrayList<BaseStage>();

    public StageManager(SolarEngine SE)
    {
        super(SE, "StageManager");
    }

    public void StartGame()
    {
        Stages.add(new StartStage(SE));
    }

    /**
     * Returns a stage with the given tag
     * @param TAG
     * @return null if stage is not found
     */
    public Stage getStage(String TAG)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            if (Stages.get(X).TAG.equals(TAG))
                return Stages.get(X);
        }
        return null;
    }

    /**
     * Inserts a stage to position 0
     * @param S
     */
    public void insertStageToBack(BaseStage S)
    {
        Stages.add(0, S);
    }

    public void swapCurrentStage(BaseStage S)
    {
        Stages.remove(Stages.size() - 1);
        Stages.add(S);
    }

    /**
     * Adds a stage to the list
     * @param S
     */
    public void addStage(BaseStage S)
    {
        Stages.add(S);
    }

    /**
     * Removes the stage with the given tag
     * @param TAG
     */
    public void removeStage(String TAG)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            if (Stages.get(X).TAG.equals(TAG))
            {
                Stages.remove(X);
                return;
            }
        }
        return;
    }
    
    /**
     * Removes all stages from the list
     */
    public void removeStages()
    {
        Stages.clear();
    }

    @Override
    public void draw()
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).act(Gdx.graphics.getDeltaTime());
            Stages.get(X).draw();
        }
        super.draw();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            if (Stages.get(X).keyDown(keycode))
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            if (Stages.get(X).keyUp(keycode))
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            if (Stages.get(X).keyTyped(character))
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).draw();
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).mouseMoved(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        for (int X = 0; X < Stages.size(); X++)
        {
            Stages.get(X).scrolled(amount);
        }
        return false;
    }
}
