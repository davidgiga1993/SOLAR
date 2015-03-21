package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

import java.util.ArrayList;
import java.util.List;

public class StageManager extends BaseStage {
    private List<BaseStage> stages = new ArrayList<BaseStage>();

    public StageManager(SolarEngine se)   {
        super(se, "StageManager");
    }

    public void startGame()    {
        stages.add(new StartStage(SE));
    }

    /**
     * Returns a stage with the given tag
     * 
     * @param tag
     * @return null if stage is not found
     */
    public BaseStage getStage(String tag)   {
        for (int x = 0; x < stages.size(); x++)        {
            if (stages.get(x).TAG.equals(tag)) {
            	return getStage(x);            	
            }
        }
        return null;
    }

    public BaseStage getStage(int index)   {
        if (index < stages.size()) {
        	return stages.get(index);        	
        }
        return null;
    }

    /**
     * Inserts a stage to position 0
     * 
     * @param s
     */
    public void insertStageToBack(BaseStage s)   {
        stages.add(0, s);
    }

    public void swapCurrentStage(BaseStage s)   {
        stages.remove(stages.size() - 1);
        stages.add(s);
    }

    /**
     * Adds a stage to the list
     * 
     * @param s
     */
    public void addStage(BaseStage s)   {
        stages.add(s);
    }

    /**
     * Removes the stage with the given tag
     * 
     * @param tag
     */
    public Stage removeStage(String tag)   {
        for (int x = 0; x < stages.size(); x++)	{
            if (stages.get(x).TAG.equals(tag))	{
                return stages.remove(x);
            }
        }
        return null;
    }

    /**
     * Removes all stages from the list
     */
    public void removeStages()    {
        stages.clear();
    }

    @Override
    public void draw()    {
        for (int x = 0; x < stages.size(); x++)        {
            stages.get(x).act(Gdx.graphics.getDeltaTime());
            stages.get(x).draw();
        }
        super.draw();
    }

    @Override
    public boolean keyDown(int keycode)    {
        for (int x = 0; x < stages.size(); x++)       {
            if (stages.get(x).keyDown(keycode)) {
            	break;            	
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)    {
        for (int x = 0; x < stages.size(); x++)        {
            if (stages.get(x).keyUp(keycode)) {
            	break;            	
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character)    {
        for (int x = 0; x < stages.size(); x++)        {
            if (stages.get(x).keyTyped(character)) {
            	break;            	
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)   {
        for (int x = 0; x < stages.size(); x++)        {
            stages.get(x).touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)    {
        for (int x = 0; x < stages.size(); x++)        {
            stages.get(x).touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)    {
        for (int x = 0; x < stages.size(); x++)        {
            stages.get(x).touchDragged(screenX, screenY, pointer);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)    {
        for (int x = 0; x < stages.size(); x++)       {
            stages.get(x).mouseMoved(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount)    {
        for (int x = 0; x < stages.size(); x++)        {
            stages.get(x).scrolled(amount);
        }
        return false;
    }

    public void resize(int width, int height) {
        for(Stage stage : stages) {
            ((BaseStage) stage).resize(width, height);
        }
    }
}
