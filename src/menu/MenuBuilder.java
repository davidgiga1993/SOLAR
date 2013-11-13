package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;

import fonts.FontBuilder;

public class MenuBuilder
{
    private Paint BorderPaint;
    private Font TextFont;
    private Paint TextPaint;
    private Paint BackgroundPaint;
    private Paint HoverPaint;
    private Paint SelectedPaint;

    private int BorderSize;

    private int PosX;
    private int PosY;
    private int ItemHeight;
    private int Width;

    private List<MenuItem> Items = new ArrayList<MenuItem>();

    public MenuBuilder(int X, int Y)
    {
        this.PosX = X;
        this.PosY = Y;
    }

    public MenuBuilder setBorderSize(int Size)
    {
        this.BorderSize = Size;
        return this;
    }

    public MenuBuilder addItem(String Text, boolean Toggelable, boolean Selected)
    {
        MenuItem Item = new MenuItem(Text, TextPaint, HoverPaint, BackgroundPaint, SelectedPaint, TextFont, Toggelable ? MenuItemModes.OnOff : MenuItemModes.Default);
        Item.Selected = Selected;
        Items.add(Item);
        PosY -= ItemHeight / 2;
        if (PosY < 0)
            PosY = 0;
        return this;
    }

    public MenuBuilder addItem(String Text, boolean Toggelable)
    {
        return addItem(Text, Toggelable, false);
    }

    public MenuBuilder setFont(Font TextFont, Paint TextPaint)
    {
        this.TextFont = TextFont;
        this.TextPaint = TextPaint;
        return this;
    }

    public MenuBuilder setBorderPaint(Paint BorderPaint)
    {
        this.BorderPaint = BorderPaint;
        return this;
    }

    public MenuBuilder setBackgroundPaint(Paint BackgroundPaint)
    {
        this.BackgroundPaint = BackgroundPaint;
        return this;
    }

    public MenuBuilder setHoverPaint(Paint HoverPaint)
    {
        this.HoverPaint = HoverPaint;
        return this;
    }

    public MenuBuilder setSelectedPaint(Paint SelectedPaint)
    {
        this.SelectedPaint = SelectedPaint;
        return this;
    }

    public MenuBuilder setItemHeight(int ItemHeight)
    {
        this.ItemHeight = ItemHeight;
        return this;
    }

    public MenuBuilder setWidth(int Width)
    {
        this.Width = Width;
        return this;
    }

    public MenuObject Build()
    {
        return new MenuObject(PosX, PosY, Width, ItemHeight, BorderSize, Items.toArray(new MenuItem[Items.size()]), BorderPaint);
    }

    public static MenuBuilder BuildDefaultMenu(int X, int Y)
    {
        return new MenuBuilder(X - 150, Y).setBackgroundPaint(new Color(60, 60, 60)).setBorderSize(0).setWidth(150).setItemHeight(50).setFont(FontBuilder.BuildDefaultFont(30), Color.white).setSelectedPaint(new Color(190, 100, 100, 255)).setHoverPaint(new Color(100, 100, 100, 100));
    }
}
