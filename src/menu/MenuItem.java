package menu;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;

import objects.GameObject;
import objects.GameObjectRectangle;
import objects.TextObject;

public class MenuItem extends GameObject
{
    public boolean Selected;
    public boolean Hovered;
    private GameObjectRectangle DrawRect;
    private TextObject TextItem;

    private Paint HoverColor;
    private Paint BackgroundColor;
    private Paint SelectedColor;
    
    public MenuItemModes ItemMode;

    public MenuItem(String Label, Paint TextColor, Paint HoverColor, Paint BackgroundColor, Paint SelectedColor, Font TextFont, MenuItemModes ItemMode)
    {
        TextItem = new TextObject(Label, TextColor, TextFont);
        this.HoverColor = HoverColor;
        this.BackgroundColor = BackgroundColor;
        this.SelectedColor = SelectedColor;
        this.ItemMode = ItemMode;
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
            G.setPaint(SelectedColor);
        else if(Hovered)
            G.setPaint(HoverColor);
        else
            G.setPaint(BackgroundColor);

        DrawRect.Draw(G);
        TextItem.Draw(G);
    }

    @Override
    public void Update(long Tick)
    {
        // TODO Auto-generated method stub
    }

	@Override
	protected void ZoomChanged() {
		// TODO Auto-generated method stub
		
	
    }
}
