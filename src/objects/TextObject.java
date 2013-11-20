package objects;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;

import solar.GameEngine;

public class TextObject extends GameObject
{
    private final String Label;
    private Paint TextPaint;
    private Font TextFont;
    private GameObjectRectangle CenterRect;
    
    private int Width;
    private int Height;
    
    public TextObject(String Label, Paint TextPaint, Font TextFont)
    {
        this.Label = Label;
        this.TextPaint = TextPaint;
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
        CenterRect = null;
    }
    
    @Override
    public void Draw(Graphics2D G)
    {
        if(CenterRect != null || Height == 0)
        {
            CenterInRect(G);
        } 
        
        G.setFont(TextFont);
        G.setPaint(TextPaint);
        G.drawString(Label, PosX, PosY);               
    }

    @Override
    public void Update(long Tick)
    {
    }

    @Override
    protected void ZoomChanged()
    {
        // TODO Auto-generated method stub
        
    }
}
