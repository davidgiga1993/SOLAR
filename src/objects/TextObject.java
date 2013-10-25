package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import solar.GameEngine;

public class TextObject extends GameObject
{
    private final String Label;
    private Color TextColor;
    private Font TextFont;
    private GameObjectRectangle CenterRect;
    
    private int Width;
    private int Height;
    
    public TextObject(String Label, Color TextColor, Font TextFont)
    {
        this.Label = Label;
        this.TextColor = TextColor;
        this.TextFont = TextFont;
        PosX = GameEngine.Width;
        PosY = GameEngine.Height;
    }
    
    public void CenterInRect(GameObjectRectangle Rect)
    {
        CenterRect = Rect;
    }
    
    private void CenterInRect(Graphics2D G)
    {
        FontMetrics metric = G.getFontMetrics(TextFont);
        Width = metric.stringWidth(Label);
        Height = TextFont.getSize();
        PosX = CenterRect.PosX + (CenterRect.Width / 2) - (Width / 2);
        PosY = CenterRect.PosY + (CenterRect.Height / 2) + (Height / 2);
    }
    
    @Override
    public void Draw(Graphics2D G)
    {
        G.setFont(TextFont);
        G.setColor(TextColor);
        G.drawString(Label, PosX, PosY);
        
        if(CenterRect != null || Height == 0)
        {
            CenterInRect(G);
        }
        
    }

    @Override
    public void Update(long Tick)
    {
    }

}
