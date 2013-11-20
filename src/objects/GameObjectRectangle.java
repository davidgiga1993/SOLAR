package objects;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

import solar.EffectTypes;
import solar.LinearScaler;
import solar.ScaleEffect;

public class GameObjectRectangle extends GameObject
{
    protected int Width;
    protected int Height;
    public boolean Fill;
    
    public Paint paint;

    protected ScaleEffect[] Effect;

    public GameObjectRectangle(int PosX, int PosY, int Width, int Height)
    {
        this.PosX = PosX;
        this.PosY = PosY;
        this.Width = Width;
        this.Height = Height;
    }

    public GameObjectRectangle(int PosX, int PosY, int Width, int Height, boolean Fill, Paint paint)
    {
        this(PosX, PosY, Width, Height);
        this.Fill = Fill;
        this.paint = paint;
    }

    public void SetEffect(EffectTypes Type)
    {
        switch (Type)
        {
        case None:
            Effect = null;
            break;
        case Linear:
            Effect = new LinearScaler[4];
            for (int X = 0; X < Effect.length; X++)
            {
                Effect[X] = new LinearScaler();
            }
            break;
        }
    }

    public void SetDimension(int Width, int Height)
    {
        if (Effect != null)
        {
            if (this.Width != Width)
            {
                Effect[2].SetValues(this.Width, Width, 5);
            }
            if (this.Height != Height)
            {
                Effect[3].SetValues(this.Height, Height, 5);
            }
        }
        else
        {
            this.Width = Width;
            this.Height = Height;
        }
    }

    public void SetPosition(int PosX, int PosY)
    {
        if (Effect != null)
        {
            if (this.PosX != PosX)
            {
                Effect[0].SetValues(this.PosX, PosX, 4);
            }
            if (this.PosY != PosY)
            {
                Effect[1].SetValues(this.PosY, PosY, 4);
            }
        }
        else
        {
            this.PosX = PosX;
            this.PosY = PosY;
        }
    }
    
    public Rectangle toRectangle()
    {
        return new Rectangle(PosX, PosY, Width, Height);
    }
    
    @Override
    public void setPosX(int PosX)
    {       
        if (Effect != null)
        {
            if (this.PosX != PosX)
            {
                Effect[0].SetValues(this.PosX, PosX, 4);
            }
        }
        else
        {
            this.PosX = PosX;
        }
    }
    
    @Override
    public void setPosY(int PosY)
    {
        if (Effect != null)
        {
            if (this.PosY != PosY)
            {
                Effect[1].SetValues(this.PosY, PosY, 4);
            }
        }
        else
        {
            this.PosY = PosY;
        }
    }

    @Override
    public void Draw(Graphics2D G)
    {
        if(paint != null)
            G.setPaint(paint);
        
        if (Fill)
            G.fillRect(PosX, PosY, Width, Height);
        else
            G.drawRect(PosX, PosY, Width, Height);
    }

    @Override
    public void Update(long Tick)
    {
        if (Effect != null)
        {
            if (!Effect[0].IsFinal)
                PosX = Effect[0].Update(Tick);

            if (!Effect[1].IsFinal)
                PosY = Effect[1].Update(Tick);

            if (!Effect[2].IsFinal)
                Width = Effect[2].Update(Tick);

            if (!Effect[3].IsFinal)
                Height = Effect[3].Update(Tick);
        }
    }

	@Override
	protected void ZoomChanged() {
		// TODO Auto-generated method stub
		
	}
}
