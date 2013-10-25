package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import objects.GameObject;
import objects.GameObjectRectangle;
import objects.TextObject;

public class MenuItem extends GameObject
{
    public boolean Selected;
    private GameObjectRectangle DrawRect;
    private TextObject TextItem;

    private Color HoverColor;
    private Color BackgroundColor;

    public MenuItem(String Label, Color TextColor, Color HoverColor, Color BackgroundColor, Font TextFont)
    {
        TextItem = new TextObject(Label, TextColor, TextFont);
        this.HoverColor = HoverColor;
        this.BackgroundColor = BackgroundColor;
    }

    public void setDrawRect(GameObjectRectangle drawRect)
    {
        DrawRect = drawRect;
        TextItem.CenterInRect(DrawRect);
    }

    @Override
    public void Draw(Graphics2D G)
    {
        if (Selected)
            G.setColor(HoverColor);
        else
            G.setColor(BackgroundColor);

        DrawRect.Draw(G);
        TextItem.Draw(G);
    }

    @Override
    public void Update(long Tick)
    {
        // TODO Auto-generated method stub
    }
}
