package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import fonts.FontBuilder;
import menu.MenuBuilder;
import menu.MenuClickedInterface;
import menu.MenuObject;
import solar.GameLogic;
import solar.GameState;
import solar.Logger;

public class StateMainMenu extends GameState
{

    public StateMainMenu(GameLogic GL)
    {
        super(GL);

        MenuObject mo = MenuBuilder.Build(100, 100, 2, FontBuilder.BuildDefaultFont(30), new String[]
        { "Start", "Einstellungen", "Exit" }, Color.red, Color.gray, Color.darkGray, Color.blue);

        mo.SetMenuClickedListener(new MenuItemClicked());
        Objects.add(mo);
    }

    private class MenuItemClicked implements MenuClickedInterface
    {
        @Override
        public void MenuClicked(int Index)
        {
            if (Index == 1)
                GL.ChangeState(new StateSettingsMenu(GL));
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
}
