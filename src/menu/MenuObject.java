package menu;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;

import objects.GameObject;
import objects.GameObjectRectangle;
import solar.MouseMoveInterface;

public class MenuObject extends GameObject implements MouseMoveInterface
{

    private MenuClickedInterface MenuClickedListener;
    private GameObjectRectangle Container;
    private Paint BorderPaint;

    private MenuItem[] Items;

    private int Width;
    private int ItemHeight;

    public MenuObject(int PosX, int PosY, int Width, int ItemHeight, int Border, MenuItem[] Items, Paint BorderPaint)
    {
        this.PosX = PosX;
        this.PosY = PosY;
        this.BorderPaint = BorderPaint;
        this.Items = Items;
        this.Width = Width;
        this.ItemHeight = ItemHeight;

        if (Border > 0)
            Container = new GameObjectRectangle(PosX, PosY, Width + Border, Items.length * ItemHeight + Border, true, BorderPaint);

        for (int X = 0; X < Items.length; X++)
        {
            this.Items[X].setDrawRect(new GameObjectRectangle(PosX + Border / 2, PosY + ItemHeight * X + Border / 2, 280, ItemHeight, true, BorderPaint));
            this.Items[X].ClickArea = new Rectangle(PosX + Border / 2, PosY + ItemHeight * X + Border / 2, 280, ItemHeight);
        }
    }

    public int getHeight()
    {
        return ItemHeight * Items.length;
    }

    public int getWidth()
    {
        return Width;
    }

    public void SetMenuClickedListener(MenuClickedInterface I)
    {
        MenuClickedListener = I;
    }

    @Override
    public void Draw(Graphics2D G)
    {
        if (Container != null)
        {
            G.setPaint(BorderPaint);
            Container.Draw(G);
        }
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
            Items[X].Hovered = Items[X].ClickArea.contains(P);
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
                MenuItem Item = Items[X];
                if(Item.ItemMode == MenuItemModes.OnOff)
                    Item.Selected = !Item.Selected;
                
                if (MenuClickedListener != null)
                    MenuClickedListener.MenuClicked(Item, X);
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

    @Override
    public boolean MouseWheelMoved(MouseWheelEvent event)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
