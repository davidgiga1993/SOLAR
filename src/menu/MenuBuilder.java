package menu;

import java.awt.Color;
import java.awt.Font;

public class MenuBuilder
{
    public static MenuObject Build(int PosX, int PosY, int Border, Font TextFont, String[] Labels, Color BackgroundMain, Color BackgroundColor, Color HoverColor, Color TextColor)
    {
        MenuItem[] Items = new MenuItem[Labels.length];
        for(int X = 0; X< Items.length; X++)
        {
            Items[X] = new MenuItem(Labels[X], TextColor, HoverColor, BackgroundColor, TextFont);
        }
        return new MenuObject(PosX, PosY, Border, Items, BackgroundMain);
    }
}
