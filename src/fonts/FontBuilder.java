package fonts;

import java.awt.Font;

public class FontBuilder
{
    public static Font BuildDefaultFont(int FontSize)
    {
        return new Font("Arial", Font.PLAIN, FontSize);
    }
}
