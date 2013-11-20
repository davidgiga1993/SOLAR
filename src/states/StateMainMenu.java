package states;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import menu.MenuBuilder;
import menu.MenuClickedInterface;
import menu.MenuItem;
import menu.MenuObject;
import solar.GameEngine;
import solar.GameLogic;
import solar.GameState;
import solar.Logger;

public class StateMainMenu extends GameState
{

    public StateMainMenu(GameLogic GL)
    {
        super(GL);
        GameState BackState = new StateStarBackground(GL);
        if (!GL.HasState(BackState))
            GL.AddStateToLast(BackState);

        MenuObject MO = MenuBuilder.BuildDefaultMenu(0, 0).addItem("Start", false).addItem("Settings", false).addItem("Exit", false).Build();

        MO.SetMenuClickedListener(new MenuItemClicked());
        Objects.add(MO);

    }

    private class MenuItemClicked implements MenuClickedInterface
    {
        @Override
        public void MenuClicked(MenuItem Item, int Index)
        {
            switch (Index)
            {
            case 0:
                GL.ChangeState(new StateGameStart(GL));
                break;
            case 1:
                GL.ChangeState(new StateSettingsMenu(GL));
                break;
            case 2:
                System.exit(0);
                break;
            }

            Logger.LogD("Menuitem clicked " + Index);
        }
    }

    @Override
    public void Draw(Graphics2D G)
    {
        super.Draw(G);
        // TODO Auto-generated method stub

    }

    @Override
    public void Update(long Tick)
    {
        super.Update(Tick);
        // TODO Auto-generated method stub

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
        if (KeyCode == KeyEvent.VK_MINUS)
        {
        }
        else if (KeyCode == KeyEvent.VK_PLUS)
        {
        }
        return false;
    }

    @Override
    public boolean KeyUp(int KeyCode)
    {
        return false;
    }

    @Override
    public boolean KeyPressed(int KeyCode, char KeyChar)
    {
        return false;
    }

    @Override
    public boolean MouseMove(Point P)
    {
        ((MenuObject) Objects.get(0)).MouseMove(P);
        return false;
    }

    @Override
    public boolean MouseDown(Point P)
    {
        ((MenuObject) Objects.get(0)).MouseDown(P);
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
