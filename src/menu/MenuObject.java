package menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import objects.GameObject;
import objects.GameObjectRectangle;
import solar.MouseMoveInterface;

public class MenuObject extends GameObject implements MouseMoveInterface
{

    private MenuClickedInterface MenuClickedListener;
    private GameObjectRectangle Container;
    private Color BackgroundColor;
    private static int ItemHeight = 50;

    private MenuItem[] Items;

    public MenuObject(int PosX, int PosY, int Border, MenuItem[] Items, Color BackgroundColor)
    {
        this.PosX = PosX;
        this.PosY = PosY;
        this.BackgroundColor = BackgroundColor;
        this.Items = Items;
        Container = new GameObjectRectangle(PosX, PosY, 280 + Border, Items.length * ItemHeight + Border, true);
        for (int X = 0; X < Items.length; X++)
        {
            this.Items[X].setDrawRect(new GameObjectRectangle(PosX + Border / 2, PosY + ItemHeight * X + Border / 2, 280, ItemHeight, true));
            this.Items[X].ClickArea = new Rectangle(PosX + Border / 2, PosY + ItemHeight * X + Border / 2, 280, ItemHeight);
        }
    }

    public void SetMenuClickedListener(MenuClickedInterface I)
    {
        MenuClickedListener = I;
    }

    @Override
    public void Draw(Graphics2D G)
    {
        G.setColor(BackgroundColor);
        Container.Draw(G);
        for (int X = 0; X < Items.length; X++)
        {
            Items[X].Draw(G);
        }
    }

    @Override
    public void Update(long Tick)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean MouseMove(Point P)
    {
        for (int X = 0; X < Items.length; X++)
        {
            Items[X].Selected = Items[X].ClickArea.contains(P);            
        }
        return false;
    }

    @Override
    public boolean MouseDown(Point P)
    {
        for (int X = 0; X < Items.length; X++)
        {
            if (Items[X].ClickArea.contains(P))
            {
                if (MenuClickedListener != null)
                    MenuClickedListener.MenuClicked(X);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean MouseRelease(Point P)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
