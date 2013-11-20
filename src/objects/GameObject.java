package objects;

import java.awt.Rectangle;

import solar.GameObjectInterface;

public abstract class GameObject implements GameObjectInterface
{
    protected int PosX;
    protected int PosY;
    protected float Zoom;
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
    
    public void setZoom(float Zoom)
    {
        this.Zoom = Zoom;
        ZoomChanged();
    }
    
    protected abstract void ZoomChanged();
}
