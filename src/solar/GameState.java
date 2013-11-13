package solar;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import objects.GameObject;

public abstract class GameState implements GameObjectInterface,
        SimpleInputInterface, MouseMoveInterface
{
    protected GameLogic GL;
    protected int ID; // Eigene ID -> Entspricht dem Index in der GameLogic
                      // Liste

    protected List<GameObject> Objects = new ArrayList<GameObject>();

    public GameState(GameLogic GL)
    {
        this.GL = GL;
    }

    @Override
    public void Draw(Graphics2D G)
    {
        for (int X = 0; X < Objects.size(); X++)
        {
            Objects.get(X).Draw(G);
        }
    }

    @Override
    public void Update(long Tick)
    {
        for (int X = 0; X < Objects.size(); X++)
        {
            Objects.get(X).Update(Tick);
        }
    }
}
