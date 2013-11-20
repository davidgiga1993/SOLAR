package states;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;

import objects.*;
import solar.GameLogic;
import solar.GameState;

public class StateGameStart extends GameState
{

    public StateGameStart(GameLogic GL)
    {
        super(GL);
        // TODO Auto-generated constructor stub
        Objects.add(new ObjectRaumschiff1());
        Objects.add(new ObjectStern(300, 50, Color.cyan , new Point(200,200) ));
    }

    @Override
    public boolean MouseClick(Point P)
    {
        ((ObjectRaumschiff1) Objects.get(0)).MouseClick(P);
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
