package texture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TextureLoader
{
    public static BufferedImage LoadTexture(String Name)
    {
        try
        {
            return ImageIO.read(TextureLoader.class.getClassLoader().getResource("textures/" + Name));
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
