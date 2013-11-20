package states;

import java.awt.Point;
import java.awt.event.MouseWheelEvent;

import solar.GameLogic;
import solar.GameState;

public class StateGameStart extends GameState
{

    public StateGameStart(GameLogic GL)
    {
        super(GL);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean MouseClick(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyDown(int KeyCode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyUp(int KeyCode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean KeyPressed(int KeyCode, char KeyChar)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseMove(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseDown(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseRelease(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean MouseWheelMoved(MouseWheelEvent event)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
