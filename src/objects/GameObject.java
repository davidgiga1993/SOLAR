package objects;

import java.awt.Rectangle;

import solar.GameObjectInterface;

public abstract class GameObject implements GameObjectInterface
{
    protected int PosX;
    protected int PosY;
    public Rectangle ClickArea;

    public int getPosX()
    {
        return PosX;
    }

    public int getPosY()
    {
        return PosY;
    }
    
    public void setPosX(int PosX)
    {
        this.PosX = PosX;
    }
    public void setPosY(int PosY)
    {
        this.PosY = PosY;
    }
    
}
