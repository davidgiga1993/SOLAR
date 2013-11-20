package fonts;

import java.awt.Font;

public class FontBuilder
{
    public static Font BuildDefaultFont(int FontSize)
    {
        return new Font("Verdana", Font.PLAIN, FontSize);
    }
}
