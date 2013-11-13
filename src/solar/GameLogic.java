package solar;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import states.StateMainMenu;

public class GameLogic implements GameObjectInterface, SimpleInputInterface,
        MouseMoveInterface
{
    public GameEngine GE;
    private List<GameState> States = new ArrayList<GameState>();
    private int StateCounter = 0;

    public GameLogic(GameEngine GE)
    {
        this.GE = GE;
        StartGame();
    }

    private void StartGame()
    {
        StateMainMenu M = new StateMainMenu(this);
        M.ID = AddState(M);
    }

    // Fügt ein neues GameObject der Liste hinzu und gibt dessen Index zurück
    public int AddState(GameState S)
    {
        States.add(S);
        StateCounter++;
        Logger.LogD("New State");
        return (StateCounter - 1);
    }

    public void RemoveState(int Index)
    {
        if (States.remove(Index) != null)
        {
            StateCounter--;
            Logger.LogD("State removed");
        }
    }

    public void RemoveState(GameState GS)
    {
        if (States.remove(GS))
        {
            StateCounter--;
            Logger.LogD("State removed");
        }
    }

    public void ChangeState(GameState NewState)
    {
        States.remove(States.size() - 1);
        States.add(NewState);
        Logger.LogD("State changed");
    }

    @Override
    public void Draw(Graphics2D G)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            States.get(X).Draw(G);
        }
    }

    @Override
    public void Update(long Tick)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            States.get(X).Update(Tick);
        }
    }

    public boolean MouseMove(Point P)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).MouseMove(P))
                break;
        }
        return true;
    }

    @Override
    public boolean MouseClick(Point P)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).MouseClick(P))
                break;
        }
        return true;
    }

    public boolean MouseDown(Point P)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).MouseDown(P))
                break;
        }
        return true;
    }

    public boolean MouseRelease(Point P)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).MouseMove(P))
                break;
        }
        return true;
    }

    @Override
    public boolean KeyDown(int KeyCode)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).KeyDown(KeyCode))
                break;
        }
        return true;
    }

    @Override
    public boolean KeyUp(int KeyCode)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).KeyUp(KeyCode))
                break;
        }
        return true;
    }

    @Override
    public boolean KeyPressed(int KeyCode, char KeyChar)
    {
        for (int X = 0; X < StateCounter; X++)
        {
            if (States.get(X).KeyPressed(KeyCode, KeyChar))
                break;
        }
        return true;
    }
}
